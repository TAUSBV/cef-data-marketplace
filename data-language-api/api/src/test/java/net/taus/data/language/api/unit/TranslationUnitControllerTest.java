package net.taus.data.language.api.unit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import net.taus.data.language.api.WebIntegrationTest;
import net.taus.data.language.api.util.DataGenerator;
import net.taus.data.language.model.TranslationDocument;
import net.taus.data.language.model.meta.Language;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;

import static java.util.stream.Collectors.toList;
import static net.taus.data.language.api.util.Descriptors.*;
import static net.taus.data.language.model.TranslationUnit.Quality.HIGH;
import static net.taus.data.language.model.TranslationUnit.Quality.LOW;
import static net.taus.data.language.model.meta.LanguageDataCharacteristicsDomain.Type.PREDICTED;
import static org.hamcrest.Matchers.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.snippet.Attributes.attributes;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
class TranslationUnitControllerTest extends WebIntegrationTest {

  @Autowired
  private TestEntityManager em;
  @Autowired
  private ObjectMapper mapper;

  private Language sourceLanguage;
  private Language targetLanguage;
  private Faker faker;

  @BeforeEach
  void setUp() {
    this.faker = new Faker(Locale.UK);
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
  void save() throws Exception {
    TranslationDocument document = em.persistAndFlush(DataGenerator.newDocument(sourceLanguage, targetLanguage).generate());
    final List<TranslationUnitForm> formList = DataGenerator.unitsForDocument(document).quantity(5).generate().stream()
      .map(u -> {
        u.setSource(em.persistAndFlush(u.getSource()));
        u.setTarget(em.persistAndFlush(u.getTarget()));
        return em.persistAndFlush(u);
      })
      .map(unit -> {
        TranslationUnitForm form = new TranslationUnitForm();
        form.setId(unit.getId());
        TranslationUnitForm.TranslationVariantForm sourceForm = new TranslationUnitForm.TranslationVariantForm();
        sourceForm.setUid(unit.getSource().getUid());
        form.setSource(sourceForm);
        TranslationUnitForm.TranslationVariantForm targetForm = new TranslationUnitForm.TranslationVariantForm();
        targetForm.setUid(unit.getTarget().getUid());
        form.setTarget(targetForm);
        final boolean even = unit.getSequence() % 2 == 0;
        form.setQuality(even ? HIGH.name() : LOW.name());
        form.setQualityFlags(even ? "OK" : "dedupfilter");
        if (even) {
          form.setDomains(List.of(
            new TranslationUnitForm.TranslationUniDomain(1L, faker.number().randomDouble(3, 0, 1), PREDICTED.name()),
            new TranslationUnitForm.TranslationUniDomain(2L, faker.number().randomDouble(3, 0, 1), PREDICTED.name()),
            new TranslationUnitForm.TranslationUniDomain(3L, faker.number().randomDouble(3, 0, 1), PREDICTED.name())
          ));
          form.getSource().setAnnotations(List.of(
            new TranslationUnitForm.TranslationVariantForm.DataAnnotationForm(null, 1L, 10, 48),
            new TranslationUnitForm.TranslationVariantForm.DataAnnotationForm(null, 2L, 100, 112)
          ));
          form.getTarget().setAnnotations(List.of(
            new TranslationUnitForm.TranslationVariantForm.DataAnnotationForm(null, 4L, 88, 48),
            new TranslationUnitForm.TranslationVariantForm.DataAnnotationForm(null, 5L, 100, 112)
          ));
        }
        return form;
      }).collect(toList());
    this.mockMvc.perform(
        post("/units")
          .content(mapper.writeValueAsBytes(formList))
          .contentType(APPLICATION_JSON)
          .accept(APPLICATION_JSON)
      )
      .andExpect(status().isOk())
      .andDo(print())
      .andExpect(jsonPath("$[0].id", is(notNullValue())))
      .andExpect(jsonPath("$[0].ownerId", is(document.getOwnerId()), Long.class))
      .andExpect(jsonPath("$[0].documentUid", is(document.getUid().toString())))
      .andExpect(jsonPath("$[0].hash", is(notNullValue())))
      .andExpect(jsonPath("$[0].sequence", is(1)))
      .andExpect(jsonPath("$[0].source", is(notNullValue())))
      .andExpect(jsonPath("$[0].source.uid", is(notNullValue())))
      .andExpect(jsonPath("$[0].source.value", is(notNullValue())))
      .andExpect(jsonPath("$[0].source.wordCount", is(notNullValue())))
      .andExpect(jsonPath("$[0].source.hash", is(notNullValue())))
      .andExpect(jsonPath("$[0].source.language", is(notNullValue())))
      .andExpect(jsonPath("$[0].source.language.id", is(sourceLanguage.getId()), Long.class))
      .andExpect(jsonPath("$[0].source.language.tag", is(sourceLanguage.getTag())))
      .andExpect(jsonPath("$[0].source.language.name", is(sourceLanguage.getName())))
      .andExpect(jsonPath("$[0].source.language.displayName", is(sourceLanguage.getDisplayName())))
      .andExpect(jsonPath("$[0].source.language.country", is(sourceLanguage.getCountry())))
      .andExpect(jsonPath("$[0].source.language.displayCountry", is(sourceLanguage.getDisplayCountry())))
      .andExpect(jsonPath("$[0].source.language.enabledPii", is(sourceLanguage.isEnabledPii())))
      .andExpect(jsonPath("$[0].source.language.nameAndCountry", is(String.format("%s (%s)", sourceLanguage.getDisplayName(), sourceLanguage.getDisplayCountry()))))
      .andExpect(jsonPath("$[0].target", is(notNullValue())))
      .andExpect(jsonPath("$[0].target.uid", is(notNullValue())))
      .andExpect(jsonPath("$[0].target.value", is(notNullValue())))
      .andExpect(jsonPath("$[0].target.wordCount", is(notNullValue())))
      .andExpect(jsonPath("$[0].target.hash", is(notNullValue())))
      .andExpect(jsonPath("$[0].target.language", is(notNullValue())))
      .andExpect(jsonPath("$[0].target.language.id", is(targetLanguage.getId()), Long.class))
      .andExpect(jsonPath("$[0].target.language.tag", is(targetLanguage.getTag())))
      .andExpect(jsonPath("$[0].target.language.name", is(targetLanguage.getName())))
      .andExpect(jsonPath("$[0].target.language.displayName", is(targetLanguage.getDisplayName())))
      .andExpect(jsonPath("$[0].target.language.country", is(targetLanguage.getCountry())))
      .andExpect(jsonPath("$[0].target.language.displayCountry", is(targetLanguage.getDisplayCountry())))
      .andExpect(jsonPath("$[0].target.language.enabledPii", is(targetLanguage.isEnabledPii())))
      .andExpect(jsonPath("$[0].target.language.nameAndCountry", is(String.format("%s (%s)", targetLanguage.getDisplayName(), targetLanguage.getDisplayCountry()))))
      .andExpect(jsonPath("$[0].domain.id", is(nullValue())))
      .andExpect(jsonPath("$[0].domain.description", is(nullValue())))
      .andExpect(jsonPath("$[0].contentType.id", is(nullValue())))
      .andExpect(jsonPath("$[0].contentType.description", is(nullValue())))
      .andDo(document("units/{method-name}",
        requestFields(
          attributes(key("title").value("Fields accepted by this endpoint.")),
          fieldWithPath("[]").description("Unit form list")
        )
          .andWithPrefix("[].", unitForm())
          .andWithPrefix("[].domains[].", domain())
          .andWithPrefix("[].source.", variantForm())
          .andWithPrefix("[].source.annotations[].", variantAnnotationForm())
          .andWithPrefix("[].target.", variantForm())
          .andWithPrefix("[].target.annotations[].", variantAnnotationForm())
          .and(
            fieldWithPath("[].domains[]").description("The list of domains that the unit fits in."),
            fieldWithPath("[].source.annotations[]").description("The annotations to be added to the unit variant source."),
            fieldWithPath("[].target.annotations[]").description("The annotations to be added to the unit variant target.")
          ),
        responseFields(
          attributes(key("title").value("This endpoints returns a list of units..")),
          fieldWithPath("[]").description("Units")
        )
          .andWithPrefix("[].", unit())
          .andWithPrefix("[].domain.", domain())
          .andWithPrefix("[].contentType.", contentType())
          .andWithPrefix("[].source.", variant())
          .andWithPrefix("[].source.language.", language())
          .andWithPrefix("[].target.", variant())
          .andWithPrefix("[].target.language.", language())
      ));
  }

  @Test
  void findUnitsByLanguageDataParams() {

  }

  @Test
  void findUnitsByIds() {
  }

}