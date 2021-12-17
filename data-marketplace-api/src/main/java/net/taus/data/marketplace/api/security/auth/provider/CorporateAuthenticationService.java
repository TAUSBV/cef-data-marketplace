package net.taus.data.marketplace.api.security.auth.provider;

import net.taus.data.marketplace.api.security.UserSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Objects;

import static java.lang.String.format;

public class CorporateAuthenticationService {

  private final Logger logger = LoggerFactory.getLogger(CorporateAuthenticationService.class.getName());
  private final String loginEndpoint;
  private final String logoutEndpoint;
  private final String validateEndpoint;
  private final String apiKey;
  private final String cryptoKey;
  private final RestTemplate restTemplate;

  public CorporateAuthenticationService(
    final RestTemplate restTemplate,
    final String loginEndpoint,
    final String logoutEndpoint,
    final String validateEndpoint,
    final String apiKey,
    final String cryptoKey
  ) {
    this.restTemplate = restTemplate;
    this.loginEndpoint = loginEndpoint;
    this.logoutEndpoint = logoutEndpoint;
    this.validateEndpoint = validateEndpoint;
    this.apiKey = apiKey;
    this.cryptoKey = cryptoKey;
  }

  public UserSession authenticate(final String principal, final String credentials) throws BadCredentialsException, LockedException {
    var headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    var bodyParams = new HashMap<String, String>();
    bodyParams.put("apiKey", apiKey);
    UserSession session;
    try {
      bodyParams.put("emailEncrypted", Crypto.encrypt(principal, cryptoKey));
      bodyParams.put("passwordEncrypted", Crypto.encrypt(credentials, cryptoKey));
      var request = new HttpEntity<>(bodyParams, headers);
      var authenticationProviderResponse = restTemplate.postForObject(loginEndpoint, request, CorporateAuthResponse.class);
      if (Objects.isNull(authenticationProviderResponse)) throw new Exception("Provider sent null response");
      final var userInfo = authenticationProviderResponse.getUser();
      var corporateId = userInfo.getId();
      var sessionId = authenticationProviderResponse.getSessionId();
      session = new UserSession(corporateId, sessionId);
    }
    catch (RestClientResponseException e) { // TODO handle bouncer exceptions internally (especially 5xx errors)
      logger.error(format("Fail to authenticate principal %s against authentication provider %s.", principal, loginEndpoint), e);
      switch (e.getRawStatusCode()) {
        case 401 -> throw new BadCredentialsException("Invalid credentials");
        case 403 -> throw new LockedException("User is blocked");
        default -> throw new InternalAuthenticationServiceException("Something wrong happened");
      }
    }
    catch (Exception e) {
      logger.error(format("Fail to authenticate principal %s against authentication provider %s.", principal, loginEndpoint), e);
      throw new InternalAuthenticationServiceException("Something wrong happened", e);
    }
    return session;
  }

  public boolean isValid(final String sessionId) {
    var isValid = true;
    var headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    var bodyParams = new HashMap<String, Object>();
    bodyParams.put("apiKey", apiKey);
    bodyParams.put("sessionId", sessionId);
    var entity = new HttpEntity<>(bodyParams, headers);
    try {
      restTemplate.postForEntity(validateEndpoint, entity, String.class);
    }
    catch (HttpStatusCodeException ex) {
      logger.warn(String.format("Invalid session %s for provider %s.", sessionId, logoutEndpoint));
      isValid = false;
    }
    return isValid;
  }

  public void logout(long userId) {
    var headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    var bodyParams = new HashMap<String, Object>();
    bodyParams.put("apiKey", apiKey);
    bodyParams.put("userId", userId);
    var entity = new HttpEntity<>(bodyParams, headers);
    try {
      var restTemplate = new RestTemplateBuilder().errorHandler(new CorporateAuthenticationResponseErrorHandler()).build();
      restTemplate.postForEntity(logoutEndpoint, entity, String.class);
    }
    catch (HttpStatusCodeException ex) {
      logger.warn(String.format("Fail to logout principal %s from provider %s. Session was made invalid.", userId, logoutEndpoint));
    }
  }

}