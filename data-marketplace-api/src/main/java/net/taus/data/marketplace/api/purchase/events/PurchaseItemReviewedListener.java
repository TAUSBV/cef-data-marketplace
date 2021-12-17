package net.taus.data.marketplace.api.purchase.events;

import net.taus.data.marketplace.model.BusinessException;
import net.taus.data.marketplace.model.SystemException;
import net.taus.data.marketplace.model.email.Email;
import net.taus.data.marketplace.model.email.EmailSenderService;
import net.taus.data.marketplace.model.purchase.PurchaseItem;
import org.springframework.context.ApplicationListener;
import org.springframework.core.convert.converter.Converter;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static java.lang.String.format;
import static net.taus.data.marketplace.model.email.Email.Template.PURCHASE_ITEM_REVIEW_MODERATION;

@Transactional
public class PurchaseItemReviewedListener implements ApplicationListener<PurchaseItemReviewedEvent> {

  private final String appUiUrl;
  private final EmailSenderService emailService;
  private final Converter<UUID, String> converter;

  public PurchaseItemReviewedListener(final EmailSenderService emailService,
                                      final Converter<UUID, String> converter,
                                      final String appUiUrl) {
    this.emailService = emailService;
    this.appUiUrl = appUiUrl;
    this.converter = converter;
  }

  @Async
  @Override
  public void onApplicationEvent(final PurchaseItemReviewedEvent event) {
    final PurchaseItem item = event.getPurchaseItem();
    final String purchaseUid = converter.convert(item.getPurchase().getUid());
    final Map<String, String> data = item.getReviews().stream().findFirst().map(r -> Map.of(
      "PURCHASE_URL", format("%s/purchases/%s?moderation_code=%s", appUiUrl, purchaseUid, converter.convert(r.getModerationCode())),
      "REVIEWER_FIRST_NAME", r.getReviewer().getFirstName(),
      "REVIEWER_LAST_NAME", r.getReviewer().getLastName(),
      "REVIEWER_SCORE", r.getScore() + "",
      "REVIEWER_COMMENT", r.getComment()
    )).orElseThrow(() -> new BusinessException(format("Purchase Item %s does not have any reviews.", item.getId())));
    final Email email = new Email.Builder()
      .addRecipient("datamarketplace@taus.net")
      .sender("datamarketplace@taus.net")
      .subject("Purchase item review moderation required.")
      .template(PURCHASE_ITEM_REVIEW_MODERATION)
      .templateData(new HashMap<>(data))
      .build();
    try {
      emailService.send(email);
    }
    catch (Exception e) {
      throw new SystemException(format("Could not send the Purchase Item Review Moderation for item %s.", item.getId()), e);
    }
  }

}
