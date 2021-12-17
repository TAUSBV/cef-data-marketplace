package net.taus.data.language.model;

import net.taus.data.language.IntegrationTest;
import net.taus.data.language.model.meta.ContentType;
import net.taus.data.language.model.meta.Domain;
import net.taus.data.language.model.meta.Language;
import net.taus.data.language.model.meta.LanguageDataCharacteristics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static net.taus.data.language.model.TranslationDocument.Status.PUBLISHED;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Transactional
class TranslationDocumentRepositoryTest extends IntegrationTest {

  @Autowired
  private TestEntityManager em;

  @Autowired
  private CacheManager cacheManager;

  @Autowired
  private TranslationDocumentRepository documents;

  @Autowired
  private TranslationUnitRepository units;

  private Domain domain;
  private ContentType contentType;
  private Language sourceLanguage;
  private Language targetLanguage;
  private List<Domain> domains;
  private List<ContentType> contentTypes;

  @BeforeEach
  public void setUp() {
    domains = em.getEntityManager().createQuery("select d from Domain d where d.origin = 'MARKETPLACE'", Domain.class).getResultList();
    domain = domains.get(0);
    contentTypes = em.getEntityManager().createQuery("select c from ContentType c where c.origin = 'MARKETPLACE'", ContentType.class).getResultList();
    contentType = contentTypes.get(0);
    sourceLanguage = em.getEntityManager().createQuery("select o from Language o where o.name = 'en' and o.country = 'GB'", Language.class).setMaxResults(1).getSingleResult();
    targetLanguage = em.getEntityManager().createQuery("select o from Language o where o.name = 'es' and o.country = 'ES'", Language.class).setMaxResults(1).getSingleResult();
    cacheManager.getCacheNames().forEach(cacheName -> Objects.requireNonNull(cacheManager.getCache(cacheName)).clear());
  }

  @Test
  public void shouldPublishADocument() throws TranslationDocumentException {
    long sequence = 1L;
    var statistics = new TranslationDocumentStatistics(
      2L,
      2L,
      0L,
      0L,
      0L,
      6L,
      6L,
      0L,
      0L,
      0L,
      4L,
      4L,
      0L,
      0L,
      0L);
    var document = LanguageDataUtil.newTranslationDocument(domain, contentType, 0.005, sourceLanguage, targetLanguage, TranslationDocument.Status.NEW, statistics);
    document = em.persistFlushFind(document);
    var source = LanguageDataUtil.newTranslationVariant(sourceLanguage, "Thank you", 2, 12345);
    var target = LanguageDataUtil.newTranslationVariant(targetLanguage, "Gracias", 1, 98765);
    var tu1 = LanguageDataUtil.newTranslationUnit(document, source, target, 1234598765, sequence++);
    tu1 = units.saveOriginal(tu1);
    source = LanguageDataUtil.newTranslationVariant(sourceLanguage, "Hey, how are you?", 4, 9999);
    target = LanguageDataUtil.newTranslationVariant(targetLanguage, "Ola, que tal?", 3, 8888);
    var tu2 = LanguageDataUtil.newTranslationUnit(document, source, target, 9988, sequence);
    tu2 = units.saveOriginal(tu2);

    documents.publish(document);

    tu1 = em.refresh(tu1);
    tu2 = em.refresh(tu2);

    assertThat(document.getStatus(), is(equalTo(PUBLISHED)));
    assertThat(document.getCurrentCharacteristics().isPresent(), is(true));
    assertThat(document.getCurrentCharacteristics().get().getPricePerWord(), is(equalTo(0.005)));
    assertTrue(document.getCurrentStatistics().isPresent());
    assertThat(document.getCurrentStatistics().get(), is(statistics));
    assertThat(tu1.getStatus(), is(equalTo(PUBLISHED)));
    assertThat(tu2.getStatus(), is(equalTo(PUBLISHED)));
  }

  @Test
  public void shouldSyncOriginalsAndReplicasWhenPublishDocument() throws TranslationDocumentException {
    long sequence = 1L;
    final TranslationDocumentStatistics document1Statistics = new TranslationDocumentStatistics(
      3L,
      3L,
      0L,
      0L,
      0L,
      8L,
      8L,
      0L,
      0L,
      0L,
      6L,
      6L,
      0L,
      0L,
      0L);
    var document1 = LanguageDataUtil.newTranslationDocument(domain, contentType, 0.005, sourceLanguage, targetLanguage, TranslationDocument.Status.NEW, document1Statistics);
    document1 = em.persistFlushFind(document1);
    var source = LanguageDataUtil.newTranslationVariant(sourceLanguage, "Thank you", 2, 12345);
    var target = LanguageDataUtil.newTranslationVariant(targetLanguage, "Gracias", 1, 98765);
    var tu1Document1 = LanguageDataUtil.newTranslationUnit(document1, source, target, 1234598765, sequence++);
    tu1Document1 = units.saveOriginal(tu1Document1);
    source = LanguageDataUtil.newTranslationVariant(sourceLanguage, "Hey, how are you?", 4, 9999);
    target = LanguageDataUtil.newTranslationVariant(targetLanguage, "Ola, que tal?", 3, 8888);
    var tu2Document1 = LanguageDataUtil.newTranslationUnit(document1, source, target, 9988, sequence++);
    source = LanguageDataUtil.newTranslationVariant(sourceLanguage, "What happens?", 2, 7777);
    target = LanguageDataUtil.newTranslationVariant(targetLanguage, "Lo que pasa?", 2, 6666);
    tu2Document1 = units.saveOriginal(tu2Document1);
    var tu3Document1 = LanguageDataUtil.newTranslationUnit(document1, source, target, 7766, sequence++);
    tu3Document1 = units.saveOriginal(tu3Document1);

    final TranslationDocumentStatistics document2Statistics = new TranslationDocumentStatistics(
      3L,
      3L,
      0L,
      0L,
      0L,
      0L,
      0L,
      0L,
      0L,
      0L,
      0L,
      0L,
      0L,
      0L,
      0L);
    var document2 = LanguageDataUtil.newTranslationDocument(domain, contentType, 0.008, sourceLanguage, targetLanguage, TranslationDocument.Status.NEW, document2Statistics);
    document2 = em.persistFlushFind(document2);
    source = LanguageDataUtil.newTranslationVariant(sourceLanguage, "Thank you", 2, 12345);
    target = LanguageDataUtil.newTranslationVariant(targetLanguage, "Gracias", 1, 98765);
    var tu1Document2 = LanguageDataUtil.newTranslationUnit(document2, source, target, 1234598765, sequence++);
    tu1Document2 = units.saveDuplicate(tu1Document2);
    source = LanguageDataUtil.newTranslationVariant(sourceLanguage, "Hey, how are you?", 4, 9999);
    target = LanguageDataUtil.newTranslationVariant(targetLanguage, "Ola, que tal?", 3, 8888);
    var tu2Document2 = LanguageDataUtil.newTranslationUnit(document2, source, target, 9988, sequence++);
    tu2Document2 = units.saveDuplicate(tu2Document2);
    source = LanguageDataUtil.newTranslationVariant(sourceLanguage, "Happy birthday!", 2, 5555);
    target = LanguageDataUtil.newTranslationVariant(targetLanguage, "Feliz cumpleaños!", 2, 4444);
    var tu3Document2 = LanguageDataUtil.newTranslationUnit(document2, source, target, 5544, sequence);
    tu3Document2 = units.saveOriginal(tu3Document2);

    assertThat(tu1Document1.isOriginal(), is(true));
    assertThat(tu2Document1.isOriginal(), is(true));
    assertThat(tu3Document1.isOriginal(), is(true));

    assertThat(tu1Document2.isOriginal(), is(true));
    assertThat(tu2Document2.isOriginal(), is(true));
    assertThat(tu3Document2.isOriginal(), is(true));

    documents.publish(document1);
    documents.publish(document2);

    tu1Document1 = em.refresh(tu1Document1);
    tu2Document1 = em.refresh(tu2Document1);
    tu3Document1 = em.refresh(tu3Document1);

    tu1Document2 = em.refresh(tu1Document2);
    tu2Document2 = em.refresh(tu2Document2);
    tu3Document2 = em.refresh(tu3Document2);

    assertThat(document1.getStatisticsList().size(), is(1));
    assertThat(document2.getStatisticsList().size(), is(2));
    assertTrue(document2.getCurrentStatistics().isPresent());
    assertThat(document2.getCurrentStatistics().get().getTotalUnitsOriginal(), is(1L));
    assertThat(document2.getCurrentStatistics().get().getTotalUnitsReplica(), is(2L));

    assertThat(tu1Document1.isOriginal(), is(true));
    assertThat(tu2Document1.isOriginal(), is(true));
    assertThat(tu3Document1.isOriginal(), is(true));

    assertThat(tu1Document2.isOriginal(), is(false));
    assertThat(tu1Document2.isReplica(), is(true));
    assertThat(tu1Document2.getReference(), is(equalTo(tu1Document1)));

    assertThat(tu2Document2.isOriginal(), is(false));
    assertThat(tu2Document2.isReplica(), is(true));
    assertThat(tu2Document2.getReference(), is(equalTo(tu2Document1)));

    assertThat(tu3Document2.isOriginal(), is(true));
  }

  @Test
  public void shouldRejectDocumentWhenItHasNotEnoughOriginals() throws TranslationDocumentException {
    long sequence = 1;
    final TranslationDocumentStatistics document1Statistics = new TranslationDocumentStatistics(
      3L,
      3L,
      0L,
      0L,
      0L,
      8L,
      8L,
      0L,
      0L,
      0L,
      6L,
      6L,
      0L,
      0L,
      0L);
    var document1 = LanguageDataUtil.newTranslationDocument(domain, contentType, 0.005, sourceLanguage, targetLanguage, TranslationDocument.Status.NEW, document1Statistics);
    document1 = em.persistFlushFind(document1);
    var source = LanguageDataUtil.newTranslationVariant(sourceLanguage, "Thank you", 2, 12345);
    var target = LanguageDataUtil.newTranslationVariant(targetLanguage, "Gracias", 1, 98765);
    var tu1Document1 = LanguageDataUtil.newTranslationUnit(document1, source, target, 1234598765, sequence++);
    tu1Document1 = units.saveOriginal(tu1Document1);
    source = LanguageDataUtil.newTranslationVariant(sourceLanguage, "Hey, how are you?", 4, 9999);
    target = LanguageDataUtil.newTranslationVariant(targetLanguage, "Ola, que tal?", 3, 8888);
    var tu2Document1 = LanguageDataUtil.newTranslationUnit(document1, source, target, 9988, sequence++);
    source = LanguageDataUtil.newTranslationVariant(sourceLanguage, "What happens?", 2, 7777);
    target = LanguageDataUtil.newTranslationVariant(targetLanguage, "Lo que pasa?", 2, 6666);
    tu2Document1 = units.saveOriginal(tu2Document1);
    var tu3Document1 = LanguageDataUtil.newTranslationUnit(document1, source, target, 7766, sequence++);
    tu3Document1 = units.saveOriginal(tu3Document1);

    var document2 = LanguageDataUtil.newTranslationDocument(domain, contentType, 0.008, sourceLanguage, targetLanguage, TranslationDocument.Status.NEW,
      new TranslationDocumentStatistics(
        3L,
        3L,
        0L,
        0L,
        0L,
        0L,
        0L,
        0L,
        0L,
        0L,
        0L,
        0L,
        0L,
        0L,
        0L));
    document2 = em.persistFlushFind(document2);
    source = LanguageDataUtil.newTranslationVariant(sourceLanguage, "Thank you", 2, 12345);
    target = LanguageDataUtil.newTranslationVariant(targetLanguage, "Gracias", 1, 98765);
    var tu1Document2 = LanguageDataUtil.newTranslationUnit(document2, source, target, 1234598765, sequence++);
    tu1Document2 = units.saveDuplicate(tu1Document2);
    source = LanguageDataUtil.newTranslationVariant(sourceLanguage, "Hey, how are you?", 4, 9999);
    target = LanguageDataUtil.newTranslationVariant(targetLanguage, "Ola, que tal?", 3, 8888);
    var tu2Document2 = LanguageDataUtil.newTranslationUnit(document2, source, target, 9988, sequence++);
    tu2Document2 = units.saveDuplicate(tu2Document2);
    source = LanguageDataUtil.newTranslationVariant(sourceLanguage, "What happens?", 2, 7777);
    target = LanguageDataUtil.newTranslationVariant(targetLanguage, "Lo que pasa?", 2, 6666);
    var tu3Document2 = LanguageDataUtil.newTranslationUnit(document2, source, target, 7766, sequence);
    tu3Document2 = units.saveDuplicate(tu3Document2);

    assertThat(tu1Document1.isOriginal(), is(true));
    assertThat(tu2Document1.isOriginal(), is(true));
    assertThat(tu3Document1.isOriginal(), is(true));

    assertThat(tu1Document2.isOriginal(), is(true));
    assertThat(tu2Document2.isOriginal(), is(true));
    assertThat(tu3Document2.isOriginal(), is(true));

    documents.publish(document1);
    final var innerDoc = document2;

    assertThrows(TranslationDocumentException.class, () -> documents.publish(innerDoc));

    assertThat(document1.getStatus(), is(PUBLISHED));
    assertThat(document1.getStatisticsList().size(), is(1));

    assertThat(document2.getStatus(), is(TranslationDocument.Status.NEW));
    assertThat(document2.getStatisticsList().size(), is(2));
    assertTrue(document2.getCurrentStatistics().isPresent());
    assertThat(document2.getCurrentStatistics().get().getTotalUnitsOriginal(), is(0L));
    assertThat(document2.getCurrentStatistics().get().getTotalUnitsReplica(), is(3L));
  }

  @Test
  public void shouldLoadOneSummaryWhenWithMoreThanOneStatistics() {
    var statistics = new TranslationDocumentStatistics(
      2L,
      2L,
      0L,
      0L,
      0L,
      0L,
      0L,
      0L,
      0L,
      0L,
      0L,
      0L,
      0L,
      0L,
      0L);
    var document = LanguageDataUtil.newTranslationDocument(domain, contentType, 0.005, sourceLanguage, targetLanguage, TranslationDocument.Status.NEW, statistics);
    document = em.persistFlushFind(document);
    final LanguageDataCharacteristics characteristics = LanguageDataUtil.newCharacteristics(domain, contentType, 0.0020);
    document.addCharacteristics(characteristics);
    statistics = new TranslationDocumentStatistics(
      10L,
      5L,
      0L,
      0L,
      0L,
      0L,
      0L,
      0L,
      0L,
      0L,
      0L,
      0L,
      0L,
      0L,
      0L);
    document.addStatistics(statistics);
    document = em.persistFlushFind(document);
    final Optional<net.taus.data.language.api.model.TranslationDocument> summary = documents.findOneSummaryByUid(document.getUid());
    assertTrue(summary.isPresent());
    final net.taus.data.language.api.model.TranslationDocument translationDocumentSummary = summary.get();
    assertThat(translationDocumentSummary.getStatisticsList().size(), is(equalTo(2)));
    final Optional<net.taus.data.language.api.model.TranslationDocumentStatistics> currentStatistics = translationDocumentSummary.getCurrentStatistics();
    assertTrue(currentStatistics.isPresent());
    assertThat(currentStatistics.get().getTotalUnits(), is(10L));
    assertThat(currentStatistics.get().getTotalUnitsOriginal(), is(5L));
    assertThat(translationDocumentSummary.getPricePerWord(), is(equalTo(0.0020)));
  }

  @Test
  public void shouldLoadSummaryListWhenAnyDocumentHasMoreThanOneStatistics() {
    for (int i = 0; i < 15; i++) {
      em.persistAndFlush(LanguageDataUtil.newTranslationDocument(domain, contentType, 0.005, sourceLanguage, targetLanguage, PUBLISHED, new TranslationDocumentStatistics()));
    }
    var statistics = new TranslationDocumentStatistics();
    var document = LanguageDataUtil.newTranslationDocument(domain, contentType, 0.005, sourceLanguage, targetLanguage, PUBLISHED, statistics);
    document = em.persistFlushFind(document);
    final LanguageDataCharacteristics characteristics = LanguageDataUtil.newCharacteristics(domain, contentType, 0.0020);
    document.addCharacteristics(characteristics);
    statistics = new TranslationDocumentStatistics(
      10L,
      5L,
      0L,
      0L,
      0L,
      0L,
      0L,
      0L,
      0L,
      0L,
      0L,
      0L,
      0L,
      0L,
      0L);
    document.addStatistics(statistics);
    document = em.persistFlushFind(document);

    var finalDocument = document;
    final List<net.taus.data.language.api.model.TranslationDocument> summary = documents.getFeatured(10, 1);
    assertThat(summary.size(), is(equalTo(10)));
    assertTrue(summary.stream().anyMatch(d -> d.getUid().equals(finalDocument.getUid())));
  }

  @Test
  public void shouldPageWhenAnyDocumentHasMoreThanOneStatistics() {
    var statistics = new TranslationDocumentStatistics(
      2L,
      2L,
      0L,
      0L,
      0L,
      0L,
      0L,
      0L,
      0L,
      0L,
      0L,
      0L,
      0L,
      0L,
      0L);
    var document = LanguageDataUtil.newTranslationDocument(domain, contentType, 0.005, sourceLanguage, targetLanguage, PUBLISHED, statistics);
    document = em.persistFlushFind(document);
    final LanguageDataCharacteristics characteristics = LanguageDataUtil.newCharacteristics(domain, contentType, 0.0020);
    document.addCharacteristics(characteristics);
    statistics = new TranslationDocumentStatistics(
      10L,
      5L,
      0L,
      0L,
      0L,
      0L,
      0L,
      0L,
      0L,
      0L,
      0L,
      0L,
      0L,
      0L,
      0L);
    document.addStatistics(statistics);
    document = em.persistFlushFind(document);
    for (int i = 0; i < 15; i++) {
      em.persistAndFlush(LanguageDataUtil.newTranslationDocument(domain, contentType, 0.005, sourceLanguage, targetLanguage, PUBLISHED,
        new TranslationDocumentStatistics(
          10L,
          5L,
          0L,
          0L,
          0L,
          0L,
          0L,
          0L,
          0L,
          0L,
          0L,
          0L,
          0L,
          0L,
          0L)));
    }

    final Page<net.taus.data.language.api.model.TranslationDocument> summary = documents.findPageSummaryPublishedByOwnerId(1L, 1, PageRequest.of(0, 10));
    assertThat(summary.getTotalElements(), is(equalTo(16L)));
    assertThat(summary.getTotalPages(), is(equalTo(2)));
    assertThat(summary.getNumberOfElements(), is(equalTo(10)));
    var finalDocument = document;
    assertThat(summary.stream().filter(d -> d.getUid().equals(finalDocument.getUid())).count(), is(equalTo(1L)));
  }

  @Test
  public void Given_published_documents_When_finding_for_documents_Then_published_documents_should_be_returned() {
    final int numberOfDocuments = 20;
    final int numberOfUnits = 10;
    final long ownerId = 1;
    final double pricePerWord = 0.015;
    var perDocumentStatistics = new TranslationDocumentStatistics();
    perDocumentStatistics.setTotalUnits(23L);
    perDocumentStatistics.setTotalUnitsOriginal(10L);
    perDocumentStatistics.setTotalUnitsReplica(10L);
    perDocumentStatistics.setTotalUnitsLowQuality(3L);
    perDocumentStatistics.setTotalSourceWordCount(20L);
    perDocumentStatistics.setTotalSourceOriginalWordCount(12L);
    perDocumentStatistics.setTotalSourceReplicaWordCount(10L);
    perDocumentStatistics.setTotalTargetWordCount(10L);
    perDocumentStatistics.setTotalTargetOriginalWordCount(10L);
    perDocumentStatistics.setTotalTargetReplicaWordCount(10L);
    final List<TranslationDocument> documentList = LanguageDataUtil.prepare(sourceLanguage,
      targetLanguage,
      numberOfDocuments,
      numberOfUnits,
      ownerId,
      pricePerWord,
      perDocumentStatistics,
      domains,
      contentTypes,
      em);
    documentList.forEach(d -> documents.publish(d));

    final List<net.taus.data.language.api.model.TranslationDocument> summary = documents.findListSummaryPublishedByOwnerId(ownerId, numberOfDocuments, numberOfUnits);
    final int actualNumberOfUnits = 5; // the actual number is the half because we never return the whole document in a sample.
    assertThat(format("Should contain %s documents", numberOfDocuments), summary.size(), is(numberOfDocuments));
    final net.taus.data.language.api.model.TranslationDocument document = summary.get(0);
    assertThat(format("Should contain %s units", actualNumberOfUnits), document.getUnits().size(), is(actualNumberOfUnits));
    final Optional<net.taus.data.language.api.model.TranslationDocumentStatistics> currentStatistics = document.getCurrentStatistics();
    assertTrue(currentStatistics.isPresent());
    assertThat(format("Should contain %s source words", 20), currentStatistics.get().getTotalSourceWordCount(), is(20L));
    assertThat(format("Should contain %s target words", 10), currentStatistics.get().getTotalTargetWordCount(), is(10L));
  }


}