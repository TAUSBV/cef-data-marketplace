package net.taus.data.language.api.model.search;

import lombok.*;
import net.taus.data.language.api.model.Page;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class LanguageDataSearchUnitResult extends LanguageDataSearchResult<TranslationUnitSample> {

  private Page<TranslationUnitSample> sample;

  public LanguageDataSearchUnitResult(final LanguageDataFilters filters,
                                      final LanguageDataStatistics statistics,
                                      final Page<TranslationUnitSample> sample) {
    super(filters, statistics);
    this.sample = sample;
  }
}
