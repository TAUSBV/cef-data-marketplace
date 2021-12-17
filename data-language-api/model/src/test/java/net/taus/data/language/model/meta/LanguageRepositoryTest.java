package net.taus.data.language.model.meta;

import net.taus.data.language.IntegrationTest;
import net.taus.data.language.api.model.CompositeLanguageSourceTargetCount;
import net.taus.data.language.model.TranslationDocument;
import net.taus.data.language.model.TranslationDocumentStatistics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import static net.taus.data.language.model.TranslationDocument.Status.PUBLISHED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.atIndex;

@Transactional
class LanguageRepositoryTest extends IntegrationTest {

  @Autowired
  private LanguageRepository languageRepository;

  @Autowired
  private TestEntityManager em;
  private Language english;
  private Language chinese;
  private Language portuguese;
  private Language spanish;

  @BeforeEach
  public void setUp() {
    this.english = em.getEntityManager().createNamedQuery(Language.FIND_BY_TAG, Language.class).setParameter("tag", "en-US").setMaxResults(1).getSingleResult();
    this.chinese = em.getEntityManager().createNamedQuery(Language.FIND_BY_TAG, Language.class).setParameter("tag", "zh-CN").setMaxResults(1).getSingleResult();
    this.portuguese = em.getEntityManager().createNamedQuery(Language.FIND_BY_TAG, Language.class).setParameter("tag", "pt-BR").setMaxResults(1).getSingleResult();
    this.spanish = em.getEntityManager().createNamedQuery(Language.FIND_BY_TAG, Language.class).setParameter("tag", "es-ES").setMaxResults(1).getSingleResult();
    var document1 = new TranslationDocument("test.tmx");
    var statistics = new TranslationDocumentStatistics(
      10L,
      10L,
      0L,
      0L,
      0L,
      100L,
      100L,
      0L,
      0L,
      0L,
      100L,
      0L,
      0L,
      0L,
      0L);
    LanguageDataCharacteristics characteristics = new LanguageDataCharacteristics();
    document1.addStatistics(statistics);
    document1.addCharacteristics(characteristics);
    document1.setSourceLanguage(english);
    document1.setTargetLanguage(chinese);
    document1.setStatus(PUBLISHED);
    em.persistAndFlush(document1);

    var document2 = new TranslationDocument("test2.tmx");
    statistics = new TranslationDocumentStatistics(
      10L,
      10L,
      0L,
      0L,
      0L,
      100L,
      100L,
      0L,
      0L,
      0L,
      100L,
      0L,
      0L,
      0L,
      0L);
    characteristics = new LanguageDataCharacteristics();
    document2.addStatistics(statistics);
    document2.addCharacteristics(characteristics);
    document2.setSourceLanguage(portuguese);
    document2.setTargetLanguage(spanish);
    document2.setStatus(PUBLISHED);
    em.persistAndFlush(document2);

  }

  @Test
  public void testLanguages() {
    final CompositeLanguageSourceTargetCount groups = languageRepository.findSourceTargetGroups();
    final var counts = new ArrayList<>(groups.getSources());
    assertThat(counts).isNotEmpty();
    assertThat(counts).satisfies(c -> {
      assertThat(c.getSource().getId()).isEqualTo(english.getId());
      assertThat(c.getTargets()).satisfies(t -> {
        assertThat(t.getCount()).isEqualTo(10L);
        assertThat(t.getLanguage().getId()).isEqualTo(chinese.getId());
      }, atIndex(0));
    }, atIndex(0));
    assertThat(counts).satisfies(c -> {
      assertThat(c.getSource().getId()).isEqualTo(portuguese.getId());
      assertThat(c.getTargets()).satisfies(t -> {
        assertThat(t.getCount()).isEqualTo(10L);
        assertThat(t.getLanguage().getId()).isEqualTo(spanish.getId());
      }, atIndex(0));
    }, atIndex(1));
  }

}