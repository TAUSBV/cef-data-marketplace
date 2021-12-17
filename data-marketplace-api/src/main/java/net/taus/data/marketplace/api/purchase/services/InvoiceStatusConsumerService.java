package net.taus.data.marketplace.api.purchase.services;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
import net.taus.data.marketplace.api.purchase.events.PurchasePaymentPaidEvent;
import net.taus.data.marketplace.model.purchase.Purchase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Transactional
public class InvoiceStatusConsumerService implements ApplicationEventPublisherAware {

  private final Logger logger = LoggerFactory.getLogger(InvoiceStatusConsumerService.class.getCanonicalName());
  private final PurchaseService purchaseService;
  private ApplicationEventPublisher publisher;

  public InvoiceStatusConsumerService(PurchaseService purchaseService) {
    this.purchaseService = purchaseService;
  }

  @KafkaListener(topics = "${app.kafka.consumer.topics.sale_status}", groupId = "${spring.kafka.consumer.group-id}")
  public void consume(@Payload final InvoiceStatus invoiceStatus) {
    Optional.of(invoiceStatus)
      .filter(InvoiceStatus::isCompleted)
      .flatMap(i -> purchaseService.findByUid(i.stateValue))
      .map(Purchase::complete)
      .ifPresentOrElse(p -> p.getCurrentPayment().ifPresent(pm -> {
        if (pm.isManual()) {
          purchaseService.saveAndNotify(p);
          publisher.publishEvent(new PurchasePaymentPaidEvent(this, p));
        }
        else if (pm.isAutomatic()) {
          purchaseService.save(p);
        }
      }), () -> logger.warn("Could not find purchase for Invoice Status of {}", invoiceStatus.stateValue));
  }

  @Override
  public void setApplicationEventPublisher(@Nonnull final ApplicationEventPublisher applicationEventPublisher) {
    this.publisher = applicationEventPublisher;
  }

  @Value
  public static class InvoiceStatus {
    String type;
    UUID stateValue;
    String prevStatus;
    String newStatus;

    @JsonCreator
    public InvoiceStatus(@JsonProperty("type") String type,
                         @JsonProperty("stateValue") UUID stateValue,
                         @JsonProperty("prevStatus") String prevStatus,
                         @JsonProperty("newStatus") String newStatus) {
      this.type = Objects.requireNonNull(type, "type");
      this.stateValue = Objects.requireNonNull(stateValue, "stateValue");
      this.prevStatus = Objects.requireNonNull(prevStatus, "prevStatus");
      this.newStatus = Objects.requireNonNull(newStatus, "newStatus");
    }

    public boolean isCompleted() {
      return this.getNewStatus().equalsIgnoreCase("Completed");
    }
  }

}