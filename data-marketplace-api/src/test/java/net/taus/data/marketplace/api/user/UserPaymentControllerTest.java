package net.taus.data.marketplace.api.user;

import net.taus.data.marketplace.api.IntegrationTest;
import net.taus.data.marketplace.api.WebIntegrationTest;
import net.taus.payment.stripe.client.StripeClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserPaymentControllerTest extends WebIntegrationTest {

  private MockMvc mockMvc;
  @Autowired
  private StripeClient stripeClient;

  @BeforeEach
  public void setUp(final WebApplicationContext webApplicationContext,
                    final RestDocumentationContextProvider restDocumentation) {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
      .apply(documentationConfiguration(restDocumentation))
      .build();
  }

  @Test
  public void createBrokerOnboard() throws Exception {
    var id = "users/payments/stripe/onboard";
    this.mockMvc.perform(
      post("/" + id).accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("accountLink", is(notNullValue())))
      .andDo(document("users/payments/{method-name}"));
  }

  @Test
  public void getBrokerAccount() throws Exception {
    final String accountId = stripeClient.createAccount().getId();
    var id = "users/payments/stripe/account";
    this.mockMvc.perform(
      get("/" + id + "/" + accountId).accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("id", is(notNullValue())))
      .andDo(document("users/payments/{method-name}"));
  }

}