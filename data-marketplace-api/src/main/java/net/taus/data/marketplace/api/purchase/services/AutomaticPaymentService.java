package net.taus.data.marketplace.api.purchase.services;

import net.taus.data.marketplace.api.purchase.events.PurchaseInvoiceIssueEvent;
import net.taus.data.marketplace.api.purchase.events.PurchasePaymentPaidEvent;
import net.taus.data.marketplace.model.purchase.Payment;
import net.taus.data.marketplace.model.purchase.Purchase;

import java.util.Optional;

public abstract class AutomaticPaymentService extends PaymentService {

  public Purchase check(final Purchase purchase) {
    return Optional.of(purchase).filter(Purchase::isAwaitingPayment).flatMap(p -> p.getCurrentPayment().filter(Payment::isUnpaid).map(pm -> {
      Payment.Status status = getLatestPaymentStatus(pm);
      if (!pm.getStatus().equals(status)) {
        pm.setStatus(status);
        if (Payment.Status.PAID.equals(status)) {
          publisher.publishEvent(new PurchaseInvoiceIssueEvent(this, p.invoiceRequested()));
          publisher.publishEvent(new PurchasePaymentPaidEvent(this, p));
        }
      }
      return p;
    })).orElse(purchase);
  }

  public abstract Payment.Status getLatestPaymentStatus(final Payment pm);

}
