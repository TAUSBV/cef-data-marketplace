package net.taus.data.marketplace.model.purchase.views;

import lombok.Getter;
import net.taus.data.marketplace.model.purchase.Buyer;
import net.taus.data.marketplace.model.purchase.Payment;
import net.taus.data.marketplace.model.purchase.Purchase;
import net.taus.data.marketplace.model.user.UserDetail;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Getter
public class PurchaseSummary {

  private final UUID uid;
  private final Purchase.Status status;
  private final LocalDateTime createdAt;
  private final LocalDateTime updatedAt;
  private final double serviceFee;
  private final BuyerSummary buyer;
  private final List<PurchaseItemSummary> items;
  private final Payment payment;

  public PurchaseSummary(final UUID uid,
                         final Purchase.Status status,
                         final LocalDateTime createdAt,
                         final LocalDateTime updatedAt,
                         final double serviceFee,
                         final BuyerSummary buyer,
                         final List<PurchaseItemSummary> items,
                         final Payment payment) {
    this.uid = uid;
    this.status = status;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.serviceFee = serviceFee;
    this.buyer = buyer;
    this.items = items;
    this.payment = payment;
  }

  public static PurchaseSummary getSummary(final Purchase purchase) {
    final var items = purchase.getItems().stream()
      .map(item -> {
        var priceSummary = new PurchaseItemSummary.PriceSummary(item.getTotalUnits(),
          item.getTotalSourceWordCount(), item.getTotalTargetWordCount(), item.getPrice(),
          item.getAmount(), item.getTaxPercentage(), item.getTaxAmount(), item.getTotalPrice());
        final var productSummary = new PurchaseItemSummary.ProductSummary(item.getProduct(), item.getShares());
        // For the purchase item just the first one is required
        final PurchaseItemSummary.PurchaseItemReviewSummary review = item.getReviews().stream()
          .findFirst().map(PurchaseItemSummary.PurchaseItemReviewSummary::new).orElse(null);
        return new PurchaseItemSummary(item.getUid(), productSummary, priceSummary, review);
      })
      .collect(toList());
    final var buyer = Optional.ofNullable(purchase.getBuyer())
      .map(BuyerSummary::new).orElse(null);
    return new PurchaseSummary(purchase.getUid(),
      purchase.getStatus(),
      purchase.getCreatedAt(),
      purchase.getUpdatedAt(),
      purchase.getServiceFee(),
      buyer,
      items,
      purchase.getCurrentPayment().orElse(null));
  }

  public double getSubTotal() {
    return this.items.stream().mapToDouble(i -> i.getPrice().getPrice()).sum();
  }

  public double getTax() {
    return this.items.stream().mapToDouble(i -> i.getPrice().getTaxAmount()).sum();
  }

  public double getTotal() {
    return this.items.stream().mapToDouble(i -> i.getPrice().getTotalPrice()).sum();
  }

  public static class BuyerSummary {
    private final Buyer buyer;

    public BuyerSummary(final Buyer buyer) {
      this.buyer = buyer;
    }

    public String getEmail() {
      return this.buyer.getEmail();
    }

    public String getFirstName() {
      return this.buyer.getFirstName();
    }

    public String getLastName() {
      return this.buyer.getLastName();
    }

    public String getCountry() {
      return this.buyer.getDetails().getCountry();
    }

    public String getAddress() {
      return this.buyer.getDetails().getAddress();
    }

    public String getCity() {
      return this.buyer.getDetails().getCity();
    }

    public String getPostalCode() {
      return this.buyer.getDetails().getPostalCode();
    }

    public String getState() {
      return this.buyer.getDetails().getState();
    }

    public UserDetail.EntityType getEntityType() {
      return this.buyer.getDetails().getEntityType();
    }

    public String getCompany() {
      return this.buyer.getDetails().getCompany();
    }

    public String getVat() {
      return this.buyer.getDetails().getVat();
    }

    public boolean isPrivacyPolicyConsent() {
      return this.buyer.isPrivacyPolicyConsent();
    }

    public boolean isTermsOfUseConsent() {
      return this.buyer.isTermsOfServiceConsent();
    }
  }
}
