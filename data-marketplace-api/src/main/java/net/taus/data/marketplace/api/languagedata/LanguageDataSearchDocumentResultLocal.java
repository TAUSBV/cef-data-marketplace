package net.taus.data.marketplace.api.languagedata;

import net.taus.data.language.api.model.Page;
import net.taus.data.language.api.model.search.LanguageDataSearchDocumentResult;
import net.taus.data.language.api.model.search.LanguageDataSearchResult;
import net.taus.data.marketplace.api.user.UserService;
import net.taus.data.marketplace.model.languagedata.TranslationDocumentReviewRepository;
import net.taus.data.marketplace.model.languagedata.TranslationDocumentReviewStatistics;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class LanguageDataSearchDocumentResultLocal extends LanguageDataSearchResult<TranslationDocumentSampleLocal> {

  private final LanguageDataSearchDocumentResult result;
  private final UserService users;
  private final TranslationDocumentReviewRepository reviews;

  public LanguageDataSearchDocumentResultLocal(final LanguageDataSearchDocumentResult result,
                                               final UserService users,
                                               final TranslationDocumentReviewRepository reviews) {
    this.result = result;
    this.users = users;
    this.reviews = reviews;
  }

  public Page<TranslationDocumentSampleLocal> getSample() {
    final List<TranslationDocumentSampleLocal> list = result.getSample().getContent()
      .stream().map(document -> {
        final TranslationDocumentReviewStatistics reviewStatistics = this.reviews.getStatistics(document.getUid());
        return new TranslationDocumentSampleLocal(document, users.findByCorporateId(document.getOwnerId()).orElse(null), reviewStatistics);
      }).collect(toList());
    return new Page<>(list, result.getSample().getPageable(), result.getSample().getTotalElements());
  }

  @Override
  public LanguageDataStatistics getStatistics() {
    return result.getStatistics();
  }

  @Override
  public LanguageDataFiltersLocal getFilters() {
    return new LanguageDataFiltersLocal(result.getFilters(), users);
  }
}
