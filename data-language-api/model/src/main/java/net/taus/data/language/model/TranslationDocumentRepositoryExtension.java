package net.taus.data.language.model;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.JPQLTemplates;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.sql.SQLExpressions;
import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.SQLQueryFactory;
import net.taus.data.language.api.model.LanguageDataParams;
import net.taus.data.language.api.model.OwnerDocumentStatisticsSummary;
import net.taus.data.language.api.model.OwnerStatisticsSummary;
import net.taus.data.language.api.model.OwnerSummary;
import net.taus.data.language.api.model.search.UUIDUtil;
import net.taus.data.language.model.meta.*;
import net.taus.data.language.model.sql.QSQLTranslationDocuments;
import net.taus.data.language.model.sql.QSQLTranslationUnits;
import net.taus.data.language.model.sql.QSQLTranslationVariants;
import net.taus.data.language.model.sql.QSQLTranslationVariantsAnnotations;
import net.taus.data.language.model.util.Summaries;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.*;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.set;
import static com.querydsl.core.types.Projections.constructor;
import static com.querydsl.jpa.JPAExpressions.select;
import static java.lang.String.format;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static net.taus.data.language.model.TranslationDocument.Status.PUBLISHED;
import static net.taus.data.language.model.TranslationDocumentNotEnoughOriginalException.NOT_ENOUGH_ORIGINAL_DATA_MESSAGE;
import static net.taus.data.language.model.TranslationUnit.Quality.HIGH;
import static net.taus.data.language.model.TranslationUnit.Quality.LOW;
import static net.taus.data.language.model.meta.LanguageDataCharacteristicsDomain.Type.DEFINED;
import static net.taus.data.language.model.util.Summaries.documentStatisticsSummaryShort;

public class TranslationDocumentRepositoryExtension implements TranslationDocumentRepositoryCustom {

  private final EntityManager entityManager;
  private final SQLQueryFactory sqlQueryFactory;
  private final TranslationUnitRepository unitRepository;
  private final QTranslationDocument document;
  private final QTranslationDocumentStatistics statistics;
  private final QLanguageDataCharacteristics characteristics;
  private final QLanguage sourceLanguage;
  private final QLanguage targetLanguage;
  private final QDomain domain;
  private final QContentType contentType;
  private final QTranslationUnit translationUnit;
  private final QTranslationVariant ts;
  private final QTranslationVariant tt;
  private final BooleanExpression isPublished;
  private final QLanguageDataCharacteristicsDomain characteristicsDomain;

  public TranslationDocumentRepositoryExtension(final EntityManager entityManager,
                                                final SQLQueryFactory sqlQueryFactory,
                                                final TranslationUnitRepository unitRepository) {
    this.entityManager = entityManager;
    this.sqlQueryFactory = sqlQueryFactory;
    this.unitRepository = unitRepository;
    this.document = new QTranslationDocument("td");
    this.statistics = new QTranslationDocumentStatistics("sc");
    this.sourceLanguage = new QLanguage("st");
    this.targetLanguage = new QLanguage("lt");
    this.domain = new QDomain("dm");
    this.contentType = new QContentType("ct");
    this.translationUnit = new QTranslationUnit("tu");
    this.characteristics = new QLanguageDataCharacteristics("ch");
    this.ts = new QTranslationVariant("ts");
    this.tt = new QTranslationVariant("tt");
    this.characteristicsDomain = new QLanguageDataCharacteristicsDomain("ldcd");
    this.isPublished = document.status.eq(PUBLISHED);
  }

  @Override
  public TranslationDocument publish(TranslationDocument document) throws TranslationDocumentException {
    if (PUBLISHED.equals(document.getStatus())) {
      throw new TranslationDocumentException("Document is already published");
    }
    document = updateStatistics(document);
    if (!document.hasEnoughOriginals()) {
      throw new TranslationDocumentNotEnoughOriginalException(NOT_ENOUGH_ORIGINAL_DATA_MESSAGE);
    }
    document.setStatus(PUBLISHED);
    final var queryFactory = new JPAQueryFactory(JPQLTemplates.DEFAULT, entityManager);
    queryFactory.update(translationUnit)
      .set(translationUnit.ownerId, document.getOwnerId())
      .set(translationUnit.status, document.getStatus())
      .set(translationUnit.updatedAt, LocalDateTime.now())
      .where(translationUnit.document.eq(document).and(translationUnit.quality.eq(HIGH)))
      .execute();
    final TranslationDocumentUnitSampleGenerator generator = new TranslationDocumentUnitSampleGenerator(sqlQueryFactory);
    final long totalUnits = document.getCurrentStatistics().map(s -> s.getTotalUnitsOriginal() + s.getTotalUnitsReplica()).orElse(0L);
    final int sampleSize = totalUnits <= 100 ? (int) (Math.ceil(totalUnits / 2.0)) : 100;
    document = this.entityManager.merge(document);
    this.entityManager.flush();
    generator.generate(document, sampleSize);
    return document;
  }

  @Override
  public TranslationDocument updateStatistics(final TranslationDocument document) {
    return updateStatistics(document, "Baseline");
  }

  @Override
  public TranslationDocument updateStatistics(TranslationDocument document, final String comment) {
    // FIXME find a better way to run this update without use of native query
    var syncOriginalsUpdate = "UPDATE translation_units tu, " +
      "        (SELECT min(id) as id, hash, document_id FROM translation_units" +
      "           WHERE status = ?1 " +
      "           AND reference_id is null " +
      "           AND hash in (select distinct hash from translation_units where document_id = ?2) " +
      "           GROUP BY hash, document_id" +
      "         ) AS original " +
      "SET tu.reference_id = original.id " +
      "WHERE tu.document_id <> original.document_id " +
      "  AND tu.hash = original.hash" +
      "  AND tu.quality = ?3 " +
      "  AND tu.reference_id is null " +
      "  AND tu.document_id = ?4";
    entityManager.createNativeQuery(syncOriginalsUpdate)
      .setParameter(1, PUBLISHED.toString())
      .setParameter(2, document.getId())
      .setParameter(3, HIGH.toString())
      .setParameter(4, document.getId())
      .executeUpdate();

    final BooleanExpression highQuality = translationUnit.quality.eq(HIGH);
    final BooleanExpression lowQuality = translationUnit.quality.eq(LOW);
    final var originalExpression = translationUnit.reference.isNull().and(highQuality);
    final var replicaExpression = translationUnit.reference.isNotNull().and(highQuality);

    var totalUnitsLowQuality = new CaseBuilder()
      .when(lowQuality)
      .then(Expressions.numberTemplate(Long.class, "1"))
      .otherwise(Expressions.numberTemplate(Long.class, "0"))
      .sum();
    var totalUnitsOriginal = new CaseBuilder()
      .when(originalExpression)
      .then(Expressions.numberTemplate(Long.class, "1"))
      .otherwise(Expressions.numberTemplate(Long.class, "0"))
      .sum();
    var totalUnitsReplica = new CaseBuilder()
      .when(replicaExpression)
      .then(Expressions.numberTemplate(Long.class, "1"))
      .otherwise(Expressions.numberTemplate(Long.class, "0"))
      .sum();

    var totalSourceOriginalWordCount = new CaseBuilder()
      .when(originalExpression)
      .then(ts.wordCount)
      .otherwise(Expressions.numberTemplate(Long.class, "0"))
      .sum();
    var totalSourceReplicaWordCount = new CaseBuilder()
      .when(replicaExpression)
      .then(ts.wordCount)
      .otherwise(Expressions.numberTemplate(Long.class, "0"))
      .sum();
    var totalSourceLowQualityWordCount = new CaseBuilder()
      .when(lowQuality)
      .then(ts.wordCount)
      .otherwise(Expressions.numberTemplate(Long.class, "0"))
      .sum();

    var totalTargetOriginalWordCount = new CaseBuilder()
      .when(originalExpression)
      .then(tt.wordCount)
      .otherwise(Expressions.numberTemplate(Long.class, "0"))
      .sum();
    var totalTargetReplicaWordCount = new CaseBuilder()
      .when(replicaExpression)
      .then(tt.wordCount)
      .otherwise(Expressions.numberTemplate(Long.class, "0"))
      .sum();
    var totalTargetLowQualityWordCount = new CaseBuilder()
      .when(lowQuality)
      .then(tt.wordCount)
      .otherwise(Expressions.numberTemplate(Long.class, "0"))
      .sum();

    final TranslationDocumentStatistics statistics = new JPAQueryFactory(JPQLTemplates.DEFAULT, entityManager)
      .from(translationUnit)
      .innerJoin(ts).on(translationUnit.source.eq(ts))
      .innerJoin(tt).on(translationUnit.target.eq(tt))
      .where(translationUnit.document.eq(document))
      .groupBy(translationUnit.document)
      .select(constructor(TranslationDocumentStatistics.class,
        translationUnit.count(),
        totalUnitsOriginal,
        totalUnitsReplica,
        totalUnitsLowQuality,
        Expressions.numberPath(Long.class, "0"),
        ts.wordCount.sum(),
        totalSourceOriginalWordCount,
        totalSourceReplicaWordCount,
        totalSourceLowQualityWordCount,
        Expressions.numberPath(Long.class, "0"),
        tt.wordCount.sum(),
        totalTargetOriginalWordCount,
        totalTargetReplicaWordCount,
        totalTargetLowQualityWordCount,
        Expressions.numberPath(Long.class, "0")))
      .fetchOne();

    if (Objects.nonNull(statistics)) {
      final Map<String, Long> map = getAnnotationStatistics(document);
      final long defaultValue = 0L;
      statistics.setTotalUnitsAnnotatedPii(map.getOrDefault("total_units", defaultValue));
      statistics.setTotalSourceWordCountAnnotatedPii(map.getOrDefault("total_source_word_count", defaultValue));
      statistics.setTotalTargetWordCountAnnotatedPii(map.getOrDefault("total_target_word_count", defaultValue));
      document = entityManager.merge(document);
      if (document.getCurrentStatistics().isPresent()) {
        final TranslationDocumentStatistics s = document.getCurrentStatistics().get();
        if (!s.equals(statistics)) {
          statistics.setComment(Optional.ofNullable(comment).orElse("Recalculation."));
          document.addStatistics(statistics);
        }
      }
      else {
        document.addStatistics(statistics);
      }
    }
    return entityManager.merge(document);
  }

  @SuppressWarnings("unchecked")
  private Map<String, Long> getAnnotationStatistics(final TranslationDocument document) {
    final QSQLTranslationDocuments td = new QSQLTranslationDocuments("td");
    final QSQLTranslationUnits units = new QSQLTranslationUnits("tu");
    final QSQLTranslationVariants tvs = new QSQLTranslationVariants("tvs");
    final QSQLTranslationVariants tvt = new QSQLTranslationVariants("tvt");
    final QSQLTranslationVariantsAnnotations annotations = new QSQLTranslationVariantsAnnotations("tva");

    final SQLQuery<Tuple> sourceAnnotations = SQLExpressions.select(units.id.as("id"), tvs.wordCount.as("source_word_count"),
        Expressions.numberTemplate(Long.class, "0").as("target_word_count"))
      .from(units)
      .innerJoin(td).on(units.documentId.eq(td.id))
      .innerJoin(tvs).on(units.sourceId.eq(tvs.id))
      .innerJoin(annotations).on(tvs.id.eq(annotations.translationVariantId))
      .where(units.quality.eq(HIGH.name())
        .and(td.uid.eq(UUIDUtil.toBytes(document.getUid()))));

    final SQLQuery<Tuple> targetAnnotations = SQLExpressions.select(units.id.as("id"),
        Expressions.numberTemplate(Long.class, "0").as("source_word_count"), tvt.wordCount.as("target_word_count"))
      .from(units)
      .innerJoin(td).on(units.documentId.eq(td.id))
      .innerJoin(tvt).on(units.targetId.eq(tvt.id))
      .innerJoin(annotations).on(tvt.id.eq(annotations.translationVariantId))
      .where(units.quality.eq(HIGH.name())
        .and(td.uid.eq(UUIDUtil.toBytes(document.getUid()))));

    final NumberExpression<Long> unitsCount = Expressions.numberPath(Long.class, "id").count().as("total_units");
    final NumberExpression<Long> sourceWordSum = Expressions.numberPath(Long.class, "source_word_count").sum().as("total_source_word_count");
    final NumberExpression<Long> targetWordSum = Expressions.numberPath(Long.class, "target_word_count").sum().as("total_target_word_count");
    final Expression<?>[] expressions = { unitsCount, sourceWordSum, targetWordSum };

    final Tuple tuple = sqlQueryFactory.select(expressions).unionAll(Expressions.stringPath("annotations"),
      sourceAnnotations, targetAnnotations).fetchFirst();
    return Optional.ofNullable(tuple).map(t -> Map.of(
        "total_units", Optional.ofNullable(t.get(unitsCount)).orElse(0L),
        "total_source_word_count", Optional.ofNullable(t.get(sourceWordSum)).orElse(0L),
        "total_target_word_count", Optional.ofNullable(t.get(targetWordSum)).orElse(0L)))
      .orElseGet(Map::of);
  }

  @Override
  public Map<Long, List<net.taus.data.language.api.model.TranslationDocument>> findByOwnersIdGrouped(final Collection<Long> ownersId,
                                                                                                     final int numberOfDocuments,
                                                                                                     final int numberOfUnits) {
    // FIXME use one query RANK()/Window functions.
    return ownersId.stream().collect(toMap(identity(), (id) -> findListSummaryPublishedByOwnerId(id, numberOfDocuments, numberOfUnits)));
  }

  @Override
  public Optional<net.taus.data.language.api.model.TranslationDocument> findOneSummaryByUidAndStatusAndUnitQualityAndAnnotations(final UUID documentUid,
                                                                                                                                 final String documentStatus,
                                                                                                                                 final String unitQuality,
                                                                                                                                 final int numberOfUnits,
                                                                                                                                 final boolean withAnnotations) {
    BooleanExpression criteria = document.uid.eq(documentUid);
    if (Objects.nonNull(documentStatus)) {
      criteria = criteria.and(document.status.eq(TranslationDocument.Status.valueOf(documentStatus)));
    }
    final TranslationUnit.Quality quality = Optional.ofNullable(unitQuality).map(TranslationUnit.Quality::valueOf).orElse(null);
    final Optional<net.taus.data.language.api.model.TranslationDocument> summary = this.findOneSummaryByPredicate(criteria, null, quality, numberOfUnits);
    if (summary.isPresent() && withAnnotations) {
      var annotations = new DataAnnotationRepositoryExtension(sqlQueryFactory).getDocumentAnnotationSummaryByUid(documentUid);
      summary.get().setAnnotations(annotations);
    }
    return summary;
  }

  @Override
  public Optional<net.taus.data.language.api.model.TranslationDocument> findOneSummaryPublishedByUid(final UUID uuid) {
    return findOneSummaryByPredicate(isPublished.and(this.document.uid.eq(uuid)), document.name.asc(), HIGH, 30);
  }

  @Override
  public Optional<net.taus.data.language.api.model.TranslationDocument> findOneSummaryByUid(final UUID documentUid) {
    return findOneSummaryByPredicate(this.document.uid.eq(documentUid), this.document.name.asc(), HIGH, 0);
  }

  @Override
  public Optional<net.taus.data.language.api.model.TranslationDocument> findOneSummaryByPredicate(final Predicate predicate,
                                                                                                  final OrderSpecifier<?> order,
                                                                                                  final int numberOfUnits) {
    return findOneSummaryByPredicate(predicate, order, HIGH, numberOfUnits);
  }

  public Optional<net.taus.data.language.api.model.TranslationDocument> findOneSummaryByPredicate(final Predicate predicate,
                                                                                                  final OrderSpecifier<?> order,
                                                                                                  final TranslationUnit.Quality unitQuality,
                                                                                                  final int numberOfUnits) {
    final ConstructorExpression<net.taus.data.language.api.model.TranslationDocument> documentSummary = Summaries
      .documentSummary(document, characteristics, statistics, domain, contentType, sourceLanguage, targetLanguage);
    final var orderBy = Objects.nonNull(order) ? order : document.name.asc();
    final var result = newDocumentBaseQuery()
      .leftJoin(document.statisticsList, statistics)
      .where(predicate)
      .orderBy(orderBy)
      .transform(groupBy(document).as(documentSummary)).values().stream().findFirst();
    result.ifPresent(d -> getUnits(d, unitQuality, numberOfUnits));
    return result;
  }

  @Override
  public Page<net.taus.data.language.api.model.TranslationDocument> findPageSummaryPublishedByOwnerId(final Long ownerId,
                                                                                                      final int numberOfUnits,
                                                                                                      final Pageable pageable) {
    return this.findPageSummaryByPredicateAndOrder(isPublished.and(document.ownerId.eq(ownerId)), null, numberOfUnits, pageable);
  }

  @Override
  public Page<net.taus.data.language.api.model.TranslationDocument> findPageSummaryByOwnerIdAndNameLike(final Long ownerId,
                                                                                                        final String documentStatus,
                                                                                                        final String nameLike,
                                                                                                        final int numberOfUnits,
                                                                                                        final Pageable pageable) {
    var criteria = this.document.ownerId.eq(ownerId);
    if (Objects.nonNull(documentStatus) && !documentStatus.isBlank()) {
      final TranslationDocument.Status status = Arrays.stream(TranslationDocument.Status.values())
        .filter(s -> s.name().equals(documentStatus)).findFirst().orElse(PUBLISHED);
      criteria = criteria.and(document.status.eq(status));
    }
    if (Objects.nonNull(nameLike) && !nameLike.isBlank()) {
      criteria = criteria.and(this.document.name.likeIgnoreCase(format("%%%s%%", nameLike)));
    }
    return findPageSummaryByPredicateAndOrder(criteria, this.document.name.asc(), numberOfUnits, pageable);
  }

  @Override
  public Page<net.taus.data.language.api.model.TranslationDocument> findPageSummaryByPredicateAndOrder(final Predicate predicate,
                                                                                                       final OrderSpecifier<?> order,
                                                                                                       final int numberOfUnits,
                                                                                                       final Pageable pageable) {
    final var count = newDocumentBaseQuery().where(predicate).fetchCount();
    final var orderBy = Objects.nonNull(order) ? order : this.document.name.asc();
    final List<UUID> ids = newDocumentBaseQuery().where(predicate)
      .orderBy(orderBy)
      .offset(pageable.getOffset())
      .limit(pageable.getPageSize())
      .select(document.uid)
      .fetch();
    final List<net.taus.data.language.api.model.TranslationDocument> result = findListByUids(ids, numberOfUnits);
    return new PageImpl<>(result, pageable, count);
  }

  @Override
  public List<net.taus.data.language.api.model.TranslationDocument> findListSummaryPublishedByOwnerId(final Long ownerId,
                                                                                                      final int numberOfDocuments,
                                                                                                      final int numberOfUnits) {
    return findListSummaryByPredicateOrderBy(isPublished.and(document.ownerId.eq(ownerId)), null, numberOfDocuments, numberOfUnits);
  }

  @Override
  public List<net.taus.data.language.api.model.TranslationDocument> getFeatured(final int numberOfDocuments, final int numberOfUnits) {
    return findListSummaryByPredicateOrderBy(isPublished, document.updatedAt.desc(), numberOfDocuments, numberOfUnits);
  }

  @Override
  public List<net.taus.data.language.api.model.TranslationDocument> findListSummaryByPredicateOrderBy(final Predicate predicate,
                                                                                                      final OrderSpecifier<?> order,
                                                                                                      final int numberOfDocuments,
                                                                                                      final int numberOfUnits) {
    final var orderBy = Objects.nonNull(order) ? order : this.document.name.asc();

    final List<UUID> ids = newDocumentBaseQuery().where(predicate)
      .orderBy(orderBy)
      .limit(numberOfDocuments)
      .select(document.uid)
      .fetch();

    return findListByUids(ids, numberOfUnits);
  }

  private List<net.taus.data.language.api.model.TranslationDocument> findListByUids(final List<UUID> ids, final int numberOfUnits) {
    final ConstructorExpression<net.taus.data.language.api.model.TranslationDocument> documentSummary = Summaries
      .documentSummary(document, characteristics, statistics, domain, contentType, sourceLanguage, targetLanguage);
    final var result = new ArrayList<>(newDocumentBaseQuery()
      .leftJoin(document.statisticsList, statistics)
      .where(document.uid.in(ids))
      .transform(groupBy(document).as(documentSummary)).values());

    result.forEach(d -> getUnits(d, HIGH, numberOfUnits));
    return result;
  }

  @Override
  public Optional<OwnerSummary> findOwnerSummaryByOwnerId(final Long ownerId) {
    var map = getOwnerSummaryGroupedByOwner(List.of(ownerId));
    return Optional.ofNullable(map.get(ownerId));
  }

  @Override
  public Page<OwnerSummary> findOwnersSummary(final int numberOfDocuments, final int numberOfUnits, Pageable pageable) {
    final var count = newQuery().select(document.ownerId).distinct().from(document).where(isPublished).fetchCount();

    final var ownerIds = newQuery()
      .from(document)
      .where(isPublished)
      .groupBy(document.ownerId)
      .orderBy(document.count().desc(), document.ownerId.asc())
      .offset(pageable.getOffset())
      .limit(pageable.getPageSize())
      .select(document.ownerId)
      .fetch();

    final Map<Long, OwnerSummary> result = getOwnerSummaryGroupedByOwner(ownerIds);

    final var list = findByOwnersIdGrouped(result.keySet(), numberOfDocuments, numberOfUnits);
    result.forEach((key, value) -> value.setDocuments(list.getOrDefault(key, new ArrayList<>())));
    return new PageImpl<>(new ArrayList<>(result.values()), pageable, count);
  }

  @Override
  public List<OwnerDocumentStatisticsSummary> findOwnerDocumentStatisticsSummaryByLanguageDataParamsGroupByOwnerId(final LanguageDataParams params) {
    BooleanExpression criteria = sourceLanguage.id.eq(params.getSourceLanguageId())
      .and(targetLanguage.id.eq(params.getTargetLanguageId())).and(document.status.eq(PUBLISHED));
    if (Objects.nonNull(params.getDomainIds()) && params.getDomainIds().length > 0) {
      criteria = criteria.and(domain.id.in(params.getDomainIds()));
    }
    if (Objects.nonNull(params.getContentTypeIds()) && params.getContentTypeIds().length > 0) {
      criteria = criteria.and(contentType.id.in(params.getContentTypeIds()));
    }
    if (Objects.nonNull(params.getOwnerIds()) && params.getOwnerIds().length > 0) {
      criteria = criteria.and(document.ownerId.in(params.getOwnerIds()));
    }
    if (Objects.nonNull(params.getPriceMin()) && Objects.nonNull(params.getPriceMax())) {
      criteria = criteria.and(characteristics.pricePerWord.between(params.getPriceMin(), params.getPriceMax()));
    }
    if (Objects.nonNull(params.getDocumentUids()) && params.getDocumentUids().length > 0) {
      criteria = criteria.and(document.uid.in(params.getDocumentUids()));
    }
    return newQuery()
      .from(document)
      .innerJoin(sourceLanguage).on(document.sourceLanguage.eq(sourceLanguage))
      .innerJoin(targetLanguage).on(document.targetLanguage.eq(targetLanguage))
      .innerJoin(document.characteristics, characteristics).on(
        characteristics.createdAt.eq(select(characteristics.createdAt.max())
          .from(document.characteristics, characteristics)
          .where(characteristics.createdAt.loe(params.getReferenceDateTime())))
      )
      .innerJoin(document.statisticsList, statistics).on(
        statistics.createdAt.eq(select(statistics.createdAt.max())
          .from(document.statisticsList, statistics)
          .where(statistics.createdAt.loe(params.getReferenceDateTime())))
      )
      .innerJoin(characteristics.domains, characteristicsDomain).on(
        characteristicsDomain.domain.id.eq(select(characteristicsDomain.domain.id.max())
          .from(characteristics.domains, characteristicsDomain))
      )
      .innerJoin(characteristics.contentTypes, contentType).on(
        contentType.id.eq(select(contentType.id.max()).from(characteristics.contentTypes, contentType))
      )
      .where(criteria)
      .groupBy(document.ownerId,
        document.id,
        characteristics.pricePerWord,
        statistics.totalUnitsOriginal,
        statistics.totalSourceOriginalWordCount,
        statistics.totalTargetOriginalWordCount,
        statistics.totalUnitsReplica,
        statistics.totalSourceReplicaWordCount,
        statistics.totalTargetReplicaWordCount
      )
      .transform(groupBy(document.ownerId).as(set(documentStatisticsSummaryShort(params.getUnitUnicity(), document, characteristics, statistics))))
      .entrySet().stream().map(e -> new OwnerDocumentStatisticsSummary(e.getKey(), e.getValue())).collect(toList());
  }

  @Override
  public List<OwnerStatisticsSummary> findOwnerStatisticsSummaryByLanguageDataParamsGroupByOwnerId(final LanguageDataParams params) {
    BooleanExpression criteria = sourceLanguage.id.eq(params.getSourceLanguageId())
      .and(targetLanguage.id.eq(params.getTargetLanguageId())).and(document.status.eq(PUBLISHED));
    if (Objects.nonNull(params.getDomainIds()) && params.getDomainIds().length > 0) {
      criteria = criteria.and(domain.id.in(params.getDomainIds()));
    }
    if (Objects.nonNull(params.getContentTypeIds()) && params.getContentTypeIds().length > 0) {
      criteria = criteria.and(contentType.id.in(params.getContentTypeIds()));
    }
    if (Objects.nonNull(params.getOwnerIds()) && params.getOwnerIds().length > 0) {
      criteria = criteria.and(document.ownerId.in(params.getOwnerIds()));
    }
    if (Objects.nonNull(params.getPriceMin()) && Objects.nonNull(params.getPriceMax())) {
      criteria = criteria.and(characteristics.pricePerWord.between(params.getPriceMin(), params.getPriceMax()));
    }
    if (Objects.nonNull(params.getDocumentUids()) && params.getDocumentUids().length > 0) {
      criteria = criteria.and(document.uid.in(params.getDocumentUids()));
    }
    final ConstructorExpression<OwnerStatisticsSummary> summary = Summaries.ownerStatisticsSummary(params.getUnitUnicity(), document, characteristics, statistics);
    return newQuery()
      .from(document)
      .innerJoin(sourceLanguage).on(document.sourceLanguage.eq(sourceLanguage))
      .innerJoin(targetLanguage).on(document.targetLanguage.eq(targetLanguage))
      .innerJoin(document.characteristics, characteristics).on(
        characteristics.createdAt.eq(select(characteristics.createdAt.max())
          .from(document.characteristics, characteristics)
          .where(characteristics.createdAt.loe(params.getReferenceDateTime())))
      )
      .innerJoin(document.statisticsList, statistics).on(
        statistics.createdAt.eq(select(statistics.createdAt.max())
          .from(document.statisticsList, statistics)
          .where(statistics.createdAt.loe(params.getReferenceDateTime())))
      )
      .innerJoin(characteristics.domains, characteristicsDomain)
      .on(characteristicsDomain.createdAt.eq(
        select(characteristicsDomain.createdAt.max())
          .from(characteristics.domains, characteristicsDomain)
          .where(characteristicsDomain.createdAt.loe(params.getReferenceDateTime())
            .and(characteristicsDomain.type.eq(DEFINED))))
      )
      .innerJoin(characteristicsDomain.domain, domain)
      .innerJoin(characteristics.contentTypes, contentType).on(
        contentType.id.eq(select(contentType.id.max()).from(characteristics.contentTypes, contentType))
      )
      .where(criteria)
      .groupBy(document.ownerId, characteristics.pricePerWord, statistics.totalSourceOriginalWordCount)
      .select(summary)
      .fetch();
  }

  private void getUnits(final net.taus.data.language.api.model.TranslationDocument d, final TranslationUnit.Quality unitQuality, final int numberOfUnits) {
    if (numberOfUnits > 0) {
      final Optional<net.taus.data.language.api.model.TranslationDocumentStatistics> currentStatistics = d.getCurrentStatistics();
      currentStatistics.flatMap(s -> Optional.ofNullable(s.getTotalUnitsOriginal())).ifPresent(t -> {
        int actualNumberOfUnits = Math.max((int) (50 * t / 100), 1);
        actualNumberOfUnits = Math.min(numberOfUnits, actualNumberOfUnits);
        d.setUnits(unitRepository.findByDocumentUidAndQuality(d.getUid(), unitQuality, actualNumberOfUnits));
      });
    }
  }

  private Map<Long, OwnerSummary> getOwnerSummaryGroupedByOwner(final List<Long> ownerIds) {
    final QTranslationDocument subDocumentCount = new QTranslationDocument("td2");
    final JPQLQuery<Long> documentCount = select(subDocumentCount.countDistinct()).from(subDocumentCount)
      .where(document.ownerId.eq(subDocumentCount.ownerId).and(subDocumentCount.status.eq(PUBLISHED)));

    return newOwnerQuery()
      .where(document.ownerId.in(ownerIds))
      .transform(groupBy(document.ownerId)
        .as(Summaries.ownerSummary(document, sourceLanguage, targetLanguage, domain, contentType, documentCount)));
  }

  private JPAQuery<Void> newOwnerQuery() {
    return newQuery()
      .from(document)
      .innerJoin(sourceLanguage).on(document.sourceLanguage.eq(sourceLanguage))
      .innerJoin(targetLanguage).on(document.targetLanguage.eq(targetLanguage))
      .innerJoin(document.characteristics, characteristics).on(
        characteristics.createdAt.eq(select(characteristics.createdAt.max()).from(document.characteristics, characteristics)))
      .innerJoin(characteristics.domains, characteristicsDomain)
      .on(characteristicsDomain.createdAt.eq(
        select(characteristicsDomain.createdAt.max())
          .from(characteristics.domains, characteristicsDomain)
          .where(characteristicsDomain.type.eq(DEFINED)))
      )
      .innerJoin(characteristicsDomain.domain, domain)
      .innerJoin(characteristics.contentTypes, contentType).on(
        contentType.id.eq(select(contentType.id.max()).from(characteristics.contentTypes, contentType))
      )
      .groupBy(
        document.ownerId,
        domain.description,
        contentType.description,
        sourceLanguage.tag,
        targetLanguage.tag
      );
  }

  private JPAQuery<Void> newDocumentBaseQuery() {
    return newQuery()
      .from(document)
      .leftJoin(document.characteristics, characteristics).on(
        characteristics.createdAt.eq(select(characteristics.createdAt.max()).from(document.characteristics, characteristics))
      )
      .leftJoin(characteristics.domains, characteristicsDomain)
      .on(characteristicsDomain.createdAt.eq(
        select(characteristicsDomain.createdAt.max())
          .from(characteristics.domains, characteristicsDomain)
          .where(characteristicsDomain.type.eq(DEFINED)))
      )
      .leftJoin(characteristicsDomain.domain, domain)
      .leftJoin(characteristics.contentTypes, contentType).on(
        contentType.id.eq(select(contentType.id.max()).from(characteristics.contentTypes, contentType))
      )
      .leftJoin(sourceLanguage).on(sourceLanguage.eq(document.sourceLanguage))
      .leftJoin(targetLanguage).on(targetLanguage.eq(document.targetLanguage));
  }

  private JPAQuery<Void> newQuery() {
    return new JPAQuery<>(entityManager, JPQLTemplates.DEFAULT);
  }

}
