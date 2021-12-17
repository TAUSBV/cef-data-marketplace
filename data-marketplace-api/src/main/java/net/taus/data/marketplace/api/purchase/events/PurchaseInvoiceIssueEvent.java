package net.taus.data.marketplace.api.purchase.events;

import net.taus.data.marketplace.api.purchase.services.PaymentService;
import net.taus.data.marketplace.model.purchase.Purchase;
import org.springframework.context.ApplicationEvent;

public class PurchaseInvoiceIssueEvent extends ApplicationEvent {
  private final Purchase purchase;

  public PurchaseInvoiceIssueEvent(final PaymentService paymentService, final Purchase purchase) {
    super(paymentService);
    this.purchase = purchase;
  }

  public Purchase getPurchase() {
    return purchase;
  }
}
