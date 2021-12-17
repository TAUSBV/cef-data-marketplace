package net.taus.data.marketplace.model.matchingdata.views;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import net.taus.data.language.api.model.Domain;
import net.taus.data.language.api.model.Language;
import net.taus.data.marketplace.model.matchingdata.MatchingDataRequest;
import net.taus.data.marketplace.model.matchingdata.MatchingDataRequestEvent;
import net.taus.data.marketplace.model.purchase.DatasetSize;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * User-presentable summary describing a {@link MatchingDataRequest}.
 */
@Data
public class MatchingDataRequestSummary {

  @Getter(AccessLevel.NONE)
  @Setter(AccessLevel.NONE)
  static ObjectMapper mapper = new ObjectMapper();

  final UUID requestId;
  final String status;
  final String type;
  final String email;
  final String fileName;
  final Long fileSize;
  final Long totalUnits;
  final String sampleLanguage;
  Language sourceLanguage;
  Language targetLanguage;
  Domain domain;
  final JsonNode analysisResult;
  final List<MatchingDataRequestEvent> allEvents;
  final List<MatchingDataRequestEvent> errors;
  final boolean hasErrors;
  AvailableSubsets availableSubsets;
  List<Sample> sampleUnits;

  /**
   * All-args constructor.
   *
   * @param requestId        the matching data request's uid
   * @param status           the request's status
   * @param type             the input query type (x. MONOLINGUAL, BILINGUAL)
   * @param email            the email of the user making the request
   * @param fileName         the name of the input query file
   * @param fileSize         the size of the input query file
   * @param totalUnits       the total number of rows in the input query
   * @param sampleLanguage   the sample language specified by the user (ex. SOURCE, TARGET). Only for MONOLINGUAL type.
   * @param sourceLanguage   the source language of the MD request
   * @param targetLanguage   the target language of the MD request
   * @param domain           the optional domain of the input query if provided by the user
   * @param analysisResult   the analysis results of the input query (ex. how many HIGH quality segments there where)
   * @param allEvents        all events that took place for the MD request
   * @param errors           only the erroneous events that took place for the MD request
   * @param hasErrors        if the MD request had any error events
   * @param availableSubsets a predefined list of {@link AvailableSubsets.AvailableSubset} for the MD request along with
   *                         calculated prices
   * @param sampleUnits      a list of source/target sample units that we present to the user when the results are ready
   */
  public MatchingDataRequestSummary(final UUID requestId,
                                    final String status,
                                    final String type,
                                    final String email,
                                    final String fileName,
                                    final Long fileSize,
                                    final Long totalUnits,
                                    final String sampleLanguage,
                                    final Language sourceLanguage,
                                    final Language targetLanguage,
                                    final Domain domain,
                                    final JsonNode analysisResult,
                                    final List<MatchingDataRequestEvent> allEvents,
                                    final List<MatchingDataRequestEvent> errors,
                                    final boolean hasErrors,
                                    final AvailableSubsets availableSubsets,
                                    final List<Sample> sampleUnits

  ) {
    this.requestId = requestId;
    this.status = status;
    this.type = type;

    this.email = email;
    this.fileName = fileName;
    this.fileSize = fileSize;
    this.totalUnits = totalUnits;
    this.sampleLanguage = sampleLanguage;
    this.sourceLanguage = sourceLanguage;
    this.targetLanguage = targetLanguage;
    this.domain = domain;
    this.analysisResult = analysisResult;
    this.allEvents = allEvents;
    this.errors = errors;
    this.hasErrors = hasErrors;

    this.availableSubsets = availableSubsets;
    this.sampleUnits = sampleUnits;
  }

  /**
   * Helper class that wraps the source target content of the md result's samples
   */
  @Value
  public static class Sample {
    String sourceValue;
    String targetValue;
  }

  /**
   * Describes available subsets of an MD request's results
   */
  @Value
  public static class AvailableSubsets {

    @JsonValue
    List<AvailableSubset> rows;

    /**
     * All-args constructor. By default we add 4 subsets.
     *
     * @param totalSourceWordCount the total number of source words in the full MD results. Used to specify each subset's threshold.
     */
    public AvailableSubsets(long totalSourceWordCount) {
      // Rows list is unmodifiable and should be initialized by dataset size desc for performance
      this.rows = List.of(
        new AvailableSubset(DatasetSize.FULL, new MetaData(), totalSourceWordCount),
        new AvailableSubset(DatasetSize.LARGE, new MetaData(), totalSourceWordCount),
        new AvailableSubset(DatasetSize.MEDIUM, new MetaData(), totalSourceWordCount),
        new AvailableSubset(DatasetSize.COMPACT, new MetaData(), totalSourceWordCount)
      );

    }

    /**
     * Updates the counts and price for each available subset if they are below the specified threshold.
     *
     * @param currentCount    the current count (ex. sum of source words)
     * @param unitPrice       the unit price of the translation unit we are parsing
     * @param sourceWordCount the number of source words of the translation unit we are parsing
     * @param targetWordCount the number of target words of the translation unit we are parsing
     */
    public void updateRows(long currentCount, double unitPrice, long sourceWordCount, long targetWordCount) {
      // The rows list is unmodifiable and the elements are added by dataset size order
      // If this is not the case then we must add .sorted(Comparator.comparingDouble(PriceRow::getThreshold).reversed())
      // which will reduce significantly the price calculation for large datasets
      rows.stream().takeWhile(priceRow -> currentCount < priceRow.getThreshold()).forEach(priceRow -> {
        priceRow.incrementPrice(unitPrice);
        priceRow.getMetaData().increment(sourceWordCount, targetWordCount);
      });
    }

    /**
     * Helper class that wraps counts and price for available subsets
     */
    @Data
    public static class MetaData {
      private long segmentCount;
      private long sourceWordCount;
      private long targetWordCount;
      private double price;

      /**
       * Increments all counts (not price) by the given amounts. Every time this is called we also increment
       * the segmentCount by 1.
       *
       * @param sourceWordCount the amount of source words we will add to the total
       * @param targetWordCount the amount of target words we will add to the total
       */
      public void increment(long sourceWordCount, long targetWordCount) {
        this.segmentCount++;
        this.sourceWordCount += sourceWordCount;
        this.targetWordCount += targetWordCount;

      }

      /**
       * @return the total price of the subset
       */
      public double getPrice() {
        return BigDecimal.valueOf(price).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
      }

    }

    /**
     * This is what actually describes an entry of available subsets
     */
    @Value
    public static class AvailableSubset {
      DatasetSize datasetSize;
      MetaData metaData;
      long threshold;

      /**
       * All-arg constructor
       *
       * @param datasetSize          the size of the subset (ex. LARGE)
       * @param metaData             the subset's counts and price
       * @param totalSourceWordCount the total source words in the superset used to calculate the subset's threshold
       */
      public AvailableSubset(DatasetSize datasetSize, final MetaData metaData, long totalSourceWordCount) {
        this.datasetSize = datasetSize;
        this.metaData = metaData;
        this.threshold = Math.round(totalSourceWordCount * getDatasetSize().getAmount());
      }

      /**
       * Increment the subset's price by the specified amount
       *
       * @param increment the amount we will add to the subset's total price
       */
      public void incrementPrice(double increment) {
        getMetaData().price += increment;
      }
    }
  }

  /**
   * Static builder method that creates a summary for the given {@link MatchingDataRequest}.
   *
   * @param matchingDataRequest the matching data request we want to display
   * @param sourceLanguage      the matching data request's source language
   * @param targetLanguage      the matching data request's target language
   * @param domain              the the matching data request's domain (if provided by the user)
   * @return a user-friendly summary for the matching data request we want to display
   */
  public static MatchingDataRequestSummary fromMatchingDataRequest(MatchingDataRequest matchingDataRequest, Language sourceLanguage, Language targetLanguage, Domain domain) {
    JsonNode analysisResult = null;
    if (matchingDataRequest.getAnalysisResult() != null) {
      try {
        analysisResult = mapper.readTree(matchingDataRequest.getAnalysisResult());
      }
      catch (JsonProcessingException ignored) {
      }
    }

    return new MatchingDataRequestSummary(matchingDataRequest.getUid(),
      matchingDataRequest.getStatus().name(),
      matchingDataRequest.getType().name(),
      matchingDataRequest.getEmail(),
      matchingDataRequest.getFileName(),
      matchingDataRequest.getFileSize(),
      matchingDataRequest.getTotalUnits(),
      matchingDataRequest.getSampleLanguage() == null ? null : matchingDataRequest.getSampleLanguage().name(),
      sourceLanguage,
      targetLanguage,
      domain,
      analysisResult,
      matchingDataRequest.getEvents(),
      matchingDataRequest.getErrors(),
      matchingDataRequest.hasErrors(),
      null,
      new ArrayList<>()
    );
  }


  /**
   * Static builder method that creates a summary for the given {@link MatchingDataRequest}.
   * Note that using this method will not set sourceLanguage, targetLanguage and domain.
   * We hold only id references for these and they should be externally fetched.
   *
   * @param matchingDataRequest the matching data request we want to display
   * @return a user-friendly summary for the matching data request we want to display
   */
  public static MatchingDataRequestSummary fromMatchingDataRequest(MatchingDataRequest matchingDataRequest) {
    return fromMatchingDataRequest(matchingDataRequest, null, null, null);
  }

}
