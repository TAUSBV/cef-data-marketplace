package net.taus.data.marketplace.api.languagedata;

import net.taus.data.language.api.model.search.TranslationUnitSample;
import net.taus.data.marketplace.model.user.User;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class TranslationUnitSampleLocal {
  private final TranslationUnitSample unit;
  private final User seller;

  public TranslationUnitSampleLocal(final TranslationUnitSample unit, final User seller) {
    this.unit = unit;
    this.seller = seller;
  }

  public String getSourceValue() {
    return this.unit.getSourceValue();
  }
  public long getSourceWordCount() {
    return this.unit.getSourceWordCount();
  }
  public String getTargetValue() {
    return this.unit.getTargetValue();
  }
  public long getTargetWordCount() {
    return this.unit.getTargetWordCount();
  }
  public UUID getDocumentUid() {
    return this.unit.getDocumentUid();
  }
  public Long getOwnerId() {
    return this.unit.getOwnerId();
  }
  public String getDomain() {
    return this.unit.getDomain();
  }
  public String getContentType() {
    return this.unit.getContentType();
  }

  public UUID getSellerUid() {
    return Optional.ofNullable(this.seller).map(User::getUid).orElse(null);
  }

  public String getSellerFirstName() {
    return Optional.ofNullable(this.seller).map(User::getFirstName).orElse(null);
  }

  public String getSellerLastName() {
    return Optional.ofNullable(this.seller).map(User::getLastName).orElse(null);
  }

  public LocalDateTime getSellerJoinedAt() {
    return Optional.ofNullable(this.seller).map(User::getCreatedAt).orElse(null);
  }

}
