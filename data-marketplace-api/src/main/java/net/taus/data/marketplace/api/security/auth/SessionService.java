package net.taus.data.marketplace.api.security.auth;

import net.taus.data.marketplace.api.security.UserSession;
import net.taus.data.marketplace.api.security.auth.provider.CorporateAuthenticationService;
import net.taus.data.marketplace.api.user.UserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;


public class SessionService implements AuthenticationProvider {

  public final static String CORPORATE_ID = "cid";
  public final static String CORPORATE_SESSION_ID = "csid";
  public final static String SESSION_HOLDER_NAME = "shid";

  private final Long sessionLifeTime;

  private final UserService userService;
  private final CorporateAuthenticationService corporateAuthenticationService;
  private final JwtService jwtService;

  public SessionService(final Long sessionLifetime,
                        final UserService userService,
                        final CorporateAuthenticationService corporateAuthenticationService,
                        final JwtService jwtService) {
    this.jwtService = jwtService;
    this.sessionLifeTime = sessionLifetime;
    this.userService = userService;
    this.corporateAuthenticationService = corporateAuthenticationService;
  }

  public UserSession create(final AuthenticationRequest request) throws AuthenticationException {
    final var principal = request.getEmail();
    final var credentials = request.getPassword();
    return this.create(principal, credentials);
  }

  @Override
  public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
    final var principal = authentication.getPrincipal().toString();
    final var credentials = authentication.getCredentials().toString();
    final var session = this.create(principal, credentials);
    final var token = new UsernamePasswordAuthenticationToken(session, authentication.getCredentials(), authentication.getAuthorities());
    SecurityContextHolder.getContext().setAuthentication(token);
    return token;
  }

  public UserSession create(final String principal, final String credentials) throws AuthenticationException {
    var session = corporateAuthenticationService.authenticate(principal, credentials);
    final var corporateId = session.getCorporateId();
    return userService.findByCorporateId(corporateId).map(u -> {
      session.setUserUid(u.getUid());
      session.setRequestTermsAgreement(u.isRequestTermsAgreement());
      session.setLifeTime(sessionLifeTime);
      return session;
    }).orElseThrow(() -> new UsernameNotFoundException("User not found."));
  }

  public Optional<UserSession> unwrap(final String token) {
    Optional<UserSession> session = this.jwtService.unwrap(token);
    return session.filter(s -> this.corporateAuthenticationService.isValid(s.getCorporateSessionId())).map(s -> s.setLifeTime(sessionLifeTime));
  }

  public void destroy(final UserSession userSession) {
    this.corporateAuthenticationService.logout(userSession.getCorporateId());
    SecurityContextHolder.getContext().setAuthentication(null);
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }

}
