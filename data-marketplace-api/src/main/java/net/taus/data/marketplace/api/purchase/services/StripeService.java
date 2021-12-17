package net.taus.data.marketplace.api.purchase.services;

import com.stripe.model.checkout.Session;
import net.taus.data.marketplace.model.SystemException;
import net.taus.data.marketplace.model.purchase.Payment;
import net.taus.data.marketplace.model.purchase.Purchase;
import net.taus.data.marketplace.model.support.UUIDSerializer;
import net.taus.payment.PaymentBrokerException;
import net.taus.payment.stripe.client.StripeClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Map;

import static java.math.RoundingMode.HALF_EVEN;
import static java.util.stream.Collectors.toList;


public class StripeService extends AutomaticPaymentService {

  private final Logger logger = LoggerFactory.getLogger(StripeService.class);
  private final UUIDSerializer uuidSerializer;
  private final StripeClient stripeClient;

  public StripeService(@NotNull(message = "Invalid stripeClient (Null)") final StripeClient stripeClient) {
    this.stripeClient = stripeClient;
    this.uuidSerializer = new UUIDSerializer();
  }

  public Payment.Status getLatestPaymentStatus(final Payment pm) {
    Payment.Status status;
    try {
      final String brokerStatus = this.stripeClient.retrievePaymentSession(pm.getBrokerTransactionId()).getPaymentStatus();
      status = Payment.Status.get(brokerStatus.toUpperCase()).orElse(pm.getStatus());
    }
    catch (PaymentBrokerException e) {
      logger.warn("Could not check the payment status for payment {}.", pm.getId(), e);
      status = pm.getStatus();
    }
    return status;
  }

  public Purchase prepare(final Purchase purchase, Map<String, String> context) throws SystemException {
    try {
      final var items = purchase.getItems().stream().map(i ->
        this.stripeClient.createPaymentSessionLineItem(
          i.getProduct().getDescription(),
          String.format("%s, %s segments, %s source words", i.getProduct().getDescription(), i.getTotalUnits(), i.getTotalSourceWordCount()),
          1L,
          BigDecimal.valueOf(i.getTotalPrice()).setScale(2, HALF_EVEN).multiply(BigDecimal.valueOf(100)).longValue())
      ).collect(toList());

      final String callbackUrlDefault = "https://datamarketplace.taus.net/checkout/";
      final var successUrl = String.format("%s?p=%s", context.getOrDefault("callbackUrl", callbackUrlDefault), uuidSerializer.convert(purchase.getUid()));
      final var cancelUrl = String.format("%s?p=%s", context.getOrDefault("callbackUrl", callbackUrlDefault), uuidSerializer.convert(purchase.getUid()));

      Session session = this.stripeClient.createCardPaymentSession(
        uuidSerializer.convert(purchase.getUid()),
        items,
        successUrl,
        cancelUrl
      );

      return purchase.addPayment(new Payment(session.getId(), Payment.Method.STRIPE));

    }
    catch (PaymentBrokerException e) {
      throw new SystemException(e.getMessage(), e);
    }
  }
}
