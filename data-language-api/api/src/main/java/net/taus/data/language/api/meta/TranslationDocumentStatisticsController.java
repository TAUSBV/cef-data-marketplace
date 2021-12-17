package net.taus.data.language.api.meta;

import net.taus.data.language.model.*;
import net.taus.data.language.model.meta.ContentTypeRepository;
import net.taus.data.language.model.meta.DomainRepository;
import net.taus.data.language.model.meta.LanguageDataCharacteristics;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("meta/document_statistics")
public class TranslationDocumentStatisticsController {

  private final TranslationDocumentStatisticsRepository documentStatistics;

  public TranslationDocumentStatisticsController(final TranslationDocumentStatisticsRepository documentStatistics) {
    this.documentStatistics = documentStatistics;
  }

  @GetMapping(value = { "" })
  public ResponseEntity<List<net.taus.data.language.api.model.TranslationDocumentStatistics>> list() {
    return ok(documentStatistics.findSummaryBy());
  }


}
