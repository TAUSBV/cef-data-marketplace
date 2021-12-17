package net.taus.data.marketplace.api.purchase;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.taus.data.language.api.DataLanguageClient;
import net.taus.data.language.api.model.LanguageDataParams;
import net.taus.data.language.api.model.TranslationUnit;
import net.taus.data.language.api.model.TranslationUnitUnicity;
import net.taus.data.marketplace.api.matchingdata.MatchingDataResult;
import net.taus.data.marketplace.api.matchingdata.MatchingDataService;
import net.taus.data.marketplace.model.BusinessException;
import net.taus.data.marketplace.model.SystemException;
import net.taus.data.marketplace.model.matchingdata.MatchingDataRequest;
import net.taus.data.marketplace.model.product.Product;
import net.taus.data.marketplace.model.purchase.PurchaseItem;
import net.taus.data.marketplace.model.purchase.PurchaseItemSellerShare;
import net.taus.data.translationmemory.DefaultTranslationMemoryHeader;
import net.taus.data.translationmemory.DefaultTranslationMemoryUnit;
import net.taus.data.translationmemory.DefaultTranslationMemoryVariant;
import net.taus.data.translationmemory.TranslationMemoryFormat;
import net.taus.data.translationmemory.writer.TranslationMemoryWriter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.BufferedOutputStream;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static net.taus.data.language.api.model.TranslationUnitUnicity.ORIGINAL_AND_REPLICA;
import static net.taus.data.language.api.model.TranslationUnitUnicity.ORIGINAL_ONlY;
import static net.taus.data.marketplace.model.product.Product.Type.MATCHING_DOCUMENT;
import static net.taus.data.marketplace.model.product.Product.Type.SELLER_DOCUMENT;

/**
 * Responsible for retrieving translation units from purchase items through the data-language-api and streaming them
 * to a {@link StreamingResponseBody} so as to give a sense of immediate response to the user.
 * <p>
 * FIXME Maybe this is not the most ideal solution as it may take too long to read/write the results.
 * Maybe this could be split in a two-step operation where the user is notified about the progress of the processing,
 * and then when the process is done, downloads the file(s)
 */
public class TranslationUnitStreamer {

  private final DataLanguageClient client;
  private final MatchingDataService matchingDataService;
  private final int pageSize;

  public TranslationUnitStreamer(final DataLanguageClient client,
                                 final MatchingDataService matchingDataService,
                                 final int pageSize) {
    this.client = client;
    this.matchingDataService = matchingDataService;
    this.pageSize = pageSize;
  }

  /**
   * Based on the item's product type, we retrieve the respective translation units from the data-language-api.
   * We convert them to the appropriate format (currently only TMX is supported) and write them to the {@link StreamingResponseBody}
   *
   * @param item    the purchase item we want to download
   * @param format  the document's format (currently only TMX is supported)
   * @param archive whether we want the generated file to be included in a zip, drastically reducing it's size.
   *                This does not necessarily mean that the download time will be reduced as well, as the read/write
   *                operation takes much longer than what a mediocre-speed client can consume.
   * @return the {@link StreamingResponseBody} as we write to it
   */
  public StreamingResponseBody writeToStream(final PurchaseItem item,
                                             final TranslationMemoryFormat format,
                                             final boolean archive) {
    return outputStream -> {
      final var product = item.getProduct();
      final var header = product.getSourceLanguage().map(p -> new DefaultTranslationMemoryHeader(p.getTag()))
        .orElseGet(() -> new DefaultTranslationMemoryHeader("all"));

      if (archive) {
        ZipOutputStream zipOut = new ZipOutputStream(new BufferedOutputStream(outputStream));
        ZipEntry e = new ZipEntry(product.getName() + ".tmx");
        zipOut.putNextEntry(e);
        outputStream = zipOut;
      }

      try (final var writer = TranslationMemoryWriter.create(header, outputStream, format)) {
        if (item.getProduct().getType().equals(MATCHING_DOCUMENT))
          streamMatchingDocument(item, writer);
        else
          streamWithLanguageDataParams(item, writer);
      }
      catch (Exception e) {
        throw new SystemException(e.getMessage(), e);
      }

      outputStream.close();

    };
  }

  /**
   * This is the used to stream translation units for SELLER and VIRTUAL docs.
   * <p>
   * FIXME Please note that this operation takes TOO LONG for large documents (ex. 1M segments). To a point where download is incomplete even after 1h
   *
   * @param item   the purchase item we want to download
   * @param writer the writer we use to write each translation unit
   */
  private void streamWithLanguageDataParams(final PurchaseItem item, final TranslationMemoryWriter writer) {
    final Product product = item.getProduct();
    final TranslationUnitUnicity unitUnicity = product.getType().equals(SELLER_DOCUMENT) ? ORIGINAL_AND_REPLICA : ORIGINAL_ONlY;
    final UUID[] documentUids = item.getShares().stream().map(PurchaseItemSellerShare::getDocumentUid).toArray(UUID[]::new);
    final LanguageDataParams params = LanguageDataParams.builder()
      .withDocumentUids(documentUids)
      .withUnitUnicity(unitUnicity)
      .withReferenceDateTime(product.getCreatedAt())
      .build();
    long lastSeenUnitId = 0;
    long total = item.getTotalUnits();
    long actualPageSize = total < pageSize ? total : pageSize;
    do {
      // FIXME move away from spring-data Page in order to support high page number values, i.e. long instead of int
      Pageable page = PageRequest.of((int) lastSeenUnitId, (int) actualPageSize);
      System.out.println("Getting page:" + page.getPageNumber());
      Page<TranslationUnit> units = client.findUnitsByLanguageDataParams(params, page);
      for (TranslationUnit u : units) {
        var tmu = getTranslationMemoryUnit(u);
        writer.write(tmu);
        lastSeenUnitId = u.getId();
      }
      total -= page.getPageSize();
      actualPageSize = total < pageSize ? total : pageSize;
    } while (total > 0);
  }

  /**
   * @param item   the purchase item we want to download
   * @param writer the writer we use to write each translation unit
   * @throws JsonProcessingException in case there is an error when serializing the translation unit ids we want to
   *                                 retrieve from the data-language-api
   */
  private void streamMatchingDocument(PurchaseItem item, TranslationMemoryWriter writer) throws JsonProcessingException {
    MatchingDataRequest matchingDataRequest = item.getProduct().getMatchingDataRequest();
    Optional<MatchingDataResult> matchingDataResult = matchingDataService.getMatchingDataResult(matchingDataRequest);
    if (matchingDataResult.isEmpty())
      throw new BusinessException("Could not find the Matching Data results for your request");
    var selections = matchingDataResult.get().getSelections(item.getAmount());

    int batchSize = 10000;
    for (int fromIndex = 0; fromIndex < selections.size(); fromIndex += batchSize) {
      var toIndex = Math.min(fromIndex + batchSize, selections.size());
      client.findTranslationUnitsByIds(selections.subList(fromIndex, toIndex)
        .stream().mapToLong(MatchingDataResult.Selection::getTranslationUnitId).boxed()
        .collect(Collectors.toList())).forEach(translationUnit -> writer.write(getTranslationMemoryUnit(translationUnit)));
    }
  }

  /**
   * Helper function that returns a {@link DefaultTranslationMemoryUnit} based on a {@link TranslationUnit}
   *
   * @param translationUnit the translation unit we want to transform
   * @return the translation memory unit produced by translationUnit
   */
  private DefaultTranslationMemoryUnit getTranslationMemoryUnit(TranslationUnit translationUnit) {
    var source = new DefaultTranslationMemoryVariant(translationUnit.getSource().getLanguage().getTag(), translationUnit.getSource().getValue());
    var target = new DefaultTranslationMemoryVariant(translationUnit.getTarget().getLanguage().getTag(), translationUnit.getTarget().getValue());
    return new DefaultTranslationMemoryUnit(translationUnit.getSequence(), source, target);
  }

}
