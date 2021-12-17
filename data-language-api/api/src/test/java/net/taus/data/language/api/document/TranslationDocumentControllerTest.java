package net.taus.data.language.api.document;

import net.taus.data.language.api.WebIntegrationTest;
import net.taus.data.language.api.util.DataGenerator;
import net.taus.data.language.model.TranslationDocument;
import net.taus.data.language.model.meta.Language;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.http.MediaType;

import javax.transaction.Transactional;

import static net.taus.data.language.api.util.Descriptors.*;
import static net.taus.data.language.model.TranslationDocument.Status.PUBLISHED;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.snippet.Attributes.attributes;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
class TranslationDocumentControllerTest extends WebIntegrationTest {

  @Autowired
  private TestEntityManager em;
  private Language sourceLanguage;
  private Language targetLanguage;


  @BeforeEach
  void setUp() {
    final String qlString = "select o from Language o where o.name = :language and o.country = :country";
    this.sourceLanguage = em.getEntityManager()
      .createQuery(qlString, Language.class)
      .setParameter("language", "en")
      .setParameter("country", "GB")
      .setMaxResults(1).getSingleResult();
    this.targetLanguage = em.getEntityManager()
      .createQuery(qlString, Language.class)
      .setParameter("language", "es")
      .setParameter("country", "ES")
      .setMaxResults(1).getSingleResult();
  }


  @Test
  public void featured() throws Exception {
    final TranslationDocument document = em.persistAndFlush(DataGenerator.newDocument(sourceLanguage, targetLanguage)
      .addRandomStatistics()
      .withStatus(PUBLISHED)
      .withOwnerId(1L)
      .generate());
    this.mockMvc.perform(
        get("/documents/featured").accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$[*].ownerId", is(notNullValue())))
      .andExpect(jsonPath("$[*].uid", is(notNullValue())))
      .andExpect(jsonPath("$[*].name", is(notNullValue())))
      .andDo(print())
      .andDo(
        document("documents/{method-name}",
          responseFields(
            attributes(key("title").value("Fields returned by this endpoint.")),
            fieldWithPath("[]").description("Featured documents")
          )
            .andWithPrefix("[].", translationDocument())
            .andWithPrefix("[].domain.", domain())
            .andWithPrefix("[].contentType.", contentType())
            .andWithPrefix("[].sourceLanguage.", language())
            .andWithPrefix("[].targetLanguage.", language())
            .andWithPrefix("[].statisticsList.[].", documentStatistics())
            .andWithPrefix("[].currentStatistics.", documentStatistics())
            .andWithPrefix("[].units.[].", unit())
            .andWithPrefix("[].units.[].source.", variant())
            .andWithPrefix("[].units.[].source.language.", language())
            .andWithPrefix("[].units.[].target.", variant())
            .andWithPrefix("[].units.[].target.language.", language())
            .andWithPrefix("[].units.[].domain.", domain())
            .andWithPrefix("[].units.[].contentType.", contentType())
            .and(
              fieldWithPath("[].annotations.").description("The document annotations").optional(),
              fieldWithPath("[].units.").description("The document units").optional(),
              fieldWithPath("[].statisticsList.[].").description("The list of calculated document statistics.").optional(),
              fieldWithPath("[].currentStatistics.").description("The latest calculated document statistics.").optional()
            )
        )
      );
  }

  @Test
  public void getByUid() throws Exception {
    final TranslationDocument document = em.persistAndFlush(DataGenerator.newDocument(sourceLanguage, targetLanguage)
      .addRandomStatistics()
      .withStatus(PUBLISHED)
      .withOwnerId(1L)
      .generate());
    this.mockMvc.perform(
        get("/documents/{documentUid}", document.getUid())
          .accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("ownerId", is(document.getOwnerId().intValue())))
      .andExpect(jsonPath("uid", is(document.getUid().toString())))
      .andExpect(jsonPath("name", is(document.getName())))
      .andExpect(jsonPath("size", is((int) document.getSize())))
      .andDo(print())
      .andDo(
        document("documents/{method-name}",
          pathParameters(parameterWithName("documentUid").description("The document universal identifier which data should be retrieved.")),
          responseFields(
            attributes(key("title").value("Fields returned by this endpoint.")),
            translationDocument()
          )
            .andWithPrefix(".domain.", domain())
            .andWithPrefix(".contentType.", contentType())
            .andWithPrefix(".sourceLanguage.", language())
            .andWithPrefix(".targetLanguage.", language())
            .andWithPrefix(".statisticsList.[].", documentStatistics())
            .andWithPrefix(".currentStatistics.", documentStatistics())
            .andWithPrefix(".units.[].", unit())
            .andWithPrefix(".units.[].source.", variant())
            .andWithPrefix(".units.[].source.language.", language())
            .andWithPrefix(".units.[].target.", variant())
            .andWithPrefix(".units.[].target.language.", language())
            .andWithPrefix(".units.[].domain.", domain())
            .andWithPrefix(".units.[].contentType.", contentType())
            .and(
              fieldWithPath(".annotations.").description("The document annotations").optional(),
              fieldWithPath(".units[].").description("The document units").optional(),
              fieldWithPath(".statisticsList.[].").description("The list of calculated document statistics, see `currentStatistics`.").optional()
            )
        )
      );
  }

  @Test
  public void ownerDocuments() throws Exception {
    final TranslationDocument document = em.persistAndFlush(DataGenerator.newDocument(sourceLanguage, targetLanguage)
      .addRandomStatistics()
      .withStatus(PUBLISHED)
      .withOwnerId(1L)
      .generate());
    this.mockMvc.perform(
        get("/documents/owners/{ownerId}", document.getOwnerId())
          .accept(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("content.[0].uid", is(notNullValue())))
      .andExpect(status().isOk())
      .andDo(print())
      .andDo(document("documents/{method-name}"));
  }

}