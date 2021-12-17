package net.taus.data.marketplace.api.purchase.events;

import net.taus.data.marketplace.api.crm.CRMService;
import org.springframework.context.ApplicationListener;

public class PurchaseDownloadCreatedListener implements ApplicationListener<PurchaseDownloadEvent> {

  private final CRMService crmService;

  public PurchaseDownloadCreatedListener(final CRMService crmService) {
    this.crmService = crmService;
  }

  @Override
  public void onApplicationEvent(final PurchaseDownloadEvent event) {
    crmService.purchaseDownload(event);
  }
}
