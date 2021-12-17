package net.taus.data.marketplace.api.purchase.events;

import net.taus.data.marketplace.model.purchase.PurchaseItem;
import net.taus.data.marketplace.model.purchase.PurchaseItemRepository;
import org.springframework.context.ApplicationEvent;

public class PurchaseItemReviewedEvent extends ApplicationEvent {

  private final PurchaseItem purchaseItem;

  /**
   * Create a new {@code ApplicationEvent}.
   *
   * @param source       the object on which the event initially occurred or with
   *                     which the event is associated (never {@code null})
   * @param purchaseItem the purchase item that was reviewed.
   */
  public PurchaseItemReviewedEvent(final PurchaseItemRepository source, final PurchaseItem purchaseItem) {
    super(source);
    this.purchaseItem = purchaseItem;
  }

  public PurchaseItem getPurchaseItem() {
    return purchaseItem;
  }
}
