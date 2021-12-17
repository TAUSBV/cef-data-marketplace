package net.taus.data.marketplace.api.price;

import net.taus.data.language.api.DataLanguageClient;
import net.taus.data.language.api.model.ContentType;
import net.taus.data.language.api.model.Domain;
import net.taus.data.language.api.model.Language;
import net.taus.data.marketplace.model.SystemException;
import net.taus.data.marketplace.model.price.*;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public class PriceIndexService {

  private final LanguageDataAvailabilityCountRepository availabilityCounts;
  private final PriceConfigurationRepository priceConfigurations;
  private final LanguageDataAvailabilityCategoryRepository availabilityCategories;
  private final PriceIndexRepository priceIndexes;
  private final DataLanguageClient dataLanguageClient;

  public PriceIndexService(
    final LanguageDataAvailabilityCountRepository availabilityCounts,
    final LanguageDataAvailabilityCategoryRepository availabilityCategories,
    final PriceIndexRepository priceIndexes,
    final PriceConfigurationRepository priceConfigurations,
    final DataLanguageClient dataLanguageClient) {
    this.availabilityCounts = availabilityCounts;
    this.availabilityCategories = availabilityCategories;
    this.priceIndexes = priceIndexes;
    this.priceConfigurations = priceConfigurations;
    this.dataLanguageClient = dataLanguageClient;
  }

  public Iterable<PriceIndex> updateCountCategoriesPriceIndexes(final Language sourceLanguage,
                                                                final Language targetLanguage,
                                                                final Domain domain,
                                                                final ContentType contentType,
                                                                final Long wordCount) {
    availabilityCounts.updateCount(sourceLanguage, targetLanguage, domain, contentType, wordCount);
    return this.update();
  }

  public Iterable<PriceIndex> update() {
    var counts = availabilityCounts.findAll();
    final List<Domain> domains = dataLanguageClient.getDomains();
    final List<ContentType> contentTypes = dataLanguageClient.getContentTypes();
    final List<Language> languageList = dataLanguageClient.getLanguages();
    counts.forEach(c -> {
      Language language = languageList.stream().filter(l -> l.getId().equals(c.getSourceLanguageId())).findFirst().orElseThrow(() -> new SystemException(String.format("Missing source language %s", c.getSourceLanguageId())));
      c.setSourceLanguage(language);
      language = languageList.stream().filter(l -> l.getId().equals(c.getTargetLanguageId())).findFirst().orElseThrow(() -> new SystemException(String.format("Missing target language %s", c.getTargetLanguageId())));
      c.setTargetLanguage(language);
      final Domain domain = domains.stream().filter(d -> d.getId().equals(c.getDomainId())).findFirst().orElseThrow(() -> new SystemException(String.format("Missing domain %s", c.getDomainId())));
      c.setDomain(domain);
      final ContentType contentType = contentTypes.stream().filter(d -> d.getId().equals(c.getContentTypeId())).findFirst().orElseThrow(() -> new SystemException(String.format("Missing content type %s", c.getDomainId())));
      c.setContentType(contentType);
    });
    availabilityCategories.updateHigherBounds(counts);
    return this.updatePriceIndexes(counts);
  }

  public Iterable<PriceIndex> updatePriceIndexes(List<LanguageDataAvailabilityCount> counts) {
    var priceIndexEntries = new ArrayList<PriceIndex>();
    var totalWordCount = counts.stream().mapToLong(LanguageDataAvailabilityCount::getWordCount).sum();
    var priceConfiguration = priceConfigurations.findAll().get(0);
    var enoughData = priceConfiguration.hasEnoughData(totalWordCount, counts.size(), availabilityCategories.count());
    var categoryCommon = availabilityCategories.findFirstByLabel("common");
    for (var count : counts) {
      var priceIndexOptional = priceIndexes.findByLanguageDataAvailabilityCount(count);
      var priceIndex = priceIndexOptional.orElseGet(() -> new PriceIndex(count));
      var availabilityCategory = categoryCommon;
      if (enoughData) {
        availabilityCategory = availabilityCategories.findFirstByHigherBoundGreaterThanEqualOrderBySortAsc(count.getWordCount());
      }
      var priceSuggestion = new PriceSuggestion(availabilityCategory, priceConfiguration);
      priceIndex.setLanguageDataAvailabilityCategory(availabilityCategory);
      priceIndex.setSuggestedPricePerWord(priceSuggestion.getSuggestedPricePerWord());
      priceIndexEntries.add(priceIndex);
    }
    return priceIndexes.saveAll(priceIndexEntries);
  }

  public Page<PriceIndex> findAll(Example<PriceIndex> example, Pageable pageable) {
    return priceIndexes.findAll(example, pageable);
  }

  //FIXME add job to update the prices

}