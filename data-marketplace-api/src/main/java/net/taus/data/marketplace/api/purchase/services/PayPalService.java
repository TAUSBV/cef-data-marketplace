package net.taus.data.marketplace.api.purchase.services;

import com.paypal.orders.Order;
import com.paypal.orders.OrderRequest;
import net.taus.data.marketplace.model.SystemException;
import net.taus.data.marketplace.model.purchase.Payment;
import net.taus.data.marketplace.model.purchase.Purchase;
import net.taus.payment.paypal.client.PaypalClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

public class PayPalService extends AutomaticPaymentService {

  private final Logger logger = LoggerFactory.getLogger(PayPalService.class);
  private final PaypalClient paypalClient;

  public PayPalService(final PaypalClient paypalClient) {
    this.paypalClient = Objects.requireNonNull(paypalClient,"Invalid paypalClient (Null)");
  }

  protected PaypalClient getPaypalClient(){ return this.paypalClient; }

  @Override
  public Payment.Status getLatestPaymentStatus(final Payment pm) {
    Payment.Status status = pm.getStatus();
    try {
      final String brokerStatus = this.getPaypalClient().getOrder(pm.getBrokerTransactionId()).status();
      if ("APPROVED".equalsIgnoreCase(brokerStatus)) {
        status = Payment.Status.PAID;
      }
    }
    catch (IOException e) {
      logger.warn("Could not check the payment status for payment {}.", pm.getId(), e);
      status = pm.getStatus();
    }
    return status;
  }

  public Purchase prepare(final Purchase purchase, Map<String, String> context) {
    try {
      Order order = this.getPaypalClient().sendOrder(this.newOrderRequest(purchase));
      Payment payment = new Payment(order.id(), Payment.Method.PAYPAL);
      purchase.addPayment(payment);
    }
    catch (IOException e) {
      throw new SystemException(e.getMessage(), e);
    }
    return purchase;
  }

  private OrderRequest newOrderRequest(final Purchase purchase) {

    return this.getPaypalClient().createOrderRequest(
      purchase.getUid().toString(),
      "TAUS Data Marketplace",
      "Corpora",
      purchase.getSubTotal(),
      purchase.getTotalTax(),
      purchase.getItems().stream().map(i ->
        this.getPaypalClient().createOrderItem(
          i.getProduct().getDescription(),
          i.getProduct().getSku(),
          i.getPrice(),
          i.getTaxAmount(),
        1
        )
      ).collect(toList())
    );
  }

}
