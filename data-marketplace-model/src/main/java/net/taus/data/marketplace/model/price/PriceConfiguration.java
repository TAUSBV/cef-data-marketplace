package net.taus.data.marketplace.model.price;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "price_configuration")
public class PriceConfiguration {

  @Id
  @Column(updatable = false, nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "base_price")
  private double basePrice;

  @Column(name = "transaction_fee")
  private double transactionFee;

  @Column(name = "commission_fee")
  private double commissionFee;

  @Column(name = "availability_modifier_minimum_word_count")
  private long availabilityModifierMinimumWordCount;

  @Column(name = "availability_modifier_minimum_language_data_count_entries")
  private long availabilityModifierMinimumLanguageDataCountEntries;

  public PriceConfiguration() {
  }

  public PriceConfiguration(final double basePrice, final double transactionFee, final double commissionFee, final long availabilityModifierMinimumWordCount, final long availabilityModifierMinimumLanguageDataCountEntries) {
    this.basePrice = basePrice;
    this.transactionFee = transactionFee;
    this.commissionFee = commissionFee;
    this.availabilityModifierMinimumWordCount = availabilityModifierMinimumWordCount;
    this.availabilityModifierMinimumLanguageDataCountEntries = availabilityModifierMinimumLanguageDataCountEntries;
  }

  public boolean hasEnoughData(final long totalWordCount, final long countsSize, final long categoriesSize) {
    var availabilityModifierMinimumWordCount = this.getAvailabilityModifierMinimumWordCount();
    var availabilityModifierMinimumLanguageDataCountEntries = this.getAvailabilityModifierMinimumLanguageDataCountEntries();
    return (availabilityModifierMinimumWordCount <= totalWordCount &&
      availabilityModifierMinimumLanguageDataCountEntries <= countsSize &&
      categoriesSize <= countsSize);
  }
}