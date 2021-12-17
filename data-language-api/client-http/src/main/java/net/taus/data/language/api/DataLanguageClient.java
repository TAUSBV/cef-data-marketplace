package net.taus.data.language.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.taus.data.language.api.model.*;
import net.taus.data.language.api.web.QueryParam;
import net.taus.data.language.api.web.WebClient;
import net.taus.data.language.api.web.WebRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.*;

import static java.lang.String.format;
import static net.taus.data.language.api.Endpoints.*;

public class DataLanguageClient {

  private final WebClient http;

  public DataLanguageClient(final String url) {
    this.http = new WebClient(url);
  }

  public DataLanguageClient(final WebClient webClient) {
    this.http = webClient;
  }

  public List<Domain> getDomains() throws WebRequestException {
    return http.list(DOMAINS, List.of(), Domain.class);
  }

  public List<ContentType> getContentTypes() throws WebRequestException {
    return http.list(CONTENT_TYPES, List.of(), ContentType.class);
  }

  public CompositeLanguageSourceTargetCount getCompositeLanguageSourceTargetCount() throws WebRequestException {
    return http.get(LANGUAGES_GROUPED, List.of(), CompositeLanguageSourceTargetCount.class);
  }

  public List<Language> getLanguages() throws WebRequestException {
    return http.list(LANGUAGES, List.of(), Language.class);
  }

  public Optional<TranslationDocument> publish(final UUID documentUid,
                                               final Long domainId,
                                               final Long contentTypeId,
                                               final Long ownerId,
                                               final String description,
                                               final Double pricePerWord,
                                               final boolean anonymize) {
    List<QueryParam> params = new ArrayList<>(5);
    params.add(new QueryParam("domainId", Objects.requireNonNull(domainId, "Document domain is mandatory").toString()));
    params.add(new QueryParam("contentTypeId", Objects.requireNonNull(contentTypeId, "Document content type is mandatory").toString()));
    params.add(new QueryParam("ownerId", Objects.requireNonNull(ownerId, "Document owner is mandatory").toString()));
    params.add(new QueryParam("pricePerWord", Objects.requireNonNull(pricePerWord, "Document price per word is mandatory").toString()));
    params.add(new QueryParam("anonymize", Boolean.toString(anonymize)));
    if (Objects.nonNull(description)) {
      params.add(new QueryParam("description", description));
    }
    return Optional.ofNullable(http.put(format("%s/%s/publish", DOCUMENTS, documentUid), params, TranslationDocument.class));
  }

  public Optional<TranslationDocument> save(final UUID documentUid,
                                            final Long domainId,
                                            final Long contentTypeId,
                                            final String description,
                                            final Double pricePerWord) {
    List<QueryParam> params = new ArrayList<>(4);
    if (Objects.nonNull(domainId)) {
      params.add(new QueryParam("domainId", domainId.toString()));
    }
    if (Objects.nonNull(contentTypeId)) {
      params.add(new QueryParam("contentTypeId", contentTypeId.toString()));
    }
    if (Objects.nonNull(description)) {
      params.add(new QueryParam("description", description));
    }
    if (Objects.nonNull(pricePerWord)) {
      params.add(new QueryParam("pricePerWord", pricePerWord.toString()));
    }
    return Optional.ofNullable(http.put(format("%s/%s", DOCUMENTS, documentUid), params, TranslationDocument.class));
  }

  public Optional<TranslationDocument> findTranslationDocumentByUid(final UUID documentUid) {
    return Optional.ofNullable(http.get(format("%s/%s", DOCUMENTS, documentUid), List.of(), TranslationDocument.class));
  }

  public Optional<TranslationDocument> findTranslationDocumentPublishedByUid(final UUID documentUid, final int numberOfUnits) {
    return findTranslationDocumentWithUnitsByDocumentUidAndDocumentStatusAndUnitQuality(documentUid, "PUBLISHED", "HIGH", numberOfUnits);
  }

  public Optional<TranslationDocument> findTranslationDocumentWithUnitsByDocumentUidAndDocumentStatusAndUnitQuality(final UUID documentUid,
                                                                                                                    final String documentStatus,
                                                                                                                    final String unitQuality,
                                                                                                                    final int numberOfUnits) {
    return this.findTranslationDocumentWithUnitsByDocumentUidAndDocumentStatusAndUnitQualityAndAnnotations(documentUid, documentStatus, unitQuality, numberOfUnits, false);
  }

  public Optional<TranslationDocument> findTranslationDocumentWithUnitsByDocumentUidAndDocumentStatusAndUnitQualityAndAnnotations(final UUID documentUid,
                                                                                                                                  final String documentStatus,
                                                                                                                                  final String unitQuality,
                                                                                                                                  final int numberOfUnits,
                                                                                                                                  final boolean annotations) {
    final List<QueryParam> queryString = List.of(
      new QueryParam("ds", Objects.requireNonNullElse(documentStatus, "PUBLISHED")),
      new QueryParam("uq", Objects.requireNonNullElse(unitQuality, "HIGH")),
      new QueryParam("nu", String.valueOf(numberOfUnits)),
      new QueryParam("att", String.valueOf(annotations))
    );
    return Optional.ofNullable(http.get(format("%s/%s", DOCUMENTS, documentUid), queryString, TranslationDocument.class));
  }

  public List<TranslationDocument> getFeaturedTranslationDocument(final int numberOfDocuments, final int numberOfUnits) {
    final List<QueryParam> queryString = List.of(
      new QueryParam("nd", String.valueOf(numberOfDocuments)),
      new QueryParam("nu", String.valueOf(numberOfUnits))
    );
    return http.list(format("%s/featured", DOCUMENTS), queryString, TranslationDocument.class);
  }

  public Page<TranslationDocument> findTranslationDocumentByOwnerUid(final Long ownerId, final String documentStatus,
                                                                     final String nameLike, final int numberOfUnits,
                                                                     final Pageable pageRequest) {
    final List<QueryParam> params = new ArrayList<>();
    if (Objects.nonNull(nameLike) && !nameLike.isBlank()) {
      params.add(new QueryParam("k", nameLike));
    }
    if (Objects.nonNull(documentStatus) && !documentStatus.isBlank()) {
      params.add(new QueryParam("ds", documentStatus));
    }
    params.add(new QueryParam("nu", String.valueOf(numberOfUnits)));
    params.add(new QueryParam("pg", String.valueOf(pageRequest.getPageNumber())));
    params.add(new QueryParam("pgs", String.valueOf(pageRequest.getPageSize())));
    return http.page(format("%s/%s", DOCUMENTS_OWNER, ownerId), params, TranslationDocument.class);
  }

  public Page<OwnerSummary> findOwnersSummary(final int numberOfDocuments, final int numberOfUnits, final Pageable pageRequest) {
    final List<QueryParam> queryString = List.of(
      new QueryParam("nd", String.valueOf(numberOfDocuments)),
      new QueryParam("nu", String.valueOf(numberOfUnits)),
      new QueryParam("pg", String.valueOf(pageRequest.getPageNumber())),
      new QueryParam("pgs", String.valueOf(pageRequest.getPageSize()))
    );
    return http.page(format("%s", OWNERS), queryString, OwnerSummary.class);
  }

  public Optional<OwnerSummary> findOwnerSummaryByOwnerId(final Long ownerId) {
    return Optional.ofNullable(http.get(format("%s/%s", OWNERS, ownerId), List.of(), OwnerSummary.class));
  }

  public Page<TranslationUnit> findUnitsByLanguageDataParams(final LanguageDataParams params,
                                                             final Pageable pageRequest) {
    final List<QueryParam> queryParams = new ArrayList<>();
    queryParams.add(new QueryParam("pg", String.valueOf(pageRequest.getPageNumber())));
    queryParams.add(new QueryParam("pgs", String.valueOf(pageRequest.getPageSize())));
    queryParams.addAll(QueryParam.transform(params));
    return http.page(format("%s", Endpoints.UNITS_SEARCH), queryParams, TranslationUnit.class);
  }

  public List<TranslationUnit> findTranslationUnitsByIds(List<Long> ids) throws JsonProcessingException {
    return http.list(UNITS, ids, TranslationUnit.class);
  }

  public List<OwnerStatisticsSummary> findOwnerStatisticsSummaryByLanguageDataParamsGroupByOwnerId(final LanguageDataParams params) {
    return http.list(format("%s", OWNERS_STATISTICS), QueryParam.transform(params), OwnerStatisticsSummary.class);
  }

  public List<OwnerDocumentStatisticsSummary> findOwnerDocumentStatisticsSummaryByLanguageDataParamsGroupByOwnerId(final LanguageDataParams params) {
    return http.list(format("%s", OWNERS_DOCUMENT_STATISTICS), QueryParam.transform(params), OwnerDocumentStatisticsSummary.class);
  }

  public List<TranslationDocumentStatistics> findDocumentStatisticsSummary() {
    return http.list(DOCUMENT_STATISTICS, Collections.emptyList(),  TranslationDocumentStatistics.class);
  }

}
