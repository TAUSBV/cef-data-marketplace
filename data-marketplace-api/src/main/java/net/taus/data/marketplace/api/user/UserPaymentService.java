package net.taus.data.marketplace.api.user;

import net.taus.data.marketplace.api.security.UserSession;
import net.taus.data.marketplace.api.seller.provider.CorporatePaymentService;
import net.taus.data.marketplace.api.seller.provider.UserPaymentProfile;
import net.taus.data.marketplace.model.BusinessException;
import net.taus.data.marketplace.model.user.User;
import net.taus.data.marketplace.model.user.UserRepository;

import javax.validation.constraints.NotNull;

/**
 * This service manages user payment information.
 */
public class UserPaymentService {

  private final CorporatePaymentService corporatePaymentService;
  private final UserRepository userRepository;

  public UserPaymentService(@NotNull(message = "Invalid corporatePaymentService (Null)") final CorporatePaymentService corporatePaymentService,
                            @NotNull(message = "Invalid userRepository (Null)") final UserRepository userRepository) {
    this.corporatePaymentService = corporatePaymentService;
    this.userRepository = userRepository;
  }

  /**
   * This methods gets the user's payment profile and updates this payment_profile_valid using the response data.
   *
   * @param session user's session
   * @return Treasurer's response
   */
  public @NotNull UserPaymentProfile getPaymentProfile(@NotNull(message = "Invalid session (Null)") UserSession session) {
    final User user = this.userRepository.findByUid(session.getUserUid()).orElseThrow(() -> new BusinessException("User not found."));
    final UserPaymentProfile profile = this.corporatePaymentService.getUserProfile(session.getCorporateSessionId());
    profile.setPaymentTermsConsent(user.isPaymentTermConsent());
    // the need to update the payment_profile_valid (hopefully) soon will go away therefore the part below is temporary
    if (user.isPaymentProfileValid() != profile.isValidForPayment()) {
      user.setPaymentProfileValid(profile.isValidForPayment());
      this.userRepository.save(user);
    }
    return profile;
  }

  /**
   * This methods gets the user's payment profile and updates this payment_profile_valid using the response data.
   *
   * @param session user session
   * @param profile the payment profile
   * @return Treasurer's response
   */
  public @NotNull UserPaymentProfile updatePaymentMethod(@NotNull(message = "Invalid session (Null)") final UserSession session,
                                                         @NotNull(message = "Invalid details (Null)") final UserPaymentProfile profile) throws CorporatePaymentService.CorporatePaymentServiceException {
    if (!profile.isConsentWithPaymentTerms()) {
      throw new BusinessException("User must agree with the payment terms.");
    }
    final User user = this.userRepository.findByUid(session.getUserUid()).orElseThrow(() -> new BusinessException("User not found."));
    if (!user.isPaymentTermConsent()) {
      user.setPaymentTermConsent(true);
      this.userRepository.save(user);
    }
    final UserPaymentProfile updatedProfile = this.corporatePaymentService.updateUserProfile(session.getCorporateSessionId(), profile);
    updatedProfile.setPaymentTermsConsent(user.isPaymentTermConsent());
    // the need to update the payment_profile_valid (hopefully) soon will go away therefore the part below is temporary
    if (user.isPaymentProfileValid() != updatedProfile.isValidForPayment()) {
      user.setPaymentProfileValid(updatedProfile.isValidForPayment());
      this.userRepository.save(user);
    }
    return profile;
  }
}
