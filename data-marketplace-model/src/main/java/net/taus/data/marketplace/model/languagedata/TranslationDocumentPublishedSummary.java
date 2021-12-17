package net.taus.data.marketplace.model.languagedata;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Value;
import net.taus.data.language.api.model.Language;
import net.taus.data.language.api.model.TranslationDocument;
import net.taus.data.marketplace.model.user.SlugId;
import net.taus.data.marketplace.model.user.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Value
public class TranslationDocumentPublishedSummary {

  @Getter(value = AccessLevel.PRIVATE)
  TranslationDocument document;
  @Getter(value = AccessLevel.PRIVATE)
  User seller;
  TranslationDocumentReviewStatistics reviewStatistics;

  public TranslationDocumentPublishedSummary(final TranslationDocument document,
                                             final User seller,
                                             final TranslationDocumentReviewStatistics reviewStatistics) {
    this.document = document;
    this.seller = seller;
    this.reviewStatistics = reviewStatistics;
  }

  public UUID getUid() {
    return document.getUid();
  }

  public String getName() {
    return document.getName();
  }

  public Language getSourceLanguage() {
    return document.getSourceLanguage();
  }

  public Language getTargetLanguage() {
    return document.getTargetLanguage();
  }

  public String getDomain() {
    return document.getDomain().getDescription();
  }

  public String getContentType() {
    return document.getContentType().getDescription();
  }

  public double getPricePerWord() {
    return document.getPricePerWord();
  }

  public long getSize() {
    return document.getSize();
  }

  public String getFormat() {
    return document.getFormat();
  }

  public List<TranslationUnitPublishedSummary> getUnits() {
    return Optional.ofNullable(document.getUnits())
      .map(l -> l.stream().map(unit -> new TranslationUnitPublishedSummary(unit, seller)).collect(toList()))
      .orElse(new ArrayList<>());
  }

  public UUID getSellerUid() {
    return Optional.ofNullable(this.getSeller()).map(User::getUid).orElse(null);
  }

  public SlugId getSellerSlugId() {
    return new SlugId(this.getSellerUid(), this.getSellerFirstName(), this.getSellerLastName());
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

  public long getUnitCount() {
    return this.getDocument().getCurrentStatistics().map(s -> s.getTotalUnitsOriginal() + s.getTotalUnitsReplica()).orElse(0L);
  }

  public long getSourceWordCount() {
    return this.getDocument().getCurrentStatistics().map(s -> s.getTotalSourceOriginalWordCount() + s.getTotalSourceReplicaWordCount()).orElse(0L);
  }

  public long getTargetWordCount() {
    return this.getDocument().getCurrentStatistics().map(s -> s.getTotalTargetOriginalWordCount() + s.getTotalTargetReplicaWordCount()).orElse(0L);
  }

  public double getPrice() {
    return this.getDocument().getPricePerWord() * getSourceWordCount();
  }
}
