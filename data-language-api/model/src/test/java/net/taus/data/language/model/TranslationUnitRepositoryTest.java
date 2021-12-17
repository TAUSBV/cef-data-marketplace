package net.taus.data.language.model;

import net.taus.data.language.IntegrationTest;
import net.taus.data.language.model.meta.ContentType;
import net.taus.data.language.model.meta.Domain;
import net.taus.data.language.model.meta.Language;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@Transactional
class TranslationUnitRepositoryTest extends IntegrationTest {

  @Autowired
  private TestEntityManager em;

  @Autowired
  private TranslationUnitRepository translationUnitRepository;

  private Domain domain;
  private ContentType contentType;
  private Language sourceLanguage;
  private Language targetLanguage;

  @BeforeEach
  public void setUp() {
    domain = em.getEntityManager().createQuery("select d from Domain d", Domain.class).setMaxResults(1).getSingleResult();
    contentType = em.getEntityManager().createQuery("select c from ContentType c", ContentType.class).setMaxResults(1).getSingleResult();
    sourceLanguage = em.getEntityManager().createQuery("select o from Language o where o.name = 'en' and o.country = 'GB'", Language.class).setMaxResults(1).getSingleResult();
    targetLanguage = em.getEntityManager().createQuery("select o from Language o where o.name = 'es' and o.country = 'ES'", Language.class).setMaxResults(1).getSingleResult();
  }

  @Test
  public void shouldCreateNewTranslationUnit() {
    var document = LanguageDataUtil.newTranslationDocument(domain, contentType, 0.001, sourceLanguage, targetLanguage, TranslationDocument.Status.NEW,
      new TranslationDocumentStatistics(
        4L,
        10L,
        0L,
        0L,
        0L,
        12L,
        10L,
        0L,
        0L,
        0L,
        0L,
        0L,
        0L,
        0L,
        0L));
    document = em.persistFlushFind(document);
    var source = LanguageDataUtil.newTranslationVariant(sourceLanguage, "Thank you", 2, 12345);
    var target = LanguageDataUtil.newTranslationVariant(targetLanguage, "Gracias", 1, 98765);
    var unit = LanguageDataUtil.newTranslationUnit(document, source, target, 1234598765, 1);
    unit = translationUnitRepository.saveOriginal(unit);
    assertThat(unit.getId(), is(notNullValue()));
    assertThat(unit.isVirtual(), is(false));
    assertThat(unit.getReference(), is(nullValue()));
  }

  @Test
  public void shouldMarkReferenceToTheOriginalTranslationUnitWhenTranslationUnitExistsInTheSameDocument() {
    var document = LanguageDataUtil.newTranslationDocument(domain, contentType, 0.0001, sourceLanguage, targetLanguage, TranslationDocument.Status.NEW,
      new TranslationDocumentStatistics(
        4L,
        10L,
        0L,
        0L,
        0L,
        12L,
        10L,
        0L,
        0L,
        0L,
        0L,
        0L,
        0L,
        0L,
        0L));
    document = em.persistFlushFind(document);
    var source = LanguageDataUtil.newTranslationVariant(sourceLanguage, "Thank you", 2, 12345);
    var target = LanguageDataUtil.newTranslationVariant(targetLanguage, "Gracias", 1, 98765);
    var original = LanguageDataUtil.newTranslationUnit(document, source, target, 1234598765, 1);
    original = translationUnitRepository.saveOriginal(original);

    var dupSource = LanguageDataUtil.newTranslationVariant(sourceLanguage, "Thank you", 2, 12345);
    var dupTarget = LanguageDataUtil.newTranslationVariant(targetLanguage, "Gracias", 1, 98765);
    var duplicate = LanguageDataUtil.newTranslationUnit(document, dupSource, dupTarget, 1234598765, 2);
    duplicate = translationUnitRepository.saveDuplicate(duplicate);

    assertThat(duplicate.getId(), is(notNullValue()));
    assertThat(duplicate.isVirtual(), is(false));
    assertThat(duplicate.getReference(), is(equalTo(original)));
    assertThat(duplicate.getSource(), is(equalTo(source)));
    assertThat(duplicate.getTarget(), is(equalTo(target)));
  }

  @Test
  public void shouldNotMarkReferenceToTheOriginalTranslationUnitWhenTranslationUnitExistsInADifferentDocument() {
    var document = LanguageDataUtil.newTranslationDocument(domain, contentType, 0.0001, sourceLanguage, targetLanguage, TranslationDocument.Status.NEW,
      new TranslationDocumentStatistics(
        4L,
        10L,
        0L,
        0L,
        0L,
        12L,
        10L,
        0L,
        0L,
        0L,
        0L,
        0L,
        0L,
        0L,
        0L));
    document = em.persistFlushFind(document);
    var source = LanguageDataUtil.newTranslationVariant(sourceLanguage, "Thank you", 2, 12345);
    var target = LanguageDataUtil.newTranslationVariant(targetLanguage, "Gracias", 1, 98765);
    var original = LanguageDataUtil.newTranslationUnit(document, source, target, 1234598765, 1);
    original = translationUnitRepository.saveOriginal(original);

    var otherDocument = LanguageDataUtil.newTranslationDocument(domain, contentType, 0.0002, sourceLanguage, targetLanguage, TranslationDocument.Status.NEW,
      new TranslationDocumentStatistics(
        4L,
        10L,
        0L,
        0L,
        0L,
        12L,
        10L,
        0L,
        0L,
        0L,
        0L,
        0L,
        0L,
        0L,
        0L));
    otherDocument = em.persistFlushFind(otherDocument);
    var dupSource = LanguageDataUtil.newTranslationVariant(sourceLanguage, "Thank you", 2, 12345);
    var dupTarget = LanguageDataUtil.newTranslationVariant(targetLanguage, "Gracias", 1, 98765);
    var duplicate = LanguageDataUtil.newTranslationUnit(otherDocument, dupSource, dupTarget, 1234598765, 2);
    duplicate = translationUnitRepository.saveDuplicate(duplicate);
    duplicate = em.refresh(duplicate);

    assertThat(duplicate.getId(), is(notNullValue()));
    assertThat(duplicate.isVirtual(), is(false));
    assertThat(duplicate.getReference(), is(not(equalTo(original))));
    assertThat(duplicate.getSource(), is(equalTo(source)));
    assertThat(duplicate.getTarget(), is(equalTo(target)));
  }

  @Test
  public void shouldMarkReferenceToTheOriginalTranslationUnitWhenTranslationUnitExistsInADifferentDocumentThatIsPublished() {
    var document = LanguageDataUtil.newTranslationDocument(domain, contentType, 0.0002, sourceLanguage, targetLanguage, TranslationDocument.Status.PUBLISHED,
      new TranslationDocumentStatistics(
        4L,
        10L,
        0L,
        0L,
        0L,
        12L,
        10L,
        0L,
        0L,
        0L,
        0L,
        0L,
        0L,
        0L,
        0L));
    document = em.persistFlushFind(document);
    var source = LanguageDataUtil.newTranslationVariant(sourceLanguage, "Thank you", 2, 12345);
    var target = LanguageDataUtil.newTranslationVariant(targetLanguage, "Gracias", 1, 98765);
    var original = LanguageDataUtil.newTranslationUnit(document, source, target, 1234598765, 1);
    original.setStatus(document.getStatus());
    original = translationUnitRepository.saveOriginal(original);

    var otherDocument = LanguageDataUtil.newTranslationDocument(domain, contentType, 0.0002, sourceLanguage, targetLanguage, TranslationDocument.Status.NEW,
      new TranslationDocumentStatistics(
        4L,
        10L,
        0L,
        0L,
        0L,
        12L,
        10L,
        0L,
        0L,
        0L,
        0L,
        0L,
        0L,
        0L,
        0L));
    otherDocument = em.persistFlushFind(otherDocument);
    var dupSource = LanguageDataUtil.newTranslationVariant(sourceLanguage, "Thank you", 2, 12345);
    var dupTarget = LanguageDataUtil.newTranslationVariant(targetLanguage, "Gracias", 1, 98765);
    var duplicate = LanguageDataUtil.newTranslationUnit(otherDocument, dupSource, dupTarget, 1234598765, 2);
    duplicate = translationUnitRepository.saveDuplicate(duplicate);

    assertThat(duplicate.getId(), is(notNullValue()));
    assertThat(duplicate.isVirtual(), is(false));
    assertThat(duplicate.getReference(), is(equalTo(original)));
    assertThat(duplicate.getSource(), is(equalTo(source)));
    assertThat(duplicate.getTarget(), is(equalTo(target)));
  }

}
