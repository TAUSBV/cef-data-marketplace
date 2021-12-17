package net.taus.data.marketplace.api.languagedata;

import net.taus.data.language.api.DataLanguageSearchClient;
import net.taus.data.language.api.model.LanguageDataParams;
import net.taus.data.marketplace.api.user.UserService;
import net.taus.data.marketplace.model.BusinessException;
import net.taus.data.marketplace.model.languagedata.TranslationDocumentReviewRepository;
import net.taus.data.marketplace.model.user.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static java.lang.String.format;

@RestController
@RequestMapping("/search")
public class LanguageDataSearchController {

  private final DataLanguageSearchClient searchClient;
  private final UserService users;
  private final TranslationDocumentReviewRepository reviews;

  public LanguageDataSearchController(final DataLanguageSearchClient searchClient,
                                      final UserService users,
                                      final TranslationDocumentReviewRepository reviews) {
    this.searchClient = searchClient;
    this.users = users;
    this.reviews = reviews;
  }

  @ResponseBody
  @GetMapping({ "/units" })
  public ResponseEntity<LanguageDataSearchUnitResultLocal> units(
    @RequestParam(name = "s") final Long sourceLanguageId,
    @RequestParam(name = "t") final Long targetLanguageId,
    @RequestParam(name = "d", required = false) final Long[] domains,
    @RequestParam(name = "c", required = false) final Long[] contentTypes,
    @RequestParam(name = "u", required = false) final UUID[] sellerUids,
    @RequestParam(name = "pmin", required = false) final Double priceMin,
    @RequestParam(name = "pmax", required = false) final Double priceMax
  ) {
    final LanguageDataParams params = getParams(sourceLanguageId, targetLanguageId, domains, contentTypes, sellerUids, priceMin, priceMax);
    var result = searchClient.units(params);
    return ResponseEntity.ok(new LanguageDataSearchUnitResultLocal(result, users));
  }

  @ResponseBody
  @GetMapping({ "/documents" })
  public ResponseEntity<LanguageDataSearchDocumentResultLocal> documents(
    @RequestParam(name = "s") final Long sourceLanguageId,
    @RequestParam(name = "t") final Long targetLanguageId,
    @RequestParam(name = "d", required = false) final Long[] domains,
    @RequestParam(name = "c", required = false) final Long[] contentTypes,
    @RequestParam(name = "u", required = false) final UUID[] sellerUids,
    @RequestParam(name = "pmin", required = false) final Double priceMin,
    @RequestParam(name = "pmax", required = false) final Double priceMax,
    @RequestParam(name = "pg", defaultValue = "0") int pageNumber,
    @RequestParam(name = "pgs", defaultValue = "10") int pageSize
  ) {
    pageNumber = pageNumber < 0 ? 0 : pageNumber;
    pageSize = pageSize < 0 ? 0 : pageSize;
    pageSize = pageSize > 20 ? 20 : pageSize;
    final LanguageDataParams params = getParams(sourceLanguageId, targetLanguageId, domains, contentTypes, sellerUids, priceMin, priceMax);
    var result = searchClient.documents(params, PageRequest.of(pageNumber, pageSize));
    return ResponseEntity.ok(new LanguageDataSearchDocumentResultLocal(result, users, reviews));
  }

  private LanguageDataParams getParams(final Long sourceLanguageId,
                                       final Long targetLanguageId,
                                       final Long[] domains,
                                       final Long[] contentTypes,
                                       final UUID[] sellerUids,
                                       final Double priceMin,
                                       final Double priceMax) {
    if (Objects.isNull(sourceLanguageId)) {
      throw new BusinessException(format("Invalid source language %s.", sourceLanguageId));
    }
    if (Objects.isNull(targetLanguageId)) {
      throw new BusinessException(format("Invalid target language %s.", targetLanguageId));
    }
    final Long[] ownerIds = Optional.ofNullable(sellerUids)
      .map(s -> users.findAllByUidIn(Arrays.asList(s)).stream().map(User::getCorporateId).toArray(Long[]::new))
      .orElse(null);
    final LanguageDataParams.LanguageDataParamsBuilder builder = LanguageDataParams.builder()
      .withSourceLanguageId(sourceLanguageId)
      .withTargetLanguageId(targetLanguageId)
      .withDomainIds(domains)
      .withContentTypeIds(contentTypes)
      .withOwnerIds(ownerIds).withPriceMin(priceMin).withPriceMax(priceMax);
    return builder.build();
  }


}
