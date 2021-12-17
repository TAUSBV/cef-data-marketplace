package net.taus.data.marketplace.model.purchase;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.taus.data.language.api.model.TranslationDocument;
import net.taus.data.marketplace.model.product.Product;
import net.taus.data.marketplace.model.user.User;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

import static net.taus.data.marketplace.model.support.BigDecimalConverter.round;

@Data
@NoArgsConstructor
@Entity
@Table(name = "purchase_item_seller_shares")
@EntityListeners(AuditingEntityListener.class)
public class PurchaseItemSellerShare {

  @Id
  @Column(updatable = false, nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private double unitPrice;

  private long totalUnits;

  private long totalSourceWords;

  private long totalTargetWords;

  @ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.DETACH })
  @JoinColumn(name = "seller_id")
  private User seller;

  @JsonIgnore
  @ToString.Exclude
  @ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.DETACH })
  @JoinColumn(name = "purchase_item_id")
  private PurchaseItem purchaseItem;

  private UUID documentUid;

  private String documentName;

  @CreatedDate
  private LocalDateTime createdAt;

  public PurchaseItemSellerShare(final User seller,
                                 final UUID documentUid,
                                 final String documentName,
                                 final double unitPrice,
                                 final long totalUnits,
                                 final long totalSourceWords,
                                 final long totalTargetWords) {
    this.seller = seller;
    this.documentUid = documentUid;
    this.documentName = documentName;
    this.unitPrice = unitPrice;
    this.totalUnits = totalUnits;
    this.totalSourceWords = totalSourceWords;
    this.totalTargetWords = totalTargetWords;
  }

  public static PurchaseItemSellerShare fromTranslationDocument(TranslationDocument document, User user) {
    return new PurchaseItemSellerShare(user,
      document.getUid(),
      document.getName(),
      document.getPricePerWord(),
      document.getUnitCount(),
      document.getSourceWordCount(),
      document.getTargetWordCount());
  }

  /**
   * TODO This is not 100% correct. We are just multiplying by amount and rounding. This may provide a very close estimate
   * on the final source words and number of segments. However when we stream the document we fetch by the purchase item
   * total units meaning that we may provide a bit more/less words than the user paid for. If we want to be more accurate we should
   * calculate these before the checkout just as MDOCs do. This should only be used for SDOCs!
   *
   * @param purchaseItemSellerShare The purchase item seller share we want to modify
   * @param product                 The seller document product which holds the original/full counts
   * @param amount                  the amount by which we want to modify the share's counts (ex. source words)
   * @return the purchase item seller share after we modified its counts by the specified amount
   */
  public static PurchaseItemSellerShare fromSellerDocumentProduct(PurchaseItemSellerShare purchaseItemSellerShare, Product product, double amount) {
    final var totalUnits = (long) Math.ceil(product.getTotalUnits() * amount);
    final var totalSourceWords = (long) Math.ceil(product.getTotalSourceWords() * amount);
    final var totalTargetWords = (long) Math.ceil(product.getTotalTargetWords() * amount);

    purchaseItemSellerShare.setTotalUnits(totalUnits);
    purchaseItemSellerShare.setTotalSourceWords(totalSourceWords);
    purchaseItemSellerShare.setTotalTargetWords(totalTargetWords);

    return purchaseItemSellerShare;
  }

  public void incrementCounts(long sourceWordsIncrement, long targetWordsIncrement) {
    this.totalUnits++;
    this.totalSourceWords += sourceWordsIncrement;
    this.totalTargetWords += targetWordsIncrement;
  }

  public double getPrice() {
    return round(this.getUnitPrice() * this.getTotalSourceWords());
  }
}
