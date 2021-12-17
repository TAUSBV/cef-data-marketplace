package net.taus.data.marketplace.model.purchase;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import net.taus.data.marketplace.model.languagedata.TranslationDocumentReview;
import net.taus.data.marketplace.model.product.Product;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static javax.persistence.CascadeType.*;
import static net.taus.data.marketplace.model.support.BigDecimalConverter.round;

@Getter
@Setter
@Entity
@Table(name = "purchase_item")
@EntityListeners(AuditingEntityListener.class)
public class PurchaseItem {

  @Id
  @Column(updatable = false, nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(columnDefinition = "BINARY(16)")
  @EqualsAndHashCode.Include
  private UUID uid;

  @Column(name = "total_units")
  private long totalUnits;

  @Column(name = "total_source_word_count")
  private long totalSourceWordCount;

  @Column(name = "total_target_word_count")
  private long totalTargetWordCount;

  private double amount;

  private double price;

  @Column(name = "tax_percentage")
  private double taxPercentage;

  @OneToOne(cascade = { PERSIST, MERGE, DETACH, REFRESH })
  @JoinColumn(name = "product_id", referencedColumnName = "id")
  private Product product;

  @CreatedDate
  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @LastModifiedDate
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @ManyToOne(fetch = FetchType.EAGER)
  private Purchase purchase;

  @OneToMany
  @JoinColumn(name = "purchase_item_id", referencedColumnName = "id")
  private List<TranslationDocumentReview> reviews;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "purchaseItem")
  private List<PurchaseItemSellerShare> shares;

  /**
   * No-arg constructor. This is a JPA requirement and should not be invoked explicitly
   */
  public PurchaseItem() {
    this.uid = UUID.randomUUID();
    this.createdAt = LocalDateTime.now();
    this.shares = new ArrayList<>();
    this.reviews = new ArrayList<>();
  }

  /**
   * Create a purchase item entry for the given product and the appropriate seller shares. Amount will be set to 1.
   * Counts and price will b calculated based on the given shares.
   *
   * @param product The purchase item product
   * @param shares  The seller shares that derive from the purchase item entry
   */
  public PurchaseItem(final Product product, final List<PurchaseItemSellerShare> shares) {
    this(product, shares, 1);
  }

  /**
   * Create a purchase item entry for the given product and the appropriate seller shares. Amount will be set to 1.
   * Counts and price will b calculated based on the given shares.
   *
   * @param product The purchase item product
   * @param shares  The seller shares that derive from the purchase item entry
   * @param amount  The amount/quantity of the purchase item. Please note that currently this is for informative reasons
   *                only and is not used to calculate counts and prices (this is done by the shares).
   */
  public PurchaseItem(final Product product, final List<PurchaseItemSellerShare> shares, final double amount) {
    this();
    this.product = product;
    this.createdAt = LocalDateTime.now();
    this.amount = amount;
    this.setShares(shares);
  }

  /**
   * Just sets the share's parent to this instance and adds the share
   *
   * @param share The share we want to add to this instance of purchase item
   */
  private void addShare(final PurchaseItemSellerShare share) {
    share.setPurchaseItem(this);
    this.getShares().add(share);
  }

  /**
   * Recalculates all unit, source/target word counts and price based on the seller shares
   */
  private void setCountsAndPriceByShares() {
    this.totalUnits = getShares().stream().mapToLong(PurchaseItemSellerShare::getTotalUnits).sum();
    this.totalSourceWordCount = getShares().stream().mapToLong(PurchaseItemSellerShare::getTotalSourceWords).sum();
    this.totalTargetWordCount = getShares().stream().mapToLong(PurchaseItemSellerShare::getTotalTargetWords).sum();
    this.price = getShares().stream().mapToDouble(PurchaseItemSellerShare::getPrice).sum();
  }

  /**
   * Get the purchase item price without tax. We should always use BigDecimal for monetary values for precision
   *
   * @return the item price without tax
   */
  public double getPrice() {
    return round(this.price);
  }

  /**
   * Get the tax amount for the purchase item. This is a calculated value and is not persisted.
   * If we want to persist then we can either use a hibernate @Formula or virtual column but we'll need to refresh the entity after each save/update
   *
   * @return The tax amount rounded to 2 decimal places
   */
  public double getTaxAmount() {
    return round(this.price * this.taxPercentage);
  }

  /**
   * Get the total(gross) price for the purchase item. This is a calculated value and is not persisted.
   * If we want to persist then we can either use a hibernate @Formula or virtual column but we'll need to refresh the entity after each save/update
   *
   * @return The total(gross) price rounded to 2 decimal places
   */
  public double getTotalPrice() {
    return round(this.price + this.getTaxAmount());
  }

  /**
   * Set the seller shares for the purchase item. This action will automatically re-calculate the item's counts and price.
   *
   * @param shares The seller shares that derive from the purchase item entry
   */
  public void setShares(List<PurchaseItemSellerShare> shares) {
    this.shares.clear();
    shares.forEach(this::addShare);
    setCountsAndPriceByShares();
  }

}
