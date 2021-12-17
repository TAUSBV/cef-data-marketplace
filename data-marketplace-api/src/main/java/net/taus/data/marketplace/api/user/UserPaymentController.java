package net.taus.data.marketplace.api.user;


import com.fasterxml.jackson.databind.node.ObjectNode;
import net.taus.data.marketplace.api.base.BaseController;
import net.taus.data.marketplace.api.security.UserSession;
import net.taus.data.marketplace.api.seller.provider.CorporatePaymentService;
import net.taus.data.marketplace.api.seller.provider.UserPaymentProfile;
import net.taus.data.marketplace.model.seller.payment.views.SellerPaymentSummary;
import net.taus.data.marketplace.model.user.User;
import net.taus.payment.stripe.client.StripeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/users/payments")
public class UserPaymentController extends BaseController {

  private final UserPaymentService userPaymentService;
  private final CorporatePaymentService corporatePaymentService;
  private final UserService userService;
  private final StripeClient stripeClient;
  private final String returnUrl;
  private final String refreshUrl;

  @Autowired
  public UserPaymentController(final UserPaymentService userPaymentService,
                               final CorporatePaymentService corporatePaymentService,
                               final UserService userService, final StripeClient client,
                               @Value("${support.payment.stripe.onboard.return_url}") final String returnUrl,
                               @Value("${support.payment.stripe.onboard.refresh_url}") final String refreshUrl) {
    this.userPaymentService = userPaymentService;
    this.corporatePaymentService = corporatePaymentService;
    this.userService = userService;
    this.stripeClient = client;
    this.returnUrl = returnUrl;
    this.refreshUrl = refreshUrl;
  }

  @GetMapping(value = { "", "/" })
  public ResponseEntity<Page<SellerPaymentSummary>> getPayments(@AuthenticationPrincipal final UserSession userSession,
                                       @RequestParam(name = "pg", defaultValue = "0") int pageNumber) {
    final int actualPageNumber = pageNumber < 0 ? 0 : pageNumber;
    return userService.findByUid(userSession.getUserUid()).map(u -> ResponseEntity.ok(this.corporatePaymentService.listPayments(u.getEmail(), actualPageNumber, 20)))
      .orElseGet(() -> ResponseEntity.ok(Page.empty()));
  }

  @GetMapping(value = { "/profile", "/profile/" })
  public ResponseEntity<?> getPaymentProfile(@AuthenticationPrincipal final UserSession userSession) throws CorporatePaymentService.CorporatePaymentServiceException {
    return ResponseEntity.ok(this.userPaymentService.getPaymentProfile(userSession));
  }

  @PutMapping(value = { "/profile/{method}", "/profile/{method}/" })
  public ResponseEntity<?> updatePaymentProfile(@AuthenticationPrincipal final UserSession userSession,
                                                @PathVariable("method") final String method,
                                                @RequestBody final ObjectNode profile) throws CorporatePaymentService.CorporatePaymentServiceException {
    return ResponseEntity.ok(
      this.userPaymentService.updatePaymentMethod(userSession, new UserPaymentProfile(method, profile))
    );
  }

  @PostMapping(value = { "/{broker}/onboard", "/{broker}/onboard/" })
  public ResponseEntity<?> brokerOnBoard(@PathVariable(name = "broker") final String broker) {
    final ResponseEntity<?> result;
    if (!"stripe".equalsIgnoreCase(broker)) {
      result = ResponseEntity.notFound().build();
    }
    else {
      var response = this.stripeClient.onBoard(refreshUrl, returnUrl);
      result = ResponseEntity.ok(response.toJson());
    }
    return result;
  }

  @GetMapping(value = { "/stripe/account/{id}", "/stripe/account/{id}" }, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> getBrokerAccount(@PathVariable(name = "id") final String id) {
    var response = this.stripeClient.retrieveAccount(id);
    return ResponseEntity.ok(response.toJson());
  }
}