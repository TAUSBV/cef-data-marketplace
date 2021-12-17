package net.taus.data.marketplace.api.purchase.controllers;

import net.taus.data.marketplace.api.base.BaseController;
import net.taus.data.marketplace.api.purchase.services.ProductService;
import net.taus.data.marketplace.api.purchase.services.PurchaseService;
import net.taus.data.marketplace.model.price.PriceConfigurationRepository;
import net.taus.data.marketplace.model.purchase.Purchase;
import net.taus.data.marketplace.model.purchase.views.PurchaseSummary;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/cart")
public class ShoppingCartController extends BaseController {

  public final static String SHOPPING_CART_NAME = "CART";
  private final PriceConfigurationRepository priceConfigurationRepository;
  private final ProductService productService;
  private final PurchaseService purchaseService;


  public ShoppingCartController(final PriceConfigurationRepository priceConfigurationRepository,
                                final ProductService productService,
                                final PurchaseService purchaseService) {
    this.priceConfigurationRepository = priceConfigurationRepository;
    this.productService = productService;
    this.purchaseService = purchaseService;
  }

  @PutMapping(value = { "/document", "/document/" }, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public ResponseEntity<PurchaseSummary> addSellerDocument(@RequestParam(name = "documentUid") final UUID documentUid,
                                                           final HttpSession session) {
    var purchase = (Purchase) session.getAttribute(SHOPPING_CART_NAME);
    return Optional.ofNullable(purchase)
      .or(() -> {
        final var priceConfiguration = priceConfigurationRepository.getDefault();
        final var p = new Purchase();
        p.setServiceFee(priceConfiguration.getTransactionFee());
        return Optional.of(p);
      })
      .map(p -> {
        final var purchaseItem = productService.createPurchaseItem(documentUid);
        if (p.getItems().stream().noneMatch(i -> i.getProduct().getSku().equals(purchaseItem.getProduct().getSku()))) {
          p.addItem(purchaseItem);
        }
        session.setAttribute(SHOPPING_CART_NAME, p);
        return ok(PurchaseSummary.getSummary(p));
      }).orElseGet(() -> notFound().build());
  }

  @PutMapping(value = { "/virtual", "/virtual/" }, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public ResponseEntity<PurchaseSummary> addVirtualDocument(@RequestParam(name = "s") final Long sourceLanguageId,
                                                            @RequestParam(name = "t") final Long targetLanguageId,
                                                            @RequestParam(name = "d", required = false) final Long[] domainIds,
                                                            @RequestParam(name = "c", required = false) final Long[] contentTypeIds,
                                                            @RequestParam(name = "u", required = false) final UUID[] sellerUids,
                                                            @RequestParam(name = "pmin", required = false) final Double priceMin,
                                                            @RequestParam(name = "pmax", required = false) final Double priceMax,
                                                            final HttpSession session) {
    final LocalDateTime referenceDate = LocalDateTime.now();
    var purchase = (Purchase) session.getAttribute(SHOPPING_CART_NAME);
    return Optional.ofNullable(purchase)
      .or(() -> {
        final var priceConfiguration = priceConfigurationRepository.getDefault();
        final var p = new Purchase();
        p.setServiceFee(priceConfiguration.getTransactionFee());
        return Optional.of(p);
      })
      .map(p -> {
        var purchaseItem = productService.createPurchaseItem(sourceLanguageId, targetLanguageId, domainIds, contentTypeIds, sellerUids, priceMin, priceMax, referenceDate);
        if (p.getItems().stream().noneMatch(i -> i.getProduct().getSku().equals(purchaseItem.getProduct().getSku()))) {
          p.addItem(purchaseItem);
        }
        session.setAttribute(SHOPPING_CART_NAME, p);
        return ok(PurchaseSummary.getSummary(p));
      }).orElseGet(() -> notFound().build());
  }

  /**
   * Add a matching data document to the cart. The {@link net.taus.data.marketplace.model.matchingdata.MatchingDataRequest}
   * needs to be completed and the results available.
   *
   * @param requestUid The uid tied to the md-request
   * @param amount     The amount percentage (ex. 0.25) that the user wants to purchase
   * @param session    The http session that we use to retrieve the user's shopping cart
   * @return The current purchase summary after we add the matching data document
   */
  @PutMapping(value = { "/matching-document", "/matching-document/" }, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public ResponseEntity<PurchaseSummary> addMatchingDataDocument(@RequestParam(name = "requestUid") final UUID requestUid,
                                                                 @RequestParam(name = "amount") final double amount,
                                                                 final HttpSession session) {
    // Get the current purchase
    var purchase = (Purchase) session.getAttribute(SHOPPING_CART_NAME);
    return Optional.ofNullable(purchase)
      .or(() -> {
        // Create a new one if it is the first item
        final var priceConfiguration = priceConfigurationRepository.getDefault();
        final var p = new Purchase();
        p.setServiceFee(priceConfiguration.getTransactionFee());
        return Optional.of(p);
      })
      .map(p -> {
        // Create an MDOC with appropriate seller shares based on the specified amount
        final var purchaseItem = productService.createPurchaseItem(requestUid, amount);
        // Always remove the same MDOC and re-add with correct seller shares
        p.getItems().removeIf(i -> i.getProduct().getSku().equals(purchaseItem.getProduct().getSku()));
        p.addItem(purchaseItem);

        session.setAttribute(SHOPPING_CART_NAME, p);
        return ok(PurchaseSummary.getSummary(p));
      }).orElseGet(() -> notFound().build());
  }

  @GetMapping(value = { "/", "" })
  public ResponseEntity<PurchaseSummary> show(final HttpSession session) {
    final var purchase = (Purchase) session.getAttribute(SHOPPING_CART_NAME);
    return Optional.ofNullable(purchase)
      .map(p -> ok(PurchaseSummary.getSummary(p)))
      .orElseGet(() -> notFound().build());
  }

  @DeleteMapping(value = { "", "/" }, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public ResponseEntity<PurchaseSummary> delete(@RequestParam(name = "itemUid") final UUID itemUid,
                                                final HttpSession session) {
    final var purchase = (Purchase) session.getAttribute(SHOPPING_CART_NAME);
    return Optional.ofNullable(purchase)
      .map(p -> {
        // This is needed to prevent presenting a wrong purchase summary to the users if they checkout,
        // return to the cart and remove an item
        purchaseService.delete(p);
        p.getItems().removeIf(i -> i.getUid().equals(itemUid));
        if (p.getItems().isEmpty()) {
          session.removeAttribute(SHOPPING_CART_NAME);
        }
        return ok(PurchaseSummary.getSummary(p));
      })
      .orElseGet(() -> notFound().build());
  }

  @PutMapping(value = { "", "/" }, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public ResponseEntity<PurchaseSummary> update(@RequestParam(name = "itemUid") final UUID itemUid,
                                                @RequestParam(name = "amount") final double amount,
                                                final HttpSession session) {
    final var purchase = (Purchase) session.getAttribute(SHOPPING_CART_NAME);
    return Optional.ofNullable(purchase)
      .map(p -> {
        // This is needed to prevent presenting a wrong purchase summary to the users if they checkout,
        // return to the cart and change an item's amount
        purchaseService.delete(p);
        p.getItems().stream()
          .filter(i -> i.getUid().equals(itemUid)).findFirst()
          .ifPresent(i -> productService.updatePurchaseItem(i, amount));
    return ok(PurchaseSummary.getSummary(p));
  }).orElseGet(() -> notFound().build());
}

}
