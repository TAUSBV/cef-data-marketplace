package net.taus.data.marketplace.model.email;

import lombok.Data;

import java.util.*;

@Data
public class Email {

  public final static String SENDER = "datamarketplace@taus.net";
  public final static String NO_REPLY = "no-reply@taus.net";

  private String sender;
  private List<String> recipients;
  private String subject;
  private String replyTo;
  private Map<String, Object> templateData;
  private Template template;

  public static class Builder {
    private String sender;
    private final List<String> recipients;
    private String subject;
    private String replyTo;
    private Map<String, Object> templateData;
    private Template template;

    public Builder() {
      this.sender = SENDER;
      this.recipients = new ArrayList<>();
      this.subject = "";
      this.replyTo = NO_REPLY;
      this.templateData = new HashMap<>();
      this.template = null;
    }

    public Builder sender(String sender) {
      this.sender = sender;
      return this;
    }

    public Builder addRecipient(String recipient) {
      this.recipients.add(recipient);
      return this;
    }

    public Builder addRecipients(List<String> recipients) {
      this.recipients.addAll(recipients);
      return this;
    }

    public Builder subject(String subject) {
      this.subject = subject;
      return this;
    }

    public Builder replyTo(String replyTo) {
      this.replyTo = replyTo;
      return this;
    }

    public Builder templateData(Map<String, Object> templateData) {
      this.templateData = templateData;
      return this;
    }

    public Builder template(Template template) {
      this.template = template;
      return this;
    }

    public Email build() {
      return new Email(this);
    }
  }

  private Email(Builder builder) {
    sender = builder.sender;
    recipients = builder.recipients;
    subject = builder.subject;
    replyTo = builder.replyTo;
    templateData = builder.templateData;
    template = builder.template;
  }

  public void addRecipient(String recipient) {
    this.recipients.add(recipient);
  }

  public void addRecipients(List<String> recipients) {
    this.recipients.addAll(recipients);
  }

  public enum Template {
    PUBLICATION_ANALYSIS_SUCCESSFUL("publication/analysis-successful-email"),
    PUBLICATION_ANALYSIS_FAIL("publication/analysis-fail-email"),
    PURCHASE_RECEIVED("purchase/received-email"),
    PURCHASE_AWAITING_PAYMENT("purchase/awaiting-payment-email"),
    PURCHASE_DOWNLOAD("purchase/download-email"),
    PURCHASE_COMPLETED("purchase/completed-email"),
    PURCHASE_INVOICE_ISSUE_REQUEST_ERROR_ADMIN("purchase/purchase-invoice-failed-email"),
    PURCHASE_INVOICE_ISSUE_REQUEST_WARNING_ADMIN("purchase/purchase-invoice-warning-email"),
    PURCHASE_ITEM_REVIEW_MODERATION("purchase/purchase-item-review-moderation-email");

    private final String name;

    Template(String name) {
      this.name = name;
    }

    public String getName() {
      return this.name;
    }

    public static Optional<Template> get(final String name) {
      Template template;
      try {
        template = valueOf(name);
      }
      catch (IllegalArgumentException e) {
        template = null;
      }
      return Optional.ofNullable(template);
    }
  }

}