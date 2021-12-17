package net.taus.data.marketplace.model.price;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "price_index")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PriceIndex {

  @Id
  @Column(updatable = false, nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Include
  private Long id;

  @ManyToOne(fetch = FetchType.EAGER)
  @EqualsAndHashCode.Include
  @JoinColumn(name = "language_data_availability_count_id", referencedColumnName = "id")
  private LanguageDataAvailabilityCount languageDataAvailabilityCount;

  @ManyToOne(fetch = FetchType.EAGER)
  @EqualsAndHashCode.Include
  @JoinColumn(name = "language_data_availability_category_id", referencedColumnName = "id")
  private LanguageDataAvailabilityCategory languageDataAvailabilityCategory;

  @EqualsAndHashCode.Include
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @Column(columnDefinition = "decimal(6,4) default 0", name = "suggested_price_per_word")
  private double suggestedPricePerWord;

  public PriceIndex() {
    this.updatedAt = LocalDateTime.now();
    this.languageDataAvailabilityCount = new LanguageDataAvailabilityCount();
    this.languageDataAvailabilityCategory = new LanguageDataAvailabilityCategory();
  }

  public PriceIndex(final LanguageDataAvailabilityCount count) {
    this();
    this.languageDataAvailabilityCount = count;
  }
}
