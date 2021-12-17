package net.taus.data.marketplace.api.languagedata;

import net.taus.data.language.api.model.ContentType;
import net.taus.data.language.api.model.Domain;
import net.taus.data.language.api.model.search.LanguageDataSearchResult;
import net.taus.data.marketplace.api.user.UserService;
import net.taus.data.marketplace.model.user.views.SellerNameSummary;

import java.util.Set;
import java.util.stream.Collectors;

public class LanguageDataFiltersLocal extends LanguageDataSearchResult.LanguageDataFilters{

  private final LanguageDataSearchResult.LanguageDataFilters filters;
  private final UserService users;

  public LanguageDataFiltersLocal(final LanguageDataSearchResult.LanguageDataFilters filters, final UserService users) {
    this.filters = filters;
    this.users = users;
  }

  public Set<Domain> getDomains() {
    return this.filters.getDomains();
  }

  public Set<ContentType> getContentTypes() {
    return this.filters.getContentTypes();
  }

  public Set<Double> getPrices() {
    return this.filters.getPrices();
  }

  public Set<SellerNameSummary> getSellers() {
    return this.filters.getOwnerIds().stream()
      .flatMap(id -> users.findByCorporateId(id).stream().map(u -> new SellerNameSummary(u.getUid(), u.getFirstName(), u.getLastName())))
      .collect(Collectors.toSet());
  }
}
