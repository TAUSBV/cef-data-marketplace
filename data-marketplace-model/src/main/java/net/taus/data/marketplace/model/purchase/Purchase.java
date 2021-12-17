package net.taus.data.marketplace.model.purchase;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.taus.data.marketplace.model.seller.payment.SellerPayment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

import static javax.persistence.CascadeType.*;
import static net.taus.data.marketplace.model.purchase.Purchase.Status.*;
import static net.taus.data.marketplace.model.support.BigDecimalConverter.round;

@Entity
@Table(name = "purchase")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Purchase {

  @Id
  @Column(updatable = false, nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(columnDefinition = "BINARY(16)")
  @EqualsAndHashCode.Include
  private UUID uid;

  @OneToOne(cascade = { PERSIST, MERGE, REFRESH, DETACH })
  @JoinColumn(name = "buyer_id", referencedColumnName = "id")
  private Buyer buyer;

  @OneToMany(cascade = { ALL }, orphanRemoval = true)
  @JoinColumn(name = "purchase_id", referencedColumnName = "id")
  private List<PurchaseItem> items;

  @ManyToMany(cascade = { ALL })
  @JoinTable(name = "purchase_payments", joinColumns = @JoinColumn(name = "purchase_id"), inverseJoinColumns = @JoinColumn(name = "payments_id"))
  private List<Payment> payments;

  @OneToMany(cascade = { ALL })
  @JoinColumn(name = "purchase_id", referencedColumnName = "id")
  private List<SellerPayment> sellerPayments;

  @Column(name = "service_fee")
  private double serviceFee;

  @Enumerated(value = EnumType.STRING)
  @Column(length = 20)
  private Status status;

  @CreatedDate
  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @LastModifiedDate
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  public Purchase() {
    this.uid = UUID.randomUUID();
    this.items = new ArrayList<>();
    this.payments = new ArrayList<>();
    this.status = RECEIVED;
  }

  public synchronized Purchase addItem(final PurchaseItem item) {
    this.items.add(item);
    item.setPurchase(this);
    return this;
  }

  public Purchase addPayment(final Payment payment) {
    this.payments.forEach(p -> p.setStatus(Payment.Status.CANCELED));
    this.payments.add(payment);
    this.setStatus(AWAITING_PAYMENT);
    return this;
  }

  public Purchase addSellerPayment(final SellerPayment sellerPayment) {
    sellerPayment.setSentAt(LocalDateTime.now(ZoneId.systemDefault()));
    this.sellerPayments.add(sellerPayment);
    return this;
  }

  public Purchase invoiceRequested() {
    this.setStatus(AWAITING_INVOICE);
    return this;
  }

  public Purchase complete() {
    this.getCurrentPayment().filter(Payment::isUnpaid).ifPresent(p -> p.setStatus(Payment.Status.PAID));
    this.setStatus(COMPLETED);
    return this;
  }

  public Optional<Payment> getCurrentPayment() {
    return this.payments.stream().max(Comparator.comparing(Payment::getCreatedAt));
  }

  public double getTotal() {
    return round(this.items.stream().mapToDouble(PurchaseItem::getTotalPrice).sum());
  }

  public double getSubTotal() {
    return round(this.items.stream().mapToDouble(PurchaseItem::getPrice).sum());
  }

  public double getTotalTax() {
    return round(this.items.stream().mapToDouble(PurchaseItem::getTaxAmount).sum());
  }

  public boolean isAwaitingPayment() {
    return this.getStatus().equals(AWAITING_PAYMENT);
  }

  public boolean isAwaitingInvoice() {
    return this.getStatus().equals(AWAITING_INVOICE);
  }

  public boolean isCompleted() {
    return this.getStatus().equals(COMPLETED);
  }

  public boolean isPaid() {
    return this.getCurrentPayment().map(Payment::isPaid).orElse(false);
  }

  public enum Status {
    RECEIVED, AWAITING_PAYMENT, AWAITING_INVOICE, COMPLETED
  }

}
