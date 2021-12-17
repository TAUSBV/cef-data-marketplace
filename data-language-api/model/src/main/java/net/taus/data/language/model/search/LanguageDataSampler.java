package net.taus.data.language.model.search;

import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.sql.SQLExpressions;
import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.SQLQueryFactory;
import net.taus.data.language.api.model.Language;
import net.taus.data.language.api.model.LanguageDataParams;
import net.taus.data.language.api.model.Page;
import net.taus.data.language.api.model.search.TranslationDocumentSample;
import net.taus.data.language.api.model.search.TranslationUnitSample;
import net.taus.data.language.model.sql.*;
import net.taus.data.language.model.util.UUIDUtil;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

import static com.querydsl.core.types.Projections.constructor;
import static com.querydsl.sql.SQLExpressions.select;
import static java.math.BigDecimal.valueOf;
import static java.math.RoundingMode.HALF_UP;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static net.taus.data.language.model.TranslationDocument.Status.PUBLISHED;
import static net.taus.data.language.model.search.LanguageDataSearcher.SampleKind.UNITS;

@Transactional
public class LanguageDataSampler {

  final QSQLTranslationDocuments document = new QSQLTranslationDocuments("td");
  final QSQLTranslationDocumentsCharacteristics documentsCharacteristics = new QSQLTranslationDocumentsCharacteristics("tdc");
  final QSQLTranslationDocumentsCharacteristics documentsCharacteristicsMax = new QSQLTranslationDocumentsCharacteristics("tdcMax");
  final QSQLLanguageDataCharacteristics characteristics = new QSQLLanguageDataCharacteristics("ldc");
  final QSQLLanguageDataCharacteristics characteristicsMax = new QSQLLanguageDataCharacteristics("ldc2");
  final QSQLLanguageDataCharacteristicsDomains characteristicsDomains = new QSQLLanguageDataCharacteristicsDomains("ldcd");
  final QSQLLanguageDataCharacteristicsDomains characteristicsDomainsMax = new QSQLLanguageDataCharacteristicsDomains("ldcd2");
  final QSQLLanguageDataCharacteristicsContentTypes characteristicsContentTypes = new QSQLLanguageDataCharacteristicsContentTypes("ldcct");
  final QSQLLanguageDataCharacteristicsContentTypes characteristicsContentTypesMax = new QSQLLanguageDataCharacteristicsContentTypes("ldcct2");
  final QSQLTranslationDocumentsStatistics statistics = new QSQLTranslationDocumentsStatistics("ss");
  final QSQLTranslationDocumentUnitSamples samples = new QSQLTranslationDocumentUnitSamples("tdus");
  final QSQLLanguages sourceLanguage = new QSQLLanguages("sl");
  final QSQLLanguages targetLanguage = new QSQLLanguages("tl");
  final QSQLDomains domains = new QSQLDomains("dm");
  final QSQLContentTypes contentTypes = new QSQLContentTypes("ct");

  final String documentUid = "documentUid";
  final String documentName = "documentName";
  final String sourceLanguageId = "sourceLanguageId";
  final String sourceLanguageTag = "sourceLanguageTag";
  final String sourceLanguageName = "sourceLanguageName";
  final String sourceLanguageDisplayName = "sourceLanguageDisplayName";
  final String sourceLanguageCountry = "sourceLanguageCountry";
  final String sourceLanguageDisplayCountry = "sourceLanguageDisplayCountry";
  final String sourceLanguageEnabledPII = "sourceLanguageEnabledPII";
  final String targetLanguageId = "targetLanguageId";
  final String targetLanguageTag = "targetLanguageTag";
  final String targetLanguageName = "targetLanguageName";
  final String targetLanguageDisplayName = "targetLanguageDisplayName";
  final String targetLanguageCountry = "targetLanguageCountry";
  final String targetLanguageDisplayCountry = "targetLanguageDisplayCountry";
  final String targetLanguageEnabledPII = "targetLanguageEnabledPII";
  final String documentOwnerId = "documentOwnerId";
  final String documentPricePerWord = "documentPricePerWord";
  final String unitCount = "unitCount";
  final String sourceWordCount = "sourceWordCount";
  final String targetWordCount = "targetWordCount";
  final String domainDescription = "domainDescription";
  final String contentTypeDescription = "contentTypeDescription";

  final ConstructorExpression<Language> sourceLanguageSummary = constructor(Language.class,
    sourceLanguage.id.longValue().as(sourceLanguageId),
    sourceLanguage.tag.as(sourceLanguageTag),
    sourceLanguage.name.as(sourceLanguageName),
    sourceLanguage.displayName.as(sourceLanguageDisplayName),
    sourceLanguage.country.as(sourceLanguageCountry),
    sourceLanguage.displayCountry.as(sourceLanguageDisplayCountry),
    sourceLanguage.enabledPii.as(sourceLanguageEnabledPII)
  );
  final ConstructorExpression<Language> targetLanguageSummary = constructor(Language.class,
    targetLanguage.id.longValue().as(targetLanguageId),
    targetLanguage.tag.as(targetLanguageTag),
    targetLanguage.name.as(targetLanguageName),
    targetLanguage.displayName.as(targetLanguageDisplayName),
    targetLanguage.country.as(targetLanguageCountry),
    targetLanguage.displayCountry.as(targetLanguageDisplayCountry),
    targetLanguage.enabledPii.as(targetLanguageEnabledPII)
  );
  final ConstructorExpression<TranslationDocumentSample> documentSummary = constructor(TranslationDocumentSample.class,
    document.ownerId.as(documentOwnerId),
    document.uid.as(documentUid),
    document.name.as(documentName),
    sourceLanguageSummary,
    targetLanguageSummary,
    statistics.totalUnitsOriginal.add(statistics.totalUnitsReplica).as(unitCount),
    statistics.totalSourceWordCount.add(statistics.totalSourceReplicaWordCount).as(sourceWordCount),
    statistics.totalTargetOriginalWordCount.add(statistics.totalTargetReplicaWordCount).as(targetWordCount),
    characteristics.pricePerWord.as(documentPricePerWord),
    domains.description.as(domainDescription),
    contentTypes.description.as(contentTypeDescription)
  );
  final ConstructorExpression<Language> innerSourceLanguageSummary = constructor(Language.class,
    Expressions.numberPath(Long.class, sourceLanguageId),
    Expressions.stringPath(sourceLanguageTag),
    Expressions.stringPath(sourceLanguageName),
    Expressions.stringPath(sourceLanguageDisplayName),
    Expressions.stringPath(sourceLanguageCountry),
    Expressions.stringPath(sourceLanguageDisplayCountry),
    Expressions.booleanPath(sourceLanguageEnabledPII)
  );
  final ConstructorExpression<Language> innerTargetLanguageSummary = constructor(Language.class,
    Expressions.numberPath(Long.class, targetLanguageId),
    Expressions.stringPath(targetLanguageTag),
    Expressions.stringPath(targetLanguageName),
    Expressions.stringPath(targetLanguageDisplayName),
    Expressions.stringPath(targetLanguageCountry),
    Expressions.stringPath(targetLanguageDisplayCountry),
    Expressions.booleanPath(targetLanguageEnabledPII)
  );
  final ConstructorExpression<TranslationDocumentSample> innerDocumentSummary = constructor(TranslationDocumentSample.class,
    Expressions.numberPath(Long.class, documentOwnerId),
    Expressions.arrayPath(byte[].class, documentUid),
    Expressions.stringPath(documentName),
    innerSourceLanguageSummary,
    innerTargetLanguageSummary,
    Expressions.numberPath(Long.class, unitCount),
    Expressions.numberPath(Long.class, sourceWordCount),
    Expressions.numberPath(Long.class, targetWordCount),
    Expressions.numberPath(BigDecimal.class, documentPricePerWord),
    Expressions.stringPath(domainDescription),
    Expressions.stringPath(contentTypeDescription)
  );
  final ConstructorExpression<TranslationUnitSample> unitData = constructor(TranslationUnitSample.class,
    samples.ownerId,
    samples.documentUid,
    samples.sourceValue,
    samples.sourceWordCount,
    samples.targetValue,
    samples.targetWordCount,
    samples.unitDomainDescription,
    samples.unitContentTypeDescription
  );

  private final SQLQueryFactory queryFactory;

  public LanguageDataSampler(final SQLQueryFactory queryFactory) {
    this.queryFactory = queryFactory;
  }

  public Page<?> getSample(final LanguageDataSearcher.SampleKind kind,
                           final LanguageDataParams params,
                           final PageRequest pageRequest) {
    SQLQuery<?> base = newBaseQuery(params.getReferenceDateTime())
      .innerJoin(characteristics).on(documentsCharacteristics.characteristicId.eq(characteristics.id))
      .innerJoin(characteristicsDomains).on(characteristics.id.eq(characteristicsDomains.characteristicsId))
      .innerJoin(domains).on(domains.id.eq(characteristicsDomains.domainId)
        .and(domains.id.eq(select(characteristicsDomainsMax.domainId.max())
          .from(characteristicsDomainsMax)
          .where(characteristicsDomainsMax.characteristicsId.eq(characteristicsDomains.characteristicsId)))))
      .innerJoin(characteristicsContentTypes).on(characteristics.id.eq(characteristicsContentTypes.characteristicId))
      .innerJoin(contentTypes).on(contentTypes.id.eq(characteristicsContentTypes.contentTypeId)
        .and(contentTypes.id.eq(select(characteristicsContentTypesMax.contentTypeId.max())
          .from(characteristicsContentTypesMax)
          .where(characteristicsContentTypesMax.characteristicId.eq(characteristicsContentTypes.characteristicId)))))
      .innerJoin(sourceLanguage).on(document.sourceLanguageId.eq(sourceLanguage.id))
      .innerJoin(targetLanguage).on(document.targetLanguageId.eq(targetLanguage.id))
      .where(document.sourceLanguageId.eq(params.getSourceLanguageId().intValue())
        .and(document.targetLanguageId.eq(params.getTargetLanguageId().intValue()))
        .and(document.status.eq(PUBLISHED.name())));

    boolean noFilters = true;
    if (Objects.nonNull(params.getDomainIds()) && params.getDomainIds().length > 0) {
      base = base.where(characteristicsDomains.domainId.in(params.getDomainIds()));
      noFilters = false;
    }
    if (Objects.nonNull(params.getContentTypeIds()) && params.getContentTypeIds().length > 0) {
      base = base.where(characteristicsContentTypes.contentTypeId.in(params.getContentTypeIds()));
      noFilters = false;
    }
    if (Objects.nonNull(params.getPriceMin()) && Objects.nonNull(params.getPriceMax())) {
      base = base.where(characteristics.pricePerWord.between(params.getPriceMin(), params.getPriceMax()));
      noFilters = false;
    }
    if (Objects.nonNull(params.getOwnerIds()) && params.getOwnerIds().length > 0) {
      base = base.where(document.ownerId.in(Arrays.asList(params.getOwnerIds())));
    }

    final String rowNumber = "rowNumber";
    final SQLQuery<TranslationDocumentSample> select;
    long numberOfDocuments = 0;
    if (UNITS.equals(kind)) {
      if (noFilters) {
        base = base.select(documentSummary, SQLExpressions.rowNumber().over().partitionBy(document.ownerId)
          .orderBy(statistics.totalUnitsOriginal.add(statistics.totalUnitsReplica).desc()).as(rowNumber))
          .groupBy(documentSummary);
        select = queryFactory.select(innerDocumentSummary)
          .from(base.as("ranked_documents"))
          .where(Expressions.numberPath(Long.class, rowNumber).loe(1));
      }
      else {
        select = base.select(documentSummary).limit(pageRequest.getPageSize());
      }
    }
    else {
      numberOfDocuments = base.clone(queryFactory.getConnection()).select(document.id.countDistinct()).fetchOne();
      select = base.select(documentSummary).groupBy(documentSummary).limit(pageRequest.getPageSize()).offset(pageRequest.getOffset());
    }

    final List<TranslationDocumentSample> documents = select.fetch();
    Page<?> page = Page.empty(pageRequest);
    if (!documents.isEmpty()) {
      BigDecimal perDocument = valueOf(30).divide(valueOf(documents.size()), HALF_UP);
      final List<byte[]> uids = documents.stream().map(d -> UUIDUtil.toBytes(d.getUid())).collect(toList());
      final List<TranslationUnitSample> sample = queryFactory
        .select(unitData)
        .from(
          select(unitData, SQLExpressions.rowNumber().over().partitionBy(samples.documentId)
            .orderBy(samples.unitSequence).as(rowNumber))
            .from(samples)
            .where(samples.documentUid.in(uids))
            .as("tdus")
        )
        .where(Expressions.numberPath(Long.class, rowNumber).loe(perDocument.longValue()))
        .fetch();
      if (UNITS.equals(kind)) {
        Collections.shuffle(sample);
        page = new Page<>(sample);
      }
      else {
        final Map<UUID, List<TranslationUnitSample>> documentUnits = sample.stream()
          .collect(groupingBy(TranslationUnitSample::getDocumentUid));
        documents.forEach(doc -> {
          final List<TranslationUnitSample> units = documentUnits.get(doc.getUid());
          doc.setUnits(units);
        });
        page = new Page<>(documents, pageRequest, numberOfDocuments);
      }
    }
    return page;
  }

  private SQLQuery<?> newBaseQuery(final LocalDateTime referenceDateTime) {
    return queryFactory.from(document)
      .innerJoin(documentsCharacteristics)
      .on(document.id.eq(documentsCharacteristics.documentId)
        .and(documentsCharacteristics.characteristicId.eq(
          select(characteristicsMax.id.max()).from(characteristicsMax)
            .innerJoin(documentsCharacteristicsMax).on(characteristicsMax.id.eq(documentsCharacteristicsMax.characteristicId)
            .and(documentsCharacteristics.documentId.eq(documentsCharacteristicsMax.documentId))
            .and(characteristicsMax.createdAt.loe(Timestamp.valueOf(referenceDateTime))))
        )))
      .innerJoin(statistics).on(statistics.documentId.eq(document.id)
        .and(statistics.createdAt.eq(select(statistics.createdAt.max()).from(statistics)
          .where(statistics.documentId.eq(document.id)
            .and(statistics.createdAt.loe(Timestamp.valueOf(referenceDateTime))))
        )));
  }

}
