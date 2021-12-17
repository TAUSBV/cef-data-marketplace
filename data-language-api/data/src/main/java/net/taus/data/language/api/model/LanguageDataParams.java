package net.taus.data.language.api.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

@Builder(setterPrefix = "with")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LanguageDataParams {

  Long sourceLanguageId;
  Long targetLanguageId;
  UUID[] documentUids;
  Long[] domainIds;
  Long[] contentTypeIds;
  Long[] ownerIds;
  Double priceMin;
  Double priceMax;
  LocalDateTime referenceDateTime;
  TranslationUnitUnicity unitUnicity;

  public LanguageDataParams() {
    this.referenceDateTime = LocalDateTime.now();
    this.unitUnicity = TranslationUnitUnicity.ORIGINAL_AND_REPLICA;
  }

  public LanguageDataParams(final Long sourceLanguageId,
                            final Long targetLanguageId,
                            final UUID[] documentUids,
                            final Long[] domainIds,
                            final Long[] contentTypeIds,
                            final Long[] ownerIds,
                            final Double priceMin,
                            final Double priceMax,
                            final LocalDateTime referenceDateTime,
                            final TranslationUnitUnicity unitUnicity) {
    this();
    this.documentUids = documentUids;
    this.sourceLanguageId = sourceLanguageId;
    this.targetLanguageId = targetLanguageId;
    this.domainIds = domainIds;
    this.contentTypeIds = contentTypeIds;
    this.ownerIds = ownerIds;
    this.priceMin = priceMin;
    this.priceMax = priceMax;
    this.referenceDateTime = referenceDateTime;
    this.unitUnicity = unitUnicity;
  }

  public LanguageDataParams(final Long sourceLanguageId, final Long targetLanguageId) {
    this();
    this.sourceLanguageId = sourceLanguageId;
    this.targetLanguageId = targetLanguageId;
    this.documentUids = null;
    this.domainIds = null;
    this.contentTypeIds = null;
    this.ownerIds = null;
    this.priceMin = null;
    this.priceMax = null;
  }

  public LanguageDataParams(final Long sourceLanguageId,
                            final Long targetLanguageId,
                            final long[] domainIds,
                            final long[] contentTypeIds,
                            final long[] ownerIds,
                            final Double priceMin,
                            final Double priceMax,
                            final LocalDateTime referenceDateTime) {
    this();
    this.sourceLanguageId = sourceLanguageId;
    this.targetLanguageId = targetLanguageId;
    this.domainIds = Objects.nonNull(domainIds) ? Arrays.stream(domainIds).boxed().toArray(Long[]::new) : null;
    this.contentTypeIds = Objects.nonNull(contentTypeIds) ? Arrays.stream(contentTypeIds).boxed().toArray(Long[]::new) : null;
    this.ownerIds = Objects.nonNull(ownerIds) ? Arrays.stream(ownerIds).boxed().toArray(Long[]::new) : null;
    this.priceMin = priceMin;
    this.priceMax = priceMax;
    this.referenceDateTime = referenceDateTime;
  }

  public LanguageDataParams(final Long sourceLanguageId,
                            final Long targetLanguageId,
                            final long[] domainIds,
                            final long[] contentTypeIds,
                            final long[] ownerIds,
                            final Double priceMin,
                            final Double priceMax,
                            final LocalDateTime referenceDateTime,
                            final TranslationUnitUnicity unitUnicity) {
    this(sourceLanguageId, targetLanguageId, domainIds, contentTypeIds, ownerIds, priceMin, priceMax, referenceDateTime);
    this.unitUnicity = unitUnicity;
  }

  public LanguageDataParams(final UUID[] documentUids,
                            final LocalDateTime referenceDateTime) {
    this();
    this.documentUids = documentUids;
    this.referenceDateTime = referenceDateTime;
  }
}
