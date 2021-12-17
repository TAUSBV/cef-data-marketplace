package net.taus.data.marketplace.api.purchase.events;

import net.taus.data.marketplace.model.purchase.Purchase;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisherAware;

public class PurchasePaymentPaidEvent extends ApplicationEvent {
  private final Purchase purchase;

  public <T extends ApplicationEventPublisherAware> PurchasePaymentPaidEvent(T invoiceStatusConsumerService, final Purchase purchase) {
    super(invoiceStatusConsumerService);
    this.purchase = purchase;
  }

  public Purchase getPurchase() {
    return purchase;
  }
}
