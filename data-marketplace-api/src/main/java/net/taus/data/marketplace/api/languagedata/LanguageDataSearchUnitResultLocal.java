package net.taus.data.marketplace.api.languagedata;

import net.taus.data.language.api.model.Page;
import net.taus.data.language.api.model.search.LanguageDataSearchResult;
import net.taus.data.language.api.model.search.LanguageDataSearchUnitResult;
import net.taus.data.marketplace.api.user.UserService;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class LanguageDataSearchUnitResultLocal extends LanguageDataSearchResult<TranslationUnitSampleLocal> {

  private final LanguageDataSearchUnitResult result;
  private final UserService users;

  public LanguageDataSearchUnitResultLocal(final LanguageDataSearchUnitResult result, final UserService users) {
    this.result = result;
    this.users = users;
  }

  public Page<TranslationUnitSampleLocal> getSample() {
    final List<TranslationUnitSampleLocal> list = result.getSample().getContent()
      .stream().map(unit -> new TranslationUnitSampleLocal(unit, users.findByCorporateId(unit.getOwnerId()).orElse(null)))
      .collect(toList());
    return new Page<>(list, result.getSample().getPageable(), result.getSample().getTotalElements());
  }

  @Override
  public LanguageDataFiltersLocal getFilters() {
    return new LanguageDataFiltersLocal(result.getFilters(), users);
  }

  @Override
  public LanguageDataStatistics getStatistics() {
    return result.getStatistics();
  }
}
