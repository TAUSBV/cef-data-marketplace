package net.taus.data.language.model.util;

import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.jpa.JPQLQuery;
import net.taus.data.language.api.model.*;
import net.taus.data.language.model.QTranslationDocument;
import net.taus.data.language.model.QTranslationDocumentStatistics;
import net.taus.data.language.model.QTranslationUnit;
import net.taus.data.language.model.QTranslationVariant;
import net.taus.data.language.model.meta.QContentType;
import net.taus.data.language.model.meta.QDomain;
import net.taus.data.language.model.meta.QLanguage;
import net.taus.data.language.model.meta.QLanguageDataCharacteristics;

import static com.querydsl.core.group.GroupBy.set;
import static com.querydsl.core.types.Projections.constructor;
import static com.querydsl.jpa.JPAExpressions.select;
import static net.taus.data.language.api.model.TranslationUnitUnicity.ORIGINAL_ONlY;
import static net.taus.data.language.model.TranslationDocument.Status.PUBLISHED;

public class Summaries {

  public static ConstructorExpression<Domain> domainSummary(final QDomain domain) {
    return constructor(Domain.class, domain.id, domain.description);
  }

  public static ConstructorExpression<ContentType> contentTypeSummary(final QContentType contentType) {
    return constructor(ContentType.class, contentType.id, contentType.description);
  }

  public static ConstructorExpression<Language> languageSummary(final QLanguage language) {
    return constructor(Language.class,
      language.id,
      language.tag,
      language.name,
      language.displayName,
      language.country,
      language.displayCountry,
      language.enabledPii
    );
  }

  public static ConstructorExpression<TranslationVariant> variantSummary(final QTranslationVariant variant,
                                                                         final QLanguage language) {
    return constructor(TranslationVariant.class,
      variant.uid,
      variant.value,
      variant.wordCount,
      variant.hash,
      languageSummary(language)
    );
  }

  public static ConstructorExpression<TranslationUnit> unitSummaryLong(final QTranslationDocument document,
                                                                       final QTranslationUnit unit,
                                                                       final QTranslationVariant sourceVariant,
                                                                       final QTranslationVariant targetVariant,
                                                                       final QLanguage sourceLanguage,
                                                                       final QLanguage targetLanguage,
                                                                       final QDomain domain,
                                                                       final QContentType contentType) {
    return constructor(TranslationUnit.class,
      document.ownerId,
      document.uid,
      unit.id,
      unit.uid,
      unit.hash,
      unit.sequence,
      variantSummary(sourceVariant, sourceLanguage),
      variantSummary(targetVariant, targetLanguage),
      domainSummary(domain),
      contentTypeSummary(contentType)
    );
  }

  public static ConstructorExpression<TranslationUnit> unitSummaryShort(final QTranslationDocument document,
                                                                        final QTranslationUnit unit,
                                                                        final QTranslationVariant sourceVariant,
                                                                        final QTranslationVariant targetVariant,
                                                                        final QLanguage sourceLanguage,
                                                                        final QLanguage targetLanguage) {
    return constructor(TranslationUnit.class,
      document.uid, unit.id, unit.uid, unit.hash, unit.sequence, variantSummary(sourceVariant, sourceLanguage), variantSummary(targetVariant, targetLanguage));
  }

  public static ConstructorExpression<TranslationDocument> documentSummary(final QTranslationDocument document,
                                                                           final QLanguageDataCharacteristics characteristics,
                                                                           final QTranslationDocumentStatistics statistics,
                                                                           final QDomain domain,
                                                                           final QContentType contentType,
                                                                           final QLanguage sourceLanguage,
                                                                           final QLanguage targetLanguage) {
    return constructor(TranslationDocument.class,
      document.ownerId,
      document.uid,
      document.name,
      document.status.stringValue(),
      document.description,
      document.format.stringValue(),
      document.size,
      characteristics.pricePerWord,
      document.createdAt,
      document.updatedAt,
      domainSummary(domain),
      contentTypeSummary(contentType),
      languageSummary(sourceLanguage),
      languageSummary(targetLanguage),
      GroupBy.list(statisticsSummary(statistics))
    );
  }

  public static ConstructorExpression<TranslationDocumentStatistics> statisticsSummary(final QTranslationDocumentStatistics statistics) {
    return constructor(TranslationDocumentStatistics.class,
      statistics.id,
      statistics.totalUnits,
      statistics.totalUnitsOriginal,
      statistics.totalUnitsReplica,
      statistics.totalUnitsLowQuality,
      statistics.totalUnitsAnnotatedPii,
      statistics.totalSourceWordCount,
      statistics.totalSourceOriginalWordCount,
      statistics.totalSourceReplicaWordCount,
      statistics.totalSourceLowQualityWordCount,
      statistics.totalSourceWordCountAnnotatedPii,
      statistics.totalTargetWordCount,
      statistics.totalTargetOriginalWordCount,
      statistics.totalTargetReplicaWordCount,
      statistics.totalTargetLowQualityWordCount,
      statistics.totalTargetWordCountAnnotatedPii,
      statistics.createdAt);
  }

  public static ConstructorExpression<OwnerSummary> ownerSummary(final QTranslationDocument document,
                                                                 final QLanguage sourceLanguage,
                                                                 final QLanguage targetLanguage,
                                                                 final QDomain domain,
                                                                 final QContentType contentType,
                                                                 final JPQLQuery<Long> documentCount) {
    return constructor(OwnerSummary.class,
      document.ownerId,
      set(sourceLanguage.tag.concat(",").concat(targetLanguage.tag)),
      set(domain.description),
      set(contentType.description),
      documentCount
    );
  }

  public static ConstructorExpression<TranslationDocumentStatisticsSummaryShort> documentStatisticsSummaryShort(final TranslationUnitUnicity unitUnicity,
                                                                                                                final QTranslationDocument document,
                                                                                                                final QLanguageDataCharacteristics characteristics,
                                                                                                                final QTranslationDocumentStatistics statistics) {
    final ConstructorExpression<TranslationDocumentStatisticsSummaryShort> summary;
    if (ORIGINAL_ONlY.equals(unitUnicity)) {
      summary = constructor(TranslationDocumentStatisticsSummaryShort.class,
        document.uid,
        document.name,
        characteristics.pricePerWord,
        statistics.totalUnitsOriginal,
        statistics.totalSourceOriginalWordCount,
        statistics.totalTargetOriginalWordCount);
    }
    else {
      summary = constructor(TranslationDocumentStatisticsSummaryShort.class,
        document.uid,
        document.name,
        characteristics.pricePerWord,
        statistics.totalUnitsOriginal.add(statistics.totalUnitsReplica),
        statistics.totalSourceOriginalWordCount.add(statistics.totalSourceReplicaWordCount),
        statistics.totalTargetOriginalWordCount.add(statistics.totalTargetReplicaWordCount));
    }
    return summary;
  }

  public static ConstructorExpression<OwnerStatisticsSummary> ownerStatisticsSummary(final TranslationUnitUnicity unitUnicity,
                                                                                     final QTranslationDocument document,
                                                                                     final QLanguageDataCharacteristics characteristics,
                                                                                     final QTranslationDocumentStatistics statistics) {
    final ConstructorExpression<OwnerStatisticsSummary> summary;
    if (ORIGINAL_ONlY.equals(unitUnicity)) {
      summary = constructor(OwnerStatisticsSummary.class,
        document.ownerId,
        characteristics.pricePerWord.multiply(statistics.totalSourceOriginalWordCount),
        statistics.totalUnitsOriginal.sum(),
        statistics.totalSourceOriginalWordCount.sum(),
        statistics.totalTargetOriginalWordCount.sum());
    }
    else {
      summary = constructor(OwnerStatisticsSummary.class,
        document.ownerId,
        characteristics.pricePerWord.multiply(statistics.totalSourceOriginalWordCount.add(statistics.totalSourceReplicaWordCount)),
        statistics.totalUnitsOriginal.add(statistics.totalUnitsReplica).sum(),
        statistics.totalSourceOriginalWordCount.add(statistics.totalSourceReplicaWordCount).sum(),
        statistics.totalTargetOriginalWordCount.add(statistics.totalTargetReplicaWordCount).sum());
    }
    return summary;
  }
}
