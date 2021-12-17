package net.taus.data.marketplace.api.price;

import net.taus.data.language.api.DataLanguageClient;
import net.taus.data.marketplace.api.base.BaseController;
import net.taus.data.marketplace.model.BusinessException;
import net.taus.data.marketplace.model.price.PriceSuggestion;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/price")
public class PriceSuggestionController extends BaseController {

  private final PriceSuggestionService priceSuggestionService;
  private final DataLanguageClient dataLanguageClient;

  public PriceSuggestionController(final PriceSuggestionService priceSuggestionService, final DataLanguageClient dataLanguageClient) {
    this.priceSuggestionService = priceSuggestionService;
    this.dataLanguageClient = dataLanguageClient;
  }

  @GetMapping({ "", "/" })
  @ResponseBody
  public ResponseEntity<PriceSuggestion> suggest(
    @RequestParam(name = "domainId") Long domainId,
    @RequestParam(name = "contentTypeId") Long contentTypeId,
    @RequestParam(name = "documentUid") UUID documentUid
  ) throws BusinessException {
    final var domain = dataLanguageClient.getDomains().stream().filter(d -> d.getId().equals(domainId)).findFirst().orElseThrow(BusinessException::missingDomain);
    final var contentType = dataLanguageClient.getContentTypes().stream().filter(d -> d.getId().equals(contentTypeId)).findFirst().orElseThrow(BusinessException::missingContentType);
    final var document = dataLanguageClient.findTranslationDocumentByUid(documentUid).orElseThrow(() -> new BusinessException("Document not found."));
    final Long sourceLanguageId = document.getSourceLanguage().getId();
    final Long targetLanguageId = document.getTargetLanguage().getId();
    final var priceSuggestion = priceSuggestionService.getPriceSuggestion(sourceLanguageId, targetLanguageId, domain.getId(), contentType.getId());
    return ResponseEntity.ok(priceSuggestion);
  }

  @GetMapping({ "/suggest" })
  @ResponseBody
  public ResponseEntity<PriceSuggestion> suggest(
    @RequestParam(name = "sourceLanguageId") Long sourceLanguageId,
    @RequestParam(name = "targetLanguageId") Long targetLanguageId,
    @RequestParam(name = "domainId") Long domainId,
    @RequestParam(name = "contentTypeId") Long contentTypeId
  ) throws BusinessException {
    final var priceSuggestion = priceSuggestionService.getPriceSuggestion(sourceLanguageId, targetLanguageId, domainId, contentTypeId);
    return ResponseEntity.ok(priceSuggestion);
  }
}
