package net.taus.data.language.api.search;

import net.taus.data.language.api.model.LanguageDataParams;
import net.taus.data.language.api.model.search.LanguageDataSearchDocumentResult;
import net.taus.data.language.api.model.search.LanguageDataSearchUnitResult;
import net.taus.data.language.model.meta.LanguageRepository;
import net.taus.data.language.model.search.LanguageDataSearcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.lang.String.format;

@RestController
@RequestMapping("/search")
public class LanguageDataSearchController {

  private final LanguageRepository languages;
  private final LanguageDataSearcher explorer;

  public LanguageDataSearchController(final LanguageRepository languages,
                                      final LanguageDataSearcher explorer) {
    this.languages = languages;
    this.explorer = explorer;
  }

  @ResponseBody
  @GetMapping({ "/units", "/units/" })
  public ResponseEntity<LanguageDataSearchUnitResult> units(final LanguageDataParams params) {
    languages.findById(params.getSourceLanguageId()).orElseThrow(() -> new RuntimeException(format("Invalid source language %s.", params.getSourceLanguageId())));
    languages.findById(params.getTargetLanguageId()).orElseThrow(() -> new RuntimeException(format("Invalid target language %s.", params.getTargetLanguageId())));
    var result = explorer.units(params);
    return ResponseEntity.ok(result);
  }

  @ResponseBody
  @GetMapping({ "/documents", "/documents/" })
  public ResponseEntity<LanguageDataSearchDocumentResult> documents(final LanguageDataParams params,
                                                                       @RequestParam(name = "pg", defaultValue = "0") int pageNumber,
                                                                       @RequestParam(name = "pgs", defaultValue = "10") int pageSize) {
    languages.findById(params.getSourceLanguageId()).orElseThrow(() -> new RuntimeException(format("Invalid source language %s.", params.getSourceLanguageId())));
    languages.findById(params.getTargetLanguageId()).orElseThrow(() -> new RuntimeException(format("Invalid target language %s.", params.getTargetLanguageId())));
    pageNumber = pageNumber < 0 ? 0 : pageNumber;
    pageSize = pageSize < 0 ? 0 : pageSize;
    pageSize = pageSize > 20 ? 20 : pageSize;
    var result = explorer.documents(params, PageRequest.of(pageNumber, pageSize));
    return ResponseEntity.ok(result);
  }

}
