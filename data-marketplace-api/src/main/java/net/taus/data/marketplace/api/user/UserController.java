package net.taus.data.marketplace.api.user;


import net.taus.data.language.api.DataLanguageClient;
import net.taus.data.language.api.model.TranslationDocument;
import net.taus.data.marketplace.api.base.BaseController;
import net.taus.data.marketplace.api.purchase.TranslationUnitStreamer;
import net.taus.data.marketplace.api.security.RegistrationRequest;
import net.taus.data.marketplace.api.security.UserSession;
import net.taus.data.marketplace.api.validation.DocumentPrice;
import net.taus.data.marketplace.model.BusinessException;
import net.taus.data.marketplace.model.SystemException;
import net.taus.data.marketplace.model.product.Product;
import net.taus.data.marketplace.model.purchase.PurchaseItem;
import net.taus.data.marketplace.model.purchase.PurchaseItemSellerShare;
import net.taus.data.marketplace.model.user.User;
import net.taus.data.translationmemory.TranslationMemoryFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMethodName;

@RestController
@RequestMapping("/users")
@Validated
public class UserController extends BaseController {

  private final UserService userService;
  private final DataLanguageClient dataLanguageClient;
  private final TranslationUnitStreamer translationUnitStreamer;

  @Autowired
  public UserController(final UserService userService,
                        final DataLanguageClient dataLanguageClient,
                        final TranslationUnitStreamer translationUnitStreamer) {
    this.userService = userService;
    this.dataLanguageClient = dataLanguageClient;
    this.translationUnitStreamer = translationUnitStreamer;
  }

  @PostMapping(value = { "", "/" })
  public ResponseEntity<User> create(@RequestBody @Valid final RegistrationRequest request) throws SystemException {
    var user = userService.register(request);
    final var uri = fromMethodName(UserController.class, "show", user.getUid()).build().toUri();
    return ResponseEntity.created(uri).body(user);
  }

  @GetMapping(value = { "", "/" })
  public ResponseEntity<?> show(@AuthenticationPrincipal final UserSession userSession) throws SystemException {
    final var user = userService.findByUid(userSession.getUserUid());
    return user.map(ResponseEntity::ok).orElse(notFound().build());
  }

  @GetMapping(value = { "/documents/{documentUid}", "/documents/{documentUid}/" })
  public ResponseEntity<TranslationDocument> documents(@PathVariable(name = "documentUid") UUID documentUid,
                                                       @AuthenticationPrincipal final UserSession userSession) throws SystemException {
    final var document = dataLanguageClient.findTranslationDocumentByUid(documentUid);
    return document.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PutMapping(value = { "/documents/{documentUid}", "/documents/{documentUid}/" })
  public ResponseEntity<TranslationDocument> updateDocument(@PathVariable(name = "documentUid") UUID documentUid,
                                                            @RequestParam(value = "domainId", required = false) Long domainId,
                                                            @RequestParam(value = "contentTypeId", required = false) Long contentTypeId,
                                                            @RequestParam(value = "description", required = false) String description,
                                                            @RequestParam(value = "price", required = false)
                                                            @DocumentPrice @NumberFormat(pattern = "#.###") final Double pricePerWord) throws SystemException {
    final Optional<TranslationDocument> document = dataLanguageClient.save(documentUid, domainId, contentTypeId, description, pricePerWord);
    return document.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @GetMapping(value = { "/documents", "/documents/" })
  public ResponseEntity<?> documents(@AuthenticationPrincipal final UserSession userSession,
                                     @RequestParam(name = "k", required = false) String nameLike,
                                     @RequestParam(name = "pg", defaultValue = "0") int pageNumber) throws SystemException {
    pageNumber = pageNumber < 0 ? 0 : pageNumber;
    final var page = PageRequest.of(pageNumber, 20);
    final var user = dataLanguageClient.findTranslationDocumentByOwnerUid(userSession.getCorporateId(), null, nameLike, 0, page);
    return ResponseEntity.ok(user);
  }

  /**
   * This method is to allow a seller to download their own document. Our downloads are based on products that are based
   * on purchases. A seller that download their own document actually is buying the cleaning service of price ZERO. The
   * price is ZERO because they have published their document for sale.
   * We should evaluate to make this process clear to the seller, i.e. upon a request to download their own data a
   * purchase process with price ZERO should be started in the application so then they would follow the default process
   * to download their document which then would make this method unnecessary. This is important because we need to control
   * when a seller download their own document in order to prevent them to unpublish a document right after download a clean
   * version of it thus exploiting this process.
   * <p>
   * Please note that this is an async request and is using the
   * spring.mvc.async.request-timeout property
   *
   * @param documentUid
   * @param archived
   * @param response
   * @return
   * @throws IOException
   */
  @GetMapping(value = { "/documents/{documentUid}/download", "/documents/{documentUid}/download/" })
  public StreamingResponseBody download(@Validated @PathVariable("documentUid") @NotNull(message = "Document is mandatory") final UUID documentUid,
                                        @RequestParam(name = "archived", required = false, defaultValue = "false") final boolean archived,
                                        final HttpServletResponse response) throws IOException {
    return dataLanguageClient.findTranslationDocumentByUid(documentUid)
      .filter(d -> d.getStatus().equals("PUBLISHED"))
      .map(d -> userService.findByCorporateId(d.getOwnerId()).map(u -> {
        final var fileNameWithExtension = d.getName() + (archived ? ".zip" : ".tmx");

        response.addHeader("Content-disposition", "attachment;filename=" + fileNameWithExtension);
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.setStatus(HttpServletResponse.SC_OK);
        final PurchaseItemSellerShare share = PurchaseItemSellerShare.fromTranslationDocument(d, u);
        final Product product = Product.create(d, share.getPrice(), share.getTotalUnits(), share.getTotalSourceWords(), share.getTotalTargetWords());
        final PurchaseItem item = new PurchaseItem(product, List.of(share));
        return translationUnitStreamer.writeToStream(item, TranslationMemoryFormat.TMX, archived);
//      d.setOwnerDownloaded(true); // FIXME Save locally is is downloaded
//      documentRepository.save(d);
      }).orElseThrow(() -> new BusinessException("Could not find the requested seller")))
      .orElseThrow(() -> new BusinessException("Could not retrieve your document"));
  }
}