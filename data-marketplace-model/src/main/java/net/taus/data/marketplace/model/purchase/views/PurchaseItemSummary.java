package net.taus.data.marketplace.model.purchase.views;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import net.taus.data.language.api.model.ContentType;
import net.taus.data.language.api.model.Domain;
import net.taus.data.language.api.model.Language;
import net.taus.data.marketplace.model.languagedata.TranslationDocumentReview;
import net.taus.data.marketplace.model.languagedata.views.TranslationDocumentReviewSummary;
import net.taus.data.marketplace.model.product.Product;
import net.taus.data.marketplace.model.product.ProductPart;
import net.taus.data.marketplace.model.purchase.PurchaseItemSellerShare;
import net.taus.data.marketplace.model.user.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Getter
public class PurchaseItemSummary {

  private final UUID uid;
  private final ProductSummary product;
  private final PriceSummary price;
  private final PurchaseItemReviewSummary review;

  public PurchaseItemSummary(final UUID uid,
                             final ProductSummary product,
                             final PriceSummary price,
                             final PurchaseItemReviewSummary review) {
    this.uid = uid;
    this.product = product;
    this.price = price;
    this.review = review;
  }

  @Getter
  public static class PriceSummary {
    private final long totalUnits;
    private final long totalSourceWords;
    private final long totalTargetWords;
    private final double price;
    private final double amount;
    private final double taxPercentage;
    private final double taxAmount;
    private final double totalPrice;

    public PriceSummary(final long totalUnits,
                        final long totalSourceWords,
                        final long totalTargetWords,
                        final double price,
                        final double amount,
                        final double taxPercentage,
                        final double taxAmount,
                        final double totalPrice) {
      this.totalUnits = totalUnits;
      this.totalSourceWords = totalSourceWords;
      this.totalTargetWords = totalTargetWords;
      this.price = price;
      this.amount = amount;
      this.taxPercentage = taxPercentage;
      this.taxAmount = taxAmount;
      this.totalPrice = totalPrice;
    }
  }

  public static class ProductSummary {
    private final Product product;

    @JsonIgnore
    private final List<PurchaseItemSellerShare> shares;

    public ProductSummary(final Product product, final List<PurchaseItemSellerShare> shares) {
      this.product = product;
      this.shares = shares;
    }


    public String getSku() {
      return product.getSku();
    }

    public String getType() {
      return this.product.getType().getAbbreviation();
    }

    // FIXME rename to Sku here and in the view
    public String getUid() {
      return product.getSku();
    }

    public String getDescription() {
      return this.product.getDescription();
    }

    public boolean isAmountVariable() {
      return this.product.isAmountVariable();
    }

    public Language getSourceLanguage() {
      return product.getSourceLanguage().orElse(null);
    }

    public Language getTargetLanguage() {
      return product.getTargetLanguage().orElse(null);
    }

    public List<ContentType> getContentTypes() {
      return product.getParts().stream()
        .filter(p -> p.getType().equals(ProductPart.Type.CONTENT_TYPE))
        .map(p -> new ContentType(Long.parseLong(p.getTypeId()), p.getDescription())).collect(toList());
    }

    public List<Domain> getDomains() {
      return product.getParts().stream()
        .filter(p -> p.getType().equals(ProductPart.Type.DOMAIN))
        .map(p -> new Domain(Long.parseLong(p.getTypeId()), p.getDescription())).collect(toList());
    }

    public List<User> getSellers() {
      return shares.stream().map(PurchaseItemSellerShare::getSeller).distinct().collect(toList());
    }

    public Double getPriceMin() {
      return product.getPriceMin();
    }

    public Double getPriceMax() {
      return product.getPriceMax();
    }

  }

  public static class PurchaseItemReviewSummary {

    private final TranslationDocumentReview review;

    public PurchaseItemReviewSummary(final TranslationDocumentReview review) {
      this.review = review;
    }

    public UUID getPurchaseItemUid() {
      return review.getPurchaseItem().getUid();
    }

    public double getScore() {
      return review.getScore();
    }

    public String getComment() {
      return review.getComment();
    }

    public TranslationDocumentReview.Status getStatus() {
      return review.getStatus();
    }

    public boolean isAnonymous() {
      return review.isAnonymous();
    }
  }
}
