package net.taus.data.language.model;

import com.querydsl.sql.SQLQueryFactory;
import net.taus.data.language.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import static net.taus.data.language.model.TranslationDocument.Status.PUBLISHED;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
class TranslationDocumentUnitSampleGeneratorTest extends IntegrationTest {

  @Autowired
  private SQLQueryFactory queryFactory;

  @Test
  @Rollback(false)
  public void testGenerate() {
    final TranslationDocumentUnitSampleGenerator generator = new TranslationDocumentUnitSampleGenerator(queryFactory);
    final TranslationDocument document = new TranslationDocument();
    document.setId(2L);
    document.setStatus(PUBLISHED);
    generator.generate(document, 100);
  }

}