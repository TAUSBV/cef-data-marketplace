package net.taus.data.marketplace.model.languagedata;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Value;
import net.taus.data.language.api.model.ContentType;
import net.taus.data.language.api.model.Domain;
import net.taus.data.language.api.model.TranslationUnit;
import net.taus.data.marketplace.model.user.User;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Value
public class TranslationUnitPublishedSummary {

  @Getter(value = AccessLevel.PRIVATE)
  TranslationUnit unit;
  @Getter(value = AccessLevel.PRIVATE)
  User seller;

  public TranslationUnitPublishedSummary(final TranslationUnit unit, final User seller) {
    this.unit = unit;
    this.seller = seller;
  }

  public UUID getDocumentUid() {
    return this.getUnit().getDocumentUid();
  }

  public String getSourceValue() {
    return this.getUnit().getSource().getValue();
  }

  public long getSourceWordCount() {
    return this.getUnit().getSource().getWordCount();
  }

  public String getTargetValue() {
    return this.getUnit().getTarget().getValue();
  }

  public long getTargetWordCount() {
    return this.getUnit().getTarget().getWordCount();
  }

  public String getDomain() {
    return Optional.ofNullable(this.getUnit().getDomain()).map(Domain::getDescription).orElse(null);
  }

  public String getContentType() {
    return Optional.ofNullable(this.getUnit().getContentType()).map(ContentType::getDescription).orElse(null);
  }

  public UUID getSellerUid() {
    return Optional.ofNullable(this.getSeller()).map(User::getUid).orElse(null);
  }

  public String getSellerFirstName() {
    return Optional.ofNullable(this.getSeller()).map(User::getFirstName).orElse(null);
  }

  public String getSellerLastName() {
    return Optional.ofNullable(this.getSeller()).map(User::getLastName).orElse(null);
  }

  public LocalDateTime getSellerJoinedAt() {
    return Optional.ofNullable(this.getSeller()).map(User::getCreatedAt).orElse(null);
  }

}
