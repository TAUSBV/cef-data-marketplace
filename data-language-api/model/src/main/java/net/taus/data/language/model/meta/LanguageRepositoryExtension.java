package net.taus.data.language.model.meta;

import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.JPQLTemplates;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import net.taus.data.language.api.model.CompositeLanguageSourceTargetCount;
import net.taus.data.language.api.model.LanguageSourceTargetCount;
import net.taus.data.language.model.QTranslationDocument;
import net.taus.data.language.model.QTranslationDocumentStatistics;
import net.taus.data.language.model.util.Summaries;
import net.taus.data.language.model.TranslationDocument;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.types.Projections.constructor;
import static com.querydsl.jpa.JPAExpressions.select;
import static java.util.stream.Collectors.toList;

@Transactional
public class LanguageRepositoryExtension implements LanguageRepositoryCustom {

  public static final Comparator<LanguageSourceTargetCount> LANGUAGE_SOURCE_TARGET_COUNT_COMPARATOR = (o1, o2) -> o1.getSource().getName().compareToIgnoreCase(o2.getSource().getName());
  private final EntityManager entityManager;
  private final ReadWriteLock lock;

  public LanguageRepositoryExtension(final EntityManager entityManager) {
    this.entityManager = entityManager;
    this.lock = new ReentrantReadWriteLock();
  }

  @Override
  @Cacheable(value = "LanguageRepository.languageByTag", key = "#tag")
  @Transactional(isolation = Isolation.READ_UNCOMMITTED)
  public Optional<Language> findOrCreateIfExistsInIanaByTag(final String tag) {
    Optional<Language> optional;
    try {
      lock.writeLock().lock();
      optional = entityManager.createNamedQuery(Language.FIND_BY_TAG, Language.class)
        .setParameter("tag", tag).getResultList().stream()
        .findFirst()
        .or(() -> createIfExistsInIanaByTag(tag));
    }
    catch (IllformedLocaleException | LanguageException e) {
      optional = Optional.empty();
    }
    finally {
      lock.writeLock().unlock();
    }
    return optional;
  }

  public Optional<Language> createIfExistsInIanaByTag(final String tag) {
    return Language.fromTag(tag).map(language -> {
      Language languageCreated = null;
      QIana iana = QIana.iana;
      JPAQuery<Iana> query = new JPAQueryFactory(JPQLTemplates.DEFAULT, entityManager).selectFrom(iana);
      List<BooleanExpression> predicateList = new ArrayList<>();
      if (!language.getName().isEmpty()) {
        predicateList.add(iana.type.eq("language").and(iana.subtag.eq(language.getName())));
      }
      if (!language.getScript().isEmpty()) {
        predicateList.add(iana.type.eq("script").and(iana.subtag.eq(language.getScript())));
      }
      if (!language.getCountry().isEmpty()) {
        predicateList.add(iana.type.eq("region").and(iana.subtag.eq(language.getCountry())));
      }
      if (!language.getVariant().isEmpty()) {
        predicateList.add(iana.type.eq("variant").and(iana.subtag.eq(language.getVariant())));
      }
      final long list = query.where(Expressions.anyOf(predicateList.toArray(new BooleanExpression[0]))).fetchCount();
      if (list == predicateList.size()) {
        entityManager.persist(language);
        languageCreated = language;
      }
      return languageCreated;
    });
  }

  public CompositeLanguageSourceTargetCount findSourceTargetGroups() {
    final var document = new QTranslationDocument("td");
    final var statistics = new QTranslationDocumentStatistics("tds");
    final var characteristics = new QLanguageDataCharacteristics("cs");
    final var sourceLanguage = new QLanguage("ls");
    final var targetLanguage = new QLanguage("lt");
    final JPAQuery<?> sourcesQuery = new JPAQueryFactory(JPQLTemplates.DEFAULT, entityManager)
      .from(document)
      .innerJoin(document.characteristics, characteristics).on(
        characteristics.createdAt.eq(select(characteristics.createdAt.max()).from(document.characteristics, characteristics)))
      .innerJoin(document.statisticsList, statistics).on(
        statistics.createdAt.eq(select(statistics.createdAt.max()).from(document.statisticsList, statistics)))
      .innerJoin(sourceLanguage).on(document.sourceLanguage.eq(sourceLanguage))
      .innerJoin(targetLanguage).on(document.targetLanguage.eq(targetLanguage))
      .where(document.status.eq(TranslationDocument.Status.PUBLISHED))
      .groupBy(sourceLanguage, targetLanguage)
      .orderBy(sourceLanguage.id.asc(), sourceLanguage.displayName.asc(), sourceLanguage.tag.asc());
    final JPAQuery<?> targetsQuery = sourcesQuery.clone();
    final NumberExpression<Long> totalUnitsOriginal = statistics.totalUnitsOriginal.sum();
    final List<LanguageSourceTargetCount> sources = transform(sourcesQuery, sourceLanguage, targetLanguage, totalUnitsOriginal);
    final List<LanguageSourceTargetCount> targets = transform(targetsQuery, targetLanguage, sourceLanguage, totalUnitsOriginal);
    return new CompositeLanguageSourceTargetCount(sources, targets);
  }

  private List<LanguageSourceTargetCount> transform(final JPAQuery<?> query,
                                                    final QLanguage source,
                                                    final QLanguage target,
                                                    final NumberExpression<Long> totalUnitsOriginal) {
    return query.transform(groupBy(source).as(
      constructor(LanguageSourceTargetCount.class, Summaries.languageSummary(source),
        GroupBy.list(constructor(LanguageSourceTargetCount.LanguageCount.class,
          Summaries.languageSummary(target), totalUnitsOriginal))))
    ).values().stream().sorted(LANGUAGE_SOURCE_TARGET_COUNT_COMPARATOR).collect(toList());
  }
}
