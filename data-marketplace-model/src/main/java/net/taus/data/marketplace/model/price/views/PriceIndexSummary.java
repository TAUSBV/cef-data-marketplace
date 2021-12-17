package net.taus.data.marketplace.model.price.views;

import net.taus.data.language.api.model.Language;
import org.springframework.beans.factory.annotation.Value;

public interface PriceIndexSummary {

  long getId();

  @Value("#{target.languageDataAvailabilityCount.domainDescription}")
  String getDomain();

  @Value("#{target.languageDataAvailabilityCount.contentTypeDescription}")
  String getContentType();

  @Value("#{target.languageDataAvailabilityCount.sourceLanguage}")
  Language getSourceLanguage();

  @Value("#{target.languageDataAvailabilityCount.targetLanguage}")
  Language getTargetLanguage();

  @Value("#{target.languageDataAvailabilityCategory}")
  LanguageDataAvailabilityCategorySummary getAvailabilityCategory();

  double getSuggestedPricePerWord();

}