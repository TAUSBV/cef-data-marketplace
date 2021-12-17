package net.taus.data.language.model.search;

import net.taus.data.language.IntegrationTest;
import net.taus.data.language.api.model.LanguageDataParams;
import net.taus.data.language.api.model.TranslationUnitUnicity;
import net.taus.data.language.api.model.search.*;
import net.taus.data.language.model.LanguageDataUtil;
import net.taus.data.language.model.TranslationDocument;
import net.taus.data.language.model.TranslationDocumentRepository;
import net.taus.data.language.model.TranslationDocumentStatistics;
import net.taus.data.language.model.meta.ContentType;
import net.taus.data.language.model.meta.Domain;
import net.taus.data.language.model.meta.Language;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.math.RoundingMode.HALF_EVEN;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Transactional
class LanguageDataSearcherTest extends IntegrationTest {

  @Autowired
  private TestEntityManager em;

  @Autowired
  private LanguageDataSearcher searcher;
  @Autowired
  private TranslationDocumentRepository documents;

  private List<Domain> domains;
  private List<ContentType> contentTypes;
  private Language sourceLanguage;
  private Language targetLanguage;

  @BeforeEach
  public void setUp() {
    domains = em.getEntityManager().createQuery("select d from Domain d where d.origin = 'MARKETPLACE'", Domain.class).getResultList();
    contentTypes = em.getEntityManager().createQuery("select c from ContentType c where c.origin = 'MARKETPLACE'", ContentType.class).getResultList();
    sourceLanguage = em.getEntityManager().createQuery("select o from Language o where o.name = 'en' and o.country = 'GB'", Language.class).setMaxResults(1).getSingleResult();
    targetLanguage = em.getEntityManager().createQuery("select o from Language o where o.name = 'es' and o.country = 'ES'", Language.class).setMaxResults(1).getSingleResult();
  }

  @Test
  public void Given_published_documents_When_a_search_is_performed_with_parameters_that_matches_the_published_documents_Then_should_be_returned_the_published_documents() {
    List<Domain> newDomains = new ArrayList<>(domains);
    Collections.shuffle(newDomains);
    List<ContentType> newContentTypes = new ArrayList<>(contentTypes);
    Collections.shuffle(contentTypes);
    final long numberOfDocuments = 10;
    final long numberOfUnits = 5;
    final long ownerId = 1;
    final double pricePerWord = 0.015;
    var perDocumentStatistics = new TranslationDocumentStatistics();
    perDocumentStatistics.setTotalUnits(numberOfUnits);
    perDocumentStatistics.setTotalUnitsOriginal(numberOfUnits);
    perDocumentStatistics.setTotalSourceWordCount(10L);
    perDocumentStatistics.setTotalSourceOriginalWordCount(10L);
    perDocumentStatistics.setTotalTargetWordCount(5L);
    perDocumentStatistics.setTotalTargetOriginalWordCount(5L);
    perDocumentStatistics.setComment("Recalculation");

    final List<TranslationDocument> documentList = LanguageDataUtil.prepare(sourceLanguage, targetLanguage,
      numberOfDocuments, numberOfUnits, ownerId, pricePerWord, perDocumentStatistics, newDomains.subList(0, 3),
      newContentTypes.subList(0, 3), em);
    documentList.forEach(d -> documents.publish(d));
    documents.flush();

    final LanguageDataParams params = LanguageDataParams.builder()
      .withSourceLanguageId(sourceLanguage.getId())
      .withTargetLanguageId(targetLanguage.getId())
      .withUnitUnicity(TranslationUnitUnicity.ORIGINAL_AND_REPLICA)
      .withReferenceDateTime(LocalDateTime.now())
      .build();
    final LanguageDataSearchDocumentResult documents = searcher.documents(params, PageRequest.of(0, 20));
    final LanguageDataSearchResult.LanguageDataFilters filters = documents.getFilters();
    final LanguageDataSearchResult.LanguageDataStatistics statistics = documents.getStatistics();
    final Page<TranslationDocumentSample> sample = documents.getSample();

    assertThat(documents, is(not(nullValue())));
    assertThat(filters.getOwnerIds().size(), is(1));
    final Optional<Long> ownerIdOptional = filters.getOwnerIds().stream().findFirst();
    assertTrue(ownerIdOptional.isPresent());
    assertThat(ownerIdOptional.get(), is(ownerId));
    assertThat(filters.getDomains().size(), is(1));
    assertTrue(newDomains.stream().anyMatch(d -> filters.getDomains().stream().anyMatch(df -> df.getId().equals(d.getId()))));
    assertThat(filters.getContentTypes().size(), is(1));
    assertTrue(newContentTypes.stream().anyMatch(c -> filters.getContentTypes().stream().anyMatch(cf -> cf.getId().equals(c.getId()))));
    assertThat(filters.getPrices().size(), is(1));
    final Optional<Double> priceOptional = filters.getPrices().stream().findFirst();
    assertTrue(priceOptional.isPresent());
    assertThat(priceOptional.get(), is(pricePerWord));
    assertThat(statistics.getDocuments(), is(numberOfDocuments));
    assertThat(statistics.getSellersTotal(), is(1L));
    var unitsTotal = numberOfDocuments * (perDocumentStatistics.getTotalUnitsOriginal() + perDocumentStatistics.getTotalUnitsReplica());
    var sourceWordCountTotal = numberOfDocuments * (perDocumentStatistics.getTotalSourceOriginalWordCount() + perDocumentStatistics.getTotalSourceReplicaWordCount());
    var targetWordCountTotal = numberOfDocuments * (perDocumentStatistics.getTotalTargetOriginalWordCount() + perDocumentStatistics.getTotalTargetReplicaWordCount());
    var totalPrice = BigDecimal.valueOf(pricePerWord * sourceWordCountTotal).setScale(2, HALF_EVEN).doubleValue();
    assertThat(statistics.getSourceWordCountTotal(), is(sourceWordCountTotal));
    assertThat(statistics.getTargetWordCountTotal(), is(targetWordCountTotal));
    assertThat(statistics.getUnitsTotal(), is(unitsTotal));
    assertThat(BigDecimal.valueOf(statistics.getTotalPrice()).setScale(2, HALF_EVEN).doubleValue(), is(totalPrice));
    assertThat(sample.getTotalElements(), is(numberOfDocuments));
    assertThat(sample.getContent().get(0).getUnits().size(), is(3));
  }

  @Test
  public void Given_published_documents_When_a_search_for_units_is_performed_with_parameters_that_matches_units_of_published_documents_Then_should_be_returned_the_units() {
    List<Domain> newDomains = new ArrayList<>(domains);
    Collections.shuffle(newDomains);
    List<ContentType> newContentTypes = new ArrayList<>(contentTypes);
    Collections.shuffle(contentTypes);
    final long numberOfDocuments = 10;
    final long numberOfUnits = 5;
    final long ownerId = 1;
    final double pricePerWord = 0.015;
    var perDocumentStatistics = new TranslationDocumentStatistics();
    perDocumentStatistics.setTotalUnits(numberOfUnits);
    perDocumentStatistics.setTotalUnitsOriginal(numberOfUnits);
    perDocumentStatistics.setTotalSourceWordCount(10L);
    perDocumentStatistics.setTotalSourceOriginalWordCount(10L);
    perDocumentStatistics.setTotalTargetWordCount(5L);
    perDocumentStatistics.setTotalTargetOriginalWordCount(5L);
    perDocumentStatistics.setComment("Recalculation");

    final List<TranslationDocument> documentList = LanguageDataUtil.prepare(sourceLanguage, targetLanguage,
      numberOfDocuments, numberOfUnits, ownerId, pricePerWord, perDocumentStatistics,
      newDomains.subList(0, 3), newContentTypes.subList(0, 3), em);
    documentList.forEach(d -> documents.publish(d));

    final LanguageDataParams params = new LanguageDataParams(sourceLanguage.getId(), targetLanguage.getId());
    final LanguageDataSearchUnitResult documents = searcher.units(params);
    final LanguageDataSearchResult.LanguageDataFilters filters = documents.getFilters();
    final LanguageDataSearchResult.LanguageDataStatistics statistics = documents.getStatistics();
    final Page<TranslationUnitSample> sample = documents.getSample();

    assertThat(documents, is(not(nullValue())));
    assertThat(filters.getOwnerIds().size(), is(1));
    final Optional<Long> ownerOptional = filters.getOwnerIds().stream().findFirst();
    assertTrue(ownerOptional.isPresent());
    assertThat(ownerOptional.get(), is(ownerId));
    assertThat(filters.getDomains().size(), is(1));
    assertTrue(newDomains.stream().anyMatch(d -> filters.getDomains().stream().anyMatch(df -> df.getId().equals(d.getId()))));
    assertThat(filters.getContentTypes().size(), is(1));
    assertTrue(newContentTypes.stream().anyMatch(c -> filters.getContentTypes().stream().anyMatch(cf -> cf.getId().equals(c.getId()))));
    assertThat(filters.getPrices().size(), is(1));
    final Optional<Double> priceOptional = filters.getPrices().stream().findFirst();
    assertTrue(priceOptional.isPresent());
    assertThat(priceOptional.get(), is(pricePerWord));

    assertThat(statistics.getDocuments(), is(numberOfDocuments));
    assertThat(statistics.getSellersTotal(), is(1L));
    var unitsTotal = numberOfDocuments * perDocumentStatistics.getTotalUnitsOriginal();
    var sourceWordCountTotal = numberOfDocuments * perDocumentStatistics.getTotalSourceOriginalWordCount();
    var targetWordCountTotal = numberOfDocuments * perDocumentStatistics.getTotalTargetOriginalWordCount();
    var totalPrice = BigDecimal.valueOf(pricePerWord * sourceWordCountTotal).setScale(2, HALF_EVEN).doubleValue();
    assertThat(statistics.getSourceWordCountTotal(), is(sourceWordCountTotal));
    assertThat(statistics.getTargetWordCountTotal(), is(targetWordCountTotal));
    assertThat(statistics.getUnitsTotal(), is(unitsTotal));
    assertThat(BigDecimal.valueOf(statistics.getTotalPrice()).setScale(2, HALF_EVEN).doubleValue(), is(totalPrice));

    // 3 because is only 1 owner and each document has 5 units
    // and we return half of the units when the intended amount of units
    // is high than the document number of units and the sample is rounded up
    assertThat(sample.getTotalElements(), is(3L));
  }

}