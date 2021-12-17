package net.taus.data.marketplace.api.purchase.controllers;

import lombok.Data;
import net.taus.data.marketplace.api.base.BaseController;
import net.taus.data.marketplace.api.purchase.events.PurchaseItemReviewedEvent;
import net.taus.data.marketplace.model.BusinessException;
import net.taus.data.marketplace.model.languagedata.TranslationDocumentReview;
import net.taus.data.marketplace.model.languagedata.TranslationDocumentReviewRepository;
import net.taus.data.marketplace.model.purchase.PurchaseItem;
import net.taus.data.marketplace.model.purchase.PurchaseItemRepository;
import net.taus.data.marketplace.model.purchase.PurchaseRepository;
import net.taus.data.marketplace.model.purchase.views.PurchaseItemSummary;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

import static java.lang.String.format;
import static net.taus.data.marketplace.model.languagedata.TranslationDocumentReview.Status.*;
import static org.springframework.http.ResponseEntity.notFound;

@RestController
@RequestMapping("/purchases/items")
public class PurchaseItemController extends BaseController implements ApplicationEventPublisherAware {

  private final PurchaseRepository purchases;
  private final PurchaseItemRepository purchaseItems;
  private final TranslationDocumentReviewRepository reviews;
  private ApplicationEventPublisher publisher;

  public PurchaseItemController(final PurchaseRepository purchases,
                                final PurchaseItemRepository purchaseItems,
                                final TranslationDocumentReviewRepository reviews) {
    this.purchases = purchases;
    this.purchaseItems = purchaseItems;
    this.reviews = reviews;
  }

  @PostMapping(path = "/{uid}/reviews", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE })
  @ResponseBody
  public ResponseEntity<PurchaseItemSummary.PurchaseItemReviewSummary> create(@PathVariable("uid") final UUID purchaseItemUid,
                                                                              @ModelAttribute final PurchaseItemReviewForm review) {
    final Optional<PurchaseItem> itemOptional = purchaseItems.findByUid(purchaseItemUid);
    if (itemOptional.isEmpty()) {
      throw new BusinessException("Purchase item is mandatory");
    }
    final PurchaseItem purchaseItem = itemOptional.get();
    var status = review.getComment().isBlank() ? APPROVED : PENDING;
    if (!reviews.existsByPurchaseItem(purchaseItem)) {
      purchaseItem.getShares().forEach(s -> {
        var newReview = new TranslationDocumentReview();
        newReview.setReviewer(purchaseItem.getPurchase().getBuyer());
        newReview.setDocumentUid(s.getDocumentUid());
        newReview.setAnonymous(review.isAnonymous());
        newReview.setComment(review.getComment());
        newReview.setScore(review.getScore());
        newReview.setPurchaseItem(purchaseItem);
        newReview.setStatus(status);
        newReview = reviews.save(newReview);
        purchaseItem.getReviews().add(newReview);
      });
      if (PENDING.equals(status)) {
        publisher.publishEvent(new PurchaseItemReviewedEvent(purchaseItems, purchaseItem));
      }
    }
    return purchaseItem.getReviews().stream().findFirst()
      .map(r -> ResponseEntity.ok(new PurchaseItemSummary.PurchaseItemReviewSummary(r)))
      .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @GetMapping("/reviews/moderation/check")
  public ResponseEntity<Boolean> checkCode(@RequestParam("purchaseUid") final UUID purchaseUid,
                                           @RequestParam(value = "moderationCode", required = false) final UUID moderationCode) {
    return ResponseEntity.ok(purchases.findByUid(purchaseUid)
      .map(p -> reviews.existsByPurchaseItem_PurchaseAndModerationCode(p, moderationCode)).orElse(false));
  }

  @PostMapping(value = { "/{uid}/reviews/moderation" })
  public ResponseEntity<TranslationDocumentReview.Status> moderate(
    @Validated @NotNull(message = "Purchase Item Uid is mandatory") @PathVariable("uid") final UUID purchaseItemUid,
    @RequestParam("moderationCode") final UUID moderationCode,
    @RequestParam("outcome") final String outcome) {
    if (!APPROVED.name().toLowerCase(Locale.ROOT).equals(outcome) && !REJECTED.name().toLowerCase(Locale.ROOT).equals(outcome)) {
      throw new BusinessException(format("The moderation outcome should one of %s or %s", APPROVED, REJECTED));
    }
    var status = TranslationDocumentReview.Status.valueOf(outcome.toUpperCase(Locale.ROOT));
    return purchaseItems.findByUid(purchaseItemUid).filter(i -> reviews.existsByPurchaseItem_PurchaseAndModerationCode(i.getPurchase(), moderationCode)).map(i -> {
      reviews.updateStatus(i, status);
      return ResponseEntity.ok(status);
    }).orElseGet(() -> notFound().build());
  }

  @Override
  public void setApplicationEventPublisher(final ApplicationEventPublisher publisher) {
    this.publisher = publisher;
  }

  @Data
  public static class PurchaseItemReviewForm {
    private double score;
    private String comment;
    private boolean anonymous;
  }

}
