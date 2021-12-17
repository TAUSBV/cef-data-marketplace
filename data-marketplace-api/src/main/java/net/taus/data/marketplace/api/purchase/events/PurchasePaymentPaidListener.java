package net.taus.data.marketplace.api.purchase.events;

import net.taus.data.marketplace.api.purchase.services.AccountingService;
import net.taus.data.marketplace.model.price.PriceConfigurationRepository;
import net.taus.data.marketplace.model.purchase.Purchase;
import net.taus.data.marketplace.model.purchase.PurchaseRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;

/**
 * {@link ApplicationListener} for {@link PurchasePaymentPaidEvent} that is responsible for sending payment requests
 */
@Transactional
public class PurchasePaymentPaidListener implements ApplicationListener<PurchasePaymentPaidEvent> {

  // The service we use to send seller-payments that derive from the purchase
  private final AccountingService accountingService;

  private final PriceConfigurationRepository priceConfigurationRepository;
  private final PurchaseRepository purchases;

  /**
   * Default all-arg constructor. Should be @{@link org.springframework.context.annotation.Bean} instantiated
   *  @param accountingService The injected service responsible for accounting related operations such as sending payment requests
   * @param priceConfigurationRepository Repository which we use to retrieve the service's current price configuration (ex. commission fees)
   * @param purchases Repository pattern for Purchases.
   */
  public PurchasePaymentPaidListener(final AccountingService accountingService,
                                     final PriceConfigurationRepository priceConfigurationRepository,
                                     final PurchaseRepository purchases) {
    this.accountingService = accountingService;
    this.priceConfigurationRepository = priceConfigurationRepository;
    this.purchases = purchases;
  }

  @Async
  @Override
  public void onApplicationEvent(final PurchasePaymentPaidEvent event) {
    // The default commission fee
    var commissionFee = priceConfigurationRepository.getDefault().getCommissionFee();
    final Purchase purchase = purchases.getOne(event.getPurchase().getId());
    accountingService.requestSellerPayment(purchase, commissionFee);
  }

}
