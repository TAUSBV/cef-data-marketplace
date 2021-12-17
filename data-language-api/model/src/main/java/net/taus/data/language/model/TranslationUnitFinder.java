package net.taus.data.language.model;

import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.SQLQueryFactory;
import net.taus.data.language.api.model.LanguageDataParams;
import net.taus.data.language.api.model.PageById;
import net.taus.data.language.api.model.TranslationUnit;
import net.taus.data.language.api.model.TranslationUnitUnicity;
import net.taus.data.language.model.sql.*;
import net.taus.data.language.model.util.SummariesSql;
import net.taus.data.language.model.util.UUIDUtil;
import org.springframework.data.domain.Pageable;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.querydsl.sql.SQLExpressions.select;
import static com.querydsl.sql.SQLExpressions.selectZero;
import static net.taus.data.language.model.TranslationUnit.Quality.HIGH;

public class TranslationUnitFinder {

  private final SQLQueryFactory queryFactory;


  public TranslationUnitFinder(final SQLQueryFactory queryFactory) {
    this.queryFactory = queryFactory;
  }

  public PageById<TranslationUnit> findPageByLanguageDataParams(final LanguageDataParams params,
                                                                final Pageable pageable) {
    final QSQLTranslationDocuments document = new QSQLTranslationDocuments("td");
    final QSQLTranslationUnits unit = new QSQLTranslationUnits("tu");
    final QSQLTranslationVariants vs = new QSQLTranslationVariants("vs");
    final QSQLTranslationVariants vt = new QSQLTranslationVariants("vt");
    final QSQLTranslationVariantsAnnotations tva = new QSQLTranslationVariantsAnnotations("tva");
    final QSQLTranslationDocumentsCharacteristics documentsCharacteristics = new QSQLTranslationDocumentsCharacteristics("tdc");
    final QSQLTranslationDocumentsCharacteristics documentsCharacteristicsMax = new QSQLTranslationDocumentsCharacteristics("tdcMax");
    final QSQLLanguageDataCharacteristics characteristics = new QSQLLanguageDataCharacteristics("ldc");
    final QSQLLanguageDataCharacteristics characteristicsMax = new QSQLLanguageDataCharacteristics("ldc2");
    final QSQLLanguageDataCharacteristicsDomains characteristicsDomains = new QSQLLanguageDataCharacteristicsDomains("ldcd");
    final QSQLLanguageDataCharacteristicsDomains characteristicsDomainsMax = new QSQLLanguageDataCharacteristicsDomains("ldcd2");
    final QSQLLanguageDataCharacteristicsContentTypes characteristicsContentTypes = new QSQLLanguageDataCharacteristicsContentTypes("ldcct");
    final QSQLLanguageDataCharacteristicsContentTypes characteristicsContentTypesMax = new QSQLLanguageDataCharacteristicsContentTypes("ldcct2");
    final QSQLLanguages sourceLanguage = new QSQLLanguages("sl");
    final QSQLLanguages targetLanguage = new QSQLLanguages("tl");
    final QSQLDomains domains = new QSQLDomains("dm");
    final QSQLContentTypes contentTypes = new QSQLContentTypes("ct");

    if (Objects.isNull(params.getDocumentUids()) &&
      (Objects.isNull(params.getSourceLanguageId()) || Objects.isNull(params.getTargetLanguageId()))) {
      throw new IllegalArgumentException("Document uids or source and target languages is mandatory.");
    }
    BooleanExpression criteria = unit.quality.eq(HIGH.name());
    if (TranslationUnitUnicity.ORIGINAL_ONlY.equals(params.getUnitUnicity())) {
      criteria = criteria.and(unit.referenceId.isNull());
    }
    if (Objects.nonNull(params.getSourceLanguageId()) && Objects.nonNull(params.getTargetLanguageId())) {
      criteria = criteria.and(sourceLanguage.id.eq(params.getSourceLanguageId().intValue()))
        .and(targetLanguage.id.eq(params.getTargetLanguageId().intValue()));
    }
    if (Objects.nonNull(params.getDocumentUids()) && params.getDocumentUids().length > 0) {
      final byte[][] bytes = Arrays.stream(params.getDocumentUids()).map(UUIDUtil::toBytes).toArray(byte[][]::new);
      criteria = criteria.and(document.uid.in(bytes));
    }
    if (Objects.nonNull(params.getDomainIds()) && params.getDomainIds().length > 0) {
      criteria = criteria.and(domains.id.in(params.getDomainIds()));
    }
    if (Objects.nonNull(params.getContentTypeIds()) && params.getContentTypeIds().length > 0) {
      criteria = criteria.and(contentTypes.id.in(params.getContentTypeIds()));
    }
    if (Objects.nonNull(params.getOwnerIds()) && params.getOwnerIds().length > 0) {
      criteria = criteria.and(document.ownerId.in(params.getOwnerIds()));
    }
    if (Objects.nonNull(params.getPriceMin()) && Objects.nonNull(params.getPriceMax())) {
      criteria = criteria.and(characteristics.pricePerWord.between(params.getPriceMin(), params.getPriceMax()));
    }
    criteria = document.anonymize.when(Expressions.numberTemplate(Byte.class, "1"))
      .then(criteria
        .and(selectZero().from(tva).where(tva.translationVariantId.eq(unit.sourceId)).notExists())
        .and(selectZero().from(tva).where(tva.translationVariantId.eq(unit.targetId)).notExists())
      )
      .otherwise(criteria);

    final ConstructorExpression<TranslationUnit> summary = SummariesSql
      .unitSummaryLong(document, unit, vs, vt, sourceLanguage, targetLanguage, domains, contentTypes);

    SQLQuery<?> base = queryFactory.from(unit)
      .innerJoin(vs).on(unit.sourceId.eq(vs.id))
      .innerJoin(vt).on(unit.targetId.eq(vt.id))
      .innerJoin(document).on(unit.documentId.eq(document.id))
      .innerJoin(sourceLanguage).on(document.sourceLanguageId.eq(sourceLanguage.id))
      .innerJoin(targetLanguage).on(document.targetLanguageId.eq(targetLanguage.id))
      .leftJoin(documentsCharacteristics)
      .on(document.id.eq(documentsCharacteristics.documentId)
        .and(documentsCharacteristics.characteristicId.eq(
          select(characteristicsMax.id.max()).from(characteristicsMax)
            .leftJoin(documentsCharacteristicsMax).on(characteristicsMax.id.eq(documentsCharacteristicsMax.characteristicId)
              .and(documentsCharacteristics.documentId.eq(documentsCharacteristicsMax.documentId))
              .and(characteristicsMax.createdAt.loe(Timestamp.valueOf(params.getReferenceDateTime()))))
        )))
      .leftJoin(characteristics).on(documentsCharacteristics.characteristicId.eq(characteristics.id))
      .leftJoin(characteristicsDomains).on(characteristics.id.eq(characteristicsDomains.characteristicsId))
      .leftJoin(domains).on(domains.id.eq(characteristicsDomains.domainId)
        .and(domains.id.eq(select(characteristicsDomainsMax.domainId.max())
          .from(characteristicsDomainsMax)
          .where(characteristicsDomainsMax.characteristicsId.eq(characteristicsDomains.characteristicsId)))))
      .leftJoin(characteristicsContentTypes).on(characteristics.id.eq(characteristicsContentTypes.characteristicId))
      .leftJoin(contentTypes).on(contentTypes.id.eq(characteristicsContentTypes.contentTypeId)
        .and(contentTypes.id.eq(select(characteristicsContentTypesMax.contentTypeId.max())
          .from(characteristicsContentTypesMax)
          .where(characteristicsContentTypesMax.characteristicId.eq(characteristicsContentTypes.characteristicId)))))
      .where(criteria);

    PageById<net.taus.data.language.api.model.TranslationUnit> page = PageById.empty();
    final Long total = base.clone(queryFactory.getConnection()).select(unit.id.max()).where(criteria).fetchOne();
    if (Objects.nonNull(total)) {
      criteria = criteria.and(unit.id.gt(pageable.getPageNumber()));
      final List<TranslationUnit> list = base.select(summary).where(criteria)
        .groupBy(document.uid, unit.id, domains.id, contentTypes.id).limit(pageable.getPageSize()).fetch();
      page = new PageById<>(list, pageable.getPageNumber(), pageable.getPageSize(), total);
    }
    return page;
  }
}
