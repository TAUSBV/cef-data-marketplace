package net.taus.data.marketplace.api.security.auth;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Nonnull;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;

public class SessionAuthenticationFilter extends OncePerRequestFilter {

  private final SessionService sessionService;

  public SessionAuthenticationFilter(final SessionService sessionService) {
    this.sessionService = sessionService;
  }

  @Override
  protected void doFilterInternal(@Nonnull final HttpServletRequest request,
                                  @Nonnull final HttpServletResponse response,
                                  @Nonnull final FilterChain filterChain) throws ServletException, IOException, AuthenticationException {
    if (Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
      final var cookies = Optional.ofNullable(request.getCookies());
      cookies.ifPresent(cs -> {
        final var auth = Arrays.stream(cs)
          .filter(c -> SessionService.SESSION_HOLDER_NAME.equals(c.getName()))
          .findFirst();
        auth.ifPresent(c -> {
          final var token = c.getValue();
          final var userSession = sessionService.unwrap(token);
          userSession.ifPresentOrElse(
            (s) -> {
              var authentication = new UsernamePasswordAuthenticationToken(s, null, Collections.emptyList());
              SecurityContextHolder.getContext().setAuthentication(authentication);
            },
            () -> {
              // safe case, deletes the corrupted cookie found
              final var cookie = (Cookie) c.clone();
              cookie.setValue(null);
              cookie.setMaxAge(0);
              response.addCookie(cookie);
            }
          );
        });
      });
    }
    filterChain.doFilter(request, response);
  }

}