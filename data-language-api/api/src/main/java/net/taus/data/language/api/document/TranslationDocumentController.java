package net.taus.data.language.api.document;

import net.taus.data.language.model.TranslationDocument;
import net.taus.data.language.model.TranslationDocumentException;
import net.taus.data.language.model.TranslationDocumentRepository;
import net.taus.data.language.model.meta.ContentTypeRepository;
import net.taus.data.language.model.meta.DomainRepository;
import net.taus.data.language.model.meta.LanguageDataCharacteristics;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static net.taus.data.language.model.meta.LanguageDataCharacteristicsDomain.Type.DEFINED;
import static org.springframework.http.ResponseEntity.notFound;

@RestController
@RequestMapping("/documents")
public class TranslationDocumentController {

  private final TranslationDocumentRepository documents;
  private final DomainRepository domains;
  private final ContentTypeRepository contentTypes;

  public TranslationDocumentController(final TranslationDocumentRepository documents,
                                       final DomainRepository domains,
                                       final ContentTypeRepository contentTypes) {
    this.documents = documents;
    this.domains = domains;
    this.contentTypes = contentTypes;
  }

  @GetMapping(value = { "/{documentUid}" })
  public ResponseEntity<net.taus.data.language.api.model.TranslationDocument> findOneSummaryByUidAndStatusAndUnitQuality(@PathVariable(name = "documentUid") UUID documentUid,
                                                                                                                         @RequestParam(name = "ds", required = false) final String documentStatus,
                                                                                                                         @RequestParam(name = "uq", required = false) final String unitQuality,
                                                                                                                         @RequestParam(name = "nu", defaultValue = "1") final int numberOfUnits,
                                                                                                                         @RequestParam(name = "att", defaultValue = "false") final boolean annotations) {
    final var document = documents.findOneSummaryByUidAndStatusAndUnitQualityAndAnnotations(documentUid, documentStatus, unitQuality, numberOfUnits, annotations);
    return document.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @GetMapping(value = { "/owners/{ownerId}" })
  public ResponseEntity<Page<net.taus.data.language.api.model.TranslationDocument>> findByOwnerId(@PathVariable(name = "ownerId") final long ownerId,
                                                                                                  @RequestParam(name = "nu", defaultValue = "1") final int numberOfUnits,
                                                                                                  @RequestParam(name = "ds", required = false) final String documentStatus,
                                                                                                  @RequestParam(name = "k", required = false) final String nameLike,
                                                                                                  @RequestParam(name = "pg", defaultValue = "0") final int pageNumber,
                                                                                                  @RequestParam(name = "pgs", defaultValue = "6") final int pageSize) {
    final int pageNumberFinal = pageNumber < 0 ? 0 : pageNumber;
    final int pageSizeFinal = pageSize > 20 ? 20 : pageSize;
    final var page = PageRequest.of(pageNumberFinal, pageSizeFinal);
    final var user = documents.findPageSummaryByOwnerIdAndNameLike(ownerId, documentStatus, nameLike, numberOfUnits, page);
    return ResponseEntity.ok(user);
  }

  @GetMapping(value = { "/featured" })
  @ResponseBody
  public ResponseEntity<List<net.taus.data.language.api.model.TranslationDocument>> getFeatured(@RequestParam(name = "nd", defaultValue = "10") final int numberOfDocuments,
                                                                                                @RequestParam(name = "nu", defaultValue = "1") final int numberOfUnits) {
    final int actualNumberOfDocuments = numberOfDocuments < 0 || numberOfDocuments > 10 ? 10 : numberOfDocuments;
    final int actualNumberOfUnits = numberOfUnits < 0 || numberOfUnits > 5 ? 5 : numberOfUnits;
    return ResponseEntity.ok(documents.getFeatured(actualNumberOfDocuments, actualNumberOfUnits));
  }

  @PutMapping(value = { "/{documentUid}" })
  public ResponseEntity<net.taus.data.language.api.model.TranslationDocument> save(@PathVariable(name = "documentUid") final UUID documentUid,
                                                                                   @RequestParam(value = "domainId", required = false) Long domainId,
                                                                                   @RequestParam(value = "contentTypeId", required = false) Long contentTypeId,
                                                                                   @RequestParam(value = "description", required = false) String description,
                                                                                   @RequestParam(value = "pricePerWord", required = false) @DocumentPrice @NumberFormat(pattern = "#.###") final Double pricePerWord) {
    return documents.findByUid(documentUid).flatMap(d -> d.getCurrentCharacteristics().flatMap(c -> {
      final var characteristics = new LanguageDataCharacteristics();
      Optional.ofNullable(domainId).flatMap(id -> domains.findById(domainId)).ifPresent(dm -> characteristics.addDomain(dm, DEFINED, 1.0));
      Optional.ofNullable(contentTypeId).flatMap(id -> contentTypes.findById(contentTypeId)).ifPresent(characteristics::addContentType);
      Optional.ofNullable(pricePerWord).ifPresent(characteristics::setPricePerWord);
      boolean dirty = false;
      if (Objects.nonNull(description) && !description.isBlank()) {
        dirty = true;
        d.setDescription(description);
      }
      if (!c.equals(characteristics)) {
        dirty = true;
        d.addCharacteristics(characteristics);
      }
      if (dirty) {
        documents.save(d);
      }
      return documents.findOneSummaryByUid(d.getUid()).map(ResponseEntity::ok);
    })).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PutMapping(value = { "/{uid}/publish", "/{uid}/publish/" })
  @ResponseBody
  public ResponseEntity<net.taus.data.language.api.model.TranslationDocument> publish(@PathVariable("uid") final UUID documentUid,
                                                                                      @RequestParam("domainId") Long domainId,
                                                                                      @RequestParam("contentTypeId") Long contentTypeId,
                                                                                      @RequestParam("ownerId") Long ownerId,
                                                                                      @RequestParam(value = "description", required = false) String description,
                                                                                      @RequestParam(value = "pricePerWord", required = false) @DocumentPrice @NumberFormat(pattern = "#.###") final Double pricePerWord,
                                                                                      @RequestParam(value = "anonymize", defaultValue = "false") boolean anonymize) {
    return documents.findByUidWithStatistics(documentUid).flatMap(document -> {
      final var domain = domains.findById(domainId).orElseThrow(() -> new TranslationDocumentException("Invalid domain"));
      final var contentType = contentTypes.findById(contentTypeId).orElseThrow(() -> new TranslationDocumentException("Invalid content type"));
      if (!document.hasEnoughOriginals()) {
        throw new TranslationDocumentException("The document does not have enough high-quality data.");
      }
      if (document.getStatus().equals(TranslationDocument.Status.PUBLISHED)) {
        throw new TranslationDocumentException("The document is already published.");
      }
      List<LanguageDataCharacteristics> list = new ArrayList<>();
      final LanguageDataCharacteristics characteristics = new LanguageDataCharacteristics();
      characteristics.setPricePerWord(pricePerWord);
      characteristics.addDomain(domain, DEFINED, 1.0);
      characteristics.addContentType(contentType);
      list.add(characteristics);
      document.setCharacteristics(list);
      document.setDescription(description);
      document.setOwnerId(ownerId);
      document.setAnonymize(anonymize);
      documents.publish(document);
      return documents.findOneSummaryByUid(document.getUid()).map(ResponseEntity::ok);
    }).orElseGet(() -> notFound().build());
  }

  @PostMapping(value = "/{uid}/statistics")
  @ResponseBody
  public ResponseEntity<net.taus.data.language.api.model.TranslationDocument> updateStatistics(@PathVariable("uid") final UUID documentUid,
                                                                                               @RequestParam(name = "comment", required = false) final String comment) {
    return documents.findByUidWithStatistics(documentUid).flatMap(d -> {
      d = documents.updateStatistics(d, comment);
      return documents.findOneSummaryByUid(d.getUid()).map(ResponseEntity::ok);
    }).orElseGet(() -> notFound().build());
  }
}
