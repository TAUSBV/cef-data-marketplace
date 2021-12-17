package net.taus.data.marketplace.api.purchase.controllers;

import net.taus.data.marketplace.api.WebIntegrationTest;
import net.taus.data.marketplace.api.utils.DataGenerator;
import net.taus.data.marketplace.model.languagedata.TranslationDocumentReview;
import net.taus.data.marketplace.model.purchase.Buyer;
import net.taus.data.marketplace.model.purchase.PurchaseItem;
import net.taus.data.marketplace.model.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.templates.TemplateFormats;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.Locale;
import java.util.UUID;

import static net.taus.data.marketplace.model.languagedata.TranslationDocumentReview.Status.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.oneOf;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
class PurchaseItemControllerTest extends WebIntegrationTest {

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
  void createKnownReview() throws Exception {
    var id = "purchases/items/{purchaseItemUid}/reviews";
    final UUID documentUid = UUID.randomUUID();
    final PurchaseItem item = em.persistAndFlush(DataGenerator.createPurchase(documentUid, buyer, seller)).getItems().get(0);
    final double score = 4.5;
    final String comment = "Very clean and good dataset.";
    final boolean anonymous = false;
    this.mockMvc.perform(
        post("/" + id, converter.convert(item.getUid()))
          .content(String.format("score=%s&comment=%s&anonymous=%s", score, comment, anonymous))
          .contentType(MediaType.APPLICATION_FORM_URLENCODED)
          .accept(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.purchaseItemUid", is(converter.convert(item.getUid()))))
      .andExpect(jsonPath("$.score", is(score)))
      .andExpect(jsonPath("$.comment", is(comment)))
      .andExpect(jsonPath("$.status", oneOf(APPROVED.name(), PENDING.name(), REJECTED.name())))
      .andExpect(jsonPath("$.anonymous", is(anonymous)))
      .andDo(document("purchases/items/{method-name}",
        pathParameters(parameterWithName("purchaseItemUid").description("The purchase item uid whose product will receive the review.")),
        requestParameters(
          parameterWithName("score").description("The review score provide by the buyer."),
          parameterWithName("comment").description("The review comment provided by the buyer."),
          parameterWithName("anonymous").description("If buyer want the review to remain anonymous.")
        ),
        responseFields(
          fieldWithPath("purchaseItemUid").description("The purchase item uid whose product will receive the review."),
          fieldWithPath("score").description("The review score provide by the buyer."),
          fieldWithPath("comment").description("The review comment provided by the buyer."),
          fieldWithPath("status").description(String.format("The status of review can be one of %s, %s, %s", APPROVED, PENDING, REJECTED)),
          fieldWithPath("anonymous").description("If buyer want the review to remain anonymous."))
      ));
  }

  @Test
  void checkReviewModerationCode() throws Exception {
    var id = "purchases/items/reviews/moderation/check";
    final UUID documentUid = UUID.randomUUID();
    final PurchaseItem item = em.persistAndFlush(DataGenerator.createPurchase(documentUid, buyer, seller)).getItems().get(0);
    var review = new TranslationDocumentReview();
    review.setDocumentUid(documentUid);
    review.setScore(4.0);
    review.setComment("Good");
    review.setStatus(APPROVED);
    review.setReviewer(item.getPurchase().getBuyer());
    review.setPurchaseItem(item);
    review = em.persistAndFlush(review);
    final UUID moderationCode = review.getModerationCode();
    this.mockMvc.perform(
        get("/" + id, converter.convert(item.getUid()))
          .param("purchaseUid", converter.convert(item.getPurchase().getUid()))
          .param("moderationCode", converter.convert(moderationCode))
          .accept(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isOk())
      .andExpect(jsonPath("$", is(true)))
      .andDo(document("purchases/items/{method-name}",
        requestParameters(
          parameterWithName("purchaseUid").description("The Purchase which has review items to be moderated."),
          parameterWithName("moderationCode").description("The code that allows moderate Purchase items.")
        )
      ));
  }

  @Test
  void moderateReview() throws Exception {
    var id = "purchases/items/{purchaseItemUid}/reviews/moderation";
    final UUID documentUid = UUID.randomUUID();
    final PurchaseItem item = em.persistAndFlush(DataGenerator.createPurchase(documentUid, buyer, seller)).getItems().get(0);
    var review = new TranslationDocumentReview();
    review.setDocumentUid(documentUid);
    review.setScore(4.0);
    review.setComment("Good");
    review.setStatus(APPROVED);
    review.setReviewer(item.getPurchase().getBuyer());
    review.setPurchaseItem(item);
    review = em.persistAndFlush(review);
    final UUID moderationCode = review.getModerationCode();
    this.mockMvc.perform(
        post("/" + id, converter.convert(item.getUid()))
          .content(String.format("moderationCode=%s&outcome=%s", converter.convert(moderationCode), APPROVED.name().toLowerCase(Locale.ROOT)))
          .contentType(MediaType.APPLICATION_FORM_URLENCODED)
          .accept(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isOk())
      .andExpect(jsonPath("$", is(APPROVED.name())))
      .andDo(document("purchases/items/{method-name}",
        pathParameters(parameterWithName("purchaseItemUid").description("The purchase item uid whose product will receive the review.")),
        requestParameters(
          parameterWithName("moderationCode").description("The code that allows moderate Purchase items."),
          parameterWithName("outcome").description("The outcome of moderation can be one of [APPROVED, REJECTED].")
        )
      ));
  }

}