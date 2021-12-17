package net.taus.data.marketplace.api.security.auth;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.BadJOSEException;
import com.nimbusds.jose.proc.JWSKeySelector;
import com.nimbusds.jose.proc.JWSVerificationKeySelector;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.nimbusds.jwt.proc.DefaultJWTClaimsVerifier;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;
import net.taus.data.marketplace.api.security.UserSession;
import net.taus.data.marketplace.model.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.time.Instant;
import java.util.*;

import static net.taus.data.marketplace.api.security.auth.SessionService.CORPORATE_ID;
import static net.taus.data.marketplace.api.security.auth.SessionService.CORPORATE_SESSION_ID;

public class JwtService {

  private final Logger logger = LoggerFactory.getLogger(JwtService.class.getName());
  private final String marketplaceIss;
  private final String authAudience;
  private final String secretKeyBase64;
  private final Set<String> requiredClaims;
  private final Converter<UUID, String> uuidSerializer;
  private final Converter<String, UUID> uuidDeserializer;

  public JwtService(final String secretKeyBase64,
                    final Converter<UUID, String> uuidSerializer,
                    final Converter<String, UUID> uuidDeserializer) {
    this.secretKeyBase64 = secretKeyBase64;
    this.uuidSerializer = uuidSerializer;
    this.uuidDeserializer = uuidDeserializer;
    marketplaceIss = "marketplace";
    authAudience = "auth";
    requiredClaims = Set.of("sub", "exp", "iat", CORPORATE_ID, CORPORATE_SESSION_ID);
  }

  public String serialize(final UserSession session) throws JOSEException {
    Map<String, Object> claimMap = new HashMap<>();
    claimMap.put(CORPORATE_SESSION_ID, session.getCorporateSessionId());
    claimMap.put(CORPORATE_ID, session.getCorporateId());
    String subject = uuidSerializer.convert(session.getUserUid());
    return this.issue(subject, session.getLifeTime(), claimMap);
  }

  public String issue(final String subject, final long expirationTime, final Map<String, Object> claims) throws JOSEException {
    var lifetimeDate = new Date(System.currentTimeMillis() + expirationTime);
    var secretKey = Base64.getDecoder().decode(secretKeyBase64);

    JWTClaimsSet.Builder builder = new JWTClaimsSet.Builder();

    builder
      .issuer(marketplaceIss)
      .issueTime(new Date())
      .expirationTime(lifetimeDate)
      .audience(authAudience)
      .subject(subject);

    claims.forEach(builder::claim);

    JWTClaimsSet claimsSet = builder.build();

    JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);

    SignedJWT signedJWT = new SignedJWT(header, claimsSet);
    signedJWT.sign(new MACSigner(secretKey));

    return signedJWT.serialize();
  }

  public Optional<UserSession> unwrap(final String token) {
    Optional<UserSession> session;
    try {
      ConfigurableJWTProcessor<SecurityContext> jwtProcessor = new DefaultJWTProcessor<>();
      jwtProcessor.setJWTClaimsSetVerifier(
        new DefaultJWTClaimsVerifier<>(
          authAudience,
          new JWTClaimsSet.Builder().issuer(marketplaceIss).build(),
          requiredClaims
        )
      );
      JWKSource<SecurityContext> jweKeySource = new ImmutableSecret<>(Base64.getDecoder().decode(secretKeyBase64));
      JWSKeySelector<SecurityContext> jwsKeySelector = new JWSVerificationKeySelector<>(JWSAlgorithm.HS256, jweKeySource);

      jwtProcessor.setJWSKeySelector(jwsKeySelector);

      JWTClaimsSet claims = jwtProcessor.process(token, null);
      if (Objects.isNull(claims.getAudience()) || !claims.getAudience().contains(authAudience)) {
        throw new BusinessException("Invalid credentials");
      }
      if (claims.getExpirationTime().toInstant().isBefore(Instant.now())) {
        throw new BusinessException("Credentials expired");
      }
      var corporateSessionId = claims.getStringClaim(SessionService.CORPORATE_SESSION_ID);
      var userId = uuidDeserializer.convert(claims.getSubject());
      var corporateId = claims.getLongClaim(SessionService.CORPORATE_ID);
      session = Optional.of(new UserSession(userId, corporateId, corporateSessionId));
    }
    catch (BusinessException | ParseException | JOSEException | BadJOSEException e) {
      logger.warn(String.format("Invalid token %s.", token), e);
      session = Optional.empty();
    }
    return session;
  }

}
