package net.taus.data.marketplace.api.purchase.services;

import net.taus.data.marketplace.api.purchase.events.PurchaseInvoiceIssueEvent;
import net.taus.data.marketplace.model.purchase.Payment;
import net.taus.data.marketplace.model.purchase.Purchase;

import java.util.Map;

public class BankTransferService extends ManualPaymentService {

  @Override
  public Purchase prepare(final Purchase purchase, final Map<String, String> context) {
    final Purchase newPurchase = purchase.addPayment(new Payment(Payment.Method.BANK_TRANSFER));
    publisher.publishEvent(new PurchaseInvoiceIssueEvent(this, purchase));
    newPurchase.invoiceRequested();
    return newPurchase;
  }


}
