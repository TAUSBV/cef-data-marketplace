package net.taus.data.marketplace.api.purchase.services;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.Getter;
import net.taus.data.marketplace.api.validation.Country;
import net.taus.data.marketplace.model.BusinessException;
import net.taus.data.marketplace.model.user.UserDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public class TaxService {

  private final Logger logger = LoggerFactory.getLogger(TaxService.class.getCanonicalName());
  private final RestTemplate restTemplate;
  private final String taxesEndpoint;

  public TaxService(final String apiKey,
                    final String taxesEndpoint) {
    this.restTemplate = new RestTemplate();
    this.restTemplate.getInterceptors().add((request, body, execution) -> {
      request.getHeaders().set("apiKey", apiKey);
      return execution.execute(request, body);
    });
    this.taxesEndpoint = taxesEndpoint;
  }

  /**
   * Makes a call to the treasurer API and aggregates all applicable taxes based on the user's country and legal type.
   *
   * @param userDetail The {@link UserDetail} object that we want to check for applicable taxes
   * @return the aggregated tax percentage like 0.21 (not multiplied by 100)
   */
  @CircuitBreaker(name = "default", fallbackMethod = "getSalesTaxPercentageFallback")
  public double getTax(UserDetail userDetail) {
    try {
      final var country = Country.findByName(userDetail.getCountry()).orElseThrow(() -> new BusinessException(String.format("Country not supported %s.", userDetail.getCountry())));
      var legalType = userDetail.getEntityType().toString();
      UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(taxesEndpoint)
        .queryParam("countryCode", country.getCode())
        .queryParam("legalType", legalType);
      logger.info("Getting applicable taxes for {} {}", country.getCode(), legalType);
      final ResponseEntity<TaxService.Tax[]> response = restTemplate.getForEntity(builder.build().toUri(), Tax[].class);
      final Tax[] taxes = Objects.requireNonNull(response, "Tax response was null").getBody();
      return Optional.ofNullable(taxes).stream().flatMap(Arrays::stream).mapToDouble(Tax::getPercent).sum();
    }
    catch (Exception e) {
      logger.error(e.getMessage());
      throw CallNotPermittedException.createCallNotPermittedException(io.github.resilience4j.circuitbreaker.CircuitBreaker.ofDefaults("default"));
    }
  }

  /**
   * Circuit breaking fallback method for retrieving sales taxes based on the user's country and legal type
   *
   * @param userDetail The {@link UserDetail} object that we want to check for applicable taxes
   * @param e          We use @{@link CallNotPermittedException} in order to always execute the fallback method in the open state.
   *                   If we want to prevent further calls from happening in the open state and prevent fallback execution (fail-fast)
   *                   we should change the method signature to accept a more specific exception such as @{@link RestClientException}
   * @return the aggregated tax percentage like 0.21 (not multiplied by 100)
   */
  @SuppressWarnings("unused")
  private double getSalesTaxPercentageFallback(UserDetail userDetail, CallNotPermittedException e) {
    logger.warn("Running fallback operation for retrieving taxes");
    final var country = Country.findByName(userDetail.getCountry()).orElseThrow(() -> new BusinessException(String.format("Country not supported %s.", userDetail.getCountry())));
    var legalType = userDetail.getEntityType();
    boolean privateInEU = UserDetail.EntityType.PRIVATE.equals(legalType) && country.isEuropeanUnion();
    boolean businessInNL = UserDetail.EntityType.BUSINESS.equals(legalType) && country.getCode().equals("NL");
    boolean requiresTax = privateInEU || businessInNL;
    return requiresTax ? 0.21 : 0;
  }

  /**
   * Static inner class that a tax request deserializes to
   */
  @Getter
  private static class Tax {
    String code;
    String name;
    double percent;

    @JsonCreator
    private Tax(@JsonProperty("code") String code,
                @JsonProperty("name") String name,
                @JsonProperty("percent") double percent) {
      this.code = code;
      this.name = name;
      this.percent = percent;
    }
  }

}
