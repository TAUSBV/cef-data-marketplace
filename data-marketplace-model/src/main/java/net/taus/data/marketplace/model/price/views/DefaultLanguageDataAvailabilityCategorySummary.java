package net.taus.data.marketplace.model.price.views;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DefaultLanguageDataAvailabilityCategorySummary implements LanguageDataAvailabilityCategorySummary {

  private final long id;
  private final String label;
  private final int sort;
}
