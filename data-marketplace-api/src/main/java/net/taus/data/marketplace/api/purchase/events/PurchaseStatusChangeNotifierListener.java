package net.taus.data.marketplace.api.purchase.events;

import net.taus.data.marketplace.api.crm.CRMService;
import net.taus.data.marketplace.model.purchase.Purchase;
import net.taus.data.marketplace.model.purchase.PurchaseEvent;
import org.springframework.context.ApplicationListener;

public class PurchaseStatusChangeNotifierListener implements ApplicationListener<PurchaseEvent> {

  private final CRMService crmService;

  public PurchaseStatusChangeNotifierListener(CRMService crmService) {
    this.crmService = crmService;
  }

  @Override
  public void onApplicationEvent(final PurchaseEvent event) {
    final var purchase = event.getPurchase();
    notifyStatus(purchase);
  }

  public void notifyStatus(final Purchase purchase) {
    crmService.purchaseStatusChanged(purchase);
  }
}
