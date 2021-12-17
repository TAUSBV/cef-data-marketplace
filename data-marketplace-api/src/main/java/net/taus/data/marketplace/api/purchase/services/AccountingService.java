package net.taus.data.marketplace.api.purchase.services;

import lombok.Value;
import net.taus.data.marketplace.api.validation.Country;
import net.taus.data.marketplace.model.BusinessException;
import net.taus.data.marketplace.model.SystemException;
import net.taus.data.marketplace.model.email.EmailSenderService;
import net.taus.data.marketplace.model.purchase.DatasetSize;
import net.taus.data.marketplace.model.purchase.Purchase;
import net.taus.data.marketplace.model.purchase.PurchaseItemSellerShare;
import net.taus.data.marketplace.model.seller.payment.SellerPayment;
import net.taus.data.marketplace.model.seller.payment.SellerPaymentRepository;
import net.taus.data.marketplace.model.support.UUIDUtil;
import net.taus.data.marketplace.model.user.UserDetail;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

import static java.lang.String.format;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.stream.Collectors.*;

/**
 * Service responsible request invoices issue to the Corporate Financial System.
 */
@Transactional
public class AccountingService {

  private final Logger logger = LoggerFactory.getLogger(AccountingService.class.getCanonicalName());
  private final SellerPaymentRepository sellerPaymentRepository;
  private final EmailSenderService emailService;
  private final KafkaTemplate<String, Transaction> kafkaTemplate;
  private final int reconnectWarning;
  private final String topicSaleInvoice;
  private final String topicPaymentCreate;
  private final String paymentServiceApiKey;
  private final int daysToRefund;

  /**
   * @param sellerPaymentRepository TODO
   * @param emailService            Service used to send emails. Currently used to send emails as fallback when
   *                                kafka is down
   * @param kafkaTemplate           The template that will be used to produce accounting related events to Kafka
   * @param reconnectWarning        The amount of time we wait before we trigger a warning when a producer is
   *                                failing to connect and publish to a broker
   * @param topicSaleInvoice        The kafka topic where we want to publish a {@link Sale}
   * @param topicPaymentCreate      The kafka topic where we want to publish a {@link Payment}
   * @param paymentServiceApiKey    The API key of the payment service
   * @param daysToRefund            The number of days after a purchase that we allow for a buyer to ask for a refund.
   *                                A seller payment will not get executed before this period has passed
   */
  public AccountingService(final SellerPaymentRepository sellerPaymentRepository,
                           final EmailSenderService emailService,
                           final KafkaTemplate<String, Transaction> kafkaTemplate,
                           final int reconnectWarning,
                           final String topicSaleInvoice,
                           final String topicPaymentCreate,
                           final String paymentServiceApiKey,
                           final int daysToRefund) {
    this.sellerPaymentRepository = sellerPaymentRepository;
    this.emailService = emailService;
    this.kafkaTemplate = kafkaTemplate;
    this.reconnectWarning = reconnectWarning;
    this.topicSaleInvoice = topicSaleInvoice;
    this.topicPaymentCreate = topicPaymentCreate;
    this.paymentServiceApiKey = paymentServiceApiKey;
    this.daysToRefund = daysToRefund;
  }

  public void issueInvoice(final Purchase purchase) {
    AccountingCommunicationHandler handler = null;
    try {
      final Sale sale = Sale.toSale(purchase);
      handler = new AccountingCommunicationHandler(sale, emailService, reconnectWarning);

      var producerRecord = new ProducerRecord<String, Transaction>(topicSaleInvoice, sale.getStateValue(), sale);
      producerRecord.headers().add("apiKey", paymentServiceApiKey.getBytes(UTF_8));
      producerRecord.headers().add("email", purchase.getBuyer().getEmail().getBytes(UTF_8));

      kafkaTemplate.send(producerRecord).addCallback(handler);
    }
    catch (Exception e) {
      logger.error(format("Fail to request invoice issue for purchase %s", purchase.getUid()), e);
      // In case there was a serialization error before the handler is initialized, it has nothing to do
      // with Kafka and the success/fail callback and no email will be sent
      if (handler != null) {
        handler.warnAdminAboutIntegrationIssue(e.getMessage());
      }
      throw e;
    }
  }

  public void requestSellerPayment(final Purchase purchase, final double commissionFee) {
    // This will be used as the executeAt parameter for every payment
    var executeAt = LocalDateTime.now().plusDays(daysToRefund)
      .atZone(ZoneId.systemDefault())
      .toInstant()
      .toEpochMilli();

    purchase.getItems().stream().map(i -> i.getShares().stream().collect(
      groupingBy(PurchaseItemSellerShare::getSeller, mapping(Line::toLine, toList()))))
      .flatMap(m -> m.entrySet().stream())
      .collect(groupingBy(Map.Entry::getKey, mapping(Map.Entry::getValue, flatMapping(Collection::stream, toList()))))
      .forEach((seller, lines) -> {
        final Payment payment = new Payment(UUID.randomUUID().toString(), null, executeAt, lines);
        // Create the handler that will be used as a callback - We will also update the SellerPayment entry on kafka publish success
        final AccountingCommunicationHandler handler = new AccountingCommunicationHandler(payment, emailService, reconnectWarning, v -> {
          var sellerPayment = new SellerPayment(purchase, seller, commissionFee);
          sellerPaymentRepository.save(sellerPayment);
        });
        try {
          var producerRecord = new ProducerRecord<String, Transaction>(topicPaymentCreate, payment.getStateValue(), payment);
          producerRecord.headers().add("apiKey", paymentServiceApiKey.getBytes(UTF_8));
          producerRecord.headers().add("email", seller.getEmail().getBytes(UTF_8));
          kafkaTemplate.send(producerRecord).addCallback(handler);
        }
        catch (Exception e) {
          logger.error(format("Fail to send payment %s", payment.getStateValue()), e);
          handler.warnAdminAboutIntegrationIssue(e.getMessage());
          throw e;
        }
      });
  }

  public interface Transaction {
    String getStateValue();
  }

  @Value
  public static class Sale implements Transaction {
    String stateValue;
    Details details;
    List<Line> lines;
    String comments;

    public static Sale toSale(Purchase purchase) {
      var payment = purchase.getCurrentPayment().orElseThrow(() ->
        new SystemException("Cannot send an invoice when no payment is present"));
      var buyer = purchase.getBuyer();
      final var country = Country.findByName(buyer.getDetails().getCountry()).orElseThrow(() ->
        new BusinessException(format("Country not supported %s.", buyer.getDetails().getCountry())));
      var name = buyer.getDetails().getEntityType().equals(UserDetail.EntityType.BUSINESS) ?
        buyer.getDetails().getCompany() : buyer.getFullName();
      Details details = new Details(payment.getMethod().toString(),
        name,
        buyer.getDetails().getAddress(),
        buyer.getDetails().getCity(),
        buyer.getDetails().getPostalCode(),
        country.getCode(),
        country.isNetherlands() ? "NL" : country.isEuropeanUnion() ? "EU" : "NON_EU",
        buyer.getDetails().getEntityType().toString(),
        buyer.getDetails().getVat());

      final StringBuilder commentsBuilder = new StringBuilder("Price Analysis (Source Words * Price Per Word): ");
      var lines = new ArrayList<Line>();
      purchase.getItems().forEach(pi -> {
        DatasetSize datasetSize = DatasetSize.fromAmount(pi.getAmount());
        String documentName = pi.getProduct().getDescription() + (datasetSize == DatasetSize.FULL ? "" : " [" + datasetSize.getDescription() + "]");
        var product = new Line.Product(pi.getProduct().getSku(),
          documentName,
          pi.getPrice());
        var line = new Line(product, 1);
        lines.add(line);

        pi.getShares().forEach(share -> commentsBuilder.append(String.format(" [%s * %s]", share.getTotalSourceWords(), share.getUnitPrice())));
      });

      return new Sale(purchase.getUid().toString(), details, lines, commentsBuilder.toString());
    }

    @Value
    private static class Details {
      String method;
      String name;
      String addressLine;
      String city;
      String zipCode;
      String countryCode;
      String vatStatus;
      String legalType;
      String vatNumber;
    }

  }

  @Value
  public static class Payment implements Transaction {
    String stateValue;
    String comments;
    Long executeAt;
    List<Line> lines;
  }

  @Value
  private static class Line {
    Product product;
    double quantity;

    public static Line toLine(final PurchaseItemSellerShare s) {
      var product = new Line.Product(UUIDUtil.toUrlEncoded(s.getDocumentUid()), s.getDocumentName(), s.getUnitPrice());
      return new Line(product, s.getTotalSourceWords());
    }

    @Value
    private static class Product {
      String code;
      String description;
      double unitPrice;
    }
  }
}
