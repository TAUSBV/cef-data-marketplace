package net.taus.data.language.model;

import net.taus.data.language.model.meta.ContentType;
import net.taus.data.language.model.meta.Domain;
import net.taus.data.language.model.meta.Language;
import net.taus.data.language.model.meta.LanguageDataCharacteristics;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import javax.annotation.Nonnull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

import static net.taus.data.language.model.TranslationDocument.Status.NEW;

public class LanguageDataUtil {

  @Nonnull
  public static LanguageDataCharacteristics newCharacteristics(final Domain domain,
                                                               final ContentType contentType,
                                                               final double pricePerWord) {
    final LanguageDataCharacteristics characteristics = new LanguageDataCharacteristics();
    characteristics.addDomains(List.of(domain));
    characteristics.setContentTypes(List.of(contentType));
    characteristics.setPricePerWord(pricePerWord);
    return characteristics;
  }

  @Nonnull
  public static TranslationDocument newTranslationDocument(
    final Domain domain,
    final ContentType contentType,
    final double pricePerWord,
    final Language sourceLanguage,
    final Language targetLanguage,
    final TranslationDocument.Status status,
    final TranslationDocumentStatistics statistics) {
    var document = new TranslationDocument();
    document.setName("en-GB_es-ES_test.tmx");
    document.setSize(1000);
    document.setSourceLanguage(sourceLanguage);
    document.setTargetLanguage(targetLanguage);
    document.setStatus(status);
    document.setOwnerId(1L);
    if (Objects.nonNull(domain) && Objects.nonNull(contentType)) {
      document.addCharacteristics(newCharacteristics(domain, contentType, pricePerWord));
    }
    if (statistics != null) {
      document.addStatistics(statistics);
    }
    return document;
  }

  @Nonnull
  public static TranslationUnit newTranslationUnit(final TranslationDocument document,
                                                   final TranslationVariant source,
                                                   final TranslationVariant target,
                                                   final long hash,
                                                   final long sequence) {
    var unit = new TranslationUnit();
    unit.setDocument(document);
    unit.setHash(hash);
    unit.setSource(source);
    unit.setTarget(target);
    unit.setSequence(sequence);
    return unit;
  }

  @Nonnull
  public static TranslationVariant newTranslationVariant(final Language language,
                                                         final String value,
                                                         final int wordCount,
                                                         final long hash) {
    var source = new TranslationVariant();
    source.setLanguage(language);
    source.setValue(value);
    source.setWordCount(wordCount);
    source.setHash(hash);
    return source;
  }

  public static List<TranslationDocument> prepare(final Language sourceLanguage,
                                                  final Language targetLanguage,
                                                  final long numberOfDocuments,
                                                  final long numberOfUnits,
                                                  final long ownerId,
                                                  final double pricePerWord,
                                                  final TranslationDocumentStatistics perDocumentStatistics,
                                                  final List<Domain> domains,
                                                  final List<ContentType> contentTypes,
                                                  final TestEntityManager em) {
    List<TranslationDocument> documents = new ArrayList<>();
    for (int i = 0; i < numberOfDocuments; i++) {
      var statistics = new TranslationDocumentStatistics();
      statistics.setTotalUnits(2L);
      statistics.setTotalUnitsOriginal(1L);
      statistics.setTotalUnitsReplica(1L);
      statistics.setTotalSourceWordCount(1L);
      statistics.setTotalSourceOriginalWordCount(1L);
      statistics.setTotalTargetOriginalWordCount(1L);
      statistics.setCreatedAt(LocalDateTime.now().minusDays(1));
      var document = LanguageDataUtil.newTranslationDocument(domains.get(0), contentTypes.get(0),
        0.005, sourceLanguage, targetLanguage, NEW, statistics);
      document.setOwnerId(ownerId);
      document = em.persistFlushFind(document);

      final LanguageDataCharacteristics characteristics = new LanguageDataCharacteristics();
      characteristics.setPricePerWord(pricePerWord);
      characteristics.addDomains(domains.subList(1, 3));
      characteristics.addContentTypes(contentTypes.subList(1, 3));
      document.addCharacteristics(characteristics);
      statistics = new TranslationDocumentStatistics(
        perDocumentStatistics.getTotalUnits(),
        perDocumentStatistics.getTotalUnitsOriginal(),
        perDocumentStatistics.getTotalUnitsReplica(),
        perDocumentStatistics.getTotalUnitsLowQuality(),
        perDocumentStatistics.getTotalUnitsAnnotatedPii(),
        perDocumentStatistics.getTotalSourceWordCount(),
        perDocumentStatistics.getTotalSourceOriginalWordCount(),
        perDocumentStatistics.getTotalSourceReplicaWordCount(),
        perDocumentStatistics.getTotalSourceLowQualityWordCount(),
        perDocumentStatistics.getTotalSourceWordCountAnnotatedPii(),
        perDocumentStatistics.getTotalTargetWordCount(),
        perDocumentStatistics.getTotalTargetOriginalWordCount(),
        perDocumentStatistics.getTotalTargetReplicaWordCount(),
        perDocumentStatistics.getTotalTargetLowQualityWordCount(),
        perDocumentStatistics.getTotalTargetWordCountAnnotatedPii()
      );
      document.addStatistics(statistics);
      document = em.persistFlushFind(document);

      for (int j = 0; j < numberOfUnits; j++) {
        var source = LanguageDataUtil.newTranslationVariant(sourceLanguage, "Thank you",
          2, ThreadLocalRandom.current().nextLong());
        var target = LanguageDataUtil.newTranslationVariant(targetLanguage, "Gracias",
          1, ThreadLocalRandom.current().nextLong());
        source = em.persistFlushFind(source);
        target = em.persistFlushFind(target);
        var tu1 = LanguageDataUtil.newTranslationUnit(document, source, target,
          ThreadLocalRandom.current().nextLong(), j);
        em.persistAndFlush(tu1);
      }
      documents.add(document);
    }
    return documents;
  }
}
