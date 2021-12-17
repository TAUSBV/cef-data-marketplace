package net.taus.data.marketplace.api.price;


import net.taus.data.marketplace.model.price.*;

public class PriceSuggestionService {
  private final PriceConfigurationRepository priceConfigurations;
  private final LanguageDataAvailabilityCountRepository availabilityCounts;
  private final LanguageDataAvailabilityCategoryRepository availabilityCategories;

  public PriceSuggestionService(
    LanguageDataAvailabilityCountRepository availabilityCounts,
    LanguageDataAvailabilityCategoryRepository availabilityCategories,
    PriceConfigurationRepository priceConfigurations
  ) {
    this.availabilityCounts = availabilityCounts;
    this.availabilityCategories = availabilityCategories;
    this.priceConfigurations = priceConfigurations;
  }

  public PriceSuggestion getPriceSuggestion(final Long sourceLanguageId, final Long targetLanguageId, final Long domainId, final Long contentTypeId) {
    var currentWordCount = availabilityCounts
      .findOneBySourceLanguageIdAndTargetLanguageIdAndDomainIdAndContentTypeId(sourceLanguageId, targetLanguageId, domainId, contentTypeId)
      .map(LanguageDataAvailabilityCount::getWordCount).orElse(0L);
    var priceConfiguration = priceConfigurations.getDefault();
    var wordCounts = availabilityCounts.findWordCounts();
    var totalWordCount = wordCounts.stream().mapToLong(Long::longValue).sum();

    LanguageDataAvailabilityCategory availabilityCategory;
    if (!priceConfiguration.hasEnoughData(totalWordCount, wordCounts.size(), availabilityCategories.count())) {
      availabilityCategory = availabilityCategories.findFirstByLabel("common");
    }
    else {
      availabilityCategory = availabilityCategories.findFirstByHigherBoundGreaterThanEqualOrderBySortAsc(currentWordCount);
    }
    return new PriceSuggestion(availabilityCategory, priceConfiguration);
  }

}