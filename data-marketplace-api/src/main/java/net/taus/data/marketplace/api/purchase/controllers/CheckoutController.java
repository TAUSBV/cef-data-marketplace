package net.taus.data.marketplace.api.purchase.controllers;

import net.taus.data.marketplace.api.base.BaseController;
import net.taus.data.marketplace.api.purchase.services.PurchaseService;
import net.taus.data.marketplace.model.purchase.Buyer;
import net.taus.data.marketplace.model.purchase.Payment;
import net.taus.data.marketplace.model.purchase.Purchase;
import net.taus.data.marketplace.model.purchase.views.PurchaseSummary;
import net.taus.data.marketplace.model.user.UserDetail;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.notFound;

@RestController
@RequestMapping("/checkout")
public class CheckoutController extends BaseController {

  public final static String SHOPPING_CART_NAME = "CART";
  private final PurchaseService purchaseService;

  public CheckoutController(final PurchaseService purchaseService) {
    this.purchaseService = purchaseService;
  }

  @PostMapping(value = { "", "/" }, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public ResponseEntity<PurchaseSummary> create(@Validated(value = { PurchaseRequest.BuyerDetails.class }) final PurchaseRequest request,
                                                final HttpSession session) {
    var purchase = (Purchase) session.getAttribute(SHOPPING_CART_NAME);
    return Optional.ofNullable(purchase)
      .map(p -> {
        final var detail = new UserDetail(request.getEmail(),
          request.getFirstName(),
          request.getLastName(),
          request.getCountry(),
          request.getAddress(),
          request.getCity(),
          request.getState(),
          request.getPostalCode(),
          UserDetail.EntityType.get(request.getType()).orElse(UserDetail.EntityType.PRIVATE),
          request.getCompany(),
          request.getVat());
        p.setBuyer(new Buyer(detail, request.isTermsOfUseConsent(), request.isPrivacyPolicyConsent()));
        p = purchaseService.create(p);
        session.setAttribute(SHOPPING_CART_NAME, p);
        return ResponseEntity.ok(PurchaseSummary.getSummary(p));
      })
      .orElseGet(() -> notFound().build());
  }

  @PutMapping(value = { "/complete", "/complete/" }, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public ResponseEntity<PurchaseSummary> complete(@RequestParam(name = "p") final UUID purchaseUid,
                                                  final HttpSession session) {
    return purchaseService.findByUid(purchaseUid)
      .map(p -> {
        if (p.isAwaitingInvoice()) {
          session.removeAttribute(SHOPPING_CART_NAME);
        }
        return ResponseEntity.ok(PurchaseSummary.getSummary(p));
      })
      .orElseGet(() -> notFound().build());
  }

  @PutMapping(value = { "/payment", "/payment/" }, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public ResponseEntity<PurchaseSummary> payment(@RequestParam(name = "p", required = false) final UUID purchaseUid,
                                                 @Validated(value = { PurchaseRequest.PaymentMethodDetails.class }) final PurchaseRequest request,
                                                 final HttpSession session) {
    return Optional.ofNullable(purchaseUid).flatMap(purchaseService::findByUid)
      .or(() -> Optional.ofNullable((Purchase) session.getAttribute(SHOPPING_CART_NAME)))
      .map(p -> {
        final var paymentMethod = Payment.Method.valueOf(request.getPaymentMethod().toUpperCase());
        p = purchaseService.addPayment(p, paymentMethod);
        if (Payment.Method.BANK_TRANSFER.equals(paymentMethod)) {
          session.removeAttribute(SHOPPING_CART_NAME);
        }
        else {
          session.setAttribute(SHOPPING_CART_NAME, p);
        }
        return ResponseEntity.ok(PurchaseSummary.getSummary(p));
      })
      .orElseGet(() -> notFound().build());
  }

}
