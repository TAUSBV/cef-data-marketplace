package net.taus.data.marketplace.api.matchingdata;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.univocity.parsers.common.ParsingContext;
import com.univocity.parsers.common.processor.RowListProcessor;
import com.univocity.parsers.tsv.TsvParser;
import com.univocity.parsers.tsv.TsvParserSettings;
import net.taus.data.language.api.DataLanguageClient;
import net.taus.data.marketplace.api.user.UserService;
import net.taus.data.marketplace.api.utils.MaxSizeLinkedHashMap;
import net.taus.data.marketplace.api.validation.MatchingDataRequestForm;
import net.taus.data.marketplace.model.BusinessException;
import net.taus.data.marketplace.model.SystemException;
import net.taus.data.marketplace.model.matchingdata.MatchingDataRequest;
import net.taus.data.marketplace.model.matchingdata.MatchingDataRequestException;
import net.taus.data.marketplace.model.matchingdata.views.MatchingDataRequestSummary;
import net.taus.data.marketplace.model.purchase.PurchaseItemSellerShare;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.WritableResource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Service that performs vital operations related to a {@link MatchingDataRequest} such as
 * <ul>
 *   <li>validating the input query</li>
 *   <li>archiving and transferring files to remote</li>
 *   <li>decorating the the request's summary</li>
 *   <li>retrieving MD results</li>
 *   <li>calculating prices per available subset</li>
 *   <li>streaming results to calculate respective seller shares</li>
 * </ul>
 */
public class MatchingDataService {

  private final Logger logger = LoggerFactory.getLogger(MatchingDataService.class);

  // Constructor parameters
  private final DataLanguageClient dataLanguageClient;
  private final UserService userService;
  private final ResourceLoader resourceLoader;
  private final int minNumberOfRows;
  private final int maxNumberOfRows;
  private final String dmpAssetsPathPrefix;
  private final String mdAssetsPathPrefix;
  private final ObjectMapper objectMapper;

  // This is used to cache md-request samples, so we don't have to fetch and parse all the time
  private final Map<UUID, MatchingDataResult> loadedResults;

  // Constants
  protected static final String EXAMPLES_FILE_NAME = "examples.zip";
  protected static final String SAMPLE_FILE_NAME = "sample.tsv";
  protected static final String SUMMARY_FILE_NAME = "summary.tsv";
  protected static final String SELECTION_FILE_NAME = "selection.tsv";

  /**
   * All-args constructor
   *
   * @param dataLanguageClient  The data language API client
   * @param userService         Service used to retrieve corporate users
   * @param resourceLoader      Used to read/write necessary resources such as the request zip file we upload
   * @param minNumberOfRows     The minimum number of rows the input query should have
   * @param maxNumberOfRows     The maximum number of rows the input query should have
   * @param dmpAssetsPathPrefix The location of the data marketplace resources for md-requests
   * @param mdAssetsPathPrefix  The location of the Matching Data engine results
   * @param objectMapper        Currently used to serialize some of our uploaded info
   */
  public MatchingDataService(final DataLanguageClient dataLanguageClient,
                             final UserService userService,
                             final ResourceLoader resourceLoader,
                             final int minNumberOfRows,
                             final int maxNumberOfRows,
                             final String dmpAssetsPathPrefix,
                             final String mdAssetsPathPrefix,
                             final ObjectMapper objectMapper) {
    this.dataLanguageClient = dataLanguageClient;
    this.userService = userService;
    this.resourceLoader = resourceLoader;
    this.minNumberOfRows = minNumberOfRows;
    this.maxNumberOfRows = maxNumberOfRows;
    this.dmpAssetsPathPrefix = dmpAssetsPathPrefix;
    this.mdAssetsPathPrefix = mdAssetsPathPrefix;
    this.objectMapper = objectMapper;

    // Synchronized LinkedHashMap that has a maximum capacity
    this.loadedResults = Collections.synchronizedMap(new MaxSizeLinkedHashMap<>(10));
  }

  /**
   * Decorates a {@link MatchingDataRequest} with presentable info. Please note that due to the data-language-api
   * decoupling, we only persist ids for source/target languages and domain. We request this info from data-language-api
   * and then (if applicable) retrieve the MD engine's results sample document. This operation is fail-safe and if one of the
   * remote APIs is not available, the respective summary fields will remain null/empty.
   *
   * @param matchingDataRequest The MD request we want to decorate
   * @return a {@link MatchingDataRequestSummary} after we retrieve all applicable necessary info
   */
  protected MatchingDataRequestSummary decorate(MatchingDataRequest matchingDataRequest) {
    // The default summary before we populate missing fields
    MatchingDataRequestSummary summary = MatchingDataRequestSummary.fromMatchingDataRequest(matchingDataRequest);

    // Set source/target languages and domain (if present)
    var languages = dataLanguageClient.getLanguages();
    summary.setSourceLanguage(languages.stream().filter(x -> x.getId().equals(matchingDataRequest.getSourceLanguageId())).findFirst().orElse(null));
    summary.setTargetLanguage(languages.stream().filter(x -> x.getId().equals(matchingDataRequest.getTargetLanguageId())).findFirst().orElse(null));
    if (matchingDataRequest.getDomainId() != null) {
      var domains = dataLanguageClient.getDomains();
      summary.setDomain(domains.stream().filter(x -> x.getId().equals(matchingDataRequest.getDomainId())).findFirst().orElse(null));
    }

    // Get the matching data result if available
    var matchingDataResult = getMatchingDataResult(matchingDataRequest);
    if (matchingDataResult.isPresent()) {
      // Set the price summary for available subsets
      summary.setAvailableSubsets(getAvailableSubsets(matchingDataResult.get()));

      // Finally, set the sample units
      var sampleUnits = new ArrayList<MatchingDataRequestSummary.Sample>();
      matchingDataResult.get().getSamples().forEach(strings -> sampleUnits.add(new MatchingDataRequestSummary.Sample(strings[0], strings[1])));
      summary.setSampleUnits(sampleUnits);
    }

    return summary;
  }

  /**
   * Used to validate an input query which is required for the MD engine.
   *
   * @param file The file as received from the http request
   * @param type Can be monolingual or bilingual. Validations differ depending on this.
   * @return The number of total units in the file if all validations pass
   * @throws IOException In case of file access errors
   */
  protected long validateInputQuery(MultipartFile file, MatchingDataRequest.Type type) throws IOException {
    // Get the default tsv parser settings
    final TsvParserSettings parserSettings = getDefaultParserSettings();

    // Set extra validations and a row processor which does the actual validation
    parserSettings.setNumberOfRecordsToRead(maxNumberOfRows);
    parserSettings.setProcessor(new RowListProcessor() {
      @Override
      public void rowProcessed(final String[] row, final ParsingContext context) {
        super.rowProcessed(row, context);

        // Based on the input query type (monolingual/bilingual) we check the number of columns in the row
        if (row.length != 1 && MatchingDataRequest.Type.MONOLINGUAL.equals(type))
          throw new MatchingDataRequestException("Error at line " + context.currentLine() + ". Monolingual input query should have exactly 1 column.");
        if (row.length != 2 && MatchingDataRequest.Type.BILINGUAL.equals(type))
          throw new MatchingDataRequestException("Error at line " + context.currentLine() + ". Bilingual input query should have exactly 2 columns.");
      }

      @Override
      public void processEnded(final ParsingContext context) {
        super.processEnded(context);

        // When the process is finished we check the total number or input query rows to decide if they are too few or too many
        if (context.currentLine() > maxNumberOfRows)
          throw new MatchingDataRequestException("Maximum number of rows allowed: " + maxNumberOfRows);
        if (context.currentLine() < minNumberOfRows)
          throw new MatchingDataRequestException("Minimum number of rows allowed: " + minNumberOfRows);
      }
    });
    TsvParser parser = new TsvParser(parserSettings);

    // Parse the contents of the file. FailFast on parsing or business errors
    return parser.parseAll(file.getInputStream(), StandardCharsets.UTF_8).size();
  }

  /**
   * Transfer the user form as a zip alongside with relevant metadata. Please note that you should validate the input query
   * prior to making this call.
   *
   * @param matchingDataRequestForm The user submitted form for the MD request
   * @param fileName                The name we want to give to the created zip archive
   * @throws IOException On zip format and other I/O errors
   * @see MatchingDataService#validateInputQuery(MultipartFile, MatchingDataRequest.Type)
   */
  protected void transfer(MatchingDataRequestForm matchingDataRequestForm, String fileName) throws IOException {
    // Where we'll send the file
    final var location = dmpAssetsPathPrefix + "/" + fileName;
    final var resource = (WritableResource) resourceLoader.getResource(location);

    // Create the zip file
    try (final var outputStream = resource.getOutputStream();
         final var zos = new ZipOutputStream(new BufferedOutputStream(outputStream))) {
      // 1st file is the input query
      zos.putNextEntry(new ZipEntry(matchingDataRequestForm.getOriginalFilename()));
      matchingDataRequestForm.getFile().getInputStream().transferTo(zos);
      zos.closeEntry();

      // 2nd file contains relevant metadata that are required from the MD engine
      zos.putNextEntry(new ZipEntry("metadata.json"));
      zos.write(objectMapper.writeValueAsBytes(matchingDataRequestForm));
      zos.closeEntry();
    }
  }

  /**
   * Checks if a a {@link MatchingDataResult} is available/cached for a {@link MatchingDataRequest}. If not then we fetch
   * the remote results and cache them.
   * <p>
   * This is just an async wrapper of {@link MatchingDataService#getMatchingDataResult(MatchingDataRequest)}
   * using a fixed thread pool with just 1 thread.
   *
   * @param matchingDataRequest The request for which we want to fetch the results. Note that only requests with
   *                            status {@link MatchingDataRequest.Status#COMPLETED} are eligible for result fetching.
   * @return The results of the MD engine search.
   */
  @SuppressWarnings("UnusedReturnValue")
  @Async
  public CompletableFuture<Optional<MatchingDataResult>> getMatchingDataResultAsync(MatchingDataRequest matchingDataRequest) {
    return CompletableFuture.completedFuture(getMatchingDataResult(matchingDataRequest));
  }

  /**
   * Checks if a a {@link MatchingDataResult} is available/cached for a {@link MatchingDataRequest}. If not then we fetch
   * the remote results and cache them.
   *
   * @param matchingDataRequest The request for which we want to fetch the results. Note that only requests with
   *                            status {@link MatchingDataRequest.Status#COMPLETED} are eligible for result fetching.
   * @return The results of the MD engine search.
   */
  public Optional<MatchingDataResult> getMatchingDataResult(MatchingDataRequest matchingDataRequest) {

    // Check if the results are cached
    Optional<MatchingDataResult> result = loadedResults.containsKey(matchingDataRequest.getUid()) ?
      Optional.of(loadedResults.get(matchingDataRequest.getUid())) : Optional.empty();

    // Get the results from the md assets location
    if (result.isEmpty() && MatchingDataRequest.Status.COMPLETED.equals(matchingDataRequest.getStatus())) {
      final var location = mdAssetsPathPrefix + "/" + matchingDataRequest.getUid() + ".zip";

      // Open the remote stream
      final var resource = resourceLoader.getResource(location);
      try (final var inputStream = resource.getInputStream();
           final var zis = new ZipInputStream(new BufferedInputStream(inputStream))) {

        var matchingDataResult = new MatchingDataResult();

        // We create custom tsv parser settings with different row processors to match the needs of each provided file
        var summaryParserSettings = getDefaultParserSettings().clone();

        // Processor used for summary file where each row has the documentUid and the total number of units, source and target words
        summaryParserSettings.setProcessor(new RowListProcessor() {
          @Override
          public void rowProcessed(final String[] row, final ParsingContext context) {
            super.rowProcessed(row, context);
            MatchingDataResult.DocumentSummary documentSummary = new MatchingDataResult.DocumentSummary(UUID.fromString(row[0]),
              Long.parseLong(row[1]),
              Long.parseLong(row[2]),
              Long.parseLong(row[3]));
            matchingDataResult.getDocumentSummaries().add(documentSummary);
          }
        });

        // Processor used for the selection file where each row is:
        // documentUid-translationUnitUid-translationUnitId-sourceWordCount-targetWordCount
        // We take all except the translationUnitUid which is there for future-proof (ex. we migrate translation units)
        var selectionParserSettings = getDefaultParserSettings().clone();
        selectionParserSettings.setProcessor(new RowListProcessor() {
          @Override
          public void rowProcessed(final String[] row, final ParsingContext context) {
            super.rowProcessed(row, context);
            MatchingDataResult.Selection selection = new MatchingDataResult.Selection(UUID.fromString(row[0]),
              Long.parseLong(row[2]),
              Long.parseLong(row[3]),
              Long.parseLong(row[4]));
            matchingDataResult.getSelections().add(selection);
          }
        });

        ZipEntry ze;
        while ((ze = zis.getNextEntry()) != null) {
          switch (ze.getName()) {
            case SAMPLE_FILE_NAME -> {
              // The sample file does not need custom parser settings (uses default ones) as it is just source and target text
              var samples = new TsvParser(getDefaultParserSettings()).parseAll(zis, StandardCharsets.UTF_8);
              matchingDataResult.getSamples().addAll(samples);
            }
            case SUMMARY_FILE_NAME -> new TsvParser(summaryParserSettings).parseAll(zis, StandardCharsets.UTF_8);
            case SELECTION_FILE_NAME -> new TsvParser(selectionParserSettings).parseAll(zis, StandardCharsets.UTF_8);
          }
        }
        // Cache the MD result
        loadedResults.put(matchingDataRequest.getUid(), matchingDataResult);
        result = Optional.of(matchingDataResult);
      }
      catch (IOException e) {
        logger.error(e.getMessage());
        result = Optional.empty();
      }
    }
    return result;
  }

  /**
   * Get the total price for the {@link MatchingDataResult}
   *
   * @param matchingDataResult The result for which we want to calculate the total price
   * @param amount             The amount percentage we want to purchase (ex 0.25)
   * @return The total price
   */
  public double getTotalPrice(MatchingDataResult matchingDataResult, double amount) {
    return getAvailableSubsets(matchingDataResult).getRows().stream()
      .filter(availableSubset -> availableSubset.getDatasetSize().getAmount() == amount).findFirst()
      .map(availableSubset -> availableSubset.getMetaData().getPrice())
      .orElseThrow(() -> new BusinessException("No available subset found for the specified amount: " + amount));
  }

  /**
   * Calculate and get all the seller shares as they derive from the matching data result-set and the chosen amount.
   * In practice, we stream the result-set until a certain threshold and aggregate counts in the respective shares.
   *
   * @param matchingDataResult The result that contains the selection that we will stream and the summary we will use
   *                           to get unit prices per document
   * @param amount             The amount of the selection/result-set we want to calculate the shares for
   * @return Shares per seller based on the given amount
   */
  public List<PurchaseItemSellerShare> getPurchaseItemSellerShares(MatchingDataResult matchingDataResult, double amount) {
    // Iterate the documentSummaries and get the price per document
    List<PurchaseItemSellerShare> PurchaseItemSellerShares = new ArrayList<>();
    for (var documentSummary : matchingDataResult.getDocumentSummaries()) {
      var translationDocument = dataLanguageClient.findTranslationDocumentByUid(documentSummary.getDocumentUid())
        .orElseThrow(() -> new SystemException("Could not retrieve document " + documentSummary.getDocumentUid()));

      // Initialize the seller shares list
      PurchaseItemSellerShares.add(new PurchaseItemSellerShare(userService.findByCorporateId(translationDocument.getOwnerId()).orElseThrow(),
        translationDocument.getUid(),
        translationDocument.getName(),
        translationDocument.getPricePerWord(),
        0,
        0,
        0)
      );
    }

    // Stream the selection until a certain threshold based on the given amount and increment the proper seller share quantities
    matchingDataResult.getSelections(amount).forEach(selection ->
      PurchaseItemSellerShares.stream().
        filter(PurchaseItemSellerShare -> PurchaseItemSellerShare.getDocumentUid().equals(selection.getDocumentUid()))
        .findFirst().orElseThrow(() -> new SystemException("Document not present in summary when parsing selections: " + selection.getDocumentUid()))
        .incrementCounts(selection.getSourceWordCount(), selection.getTargetWordCount()));

    // Based on the given amount, not all sellers may get shares (ex. all of the segments of a seller's document wherein the
    // bottom of the selection and we chose a rather small amount to stream)
    return PurchaseItemSellerShares.stream().filter(PurchaseItemSellerShare -> PurchaseItemSellerShare.getPrice() > 0).collect(Collectors.toList());
  }

  /**
   * Convenience method used to calculate the available subsets of an MD result-set.
   * An available subset consists of a dataset size (ex. COMPACT 25%), individual counts and price.
   *
   * @param matchingDataResult The MD result that contains the selections we want to stream in order to calculate the
   *                           subset counts and prices
   * @return All the predefined (in {@link net.taus.data.marketplace.model.purchase.DatasetSize}) subsets with
   * individual metadata (ex. source/target word counts) and prices
   */
  private MatchingDataRequestSummary.AvailableSubsets getAvailableSubsets(MatchingDataResult matchingDataResult) {
    // Iterate the documentSummaries and get the price per document
    Map<UUID, Double> documentPriceMap = new HashMap<>();
    for (var documentSummary : matchingDataResult.getDocumentSummaries()) {
      var translationDocument = dataLanguageClient.findTranslationDocumentByUid(documentSummary.getDocumentUid())
        .orElseThrow(() -> new SystemException("Could not retrieve document " + documentSummary.getDocumentUid()));
      documentPriceMap.put(documentSummary.getDocumentUid(), translationDocument.getPricePerWord());
    }

    var priceSummary = new MatchingDataRequestSummary.AvailableSubsets(matchingDataResult.getTotalSourceWordCount());

    // Stream the selection and increment subset prices as we reach the end
    long totalSourceWordsParsed = 0;
    for (var selection : matchingDataResult.getSelections()) {
      var unitPrice = selection.getSourceWordCount() * documentPriceMap.get(selection.getDocumentUid());
      priceSummary.updateRows(totalSourceWordsParsed, unitPrice, selection.getSourceWordCount(), selection.getTargetWordCount());
      totalSourceWordsParsed += selection.getSourceWordCount();
    }

    return priceSummary;
  }

  /**
   * Default {@link TsvParserSettings}. Please note that we do not close the stream automatically, so you should use a
   * try-with-resources
   *
   * @return The default {@link TsvParserSettings}
   */
  private TsvParserSettings getDefaultParserSettings() {
    TsvParserSettings parserSettings = new TsvParserSettings();
    parserSettings.setLineJoiningEnabled(true);
    parserSettings.getFormat().setLineSeparator("\n");
    parserSettings.setAutoClosingEnabled(false);
    return parserSettings;
  }

}
