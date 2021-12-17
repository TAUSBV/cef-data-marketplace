package net.taus.data.marketplace.model.languagedata;

import lombok.Getter;
import lombok.Setter;
import net.taus.data.marketplace.model.purchase.Buyer;
import net.taus.data.marketplace.model.purchase.PurchaseItem;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "translation_document_review")
public class TranslationDocumentReview {

  public enum Status {
    APPROVED, REJECTED, PENDING
  }
  @CreatedDate
  public LocalDateTime createdAt;
  @Id
  @Column(updatable = false, nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @OneToOne
  private PurchaseItem purchaseItem;
  @ManyToOne
  private Buyer reviewer;
  @Column(columnDefinition = "BINARY(16)")
  private UUID documentUid;
  private double score;
  private String comment;
  @Enumerated(EnumType.STRING)
  private Status status;
  @Column(columnDefinition = "BINARY(16)")
  private UUID moderationCode = UUID.randomUUID();
  @Column(name = "anonymous", columnDefinition = "bit")
  private boolean anonymous;


}
