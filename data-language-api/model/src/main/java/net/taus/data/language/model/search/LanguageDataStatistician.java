package net.taus.data.language.model.search;

import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.dsl.NumberExpression;
import net.taus.data.language.api.model.Language;
import net.taus.data.language.api.model.LanguageDataParams;
import net.taus.data.language.api.model.TranslationUnitUnicity;
import net.taus.data.language.api.model.search.LanguageDataSearchResult;
import net.taus.data.language.model.util.Summaries;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static com.querydsl.core.types.Projections.constructor;

@Transactional
public class LanguageDataStatistician extends LanguageDataBeanQueryBase {

  public LanguageDataStatistician(final EntityManager entityManager) {
    super(entityManager);
  }

  public LanguageDataSearchResult.LanguageDataStatistics getStatistics(final LanguageDataParams params) {
    final var criteria = newCriteria(params);
    final NumberExpression<Long> documents = document.count();
    final NumberExpression<Long> sellers = document.ownerId.countDistinct();
    NumberExpression<Long> totalUnits = statistics.totalUnitsOriginal;
    NumberExpression<Long> totalSourceWords = statistics.totalSourceOriginalWordCount;
    NumberExpression<Long> totalTargetWords = statistics.totalTargetOriginalWordCount;
    NumberExpression<Double> totalPrice;
    if (params.getUnitUnicity().equals(TranslationUnitUnicity.ORIGINAL_AND_REPLICA)) {
      totalUnits = totalUnits.add(statistics.totalUnitsReplica);
      totalSourceWords = totalSourceWords.add(statistics.totalSourceReplicaWordCount);
      totalTargetWords = totalTargetWords.add(statistics.totalTargetReplicaWordCount);
    }
    totalPrice = characteristics.pricePerWord.multiply(totalSourceWords);
    final ConstructorExpression<Language> sourceLanguageSummary = Summaries.languageSummary(sourceLanguage);
    final ConstructorExpression<Language> targetLanguageSummary = Summaries.languageSummary(targetLanguage);
    final ConstructorExpression<LanguageDataSearchResult.LanguageDataStatistics> statisticsSummary = constructor(
      LanguageDataSearchResult.LanguageDataStatistics.class,
      totalUnits.sum(),
      totalSourceWords.sum(),
      totalTargetWords.sum(),
      documents,
      sellers,
      totalPrice.sum(),
      sourceLanguageSummary,
      targetLanguageSummary
    );
    return newBaseQuery(params.getReferenceDateTime())
      .select(statisticsSummary)
      .where(criteria)
      .groupBy(sourceLanguageSummary, targetLanguageSummary)
      .having(totalUnits.sum().gt(0))
      .fetchOne();
  }

}
