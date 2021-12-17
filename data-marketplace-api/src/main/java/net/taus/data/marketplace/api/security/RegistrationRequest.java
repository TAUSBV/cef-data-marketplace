package net.taus.data.marketplace.api.security;

import net.taus.corporate.user.models.CorporateUser;
import net.taus.data.marketplace.api.validation.*;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Map;

@PasswordMatches
public class RegistrationRequest {

  private final CorporateUser corporateUser;

  @AssertTrue(message = "Payment Term consent is required", groups = PaymentConstraints.class)
  private boolean paymentTermConsent;

  @AssertTrue(message = "Terms of Use consent is required")
  private boolean termsOfUseConsent;

  @AssertTrue(message = "Privacy Policy consent is required")
  private boolean privacyPolicyConsent;

  public RegistrationRequest() {
    this.corporateUser = new CorporateUser();
  }

  public RegistrationRequest(@NotEmpty(message = "Email is required") final String email,
                             @NotEmpty(message = "First name is required")
                             @Size(min = 3, message = "First name needs to be at least 3 characters long") final String firstName,
                             @NotEmpty(message = "Last name is required")
                             @Size(min = 3, message = "Last name needs to be at least 3 characters long") final String lastName,
                             @NotEmpty(message = "Password is required") final String password,
                             @NotEmpty(message = "Matching Password is required") final String matchingPassword,
                             @ValueOfEnum(enumClass = PrefixType.class)
                             @NotEmpty(message = "Prefix is required") final String prefix,
                             @ValueOfEnum(enumClass = Country.class)
                             @NotEmpty(message = "Country is required") final String country) {
    this.corporateUser = new CorporateUser(email, prefix, firstName, lastName, "", "", country, password, matchingPassword, Map.of());
  }

  @ValidEmail
  @NotEmpty(message = "Email is required")
  public String getEmail() {
    return this.getCorporateUser().getEmail();
  }

  public void setEmail(final String email) {
    this.getCorporateUser().setEmail(email);
  }

  @NotEmpty(message = "First name is required")
  @Size(min = 3, message = "First name needs to be at least 3 characters long")
  public String getFirstName() {
    return this.getCorporateUser().getFirstName();
  }

  public void setFirstName(final String firstName) {
    this.getCorporateUser().setFirstName(firstName);
  }

  @NotEmpty(message = "Last name is required")
  @Size(min = 3, message = "Last name needs to be at least 3 characters long")
  public String getLastName() {
    return this.getCorporateUser().getLastName();
  }

  public void setLastName(final String lastName) {
    this.getCorporateUser().setLastName(lastName);
  }

  @ValidPassword
  @NotEmpty(message = "Password is required")
  public String getPassword() {
    return this.getCorporateUser().getPassword();
  }

  public void setPassword(final String password) {
    this.getCorporateUser().setPassword(password);
  }

  @NotEmpty(message = "Matching Password is required")
  public String getMatchingPassword() {
    return this.getCorporateUser().getMatchingPassword();
  }

  public void setMatchingPassword(final String matchingPassword) {
    this.getCorporateUser().setMatchingPassword(matchingPassword);
  }

  @ValueOfEnum(enumClass = PrefixType.class)
  @NotEmpty(message = "Prefix is required")
  public String getPrefix() {
    return this.getCorporateUser().getPrefix();
  }

  public void setPrefix(String prefix) {
    this.getCorporateUser().setPrefix(prefix);
  }

  @ValueOfEnum(enumClass = Country.class)
  @NotEmpty(message = "Country is required")
  public String getCountry() {
    return this.getCorporateUser().getCountry();
  }

  public void setCountry(String country) {
    this.getCorporateUser().setCountry(country);
  }

  public boolean isPaymentTermConsent() {
    return paymentTermConsent;
  }

  public void setPaymentTermConsent(final boolean paymentTermConsent) {
    this.paymentTermConsent = paymentTermConsent;
  }

  public boolean isPrivacyPolicyConsent() {
    return privacyPolicyConsent;
  }

  public void setPrivacyPolicyConsent(final boolean privacyPolicyConsent) {
    this.privacyPolicyConsent = privacyPolicyConsent;
  }

  public boolean isTermsOfUseConsent() {
    return termsOfUseConsent;
  }

  public void setTermsOfUseConsent(final boolean termsOfUseConsent) {
    this.termsOfUseConsent = termsOfUseConsent;
  }

  public CorporateUser getCorporateUser() {
    return this.corporateUser;
  }

  public interface PaymentConstraints {
  }
}