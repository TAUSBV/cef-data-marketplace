package net.taus.data.marketplace.api.price;

import net.taus.data.marketplace.model.price.PriceIndex;
import net.taus.data.marketplace.model.price.views.PriceIndexSummary;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;


@RestController
@RequestMapping("/price_index")
public class PriceIndexTableController {

  private final ExampleMatcher matcher;
  private final Map<String, String> sortNames;
  private final PriceIndexService priceIndexService;

  public PriceIndexTableController(final PriceIndexService priceIndexService) {
    this.priceIndexService = priceIndexService;
    this.matcher = ExampleMatcher.matchingAny()
      .withIgnorePaths(
        "languageDataAvailabilityCategory.higherBoundPercentile",
        "languageDataAvailabilityCategory.higherBound",
        "languageDataAvailabilityCategory.availabilityModifier",
        "languageDataAvailabilityCategory.sort",
        "updatedAt",
        "suggestedPricePerWord"
      )
      .withIgnoreNullValues()
      .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
      .withIgnoreCase();
    sortNames = Map.of(
      "domain", "languageDataAvailabilityCount.domainDescription",
      "contentType", "languageDataAvailabilityCount.contentTypeDescription",
      "sourceLanguage", "languageDataAvailabilityCount.sourceLanguageDisplayDame",
      "targetLanguage", "languageDataAvailabilityCount.targetLanguageDisplayName",
      "availability", "languageDataAvailabilityCategory.sort",
      "suggestedPrice", "suggestedPricePerWord"
    );
  }

  @GetMapping({ "", "/" })
  @ResponseBody
  public ResponseEntity<Iterable<PriceIndexSummary>> all(@RequestParam(name = "k", required = false) String keywords,
                                                         @RequestParam(name = "pg", defaultValue = "0") int pageNumber,
                                                         Sort sort) {
    keywords = Objects.isNull(keywords) ? "" : keywords.trim();
    pageNumber = pageNumber < 0 ? 0 : pageNumber;
    final var orders = sort
      .filter(s -> sortNames.containsKey(s.getProperty()))
      .map(s -> s.isAscending() ?
        Sort.Order.asc(sortNames.get(s.getProperty())) :
        Sort.Order.desc(sortNames.get(s.getProperty())))
      .toList();
    final var pageRequest = PageRequest.of(pageNumber, 20, Sort.by(orders));
    final var priceIndex = new PriceIndex();
    final var count = priceIndex.getLanguageDataAvailabilityCount();
    count.setSourceLanguageName(keywords);
    count.setSourceLanguageCountry(keywords);
    count.setSourceLanguageTag(keywords);
    count.setTargetLanguageName(keywords);
    count.setTargetLanguageCountry(keywords);
    count.setDomainDescription(keywords);
    count.setContentTypeDescription(keywords);
    priceIndex.getLanguageDataAvailabilityCategory().setLabel(keywords);
    var list = priceIndexService.findAll(Example.of(priceIndex, matcher), pageRequest);
    // FIXME until https://jira.spring.io/browse/DATAJPA-1033 gets
    //  implemented, the next two lines are necessary
    SpelAwareProxyProjectionFactory pf = new SpelAwareProxyProjectionFactory();
    var page = list.map(c -> pf.createProjection(PriceIndexSummary.class, c));
    return ResponseEntity.ok(page);
  }

  @GetMapping("/up")
  public ResponseEntity<?> update() {
    this.priceIndexService.update();
    return ResponseEntity.noContent().build();
  }
}
