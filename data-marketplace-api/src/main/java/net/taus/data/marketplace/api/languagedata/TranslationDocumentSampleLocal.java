package net.taus.data.marketplace.api.languagedata;

import lombok.AccessLevel;
import lombok.Getter;
import net.taus.data.language.api.model.Language;
import net.taus.data.language.api.model.search.TranslationDocumentSample;
import net.taus.data.marketplace.model.languagedata.TranslationDocumentReviewStatistics;
import net.taus.data.marketplace.model.user.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class TranslationDocumentSampleLocal {

  @Getter(value = AccessLevel.PRIVATE)
  private final TranslationDocumentSample document;
  @Getter(value = AccessLevel.PRIVATE)
  private final User seller;
  private final TranslationDocumentReviewStatistics reviewStatistics;

  public TranslationDocumentSampleLocal(final TranslationDocumentSample document,
                                        final User seller,
                                        final TranslationDocumentReviewStatistics reviewStatistics) {
    this.document = document;
    this.seller = seller;
    this.reviewStatistics = reviewStatistics;
  }

  public UUID getUid() {
    return this.document.getUid();
  }

  public String getName() {
    return this.document.getName();
  }

  public Language getSourceLanguage() {
    return this.document.getSourceLanguage();
  }

  public Language getTargetLanguage() {
    return this.document.getTargetLanguage();
  }

  public long getUnitCount() {
    return this.document.getUnitCount();
  }

  public long getSourceWordCount() {
    return this.document.getSourceWordCount();
  }

  public long getTargetWordCount() {
    return this.document.getTargetWordCount();
  }

  public double getPricePerWord() {
    return this.document.getPricePerWord();
  }

  public double getPrice() {
    return this.document.getPrice();
  }

  public String getDomain() {
    return this.document.getDomain();
  }

  public String getContentType() {
    return this.document.getContentType();
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

  public List<TranslationUnitSampleLocal> getUnits() {
    return document.getUnits().stream().map(unit -> new TranslationUnitSampleLocal(unit, seller)).collect(Collectors.toList());
  }

  public TranslationDocumentReviewStatistics getReviewStatistics() {
    return reviewStatistics;
  }
}
