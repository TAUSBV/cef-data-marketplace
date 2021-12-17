package net.taus.data.language.api.unit;

import net.taus.data.language.api.model.LanguageDataParams;
import net.taus.data.language.api.model.PageById;
import net.taus.data.language.api.model.TranslationUnit;
import net.taus.data.language.api.unit.TranslationUnitForm.TranslationUniDomain;
import net.taus.data.language.api.unit.TranslationUnitForm.TranslationVariantForm.DataAnnotationForm;
import net.taus.data.language.exception.BusinessException;
import net.taus.data.language.model.*;
import net.taus.data.language.model.TranslationUnit.Quality;
import net.taus.data.language.model.meta.Domain;
import net.taus.data.language.model.meta.DomainRepository;
import net.taus.data.language.model.meta.LanguageDataCharacteristics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static net.taus.data.language.api.unit.TranslationUnitForm.TranslationUniDomain.getTypeParsed;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Transactional
@RequestMapping("/units")
public class TranslationUnitController {

  private final TranslationUnitRepository units;
  private final DomainRepository domains;
  private final DataAnnotationCategoryRepository annotationCategories;
  private final TranslationUnitFinder finder;
  private final Logger logger;

  public TranslationUnitController(final TranslationUnitRepository units,
                                   final DomainRepository domains,
                                   final DataAnnotationCategoryRepository annotationCategories,
                                   final TranslationUnitFinder finder) {
    this.units = units;
    this.domains = domains;
    this.annotationCategories = annotationCategories;
    this.finder = finder;
    this.logger = LoggerFactory.getLogger(TranslationUnitController.class.getSimpleName());
  }

  @GetMapping(value = { "/search" })
  public ResponseEntity<PageById<TranslationUnit>> findUnitsByLanguageDataParams(final LanguageDataParams params,
                                                                                 @RequestParam(name = "pg", defaultValue = "0") final int pageNumber,
                                                                                 @RequestParam(name = "pgs", defaultValue = "10000") final int pageSize) {
    final var actualPageSize = pageSize > 10000 ? 10000 : pageSize;
    final PageRequest pageRequest = PageRequest.of(pageNumber, actualPageSize);
    return ResponseEntity.ok(finder.findPageByLanguageDataParams(params, pageRequest));
  }

  @GetMapping(value = { "" }, consumes = APPLICATION_JSON_VALUE)
  public ResponseEntity<List<TranslationUnit>> findUnitsByIds(@RequestBody final Collection<Long> ids) {
    return ResponseEntity.ok(units.findByIds(ids));
  }

  @PostMapping(value = "", consumes = APPLICATION_JSON_VALUE)
  public ResponseEntity<List<TranslationUnit>> saveAll(@RequestBody(required = false) final List<TranslationUnitForm> unitList) {
    logger.info("{}", unitList);
    for (TranslationUnitForm unitForm : unitList) {
      final net.taus.data.language.model.TranslationUnit unit = units.getOne(unitForm.getId());
      Optional.ofNullable(unitForm.getQuality()).filter(q -> !q.isBlank()).ifPresent(q -> {
        unit.setQuality(Quality.valueOf(q));
        unit.setQualityFlags(unitForm.getQualityFlags());
      });
      final List<TranslationUniDomain> domainFormList = unitForm.getDomains();
      if (!domainFormList.isEmpty()) {
        final List<Long> domainIds = domainFormList.stream().map(TranslationUniDomain::getId).distinct().collect(toList());
        final List<Domain> domainList = this.domains.findAllByIdIn(domainIds);
        if (domainIds.size() != domainList.size()) {
          domainIds.removeIf(id -> domainList.stream().anyMatch(d -> d.getId().equals(id)));
          throw new BusinessException(String.format("Invalid domains %s", domainIds));
        }
        final LanguageDataCharacteristics characteristics = unit.getCurrentOrCreateCharacteristics();
        domainFormList.forEach(d -> domainList.stream().filter(df -> d.getId().equals(df.getId()))
          .findFirst().ifPresent(df -> characteristics.addDomain(df, getTypeParsed(d.getType()), d.getProbability())));
      }
      Optional.ofNullable(unitForm.getSource()).ifPresent(variantForm -> saveVariant(variantForm, unit.getSource()));
      Optional.ofNullable(unitForm.getTarget()).ifPresent(variantForm -> saveVariant(variantForm, unit.getTarget()));
      units.save(unit);
    }
    return ResponseEntity.ok(units.findByIds(unitList.stream().map(TranslationUnitForm::getId).collect(toList())));
  }

  public void saveVariant(final TranslationUnitForm.TranslationVariantForm variantForm, final TranslationVariant variant) {
    final List<DataAnnotationForm> annotations = variantForm.getAnnotations();
    final List<Long> categoriesIds = annotations.stream().map(DataAnnotationForm::getCategoryId).collect(toList());
    final List<DataAnnotationCategory> categories = annotationCategories.findAllById(categoriesIds);
    if (categoriesIds.size() != categories.size()) {
      categoriesIds.removeIf(id -> categories.stream().anyMatch(c -> c.getId().equals(id)));
      throw new BusinessException(String.format("Invalid categories %s", categoriesIds));
    }
    annotations.forEach(a -> categories.stream().filter(c -> a.getCategoryId().equals(c.getId()))
      .findFirst().ifPresent(c -> variant.addAnnotation(c, a.getOffsetStart(), a.getOffsetEnd())));
  }

}
