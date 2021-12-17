package net.taus.data.marketplace.api.purchase.controllers;

import net.taus.data.marketplace.api.base.BaseController;
import net.taus.data.marketplace.api.purchase.TranslationUnitStreamer;
import net.taus.data.marketplace.api.purchase.services.PurchaseService;
import net.taus.data.marketplace.model.BusinessException;
import net.taus.data.marketplace.model.purchase.Download;
import net.taus.data.marketplace.model.purchase.DownloadRepository;
import net.taus.data.marketplace.model.purchase.views.PurchaseSummary;
import net.taus.data.translationmemory.TranslationMemoryFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.util.UUID;

import static net.taus.data.marketplace.model.purchase.Download.Status.AVAILABLE;

@RestController
@RequestMapping("/purchases")
public class PurchaseController extends BaseController {

  private final PurchaseService purchaseService;
  private final DownloadRepository downloadRepository;
  private final TranslationUnitStreamer translationUnitStreamer;


  public PurchaseController(final PurchaseService purchaseService,
                            final DownloadRepository downloadRepository,
                            final TranslationUnitStreamer translationUnitStreamer) {
    this.purchaseService = purchaseService;
    this.downloadRepository = downloadRepository;
    this.translationUnitStreamer = translationUnitStreamer;
  }

  @GetMapping(value = { "/{uid}", "/{uid}/" })
  public ResponseEntity<PurchaseSummary> get(@Validated @PathVariable("uid") @NotNull(message = "Purchase is mandatory") final UUID purchaseUid) {
    return purchaseService
      .findByUid(purchaseUid)
      .map(p -> ResponseEntity.ok(PurchaseSummary.getSummary(p)))
      .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping(value = { "/{uid}/downloads", "/{uid}/downloads/" })
  public ResponseEntity<PurchaseSummary> createDownload(@Validated @PathVariable("uid") @NotNull(message = "Purchase is mandatory") final UUID purchaseUid) {
    return purchaseService
      .createDownloadAndPublish(purchaseUid)
      .map(p -> ResponseEntity.ok(PurchaseSummary.getSummary(p)))
      .orElseGet(() -> ResponseEntity.notFound().build());
  }

  /**
   * Lets the user download their purchase items. Please note that this is an async request and is using the
   * spring.mvc.async.request-timeout property
   *
   * @param downloadUid The download uid that was generated for the user is set to consumed immediately after we receive the request
   * @param archived    Whether the user will receive the file as-is (ex. product1.tmx) or in a zip file which drastically reduces the download size
   * @param response    The http response
   * @return a {@link StreamingResponseBody} as we write the transformed translation units to it
   */
  @GetMapping(value = "/downloads/{uid}")
  public StreamingResponseBody download(@Validated @PathVariable("uid") @NotNull(message = "Purchase Download is mandatory") final UUID downloadUid,
                                        @RequestParam(name = "archived", required = false, defaultValue = "false") final boolean archived,
                                        final HttpServletResponse response) {
    return downloadRepository.findByUidAndStatus(downloadUid, AVAILABLE)
      .map(d -> {
        final var item = d.getItem();
        final var fileNameWithExtension = item.getProduct().getName() + (archived ? ".zip" : ".tmx");

        d.setStatus(Download.Status.CONSUMED);
        downloadRepository.save(d);

        response.addHeader("Content-disposition", "attachment;filename=" + fileNameWithExtension);
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.setStatus(HttpServletResponse.SC_OK);
        return translationUnitStreamer.writeToStream(item, TranslationMemoryFormat.TMX, archived);

      })
      .orElseThrow(() -> new BusinessException("Link invalid or already consumed."));

  }

}
