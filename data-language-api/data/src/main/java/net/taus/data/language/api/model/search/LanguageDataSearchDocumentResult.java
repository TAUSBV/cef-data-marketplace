package net.taus.data.language.api.model.search;

import lombok.*;
import net.taus.data.language.api.model.Page;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class LanguageDataSearchDocumentResult extends LanguageDataSearchResult<TranslationDocumentSample> {

  private Page<TranslationDocumentSample> sample;

  public LanguageDataSearchDocumentResult(final LanguageDataFilters filters,
                                          final LanguageDataStatistics statistics,
                                          final Page<TranslationDocumentSample> sample) {
    super(filters, statistics);
    this.sample = sample;
  }
}
