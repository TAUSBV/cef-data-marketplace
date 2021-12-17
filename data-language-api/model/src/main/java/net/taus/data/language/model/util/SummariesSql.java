package net.taus.data.language.model.util;

import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.jpa.JPQLQuery;
import net.taus.data.language.api.model.*;
import net.taus.data.language.model.sql.*;

import static com.querydsl.core.group.GroupBy.set;
import static com.querydsl.core.types.Projections.constructor;
import static net.taus.data.language.api.model.TranslationUnitUnicity.ORIGINAL_ONlY;

public class SummariesSql {

  public static ConstructorExpression<Domain> domainSummary(final QSQLDomains domain) {
    return constructor(Domain.class, domain.id.longValue(), domain.description);
  }

  public static ConstructorExpression<ContentType> contentTypeSummary(final QSQLContentTypes contentType) {
    return constructor(ContentType.class, contentType.id.longValue(), contentType.description);
  }

  public static ConstructorExpression<Language> languageSummary(final QSQLLanguages language) {
    return constructor(Language.class,
      language.id.longValue(),
      language.tag,
      language.name,
      language.displayName,
      language.country,
      language.displayCountry,
      language.enabledPii
    );
  }

  public static ConstructorExpression<TranslationVariant> variantSummary(final QSQLTranslationVariants variant,
                                                                         final QSQLLanguages language) {
    return constructor(TranslationVariant.class,
      variant.uid,
      variant.value,
      variant.wordCount,
      variant.hash,
      languageSummary(language)
    );
  }

  public static ConstructorExpression<TranslationUnit> unitSummaryLong(final QSQLTranslationDocuments document,
                                                                       final QSQLTranslationUnits unit,
                                                                       final QSQLTranslationVariants sourceVariant,
                                                                       final QSQLTranslationVariants targetVariant,
                                                                       final QSQLLanguages sourceLanguage,
                                                                       final QSQLLanguages targetLanguage,
                                                                       final QSQLDomains domain,
                                                                       final QSQLContentTypes contentType) {
    return constructor(TranslationUnit.class,
      document.ownerId.longValue(),
      document.uid,
      unit.id.longValue(),
      unit.uid,
      unit.hash,
      unit.sequence.longValue(),
      variantSummary(sourceVariant, sourceLanguage),
      variantSummary(targetVariant, targetLanguage),
      domainSummary(domain),
      contentTypeSummary(contentType)
    );
  }

  public static ConstructorExpression<TranslationUnit> unitSummaryShort(final QSQLTranslationDocuments document,
                                                                        final QSQLTranslationUnits unit,
                                                                        final QSQLTranslationVariants sourceVariant,
                                                                        final QSQLTranslationVariants targetVariant,
                                                                        final QSQLLanguages sourceLanguage,
                                                                        final QSQLLanguages targetLanguage) {
    return constructor(TranslationUnit.class,
      document.uid, unit.id.longValue(), unit.uid, unit.hash, unit.sequence.longValue(), variantSummary(sourceVariant, sourceLanguage), variantSummary(targetVariant, targetLanguage));
  }

  public static ConstructorExpression<TranslationDocument> documentSummary(final QSQLTranslationDocuments document,
                                                                           final QSQLLanguageDataCharacteristics characteristics,
                                                                           final QSQLTranslationDocumentsStatistics statistics,
                                                                           final QSQLDomains domain,
                                                                           final QSQLContentTypes contentType,
                                                                           final QSQLLanguages sourceLanguage,
                                                                           final QSQLLanguages targetLanguage) {
    return constructor(TranslationDocument.class,
      document.ownerId.longValue(),
      document.uid,
      document.name,
      document.status.stringValue(),
      document.description,
      document.format.stringValue(),
      document.size.longValue(),
      characteristics.pricePerWord.doubleValue(),
      document.createdAt,
      document.updatedAt,
      domainSummary(domain),
      contentTypeSummary(contentType),
      languageSummary(sourceLanguage),
      languageSummary(targetLanguage),
      GroupBy.list(statisticsSummary(statistics))
    );
  }

  public static ConstructorExpression<TranslationDocumentStatistics> statisticsSummary(final QSQLTranslationDocumentsStatistics statistics) {
    return constructor(TranslationDocumentStatistics.class,
      statistics.id.longValue(),
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

  public static ConstructorExpression<OwnerSummary> ownerSummary(final QSQLTranslationDocuments document,
                                                                 final QSQLLanguages sourceLanguage,
                                                                 final QSQLLanguages targetLanguage,
                                                                 final QSQLDomains domain,
                                                                 final QSQLContentTypes contentType,
                                                                 final JPQLQuery<Long> documentCount) {
    return constructor(OwnerSummary.class,
      document.ownerId,
      set(sourceLanguage.tag.concat(",").concat(targetLanguage.tag)),
      set(domain.description),
      set(contentType.description),
      documentCount
    );
  }
}
