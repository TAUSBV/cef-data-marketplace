package net.taus.data.marketplace.api.purchase.services;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.Base64;
import java.util.Objects;

import static java.lang.String.format;
import static java.net.http.HttpResponse.BodyHandlers.ofString;

public class VATSenseService {

  private final String url;
  private final HttpClient client;
  private final String[] headers;

  private VATSenseService(final String url,
                          final String authorization,
                          final HttpClient client) {
    this.url = url;
    this.headers = new String[]{ "Authorization", "Basic " + authorization };
    this.client = client;
  }

  public static VATSenseService create(final String apiUrl, final String apiKey) {
    return new VATSenseService(apiUrl,
      Base64.getUrlEncoder().encodeToString(format("%s:%s", "user", apiKey).getBytes()),
      HttpClient.newBuilder().build());
  }

  public VatCheckResponse checkVAT(final String vat) {
    final var mapper = new ObjectMapper();
    var request = HttpRequest.newBuilder()
      .uri(URI.create(format("%s?vat_number=%s", url, vat)))
      .headers(headers)
      .GET()
      .build();
    VatCheckResponse checkResponse;
    try {
      final var response = client.send(request, ofString());
      final var node = mapper.readTree(response.body());
      final var rootError = node.hasNonNull("error");
      final var dataError = node.hasNonNull("data") && node.get("data").hasNonNull("error");
      if (rootError || dataError) {
        var error = rootError ? node.get("error") : node.get("data").get("error");
        var code = rootError ? node.get("code").asInt() : node.get("data").get("code").asInt();
        var result = code == 412 ? VatCheckResult.UNKNOWN : VatCheckResult.INVALID;
        var detail = String.format("%s - %s - %s", code, error.get("title").asText(), error.get("detail").asText());
        checkResponse = new VatCheckResponse(result, detail);
      }
      else if (node.hasNonNull("data")) {
        final var valid = node.get("data").get("valid").asBoolean();
        var result = valid ? VatCheckResult.VALID : VatCheckResult.INVALID;
        checkResponse = new VatCheckResponse(result);
      }
      else {
        checkResponse = new VatCheckResponse(VatCheckResult.UNKNOWN, "Service returned unknown response");
      }
    }
    catch (IOException | InterruptedException e) {
      checkResponse = new VatCheckResponse(VatCheckResult.UNKNOWN, e.getMessage());
    }
    return checkResponse;
  }

  public enum VatCheckResult {
    VALID, INVALID, UNKNOWN
  }

  public static class VatCheckResponse {

    private final VatCheckResult result;
    private final String detail;

    public VatCheckResponse(final VatCheckResult result) {
      this.result = result;
      this.detail = "";
    }

    public VatCheckResponse(final VatCheckResult result, final String detail) {
      this.result = result;
      this.detail = detail;
    }

    public VatCheckResult getResult() {
      return result;
    }

    public String getDetail() {
      return detail;
    }

    public boolean isValid() {
      return Objects.nonNull(this.result) && VatCheckResult.VALID.equals(this.result);
    }

    public boolean isInvalid() {
      return Objects.nonNull(this.result) && VatCheckResult.INVALID.equals(this.result);
    }

    public boolean isUnknown() {
      return Objects.nonNull(this.result) && VatCheckResult.UNKNOWN.equals(this.result);
    }

  }
}
