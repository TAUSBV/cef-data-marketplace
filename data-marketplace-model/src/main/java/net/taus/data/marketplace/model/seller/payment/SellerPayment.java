package net.taus.data.marketplace.model.seller.payment;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.taus.data.marketplace.model.purchase.Purchase;
import net.taus.data.marketplace.model.user.User;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@Entity
@Table(name = "seller_payment")
@Data
@EntityListeners(AuditingEntityListener.class)
public class SellerPayment {

  @Id
  @Column(updatable = false, nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(columnDefinition = "BINARY(16)")
  @EqualsAndHashCode.Include
  private UUID uid;

  @ToString.Exclude
  @ManyToOne
  @JoinColumn(name = "purchase_id", referencedColumnName = "id")
  private Purchase purchase;

  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;

  @Column(name = "commission_fee")
  private double commissionFee;

  @Column(name = "sent_at")
  @CreatedDate
  private LocalDateTime sentAt;

  public SellerPayment(final Purchase purchase,
                       final User user,
                       final double commissionFee) {
    this.uid = UUID.randomUUID();
    this.purchase = purchase;
    this.user = user;
    this.commissionFee = commissionFee;
  }

}
