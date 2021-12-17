package net.taus.data.marketplace.api.languagedata;

import net.taus.data.marketplace.api.WebIntegrationTest;
import net.taus.data.marketplace.api.utils.DataGenerator;
import net.taus.data.marketplace.model.languagedata.TranslationDocumentReview;
import net.taus.data.marketplace.model.purchase.Buyer;
import net.taus.data.marketplace.model.purchase.Purchase;
import net.taus.data.marketplace.model.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.templates.TemplateFormats;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;
import java.util.stream.IntStream;

import static net.taus.data.marketplace.model.languagedata.TranslationDocumentReview.Status.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Transactional
class TranslationDocumentControllerTest extends WebIntegrationTest {

  @Autowired
  private TestEntityManager em;
  @Autowired
  private Converter<UUID, String> converter;

  private MockMvc mockMvc;
  private User seller;
  private Buyer buyer;

  @BeforeEach
  public void setUp(final WebApplicationContext webApplicationContext,
                    final RestDocumentationContextProvider restDocumentation) {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
      .apply(documentationConfiguration(restDocumentation).snippets().withTemplateFormat(TemplateFormats.markdown()))
      .build();
    this.seller = em.persistAndFlush(DataGenerator.createSeller());
    this.buyer = em.persistAndFlush(DataGenerator.createBuyer());
  }

  @Test
  void getFeatured() {
  }

  @Test
  void listReviews() throws Exception {
    final UUID documentUid = UUID.randomUUID();
    final double score = 4.5;
    final String comment = "Very clean and good dataset.";
    IntStream.range(0, 2).forEach(n -> {
      final Purchase purchase = em.persistAndFlush(DataGenerator.createPurchase(documentUid, buyer, seller));
      purchase.getItems().forEach(i -> {
        final var review = new TranslationDocumentReview();
        review.setDocumentUid(documentUid);
        review.setScore(score);
        review.setComment(comment);
        review.setStatus(APPROVED);
        review.setReviewer(purchase.getBuyer());
        review.setPurchaseItem(i);
        review.setAnonymous(n == 1);
        em.persistAndFlush(review);
      });
    });

    var id = "documents/{documentUid}/reviews";
    this.mockMvc.perform(RestDocumentationRequestBuilders.get("/" + id, converter.convert(documentUid))
        .accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$[0].documentUid", is(converter.convert(documentUid))))
      .andExpect(jsonPath("$[0].reviewerFirstName", is(this.buyer.getFirstName())))
      .andExpect(jsonPath("$[0].reviewerLastName", is(this.buyer.getLastName())))
      .andExpect(jsonPath("$[0].score", is(score)))
      .andExpect(jsonPath("$[0].comment", is(comment)))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$[1].documentUid", is(converter.convert(documentUid))))
      .andExpect(jsonPath("$[1].reviewerFirstName", is("Anonymous")))
      .andExpect(jsonPath("$[1].reviewerLastName", is("")))
      .andExpect(jsonPath("$[1].score", is(score)))
      .andExpect(jsonPath("$[1].comment", is(comment)))
      .andDo(document(id,
        preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()),
        pathParameters(parameterWithName("documentUid").description("The document uid to fetch the reviews.")),
        responseFields(
          fieldWithPath("[].documentUid").description("The purchase item uid whose product will receive the review."),
          fieldWithPath("[].reviewerFirstName").description("The reviewer first name or 'Anonymous' if the reviewer " +
            "decided to remain anonymous."),
          fieldWithPath("[].reviewerLastName").description("The reviewer last name or empty if the reviewer " +
            "decided to remain anonymous."),
          fieldWithPath("[].score").description("The review score provide by the buyer."),
          fieldWithPath("[].comment").description("The review comment provided by the buyer."),
          fieldWithPath("[].createdAt").description("The date when the review was created, format YYYY-MM-DDTHH:mm:SS.MMM.")
        )
      ));
  }
}