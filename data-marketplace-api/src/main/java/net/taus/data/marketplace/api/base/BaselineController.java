package net.taus.data.marketplace.api.base;

import net.taus.data.language.api.model.CompositeLanguageSourceTargetCount;
import net.taus.data.language.api.model.ContentType;
import net.taus.data.language.api.DataLanguageClient;
import net.taus.data.language.api.model.Domain;
import net.taus.data.language.api.model.TranslationDocumentStatistics;
import net.taus.data.marketplace.api.validation.Country;
import net.taus.data.marketplace.model.price.LanguageDataAvailabilityCategoryRepository;
import net.taus.data.marketplace.model.price.views.LanguageDataAvailabilityCategorySummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BaselineController {

  private final LanguageDataAvailabilityCategoryRepository availabilityCategories;
  private final DataLanguageClient client;

  @Autowired
  public BaselineController(final LanguageDataAvailabilityCategoryRepository availabilityCategories, final DataLanguageClient client) {
    this.availabilityCategories = availabilityCategories;
    this.client = client;
  }

  @GetMapping(path = { "/base/domains", "/base/domains/" })
  public Iterable<Domain> domains() {
    return client.getDomains();
  }

  @RequestMapping(value = { "/base/content_types", "/base/content_types/" })
  public Iterable<ContentType> contentTypes() {
    return this.client.getContentTypes();
  }

  @ResponseBody
  @RequestMapping(value = { "/base/countries", "/base/countries/" })
  public ResponseEntity<List<Country>> countries() {
    return ResponseEntity.ok(Arrays.stream(Country.values()).collect(Collectors.toList()));
  }

  @ResponseBody
  @GetMapping({ "/categories", "/categories/" })
  public ResponseEntity<Iterable<LanguageDataAvailabilityCategorySummary>> categories() {
    return ResponseEntity.ok(availabilityCategories.findBy());
  }

  @ResponseBody
  @GetMapping({ "/base/languages", "/base/languages/" })
  public ResponseEntity<CompositeLanguageSourceTargetCount> languagesInterchangeable() {
    return ResponseEntity.ok(client.getCompositeLanguageSourceTargetCount());
  }

  @ResponseBody
  @GetMapping({ "/base/document_statistics", "/base/document_statistics/" })
  public ResponseEntity<List<TranslationDocumentStatistics>> documentStatistics() {
    return ResponseEntity.ok(client.findDocumentStatisticsSummary());
  }


}
