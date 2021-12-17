package net.taus.data.language.model.search;

import com.querydsl.core.group.GroupBy;
import net.taus.data.language.api.model.LanguageDataParams;
import net.taus.data.language.api.model.Page;
import net.taus.data.language.api.model.TranslationUnitUnicity;
import net.taus.data.language.api.model.search.*;
import net.taus.data.language.model.util.Summaries;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;

import static com.querydsl.core.group.GroupBy.set;
import static com.querydsl.core.types.Projections.constructor;
import static net.taus.data.language.model.TranslationDocument.Status.PUBLISHED;

@Transactional
public class LanguageDataSearcher extends LanguageDataBeanQueryBase {

  private final LanguageDataStatistician statistician;
  private final LanguageDataSampler sampler;

  public LanguageDataSearcher(final EntityManager entityManager,
                              final LanguageDataStatistician statistician,
                              final LanguageDataSampler sampler) {
    super(entityManager);
    this.statistician = statistician;
    this.sampler = sampler;
  }

  public LanguageDataSearchDocumentResult documents(final LanguageDataParams params,
                                                                       final PageRequest pageRequest) {
    params.setUnitUnicity(TranslationUnitUnicity.ORIGINAL_AND_REPLICA);
    final LanguageDataSearchResult.LanguageDataFilters filters = getFilters(params.getSourceLanguageId(), params.getTargetLanguageId(), params.getReferenceDateTime());
    final LanguageDataSearchResult.LanguageDataStatistics statistics = statistician.getStatistics(params);
    final Page<TranslationDocumentSample> sample = (Page<TranslationDocumentSample>) sampler.getSample(SampleKind.DOCUMENTS, params, pageRequest);
    return new LanguageDataSearchDocumentResult(filters, statistics, sample);
  }

  public LanguageDataSearchUnitResult units(final LanguageDataParams params) {
    return units(params, PageRequest.of(0, 100));
  }

  public LanguageDataSearchUnitResult units(final LanguageDataParams params,
                                                               final PageRequest pageRequest) {
    params.setUnitUnicity(TranslationUnitUnicity.ORIGINAL_ONlY);
    final LanguageDataSearchResult.LanguageDataFilters filters = getFilters(params.getSourceLanguageId(), params.getTargetLanguageId(), params.getReferenceDateTime());
    final LanguageDataSearchResult.LanguageDataStatistics statistics = statistician.getStatistics(params);
    final Page<TranslationUnitSample> sample = (Page<TranslationUnitSample>) sampler.getSample(SampleKind.UNITS, params, pageRequest);
    return new LanguageDataSearchUnitResult(filters, statistics, sample);
  }

  public LanguageDataSearchResult.LanguageDataFilters getFilters(final Long sourceLanguageId,
                                                                 final Long targetLanguageId,
                                                                 final LocalDateTime referenceDate) {
    return newBaseQuery(referenceDate)
      .where(document.sourceLanguage.id.eq(sourceLanguageId)
        .and(document.targetLanguage.id.eq(targetLanguageId))
        .and(document.status.eq(PUBLISHED))
      )
      .groupBy(sourceLanguage, domain, contentType, document.ownerId, characteristics.pricePerWord)
      .orderBy(characteristics.pricePerWord.asc())
      .transform(GroupBy.groupBy(this.sourceLanguage.id).as(
        constructor(LanguageDataSearchResult.LanguageDataFilters.class,
          set(Summaries.domainSummary(domain)),
          set(Summaries.contentTypeSummary(contentType)),
          set(document.ownerId),
          set(characteristics.pricePerWord)
        ))
      ).get(sourceLanguageId);
  }

  public enum SampleKind {
    DOCUMENTS, UNITS
  }

}