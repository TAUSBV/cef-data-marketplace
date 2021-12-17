package net.taus.data.language.model;

import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLTemplates;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import net.taus.data.language.model.TranslationUnit.Quality;
import net.taus.data.language.model.meta.*;
import net.taus.data.language.model.util.Summaries;

import javax.persistence.EntityManager;
import java.util.*;

import static com.querydsl.jpa.JPAExpressions.select;
import static net.taus.data.language.model.TranslationDocument.Status.PUBLISHED;
import static net.taus.data.language.model.TranslationUnit.Quality.HIGH;
import static net.taus.data.language.model.meta.LanguageDataCharacteristicsDomain.Type.DEFINED;

public class TranslationUnitRepositoryExtension implements TranslationUnitRepositoryCustom {

  private final EntityManager entityManager;
  private final TranslationVariantRepository translationVariantRepository;
  private final QTranslationDocument document;
  private final QTranslationUnit unit;
  private final QTranslationVariant sourceVariant;
  private final QTranslationVariant targetVariant;
  private final QLanguage sourceLanguage;
  private final QLanguage targetLanguage;
  private final QLanguageDataCharacteristics characteristics;
  private final QLanguageDataCharacteristicsDomain characteristicsDomain;
  private final QDomain domain;
  private final QContentType contentType;
  private final BooleanExpression isOriginal;
  private final BooleanExpression isPublished;

  public TranslationUnitRepositoryExtension(final EntityManager entityManager,
                                            final TranslationVariantRepository translationVariantRepository) {
    this.entityManager = entityManager;
    this.translationVariantRepository = translationVariantRepository;
    this.document = new QTranslationDocument("dc");
    this.unit = new QTranslationUnit("tu");
    this.sourceVariant = new QTranslationVariant("vs");
    this.targetVariant = new QTranslationVariant("vt");
    this.characteristics = new QLanguageDataCharacteristics("ldc");
    this.sourceLanguage = new QLanguage("sl");
    this.targetLanguage = new QLanguage("tl");
    this.domain = new QDomain("dm");
    this.contentType = new QContentType("ct");
    this.isOriginal = unit.reference.isNull().and(unit.quality.eq(HIGH));
    this.isPublished = unit.status.eq(PUBLISHED);
    this.characteristicsDomain = new QLanguageDataCharacteristicsDomain("ldcd");
  }

  public List<net.taus.data.language.api.model.TranslationUnit> findPublishedAndHighQualityByDocumentUid(final UUID documentUid,
                                                                                                         final int numberOfUnits) {
    return this.findByPredicate(document.uid.eq(documentUid).and(isOriginal).and(isPublished), numberOfUnits);
  }

  public List<net.taus.data.language.api.model.TranslationUnit> findByDocumentUidAndQuality(final UUID documentUid,
                                                                                            final Quality quality,
                                                                                            final int numberOfUnits) {
    BooleanExpression criteria = document.uid.eq(documentUid);
    if (Objects.nonNull(quality)) {
      criteria = criteria.and(unit.quality.eq(quality));
    }
    return this.findByPredicate(criteria, numberOfUnits);
  }

  public List<net.taus.data.language.api.model.TranslationUnit> findByDocumentUidAndStatusAndQuality(final UUID documentUid,
                                                                                                     final TranslationDocument.Status status,
                                                                                                     final Quality quality,
                                                                                                     final int numberOfUnits) {
    return this.findByPredicate(document.uid.eq(documentUid).and(unit.status.eq(status)).and(unit.quality.eq(quality)), numberOfUnits);
  }

  public List<net.taus.data.language.api.model.TranslationUnit> findByPredicate(final Predicate predicate,
                                                                                final int numberOfUnits) {
    return getTranslationUnitQueryForPredicate(predicate).limit(numberOfUnits).fetch();

  }

  public List<net.taus.data.language.api.model.TranslationUnit> findByPredicate(final Predicate predicate) {
    return getTranslationUnitQueryForPredicate(predicate).fetch();
  }

  private JPAQuery<net.taus.data.language.api.model.TranslationUnit> getTranslationUnitQueryForPredicate(final Predicate predicate) {
    final ConstructorExpression<net.taus.data.language.api.model.TranslationUnit> unitSummary = Summaries
      .unitSummaryLong(document, unit, sourceVariant, targetVariant, sourceLanguage, targetLanguage, domain, contentType);
    return new JPAQuery<>(entityManager, JPQLTemplates.DEFAULT)
      .from(unit)
      .innerJoin(sourceVariant).on(unit.source.eq(sourceVariant))
      .innerJoin(targetVariant).on(unit.target.eq(targetVariant))
      .innerJoin(sourceLanguage).on(sourceVariant.language.eq(sourceLanguage))
      .innerJoin(targetLanguage).on(targetVariant.language.eq(targetLanguage))
      .innerJoin(document).on(unit.document.eq(document))
      .leftJoin(document.characteristics, characteristics).on(
        characteristics.createdAt.eq(select(characteristics.createdAt.max())
          .from(document.characteristics, characteristics))
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
      .where(predicate)
      .groupBy(document.uid, unit.id, domain.id, contentType.id)
      .select(unitSummary);
  }

  @Override
  public Optional<TranslationUnit> findByHashAndStatusAndReferenceIsNull(final long hash,
                                                                         final TranslationDocument.Status status) {
    final var queryFactory = new JPAQueryFactory(JPQLTemplates.DEFAULT, entityManager);
    return Optional.ofNullable(queryFactory.selectFrom(unit)
      .where(unit.hash.eq(hash)
        .and(unit.reference.isNull())
        .and(unit.status.eq(status))
        .and(unit.createdAt.eq(
          select(unit.createdAt.min()).from(unit)
            .where(unit.hash.eq(hash)
              .and(unit.status.eq(status)))
          )
        )
      )
      .fetchOne());
  }

  @Override
  public List<TranslationUnit> findByHashAndDocumentAndReferenceIsNull(final long hash,
                                                                       final TranslationDocument document) {
    final var translationUnit = QTranslationUnit.translationUnit;
    final var queryFactory = new JPAQueryFactory(JPQLTemplates.DEFAULT, entityManager);
    return queryFactory.selectFrom(translationUnit)
      .where(translationUnit.hash.eq(hash)
        .and(translationUnit.reference.isNull())
        .and(translationUnit.document.eq(document)))
      .fetch();
  }

  @Override
  public TranslationVariantRepository getTranslationVariantRepository() {
    return translationVariantRepository;
  }

  @Override
  public List<net.taus.data.language.api.model.TranslationUnit> findByIds(final Collection<Long> ids) {
    return findByPredicate(unit.id.in(ids));
  }

  public void findAndCreateVirtualUnits(final TranslationUnit unit) {
//      source exists and target don't then should find correlated.
//      boolean findCorrelated = Objects.nonNull(source.getId()) && Objects.isNull(target.getId());
    // create correlation (create virtual units)
//    final var targets = this.translationVariantRepository(unit.getSource(), unit.getDomain(), TranslationVariant.class);
//    final var virtualList = targets.stream().map(t -> {
//      var virtualUnit = new TranslationUnit();
//      virtualUnit.setSource(unit.getTarget());
//      virtualUnit.setTarget(t);
//      virtualUnit.setDomain(unit.getDomain());
//      virtualUnit.setContentType(unit.getContentType());
//      virtualUnit.setVirtual(true);
//      virtualUnit.setPricePerWord(unit.getPricePerWord()); // FIXME Use suggested price
//      virtualUnit.setHash(0); // FIXME calculate hash
//      virtualUnit.setDocument(null); // FIXME assign general TAUS document for sales
//      virtualUnit.setReference(null); // Reference and virtual means
//      return virtualUnit;
//    }).collect(toList());
  }

}
