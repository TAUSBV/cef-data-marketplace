package net.taus.data.marketplace.api.security;

import com.nimbusds.jose.JOSEException;
import net.taus.data.marketplace.api.base.BaseController;
import net.taus.data.marketplace.api.security.auth.AuthenticationRequest;
import net.taus.data.marketplace.api.security.auth.JwtService;
import net.taus.data.marketplace.api.security.auth.SessionService;
import net.taus.data.marketplace.model.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;
import java.util.UUID;

import static net.taus.data.marketplace.api.security.auth.SessionService.SESSION_HOLDER_NAME;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMethodName;

@RestController
@RequestMapping("/sessions")
public class SessionController extends BaseController {

  private final SessionService sessionService;
  private final JwtService jwtService;
  private final Converter<UUID, String> uuidStringConverter;
  private final String authHolderDomain;
  private final boolean authHolderSecure;

  @Autowired
  public SessionController(final SessionService sessionService,
                           final JwtService jwtService,
                           final Converter<UUID, String> uuidStringConverter,
                           @Value("${app.security.auth.holder.domain:taus.net}") final String authHolderDomain,
                           @Value("${app.security.auth.holder.secure:true}") final boolean authHolderSecure) {
    this.sessionService = sessionService;
    this.jwtService = jwtService;
    this.uuidStringConverter = uuidStringConverter;
    this.authHolderDomain = authHolderDomain;
    this.authHolderSecure = authHolderSecure;
  }

  @PostMapping(value = { "", "/" })
  public ResponseEntity<Map<String, Object>> create(@RequestBody @Valid final AuthenticationRequest request, final HttpServletResponse response) {
    ResponseEntity<Map<String, Object>> responseEntity;
    try {
      final var userSession = sessionService.create(request);
      final var token = jwtService.serialize(userSession);
      final var lifeTime = userSession.getLifeTime();
      placeCookie(token, lifeTime, response);

      final var uid = uuidStringConverter.convert(userSession.getUserUid());
      final var uri = fromMethodName(SessionController.class, "get", uid, response).build().toUri();
      final Map<String, Object> map = Map.of("uid", uid, "requestTermsAgreement", userSession.isRequestTermsAgreement());
      responseEntity = ResponseEntity.created(uri).body(map);
    }
    catch (BadCredentialsException e) {
      responseEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", e.getMessage()));
    }
    catch (LockedException e) {
      responseEntity = ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("error", e.getMessage()));
    }
    catch (InternalAuthenticationServiceException | JOSEException e) {
      responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", SystemException.GENERIC_MESSAGE));
    }
    return responseEntity;
  }

  @GetMapping(value = { "", "/" })
  public ResponseEntity<Map<String, Object>> get(@AuthenticationPrincipal final UserSession userSession, final HttpServletResponse response) throws JOSEException {
    final var token = jwtService.serialize(userSession);
    final var lifeTime = userSession.getLifeTime();
    placeCookie(token, lifeTime, response);
    final var uid = uuidStringConverter.convert(userSession.getUserUid());
    final Map<String, Object> map = Map.of("uid", uid, "requestTermsAgreement", userSession.isRequestTermsAgreement());
    return ResponseEntity.ok(map);
  }

  @DeleteMapping(value = { "", "/" })
  public ResponseEntity<?> remove(@AuthenticationPrincipal final UserSession userSession, final HttpServletResponse response) {
    sessionService.destroy(userSession);
    placeCookie("", 0, response);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  private void placeCookie(final String token, final long lifeTime, final HttpServletResponse response) {
    final ResponseCookie responseCookie = ResponseCookie
      .from(SESSION_HOLDER_NAME, token)
      .secure(authHolderSecure)
      .httpOnly(true)
      .domain(authHolderDomain)
      .path("/")
      .maxAge((int) (lifeTime / 1000))
      .sameSite("Lax")
      .build();
    response.addHeader(HttpHeaders.SET_COOKIE, responseCookie.toString());
  }
}
