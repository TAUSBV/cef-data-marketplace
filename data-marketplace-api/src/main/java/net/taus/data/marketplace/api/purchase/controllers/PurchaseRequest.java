package net.taus.data.marketplace.api.purchase.controllers;

import net.taus.data.marketplace.api.validation.Country;
import net.taus.data.marketplace.api.validation.VAT;
import net.taus.data.marketplace.api.validation.ValidEmail;
import net.taus.data.marketplace.api.validation.ValueOfEnum;
import net.taus.data.marketplace.model.purchase.Payment;
import net.taus.data.marketplace.model.user.UserDetail.EntityType;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

public class PurchaseRequest {

  @ValidEmail(groups = { BuyerDetails.class })
  @NotEmpty(message = "Email is required", groups = { BuyerDetails.class })
  private String email;
  @NotEmpty(message = "First name is required", groups = { BuyerDetails.class })
  @Size(min = 3, message = "First name needs to be at least 3 characters long", groups = { BuyerDetails.class })
  private String firstName;
  @NotEmpty(message = "Last name is required", groups = { BuyerDetails.class })
  @Size(min = 3, message = "Last name needs to be at least 3 characters long", groups = { BuyerDetails.class })
  private String lastName;
  @ValueOfEnum(enumClass = Country.class, groups = { BuyerDetails.class })
  @NotEmpty(message = "Country is required", groups = { BuyerDetails.class })
  private String country;

  @NotEmpty(message = "Address is required", groups = { BuyerDetails.class })
  private String address;
  @NotEmpty(message = "City is required", groups = { BuyerDetails.class })
  private String city;
  @NotEmpty(message = "Postal Code is required", groups = { BuyerDetails.class })
  private String postalCode;
  private String state;

  private String company;
  @VAT(groups = { BuyerDetails.class })
  private String vat;
  @ValueOfEnum(enumClass = EntityType.class, groups = { BuyerDetails.class })
  @NotNull(message = "Entity Type is required", groups = { BuyerDetails.class })
  private String type;

  @AssertTrue(message = "Privacy Policy consent is required", groups = { BuyerDetails.class })
  private boolean privacyPolicyConsent;
  @AssertTrue(message = "Terms of Use consent is required", groups = { BuyerDetails.class })
  private boolean termsOfUseConsent;

  @ValueOfEnum(enumClass = Payment.Method.class, groups = { PaymentMethodDetails.class })
  @NotNull(message = "Payment Method is required", groups = { PaymentMethodDetails.class })
  private String paymentMethod;

  @AssertTrue(message = "Company and VAT are required for Business Entities", groups = { BuyerDetails.class })
  public boolean isValidEntityType() {
    return (EntityType.BUSINESS.name().equals(type)
      && Objects.nonNull(this.company) && !this.company.isBlank()
      && Objects.nonNull(this.vat) && !this.vat.isBlank())
      || EntityType.PRIVATE.name().equals(this.type);
  }

  public boolean isBusiness() {
    return Objects.nonNull(this.type) && EntityType.valueOf(this.type).equals(EntityType.BUSINESS);
  }

  public boolean isPrivate() {
    return Objects.nonNull(this.type) && EntityType.valueOf(this.type).equals(EntityType.PRIVATE);
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(final String email) {
    this.email = email;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(final String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(final String lastName) {
    this.lastName = lastName;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(final String country) {
    this.country = country;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(final String address) {
    this.address = address;
  }

  public String getCity() {
    return city;
  }

  public void setCity(final String city) {
    this.city = city;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(final String postalCode) {
    this.postalCode = postalCode;
  }

  public String getState() {
    return state;
  }

  public void setState(final String state) {
    this.state = state;
  }

  public String getCompany() {
    return company;
  }

  public void setCompany(final String company) {
    this.company = company;
  }

  public String getVat() {
    return vat;
  }

  public void setVat(final String vat) {
    this.vat = vat;
  }

  public String getType() {
    return type;
  }

  public void setType(final String type) {
    this.type = type;
  }

  public boolean isTermsOfUseConsent() {
    return termsOfUseConsent;
  }

  public void setTermsOfUseConsent(final boolean termsOfUseConsent) {
    this.termsOfUseConsent = termsOfUseConsent;
  }

  public boolean isPrivacyPolicyConsent() {
    return privacyPolicyConsent;
  }

  public void setPrivacyPolicyConsent(final boolean privacyPolicyConsent) {
    this.privacyPolicyConsent = privacyPolicyConsent;
  }

  public String getPaymentMethod() {
    return paymentMethod;
  }

  public void setPaymentMethod(final String paymentMethod) {
    this.paymentMethod = paymentMethod;
  }

  public interface BuyerDetails {
  }

  public interface PaymentMethodDetails {
  }

}
