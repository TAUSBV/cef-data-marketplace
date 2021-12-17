package net.taus.data.marketplace.api.purchase.events;

import net.taus.data.marketplace.api.purchase.services.AccountingService;
import net.taus.data.marketplace.model.purchase.Purchase;
import net.taus.data.marketplace.model.purchase.PurchaseEvent;
import net.taus.data.marketplace.model.purchase.PurchaseRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * {@link ApplicationListener} for {@link PurchaseEvent} that is responsible for invoicing
 */
@Transactional
public class PurchaseInvoiceIssueListener {

  // The injected service responsible for invoicing
  private final AccountingService accountingService;
  private final PurchaseRepository purchases;

  /**
   * Default all-arg constructor. Should be @{@link org.springframework.context.annotation.Bean} instantiated
   *
   * @param accountingService The injected service responsible for invoicing
   * @param purchases         Repository pattern for Purchases
   */
  public PurchaseInvoiceIssueListener(final AccountingService accountingService, final PurchaseRepository purchases) {
    this.accountingService = accountingService;
    this.purchases = purchases;
  }

  @Async
  @TransactionalEventListener
  public void onApplicationEvent(final PurchaseInvoiceIssueEvent event) {
    final Purchase purchase = purchases.getOne(event.getPurchase().getId());
    accountingService.issueInvoice(purchase);
  }

}
