package net.taus.data.marketplace.api.seller.provider;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import net.taus.data.marketplace.model.BusinessException;
import net.taus.data.marketplace.model.SystemException;
import net.taus.data.marketplace.model.seller.payment.views.SellerPaymentSummary;
import org.apache.http.entity.ContentType;
import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


public class CorporatePaymentService {

  private final Logger logger = LoggerFactory.getLogger(CorporatePaymentService.class.getSimpleName());
  private final String apiKey;
  private final HttpClient httpClient;
  private final String profileEndpoint;
  private final String stripeStandardEndpoint;
  private final String stripeForgetEndpoint;
  private final String listPaymentsEndpoint;
  private final ObjectMapper mapper;

  public CorporatePaymentService(final HttpClient client,
                                 final String apiKey,
                                 final String profileEndpoint,
                                 final String stripeStandardEndpoint,
                                 final String stripeForgetEndpoint,
                                 final String listPaymentsEndpoint) {
    this.httpClient = Objects.requireNonNull(client, "Invalid client argument (Null)");
    this.apiKey = Objects.requireNonNull(apiKey, "Invalid apiKey argument (Null)");
    this.profileEndpoint = Objects.requireNonNull(profileEndpoint, "Invalid profileEndpoint argument (Null)");
    this.stripeStandardEndpoint = Objects.requireNonNull(stripeStandardEndpoint, "Invalid stripeStandardEndpoint argument (Null)");
    this.stripeForgetEndpoint = Objects.requireNonNull(stripeForgetEndpoint, "Invalid stripeForgetEndpoint argument (Null)");
    this.listPaymentsEndpoint = Objects.requireNonNull(listPaymentsEndpoint, "Invalid listPaymentsEndpoint argument (Null)");
    this.mapper = new ObjectMapper();
  }

  protected UserPaymentProfile getProfileResponse(@NotNull(message = "Invalid request (Null)") HttpRequest request) {
    UserPaymentProfile jsonBody;
    try {
      final HttpResponse<String> response = this.httpClient.send(request, HttpResponse.BodyHandlers.ofString());
      final String bodyString = response.body();
      final ObjectNode body = mapper.readValue(bodyString, ObjectNode.class);
      if (response.statusCode() >= 200 && response.statusCode() <= 299) {
        jsonBody = new UserPaymentProfile(body);
      }
      else if (response.statusCode() >= 400 && response.statusCode() <= 499) {
        final String exceptionMessage = Optional.ofNullable(body.get("exceptionMessage")).map(JsonNode::asText).orElse(bodyString);
        throw new CorporatePaymentServiceException(response.statusCode(), exceptionMessage);
      }
      else {
        throw new SystemException(bodyString);
      }
    }
    catch (CorporatePaymentServiceException e) {
      throw e;
    }
    catch (Exception e) {
      logger.error(e.getMessage(), e);
      throw new SystemException("Request to CorporatePaymentService failed.", e);
    }
    return jsonBody;
  }


  /**
   * Requests user's payment profile from Treasurer service
   *
   * @param sessionId taus session id
   * @return The Treasurer's response
   */
  public UserPaymentProfile getUserProfile(final String sessionId) {
    if (Objects.requireNonNull(sessionId, "Invalid sessionId argument (Null)").trim().isEmpty()) {
      throw new IllegalArgumentException("Invalid sessionId argument (Empty text)");
    }
    var request = HttpRequest.newBuilder()
      .GET()
      .uri(URI.create(this.profileEndpoint))
      .header(HTTP.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString())
      .header("sessionId", sessionId)
      .header("apiKey", this.apiKey)
      .timeout(Duration.ofSeconds(5))
      .build();

    return this.getProfileResponse(request);
  }

  /**
   * Updates user's payment profile for specific payment method
   *
   * @param sessionId User's taus sessionId after authentication
   * @param profile   The profile data to be updated
   * @return The Treasurer's response
   */
  public UserPaymentProfile updateUserProfile(final String sessionId,
                                              final UserPaymentProfile profile) {
    if (Objects.requireNonNull(sessionId, "Invalid sessionId argument (Null)").isBlank()) {
      throw new IllegalArgumentException("Invalid sessionId argument (Empty text)");
    }
    if (Objects.requireNonNull(profile.getMethod(), "Invalid paymentMethod argument (Null)").isBlank()) {
      throw new IllegalArgumentException("Invalid paymentMethod argument (Empty text)");
    }
    if (Objects.requireNonNull(profile, "Invalid profile argument (Null)").size() == 0) {
      throw new IllegalArgumentException("Invalid profile argument (Empty JSON object)");
    }

    if (profile.isStripe()) {
      if (!profile.isPaymentDataValid()) {
        throw new BusinessException("Invalid payment information, charges needs to be enabled for Stripe.");
      }
      if (profile.getStripeId() != null) {
        JsonNode data = profile.getStripeData();
        this.stripeStandard(sessionId, data);
      }
      else {
        this.stripeForget(sessionId);
      }
    }

    var request = HttpRequest.newBuilder()
      .PUT(HttpRequest.BodyPublishers.ofString(profile.toString()))
      .uri(URI.create(this.profileEndpoint + "/" + profile.getMethod()))
      .header(HTTP.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString())
      .header("sessionId", sessionId)
      .header("apiKey", this.apiKey)
      .timeout(Duration.ofSeconds(5))
      .build();

    return this.getProfileResponse(request);
  }

  private void stripeStandard(final String sessionId, JsonNode data) {
    if (Objects.requireNonNull(sessionId, "Invalid sessionId argument (Null)").isBlank()) {
      throw new IllegalArgumentException("Invalid sessionId argument (Empty text)");
    }
    Objects.requireNonNull(data, "Invalid data (Null)");

    var request = HttpRequest.newBuilder()
      .POST(HttpRequest.BodyPublishers.ofString(data.toString()))
      .uri(URI.create(this.stripeStandardEndpoint))
      .header(HTTP.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString())
      .header("sessionId", sessionId)
      .header("apiKey", this.apiKey)
      .timeout(Duration.ofSeconds(5))
      .build();

    this.getProfileResponse(request);
  }

  private void stripeForget(final String sessionId) {
    if (Objects.requireNonNull(sessionId, "Invalid sessionId argument (Null)").isBlank()) {
      throw new IllegalArgumentException("Invalid sessionId argument (Empty text)");
    }

    var request = HttpRequest.newBuilder()
      .GET()
      .uri(URI.create(this.stripeForgetEndpoint))
      .header(HTTP.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString())
      .header("sessionId", sessionId)
      .header("apiKey", this.apiKey)
      .timeout(Duration.ofSeconds(5))
      .build();

    this.getProfileResponse(request);
  }

  /**
   * @param email the user's email
   * @param pn    the page number
   * @param ps    the page size
   * @return a paged list of the user's payments
   */
  public Page<SellerPaymentSummary> listPayments(String email, int pn, int ps) {
    // Build the url with pagination parameters
    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(listPaymentsEndpoint)
      .queryParam("page", pn + 1)
      .queryParam("pageSize", ps);

    // Build the request
    var request = HttpRequest.newBuilder()
      .GET()
      .uri(URI.create(builder.toUriString()))
      .header(HTTP.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString())
      .header("email", email)
      .header("apiKey", this.apiKey)
      .timeout(Duration.ofSeconds(5))
      .build();

    // Send the request
    try {
      final var response = this.httpClient.send(request, HttpResponse.BodyHandlers.ofString());
      var responseNode = mapper.readTree(response.body());

      // Check for error response status
      if (response.statusCode() >= 400 && response.statusCode() <= 499) {
        final String exceptionMessage = Optional.ofNullable(responseNode.get("exceptionMessage")).map(JsonNode::asText).orElse(responseNode.toString());
        throw new CorporatePaymentServiceException(response.statusCode(), exceptionMessage);
      }
      else if (response.statusCode() != 200) {
        throw new SystemException(response.body());
      }

      var paginationNode = responseNode.get("pagination");
      var dataNode = responseNode.get("data");

      // Construct the required list that we will return
      List<SellerPaymentSummary> paymentList = new ArrayList<>();
      for (JsonNode jsonNode : Objects.requireNonNull(dataNode)) {
        paymentList.add(SellerPaymentSummary.transformPayment(jsonNode));
      }
      return new PageImpl<>(paymentList, PageRequest.of(pn, ps), Objects.requireNonNull(paginationNode).get("totalCount").asLong());
    }
    catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }

    // If we fail to communicate with the corporate payment service, we return an empty page.
    return Page.empty();
  }

  /**
   * This exception extends the {@link SystemException} and it is used to separate the exceptions coming from
   * Treasurer erroneous responses. This exceptions are forwarded straight to FrontEnd with the same status and body.
   */
  public static class CorporatePaymentServiceException extends SystemException {
    private final int statusCode;

    public CorporatePaymentServiceException(final int statusCode, final String responseMessage) {
      super(responseMessage);
      this.statusCode = statusCode;
    }

    public int getStatusCode() {
      return this.statusCode;
    }
  }
}
