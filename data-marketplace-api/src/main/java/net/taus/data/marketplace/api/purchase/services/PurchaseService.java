package net.taus.data.marketplace.api.purchase.services;

import net.taus.data.marketplace.api.purchase.events.PurchaseDownloadEvent;
import net.taus.data.marketplace.model.BusinessException;
import net.taus.data.marketplace.model.product.ProductRepository;
import net.taus.data.marketplace.model.purchase.*;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Transactional
public class PurchaseService implements ApplicationEventPublisherAware {

  private final StripeService stripe;
  private final PayPalService payPal;
  private final BankTransferService bankTransferService;
  private final TaxService taxService;
  private final PurchaseRepository purchases;
  private final ProductRepository products;
  private final DownloadRepository downloads;
  private final String appUiUrl;
  private ApplicationEventPublisher publisher;

  public PurchaseService(final StripeService stripe,
                         final PayPalService payPal,
                         final BankTransferService bankTransferService,
                         final TaxService taxService,
                         final PurchaseRepository purchases,
                         final ProductRepository products,
                         final DownloadRepository downloads,
                         final String appUiUrl) {
    this.stripe = stripe;
    this.payPal = payPal;
    this.bankTransferService = bankTransferService;
    this.taxService = taxService;
    this.purchases = purchases;
    this.products = products;
    this.downloads = downloads;
    this.appUiUrl = appUiUrl;
  }

  public Optional<Purchase> findByUid(final UUID purchaseUid) {
    var purchase = this.purchases.findByUid(purchaseUid);
    return purchase.filter(Purchase::isAwaitingPayment)
      .map(p -> p.getCurrentPayment()
        .filter(Payment::isAutomatic)
        .map(pm -> {
          Purchase toSave = p;
          if (Payment.Method.STRIPE.equals(pm.getMethod())) {
            toSave = stripe.check(p);
          }
          else if (Payment.Method.PAYPAL.equals(pm.getMethod())) {
            toSave = payPal.check(p);
          }
          if (!toSave.isAwaitingPayment()) {
            saveAndNotify(toSave);
          }
          return p;
        })
      ).orElse(purchase);
  }

  public synchronized Purchase create(@Nonnull final Purchase purchase) {
    double taxPercent = taxService.getTax(purchase.getBuyer().getDetails());
    purchase.getItems().forEach(i -> {
      final var product = products.findBySku(i.getProduct().getSku()).orElseGet(i::getProduct);
      i.setProduct(product);
      i.setTaxPercentage(taxPercent);
    });
    return saveAndNotify(purchase);
  }

  public Purchase save(final Purchase purchase) {
    return purchases.save(purchase);
  }

  public Purchase saveAndNotify(final Purchase purchase) {
    final var saved = save(purchase);
    Optional.ofNullable(publisher).ifPresent(p -> p.publishEvent(new PurchaseEvent(this, purchase)));
    return saved;
  }

  /**
   * Delete a purchase if present
   *
   * @param purchase The purchase we want to delete
   */
  public void delete(Purchase purchase) {
    purchases.findByUid(purchase.getUid()).ifPresent(purchases::delete);
  }

  public Purchase addPayment(Purchase purchase, final Payment.Method paymentMethod) {
    final var callbackUrl = String.format("%s/checkout", appUiUrl);
    purchase = switch (paymentMethod) {
      case BANK_TRANSFER -> bankTransferService.prepare(purchase, Map.of());
      case PAYPAL -> payPal.prepare(purchase, Map.of());
      case STRIPE -> stripe.prepare(purchase, Map.of("callbackUrl", callbackUrl));
    };
    return save(purchase);
  }

  public List<Download> createDownload(Purchase purchase) {
    if (!purchase.isPaid()) {
      throw new BusinessException("Cannot create download links for an unpaid purchase");
    }
    return purchase.getItems().stream()
      .map(i -> downloads.findByItemAndStatus(i, Download.Status.AVAILABLE)
        .orElseGet(() -> downloads.save(new Download(i)))
      ).collect(toList());
  }

  public Optional<Purchase> createDownloadAndPublish(final UUID purchaseUid) {
    return findByUid(purchaseUid).map(p -> {
      var downloads = createDownload(p);
      publisher.publishEvent(new PurchaseDownloadEvent(this, p, downloads));
      return p;
    });
  }

  @Override
  public void setApplicationEventPublisher(@Nonnull final ApplicationEventPublisher applicationEventPublisher) {
    this.publisher = applicationEventPublisher;
  }

}
