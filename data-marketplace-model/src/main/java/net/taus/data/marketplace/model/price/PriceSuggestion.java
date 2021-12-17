package net.taus.data.marketplace.model.price;


public class PriceSuggestion {

  private final LanguageDataAvailabilityCategory category;
  private final PriceConfiguration priceConfiguration;

  public PriceSuggestion(final LanguageDataAvailabilityCategory category, final PriceConfiguration priceConfiguration) {
    this.category = category;
    this.priceConfiguration = priceConfiguration;
  }

  public double getSuggestedPricePerWord() {
    return priceConfiguration.getBasePrice() * category.getAvailabilityModifier();
  }

  public Long getCategoryId() {
    return this.category.getId();
  }

  public String getCategoryLabel() {
    return this.category.getLabel();
  }

  public Integer getCategorySort() {
    return this.category.getSort();
  }

  public Double getTransactionFee() {
    return this.priceConfiguration.getTransactionFee();
  }

  public Double getCommissionFee() {
    return this.priceConfiguration.getCommissionFee();
  }

}
