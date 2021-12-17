package net.taus.data.language.api.document;

import net.taus.data.language.api.model.LanguageDataParams;
import net.taus.data.language.api.model.OwnerDocumentStatisticsSummary;
import net.taus.data.language.api.model.OwnerStatisticsSummary;
import net.taus.data.language.api.model.OwnerSummary;
import net.taus.data.language.model.*;
import net.taus.data.language.model.meta.LanguageRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.lang.String.format;

@RestController
@RequestMapping("/owners")
public class OwnerTranslationDocumentController {

  private final LanguageRepository languages;
  private final TranslationDocumentRepository documents;

  public OwnerTranslationDocumentController(final LanguageRepository languages, final TranslationDocumentRepository documents) {
    this.languages = languages;
    this.documents = documents;
  }

  @GetMapping(value = { "" })
  public ResponseEntity<Page<OwnerSummary>> findOwnersSummary(
    @RequestParam(name = "nd", defaultValue = "2") final int numberOfDocuments,
    @RequestParam(name = "nu", defaultValue = "1") final int numberOfUnits,
    @RequestParam(name = "pg", defaultValue = "0") final int pageNumber,
    @RequestParam(name = "pgs", defaultValue = "6") final int pageSize) {
    final int pageNumberFinal = pageNumber < 0 ? 0 : pageNumber;
    final int pageSizeFinal = pageSize > 20 ? 20 : pageSize;
    final var page = PageRequest.of(pageNumberFinal, pageSizeFinal);
    final var summary = documents.findOwnersSummary(numberOfDocuments, numberOfUnits, page);
    return ResponseEntity.ok(summary);
  }

  @GetMapping(value = { "/{ownerId}" })
  public ResponseEntity<OwnerSummary> findOwnerSummaryByCorporateId(@PathVariable(name = "ownerId") final long ownerId) {
    final var ownerSummary = documents.findOwnerSummaryByOwnerId(ownerId);
    return ownerSummary.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @GetMapping("/statistics")
  public ResponseEntity<List<OwnerStatisticsSummary>> findOwnerStatisticsSummaryByLanguageDataParamsGroupByOwnerId(final LanguageDataParams params) {
    languages.findById(params.getSourceLanguageId()).orElseThrow(() -> new RuntimeException(format("Invalid source language %s.", params.getSourceLanguageId())));
    languages.findById(params.getTargetLanguageId()).orElseThrow(() -> new RuntimeException(format("Invalid target language %s.", params.getTargetLanguageId())));
    return ResponseEntity.ok(this.documents.findOwnerStatisticsSummaryByLanguageDataParamsGroupByOwnerId(params));
  }

  @GetMapping("/document_statistics")
  public ResponseEntity<List<OwnerDocumentStatisticsSummary>> findOwnerDocumentStatisticsSummaryByLanguageDataParamsGroupByOwnerId(final LanguageDataParams params) {
    languages.findById(params.getSourceLanguageId()).orElseThrow(() -> new RuntimeException(format("Invalid source language %s.", params.getSourceLanguageId())));
    languages.findById(params.getTargetLanguageId()).orElseThrow(() -> new RuntimeException(format("Invalid target language %s.", params.getTargetLanguageId())));
    return ResponseEntity.ok(this.documents.findOwnerDocumentStatisticsSummaryByLanguageDataParamsGroupByOwnerId(params));
  }

}
