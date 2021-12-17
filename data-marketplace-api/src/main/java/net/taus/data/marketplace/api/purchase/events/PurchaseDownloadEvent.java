package net.taus.data.marketplace.api.purchase.events;

import net.taus.data.marketplace.api.purchase.services.PurchaseService;
import net.taus.data.marketplace.model.purchase.Download;
import net.taus.data.marketplace.model.purchase.Purchase;
import org.springframework.context.ApplicationEvent;

import java.util.List;

public class PurchaseDownloadEvent extends ApplicationEvent {
  private final Purchase purchase;
  private final List<Download> downloads;

  public PurchaseDownloadEvent(final PurchaseService purchaseService, final Purchase purchase, final List<Download> downloads) {
    super(purchaseService);
    this.purchase = purchase;
    this.downloads = downloads;
  }

  public Purchase getPurchase() {
    return purchase;
  }

  public List<Download> getDownloads() {
    return downloads;
  }
}
