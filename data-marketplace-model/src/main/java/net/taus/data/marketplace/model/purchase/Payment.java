package net.taus.data.marketplace.model.purchase;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Optional;

@Entity
@Table(name = "payment")
@Data
@Inheritance
@EntityListeners(AuditingEntityListener.class)
public class Payment {

  @Id
  @Column(updatable = false, nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @CreatedDate
  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @LastModifiedDate
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @Enumerated(value = EnumType.STRING)
  @Column(length = 20)
  private Status status;

  @Enumerated(value = EnumType.STRING)
  @Column(length = 20)
  private Method method;

  @Column(length = 200, name = "broker_transaction_id")
  private String brokerTransactionId;

  public Payment() {
    this.createdAt = LocalDateTime.now();
    this.updatedAt = LocalDateTime.now();
  }

  public Payment(final Method method) {
    this();
    this.status = Status.UNPAID;
    this.method = method;
  }

  public Payment(final String brokerTransactionId, final Method method) {
    this(method);
    this.brokerTransactionId = brokerTransactionId;
  }

  public boolean isAutomatic() {
    return Method.PAYPAL.equals(this.getMethod()) || Method.STRIPE.equals(this.getMethod());
  }

  public boolean isManual() {
    return Method.BANK_TRANSFER.equals(this.getMethod());
  }

  public boolean isPaid() {
    return Status.PAID.equals(this.getStatus());
  }

  public boolean isUnpaid() {
    return Status.UNPAID.equals(this.getStatus());
  }

  public boolean isCancelled() {
    return Status.CANCELED.equals(this.getStatus());
  }

  public enum Status {
    UNPAID, PAID, CANCELED;

    public static Optional<Status> get(final String name) {
      Status value;
      try {
        value = valueOf(name);
      }
      catch (IllegalArgumentException e) {
        value = null;
      }
      return Optional.ofNullable(value);
    }
  }

  public enum Method {
    BANK_TRANSFER, PAYPAL, STRIPE;

    public static Optional<Method> get(final String name) {
      Method value;
      try {
        value = valueOf(name);
      }
      catch (IllegalArgumentException e) {
        value = null;
      }
      return Optional.ofNullable(value);
    }

  }

}
