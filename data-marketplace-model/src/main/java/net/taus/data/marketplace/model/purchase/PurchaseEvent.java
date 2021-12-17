package net.taus.data.marketplace.model.purchase;

import org.springframework.context.ApplicationEvent;

public class PurchaseEvent extends ApplicationEvent {

  private final Purchase purchase;

  /**
   * Create a new {@code ApplicationEvent}.
   *
   * @param source the object on which the event initially occurred or with
   *               which the event is associated (never {@code null})
   * @param purchase the purchase that generated the event
   */
  public PurchaseEvent(final Object source, final Purchase purchase) {
    super(source);
    this.purchase = purchase;
  }

  public Purchase getPurchase() {
    return purchase;
  }
}
