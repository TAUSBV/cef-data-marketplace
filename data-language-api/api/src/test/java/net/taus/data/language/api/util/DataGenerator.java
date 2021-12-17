package net.taus.data.language.api.util;

import com.github.javafaker.Faker;
import net.taus.data.language.model.TranslationDocument;
import net.taus.data.language.model.TranslationDocumentStatistics;
import net.taus.data.language.model.TranslationUnit;
import net.taus.data.language.model.TranslationVariant;
import net.taus.data.language.model.meta.ContentType;
import net.taus.data.language.model.meta.Domain;
import net.taus.data.language.model.meta.Language;
import net.taus.data.language.model.meta.LanguageDataCharacteristics;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class DataGenerator {

  public static TranslationDocumentDataGenerator newDocument(final Language sourceLanguage,
                                                             final Language targetLanguage) {
    final Faker faker = new Faker(Locale.forLanguageTag(sourceLanguage.getTag()));
    return TranslationDocumentDataGenerator.create(sourceLanguage, targetLanguage, faker);
  }

  public static TranslationUnitDataGenerator unitsForDocument(final TranslationDocument document) {
    final Faker sourceFaker = new Faker(Locale.forLanguageTag(document.getSourceLanguage().getTag()));
    final Faker targetFaker = new Faker(Locale.forLanguageTag(document.getTargetLanguage().getTag()));
    return TranslationUnitDataGenerator.create(document, sourceFaker, targetFaker);
  }

  public static class TranslationDocumentDataGenerator {

    private final TranslationDocument document;
    private final Faker faker;

    public TranslationDocumentDataGenerator(final TranslationDocument document, final Faker faker) {
      this.document = document;
      this.faker = faker;
    }

    public static TranslationDocumentDataGenerator create(final Language sourceLanguage,
                                                          final Language targetLanguage,
                                                          final Faker faker) {
      final TranslationDocument document = new TranslationDocument(UUID.randomUUID(), faker.funnyName().name(), 100L);
      document.setSourceLanguage(sourceLanguage);
      document.setTargetLanguage(targetLanguage);
      document.setOwnerId(faker.number().numberBetween(1L, 10L));
      document.addCharacteristics(new LanguageDataCharacteristics(faker.number().randomDouble(4, 0, 0)));
      return new TranslationDocumentDataGenerator(document, faker);
    }

    public TranslationDocumentDataGenerator withStatus(final TranslationDocument.Status status) {
      this.document.setStatus(status);
      return this;
    }

    public TranslationDocumentDataGenerator withOwnerId(final long ownerId) {
      this.document.setOwnerId(ownerId);
      return this;
    }

    public TranslationDocumentDataGenerator withPricePerWord(final double pricePerWord) {
      this.document.getCurrentCharacteristics().orElseGet(LanguageDataCharacteristics::new).setPricePerWord(pricePerWord);
      return this;
    }

    public TranslationDocumentDataGenerator withDomain(final Domain domain) {
      this.document.getCurrentCharacteristics().orElseGet(LanguageDataCharacteristics::new).addDomain(domain);
      return this;
    }

    public TranslationDocumentDataGenerator withContentType(final ContentType contentType) {
      this.document.getCurrentCharacteristics().orElseGet(LanguageDataCharacteristics::new).addContentType(contentType);
      return this;
    }

    public TranslationDocument generate() {
      return this.document;
    }

    public TranslationDocumentDataGenerator addStatistics(final TranslationDocumentStatistics statistics) {
      this.document.addStatistics(statistics);
      return this;
    }

    public TranslationDocumentDataGenerator replaceStatistics(final TranslationDocumentStatistics statistics) {
      this.document.setStatisticsList(List.of(statistics));
      return this;
    }

    public TranslationDocumentDataGenerator addRandomStatistics() {
      document.addStatistics(new TranslationDocumentStatistics(
        100L,
        faker.number().numberBetween(1L, 100L),
        faker.number().numberBetween(1L, 100L),
        faker.number().numberBetween(1L, 100L),
        faker.number().numberBetween(1L, 100L),
        faker.number().numberBetween(1L, 100L),
        faker.number().numberBetween(1L, 100L),
        faker.number().numberBetween(1L, 100L),
        faker.number().numberBetween(1L, 100L),
        faker.number().numberBetween(1L, 100L),
        faker.number().numberBetween(1L, 100L),
        faker.number().numberBetween(1L, 100L),
        faker.number().numberBetween(1L, 100L),
        faker.number().numberBetween(1L, 100L),
        faker.number().numberBetween(1L, 100L)
      ));
      return this;
    }
  }

  public static class TranslationUnitDataGenerator {

    private final TranslationDocument document;
    private final Faker sourceFaker;
    private final Faker targetFaker;
    private int amount;

    private TranslationUnitDataGenerator(final TranslationDocument document,
                                         final Faker sourceFaker,
                                         final Faker targetFaker) {
      this.document = document;
      this.sourceFaker = sourceFaker;
      this.targetFaker = targetFaker;
      this.amount = 10;
    }

    public static TranslationUnitDataGenerator create(final TranslationDocument document,
                                                      final Faker sourceFaker,
                                                      final Faker targetFaker) {
      return new TranslationUnitDataGenerator(document, sourceFaker, targetFaker);
    }

    public TranslationUnitDataGenerator quantity(final int amount) {
      this.amount = amount;
      return this;
    }

    public List<TranslationUnit> generate() {
      List<TranslationUnit> units = new ArrayList<>();
      for (int i = 0; i < amount; i++) {
        TranslationVariant sourceVariant = new TranslationVariant();
        sourceVariant.setLanguage(document.getSourceLanguage());
        sourceVariant.setValue(sourceFaker.lorem().sentence());
        sourceVariant.setHash(i + 1000);
        sourceVariant.setWordCount(1000L);
        TranslationVariant targetVariant = new TranslationVariant();
        targetVariant.setLanguage(document.getTargetLanguage());
        targetVariant.setValue(targetFaker.lorem().sentence());
        targetVariant.setHash(i + 2000);
        targetVariant.setWordCount(1000L);
        TranslationUnit unit = new TranslationUnit(document, sourceVariant, targetVariant);
        unit.setHash(i + 3000);
        unit.setSequence((long) i + 1);
        unit.setOwnerId(document.getOwnerId());
        units.add(unit);
      }
      return units;
    }
  }
}
