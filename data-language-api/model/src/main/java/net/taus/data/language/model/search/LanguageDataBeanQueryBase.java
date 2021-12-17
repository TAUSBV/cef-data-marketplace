package net.taus.data.language.model.search;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLTemplates;
import com.querydsl.jpa.impl.JPAQuery;
import net.taus.data.language.api.model.LanguageDataParams;
import net.taus.data.language.model.QTranslationDocument;
import net.taus.data.language.model.QTranslationDocumentStatistics;
import net.taus.data.language.model.meta.*;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.Objects;

import static com.querydsl.jpa.JPAExpressions.select;
import static com.querydsl.jpa.JPQLTemplates.DEFAULT;
import static net.taus.data.language.model.TranslationDocument.Status.PUBLISHED;
import static net.taus.data.language.model.meta.LanguageDataCharacteristicsDomain.Type.DEFINED;

public class LanguageDataBeanQueryBase {

  protected final EntityManager entityManager;
  protected final QTranslationDocument document;
  protected final QTranslationDocumentStatistics statistics;
  protected final QLanguage sourceLanguage;
  protected final QLanguage targetLanguage;
  protected final QDomain domain;
  protected final QContentType contentType;
  protected final QLanguageDataCharacteristics characteristics;
  protected final QLanguageDataCharacteristicsDomain characteristicsDomain;

  public LanguageDataBeanQueryBase(final EntityManager entityManager) {
    this.entityManager = entityManager;
    this.document = new QTranslationDocument("dc");
    this.statistics = new QTranslationDocumentStatistics("tds");
    this.characteristics = new QLanguageDataCharacteristics("cs");
    this.sourceLanguage = new QLanguage("sl");
    this.targetLanguage = new QLanguage("tl");
    this.domain = new QDomain("dm");
    this.contentType = new QContentType("ct");
    this.characteristicsDomain = new QLanguageDataCharacteristicsDomain("ldcd");
  }

  protected BooleanExpression newCriteria(final LanguageDataParams params) {
    var criteria = document.sourceLanguage.id.eq(params.getSourceLanguageId())
      .and(document.targetLanguage.id.eq(params.getTargetLanguageId()))
      .and(document.status.eq(PUBLISHED));
    if (Objects.nonNull(params.getDomainIds()) && params.getDomainIds().length > 0) {
      criteria = criteria.and(domain.id.in(params.getDomainIds()));
    }
    if (Objects.nonNull(params.getContentTypeIds()) && params.getContentTypeIds().length > 0) {
      criteria = criteria.and(contentType.id.in(params.getContentTypeIds()));
    }
    if (Objects.nonNull(params.getPriceMin()) && Objects.nonNull(params.getPriceMax())) {
      criteria = criteria.and(characteristics.pricePerWord.between(params.getPriceMin(), params.getPriceMax()));
    }
    if (Objects.nonNull(params.getOwnerIds()) && params.getOwnerIds().length > 0) {
      criteria = criteria.and(document.ownerId.in(params.getOwnerIds()));
    }
    return criteria;
  }

  protected JPAQuery<Object> newBaseQuery(final LocalDateTime referenceDataTime) {
    return new JPAQuery<>(entityManager, DEFAULT)
      .from(document)
      .innerJoin(sourceLanguage).on(document.sourceLanguage.eq(sourceLanguage))
      .innerJoin(targetLanguage).on(document.targetLanguage.eq(targetLanguage))
      .innerJoin(document.characteristics, characteristics).on(characteristics.createdAt.eq(
        select(characteristics.createdAt.max()).from(document.characteristics, characteristics)
          .where(characteristics.createdAt.loe(referenceDataTime)))
      )
      .innerJoin(document.statisticsList, statistics).on(statistics.createdAt.eq(
        select(statistics.createdAt.max()).from(document.statisticsList, statistics)
          .where(characteristics.createdAt.loe(referenceDataTime)))
      )
      .leftJoin(characteristics.domains, characteristicsDomain)
        .on(characteristicsDomain.createdAt.eq(
          select(characteristicsDomain.createdAt.max())
            .from(characteristics.domains, characteristicsDomain)
            .where(characteristicsDomain.createdAt.loe(referenceDataTime)
              .and(characteristicsDomain.type.eq(DEFINED))))
        )
      .leftJoin(characteristicsDomain.domain, domain)
      .innerJoin(characteristics.contentTypes, contentType).on(
        contentType.id.eq(select(contentType.id.max()).from(characteristics.contentTypes, contentType))
      );
  }


}
