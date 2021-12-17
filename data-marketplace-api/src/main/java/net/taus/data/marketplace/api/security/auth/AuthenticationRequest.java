package net.taus.data.marketplace.api.security.auth;

import net.taus.data.marketplace.api.validation.ValidEmail;

import javax.validation.constraints.NotEmpty;

public class AuthenticationRequest {

  @ValidEmail
  @NotEmpty(message = "Email is required")
  private String email;

  @NotEmpty(message = "Password is required")
  private String password;

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) { this.email = email;  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) { this.password = password; }

  @Override
  public String toString() {
    final StringBuilder builder = new StringBuilder();
    builder.append("AuthenticationRequest [email=")
            .append(email)
            .append("]");
    return builder.toString();
  }
}