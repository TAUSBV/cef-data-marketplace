package net.taus.data.language.api.meta;

import net.taus.data.language.api.model.*;
import net.taus.data.language.api.model.ContentType;
import net.taus.data.language.api.model.Domain;
import net.taus.data.language.api.model.Language;
import net.taus.data.language.model.DataAnnotationCategoryRepository;
import net.taus.data.language.model.meta.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static net.taus.data.language.model.TranslationMetadataOrigin.MARKETPLACE;


@RestController
@RequestMapping("/meta")
public class MetadataLanguageController {

  private final LanguageRepository languages;
  private final DomainRepository domainRepository;
  private final ContentTypeRepository contentTypeRepository;
  private final DataAnnotationCategoryRepository annotationCategories;

  @Autowired
  public MetadataLanguageController(final LanguageRepository languages,
                                    final ContentTypeRepository contentTypes,
                                    final DomainRepository domains,
                                    final DataAnnotationCategoryRepository annotationCategories) {
    this.languages = languages;
    this.contentTypeRepository = contentTypes;
    this.domainRepository = domains;
    this.annotationCategories = annotationCategories;
  }

  @GetMapping(path = { "/domains" })
  public Iterable<Domain> listDomains() {
    return domainRepository.findByOrigin(MARKETPLACE);
  }

  @RequestMapping(value = { "/content_types" })
  public Iterable<ContentType> listContentTypes() {
    return this.contentTypeRepository.findByOrigin(MARKETPLACE);
  }

  @GetMapping({ "/languages_grouped" })
  @ResponseBody
  public ResponseEntity<CompositeLanguageSourceTargetCount> listLanguagesGrouped() {
    return ResponseEntity.ok(languages.findSourceTargetGroups());
  }

  @GetMapping({ "/languages" })
  @ResponseBody
  public ResponseEntity<List<Language>> listLanguages() {
    return ResponseEntity.ok(languages.findSummaryBy());
  }

  @GetMapping("/annotation_categories")
  @ResponseBody
  public ResponseEntity<List<DataAnnotationCategory>> listAnnotationCategories() {
    return ResponseEntity.ok(annotationCategories.findAllSummaryBy());
  }

}
