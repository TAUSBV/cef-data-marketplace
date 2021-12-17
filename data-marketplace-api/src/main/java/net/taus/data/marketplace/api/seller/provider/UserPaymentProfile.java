package net.taus.data.marketplace.api.seller.provider;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Optional;


public class UserPaymentProfile extends ObjectNode {

  public UserPaymentProfile() {
    super(JsonNodeFactory.instance);
  }

  public UserPaymentProfile(final ObjectNode defaults) {
    super(JsonNodeFactory.instance);
    this.setAll(defaults);
  }

  public UserPaymentProfile(final String method, ObjectNode defaults) {
    super(JsonNodeFactory.instance);
    this.put("method", method);
    this.setAll(defaults);
  }


  public boolean isConsentWithPaymentTerms() {
    return Optional.ofNullable(this.get("paymentTermsConsent"))
      .filter(JsonNode::isBoolean)
      .map(JsonNode::asBoolean)
      .orElse(false);
  }

  public void setPaymentTermsConsent(boolean value) {
    this.put("paymentTermsConsent", value);
  }

  public boolean isValidForPayment() {
    return Optional.ofNullable(this.get("isValidForPayment"))
      .filter(JsonNode::isBoolean)
      .map(JsonNode::asBoolean)
      .orElse(false);
  }


  public boolean isValidForSale() {
    return Optional.ofNullable(this.get("isValidForSale"))
      .filter(JsonNode::isBoolean)
      .map(JsonNode::asBoolean)
      .orElse(false);
  }


  public String getStateValue() {
    return Optional.ofNullable(this.get("stateValue"))
      .filter(JsonNode::isTextual)
      .map(JsonNode::asText)
      .orElse(null);
  }

  public String getStripeId() {
    return Optional.ofNullable(this.get("stripeId"))
      .filter(JsonNode::isTextual)
      .map(JsonNode::asText)
      .orElse(null);
  }

  public boolean isStripeChargesEnabled() {
    return Optional.ofNullable(this.get("stripeChargesEnabled"))
      .filter(JsonNode::isBoolean)
      .map(JsonNode::asBoolean)
      .orElse(false);
  }

  public String getMethod() {
    return Optional.ofNullable(this.get("method"))
      .filter(JsonNode::isTextual)
      .map(JsonNode::asText)
      .map(String::toLowerCase)
      .orElse(null);
  }

  public boolean isStripe() {
    return getMethod().equalsIgnoreCase("stripe");
  }

  public boolean isPaymentDataValid() {
    boolean valid = true;
    if (isStripe()) {
      valid = this.has("stripeId") && this.has("stripeChargesEnabled");
    }
    return valid;
  }

  public JsonNode getStripeData() {
    return JsonNodeFactory.instance.objectNode()
      .put("code", this.getStripeId())
      .put("chargesEnabled", this.isStripeChargesEnabled());
  }
}
