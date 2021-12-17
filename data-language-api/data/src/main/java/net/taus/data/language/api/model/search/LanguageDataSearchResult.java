package net.taus.data.language.api.model.search;

import lombok.*;
import net.taus.data.language.api.model.ContentType;
import net.taus.data.language.api.model.Domain;
import net.taus.data.language.api.model.Language;
import net.taus.data.language.api.model.Page;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public abstract class LanguageDataSearchResult<T> {

  private LanguageDataFilters filters;
  private LanguageDataStatistics statistics;

  public abstract Page<T> getSample();

  @Getter
  @Setter
  @ToString
  @AllArgsConstructor
  @NoArgsConstructor
  public static class LanguageDataStatistics {
    private long unitsTotal;
    private long sourceWordCountTotal;
    private long targetWordCountTotal;
    private long documents;
    private long sellersTotal;
    private double totalPrice;
    private Language sourceLanguage;
    private Language targetLanguage;
    private LocalDateTime collectedAt;

    public LanguageDataStatistics(final long unitsTotal,
                                  final long sourceWordCountTotal,
                                  final long targetWordCountTotal,
                                  final long documents,
                                  final long sellersTotal,
                                  final double totalPrice,
                                  final Language sourceLanguage,
                                  final Language targetLanguage) {
      this.unitsTotal = unitsTotal;
      this.sourceWordCountTotal = sourceWordCountTotal;
      this.targetWordCountTotal = targetWordCountTotal;
      this.documents = documents;
      this.sellersTotal = sellersTotal;
      this.totalPrice = totalPrice;
      this.sourceLanguage = sourceLanguage;
      this.targetLanguage = targetLanguage;
      this.collectedAt = LocalDateTime.now();
    }
  }

  @Getter
  @Setter
  @ToString
  @AllArgsConstructor
  @NoArgsConstructor
  public static class LanguageDataFilters {
    private Set<Domain> domains;
    private Set<ContentType> contentTypes;
    private Set<Long> ownerIds;
    private Set<Double> prices;
  }

}
