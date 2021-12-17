package net.taus.data.marketplace.model.languagedata;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Value;
import net.taus.data.language.api.model.*;
import net.taus.data.marketplace.model.price.views.LanguageDataAvailabilityCategorySummary;
import net.taus.data.marketplace.model.user.User;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Value
public class TranslationDocumentPublishingSummary {

  @Getter(value = AccessLevel.PRIVATE)
  TranslationDocument document;
  @Getter(value = AccessLevel.PRIVATE)
  User seller;

  public TranslationDocumentPublishingSummary(final TranslationDocument document) {
    this.document = document;
    this.seller = null;
  }

  public TranslationDocumentPublishingSummary(final TranslationDocument document,
                                              final User seller) {
    this.document = document;
    this.seller = seller;
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
    return Optional.ofNullable(document.getDomain())
      .map(Domain::getDescription).orElse("");
  }

  public String getContentType() {
    return Optional.ofNullable(document).map(TranslationDocument::getDescription).orElse("");
  }

  public Double getPricePerWord() {
    return document.getPricePerWord();
  }

  public Long getSize() {
    return document.getSize();
  }

  public String getFormat() {
    return document.getFormat();
  }

  public List<TranslationUnitPublishedSummary> getUnits() {
    return Optional.ofNullable(document.getUnits()).stream().flatMap(Collection::stream)
      .map(unit -> new TranslationUnitPublishedSummary(unit, seller)).collect(Collectors.toList());
  }

  public List<TranslationDocumentStatistics> getStatisticsList() {
    return document.getStatisticsList();
  }

  public Optional<TranslationDocumentStatistics> getCurrentStatistics() {
    return document.getCurrentStatistics();
  }

  public Double getSuggestedPricePerWord() {
    return 0.0;
  }

  public LanguageDataAvailabilityCategorySummary getPriceCategory() {
    return null;
  }

  public List<DataAnnotationCategoryCount> getAnnotations() {
    return this.document.getAnnotations();
  }

}
