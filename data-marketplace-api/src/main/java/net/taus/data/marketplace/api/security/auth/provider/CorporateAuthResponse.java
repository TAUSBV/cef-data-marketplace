package net.taus.data.marketplace.api.security.auth.provider;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class CorporateAuthResponse {

  private LoginResponse loginResponse;

  public LoginResponse.User getUser() {
    return Objects.nonNull(loginResponse) ? loginResponse.getUser() : null;
  }

  public String getSessionId() {
    return Objects.nonNull(loginResponse) ? loginResponse.getSessionId() : null;
  }

  @Getter
  @Setter
  public static class LoginResponse {
    private String sessionId;
    private String message;
    private long expires;
    private User user;

    @Setter
    @Getter
    public static class User {
      long id;
    }

  }
}
