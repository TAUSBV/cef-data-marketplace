package net.taus.data.marketplace.api.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.taus.corporate.user.client.CorporateUserService;
import net.taus.corporate.user.models.CorporateUser;
import net.taus.data.marketplace.api.IntegrationTest;
import net.taus.data.marketplace.api.security.auth.AuthenticationRequest;
import net.taus.data.marketplace.api.security.auth.JwtService;
import net.taus.data.marketplace.api.security.auth.SessionService;
import net.taus.data.marketplace.api.security.auth.provider.CorporateAuthResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.*;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import javax.validation.ConstraintViolation;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;


class SessionServiceTest extends IntegrationTest {

  @Autowired
  private final LocalValidatorFactoryBean springValidator = ValidationAutoConfiguration.defaultValidator();
  @Value("${app.security.auth.provider.login_endpoint}")
  String loginEndpoint;
  @Value("${app.security.auth.provider.authenticate_endpoint}")
  String validateEndpoint;
  @MockBean
  private RestTemplate restTemplate;
  @MockBean
  private CorporateUserService corporateUsers;
  @Autowired
  private TestRestTemplate testRestTemplate;
  @Autowired
  private JwtService jwtService;
  @Autowired
  private SessionService sessionService;

  @Test
  @DisplayName("Should validate authenticationRequest no constraint violations")
  public void getNoErrors_whenAuthenticationRequestIsValid() {

    var authenticationRequest = new AuthenticationRequest();
    authenticationRequest.setEmail("mp_test@test.net");
    authenticationRequest.setPassword("mp_test_new_useR1*");

    Set<ConstraintViolation<AuthenticationRequest>> violations = springValidator.validate(authenticationRequest);
    assertTrue(violations.isEmpty());
  }

  @ParameterizedTest
  @DisplayName("Should validate authenticationRequest with correct error messages")
  @CsvSource(value = {
    "[\"password\"]|'mp_test@test.net'|''|[\"Password is required\"]",
    "[\"email\"]|''|'mp_test_new_useR1*'|[\"Invalid Email\",\"Email is required\"]",
    "[\"email\",\"password\"]|''|''|[\"Password is required\",\"Invalid Email\",\"Email is required\"]",
    "[]|'mp_test@test.net'|'mp_test_new_useR1*'|[]"
  }, delimiter = '|')
  public void getErrors_whenAuthenticationRequestIsInvalid(String fieldNames, String email, String password, String jsonErrorMessages) throws JsonProcessingException {

    var expectedMessages = new ObjectMapper().readValue(jsonErrorMessages.replaceAll("'/", "'"), List.class);
    var expectedFieldNames = new ObjectMapper().readValue(fieldNames.replaceAll("'/", "'"), List.class);

    var authenticationRequest = new AuthenticationRequest();
    authenticationRequest.setEmail(email);
    authenticationRequest.setPassword(password);

    Errors errors = new BeanPropertyBindingResult(authenticationRequest, authenticationRequest.getClass().getName());
    springValidator.validate(authenticationRequest, errors);

    errors.getFieldErrors().forEach(
      error -> assertTrue(expectedFieldNames.contains(error.getField()))
    );

    var actualMessages = errors.getAllErrors().stream()
      .map(DefaultMessageSourceResolvable::getDefaultMessage).filter(Objects::nonNull).sorted().collect(Collectors.toList());

    Collections.sort(expectedMessages);

    Assertions.assertEquals(actualMessages, expectedMessages);
  }

  @Test
  @DisplayName("Should return BadCredentialsException when AuthenticationService.authenticate() returns 401")
  public void getCorrectException_whenAuthenticationServiceAuthenticateRequestReturnsErrorResponse() {
    var authentication = new UsernamePasswordAuthenticationToken("mp_test_email@test.net", "mp_test_new_useR1*");
    var map = Map.of(
      new BadCredentialsException("Invalid credentials"), new RestClientResponseException("Invalid credentials", HttpStatus.UNAUTHORIZED.value(), "", null, null, null),
      new LockedException("User is blocked"), new RestClientResponseException("User is blocked", HttpStatus.FORBIDDEN.value(), "", null, null, null),
      new InternalAuthenticationServiceException("Something wrong happened"), new RestClientResponseException("Something wrong happened", HttpStatus.INTERNAL_SERVER_ERROR.value(), "", null, null, null)
    );

    for (Map.Entry<AuthenticationException, RestClientResponseException> entry : map.entrySet()) {
      when(restTemplate.postForObject(
        ArgumentMatchers.eq(loginEndpoint),
        ArgumentMatchers.<HttpEntity<?>>any(),
        ArgumentMatchers.eq(CorporateAuthResponse.class)
      )).thenThrow(entry.getValue());
      try {
        sessionService.authenticate(authentication);
      }
      catch (Exception actualException) {
        assertEquals(entry.getKey().getClass(), actualException.getClass());
        assertEquals(entry.getKey().getMessage(), actualException.getMessage());
      }
    }
  }

  @ParameterizedTest
  @DisplayName("Should return proper responseStatusCode for given response status code")
  @CsvSource(value = {
    "200|new",
    /*Expired JWT (Wed 26 August 2020 10:11:51)*/"403|eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJhdXRoIiwic3ViIjoibHNwcG1AdGF1cy1sc3AubmV0IiwidWlkIjoiMTc4ODgiLCJmbmFtZSI6IkxTUCBQTSIsImlzcyI6Im1hcmtldHBsYWNlIiwiZXhwIjoxNTk4NDI1OTExLCJpYXQiOjE1OTg1MjQxMTEsInNpZCI6ImUwZDg3NGVhMWE1ZDQwODVjYTFkN2MxZDcyMmEyNmMzIn0.CAno6eRZwHXX2QneIGA_hVlPTCDIOYFm9LRTLfvk9ww",
    /*Invalid unsecured/JWS/JWE header: Invalid JSON*/"403|eyJhbGciOiJIUzI1NiJa9.eyJhdWQiOiJhdXRoIiwic3ViIjoibHNwcG1AdGF1cy1sc3AubmV0IiwidWlkIjoiMTc4ODgiLCJmbmFtZSI6IkxTUCBQTSIsImlzcyI6Im1hcmtldHBsYWNlIiwiZXhwIjoxNTk4NTA3MjE3LCJpYXQiOjE1OTg1MTU0MTcsInNpZCI6ImQ3NDA2NDhmZmEwZmQ5ZGYyMzRjZDdmNTg3MTU5OWJmIn0.YBgHOMIwRMAecLfcxGDBIBmWpM0UURphL5ALnosrPLU",
    /*Payload of JWS object is not a valid JSON object*/"403|eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJhdXRoIiwic3ViIjoibHNwcG1AdGF1cy1sc3AubmV0IiwidWlkIjoiMTc4ODgiLCJmbmFtZSI6IkxTUCBQTSIsImlzcyI6Im1hcmtldHBsYWNlIiwiZXhwIjoxNTk4NTA3MjE3LCJpYXQiOjE1OTg1MTU0MTcsInNpZCI6ImQ3NDA2NDhmZmEwZmQ5ZGYyMzRjZDdmNTg3MTU5OWJmIna0.YBgHOMIwRMAecLfcxGDBIBmWpM0UURphL5ALnosrPLU",
    /*Signed JWT rejected: Invalid signature*/"403|eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJhdXRoIiwic3ViIjoibHNwcG1AdGF1cy1sc3AubmV0IiwidWlkIjoiMTc4ODgiLCJmbmFtZSI6IkxTUCBQTSIsImlzcyI6Im1hcmtldHBsYWNlIiwiZXhwIjoxNTk4NTA3MjE3LCJpYXQiOjE1OTg1MTU0MTcsInNpZCI6ImQ3NDA2NDhmZmEwZmQ5ZGYyMzRjZDdmNTg3MTU5OWJmIn0.YBgHOMIwRMAecLfcxGDBIBmWpM0UURphL5ALnosrPLaU",
    /*Expired BouncerSession Id*/"403|newValidJwtWithInactiveBouncerSessionId",
  }, delimiter = '|')
  public void getResponseStatusCode_forGivenJwtToken(final Integer expectedResponseStatusCodeValue, String jwtToken) throws Exception {
    if (jwtToken.contains("new")) {
      var loginResponseJson = "{\"loginResponse\":{\"sessionId\":\"c9c66f933fc1a6bc1041d47dc37cada0\",\"message\":\"Sessionsuccessfullycreatedforuser'lsppm@taus-lsp.net'\",\"expires\":1800,\"user\":{\"id\":17888,\"email\":\"lsppm@taus-lsp.net\",\"fullName\":\"LSPPM\",\"dqfUser\":{\"id\":3,\"email\":\"lsppm@taus-lsp.net\",\"tausId\":17888},\"organizationId\":508,\"organization\":\"TAUSLSP\",\"dqfOrganization\":{\"id\":2,\"name\":\"TAUSLSP\",\"tausId\":508},\"spentCredits\":0,\"spentCreditsOrganization\":0,\"totalCredits\":0,\"availableCredits\":0,\"isGeneric\":false}}}";
      var authentication = new UsernamePasswordAuthenticationToken("lsppm@taus-lsp.net", "lsppm123#");

      var mockedLoginResponse = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).readValue(loginResponseJson, CorporateAuthResponse.class);
      when(restTemplate.postForObject(
        ArgumentMatchers.eq(loginEndpoint),
        ArgumentMatchers.<HttpEntity<?>>any(),
        ArgumentMatchers.eq(CorporateAuthResponse.class)
      )).thenReturn(
        mockedLoginResponse
      );

      if (jwtToken.equals("newValidJwtWithInactiveBouncerSessionId")) {
        when(restTemplate.postForEntity(
          ArgumentMatchers.eq(validateEndpoint),
          ArgumentMatchers.<HttpEntity<?>>any(),
          ArgumentMatchers.eq(String.class)
        )).thenThrow(
          new RestClientResponseException("401 Unauthorized: [no body]", HttpStatus.UNAUTHORIZED.value(), "Unauthorized", null, null, null)
        );
      }
      final CorporateUser user = new CorporateUser();
      user.setId(1L);
      when(corporateUsers.findById(anyLong())).thenReturn(Optional.of(user));
      var userSession = sessionService.authenticate(authentication);
      jwtToken = jwtService.serialize((UserSession) userSession.getPrincipal());
    }

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.add(HttpHeaders.COOKIE, SessionService.SESSION_HOLDER_NAME + "=" + jwtToken);
    HttpEntity<String> entity = new HttpEntity<>(headers);

    final var response = testRestTemplate.exchange("/sessions", HttpMethod.GET, entity, String.class);
    assertEquals(expectedResponseStatusCodeValue, response.getStatusCodeValue());
  }
}