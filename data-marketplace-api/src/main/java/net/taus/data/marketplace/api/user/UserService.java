package net.taus.data.marketplace.api.user;

import net.taus.corporate.user.client.CorporateUserService;
import net.taus.corporate.user.models.CorporateUser;
import net.taus.data.marketplace.api.crm.CRMService;
import net.taus.data.marketplace.api.security.RegistrationRequest;
import net.taus.data.marketplace.model.user.User;
import net.taus.data.marketplace.model.user.UserDetail;
import net.taus.data.marketplace.model.user.UserRepository;
import net.taus.data.marketplace.model.user.views.SellerSummary;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

public class UserService {

  private final UserRepository users;
  private final CorporateUserService corporateUsers;
  private final CRMService crmService;

  public UserService(final UserRepository users,
                     final CorporateUserService corporateUsers,
                     final CRMService crmService) {
    this.users = users;
    this.corporateUsers = corporateUsers;
    this.crmService = crmService;
  }

  public User register(final RegistrationRequest request) {
    final var corporateUser = corporateUsers.create(request.getCorporateUser());
    User user = new User(corporateUser.getId(), corporateUser.getPrefix(),
      new UserDetail(corporateUser.getEmail(), corporateUser.getFirstName(), corporateUser.getLastName(), corporateUser.getCountry()));
    user.setTermsOfServiceConsent(request.isTermsOfUseConsent());
    user.setPrivacyPolicyConsent(request.isPrivacyPolicyConsent());
    user.setPaymentTermConsent(request.isPaymentTermConsent());
    user = users.save(user);
    crmService.createUser(user);
    return user;
  }

  public Optional<User> findByUid(final UUID uid) {
    return users.findByUid(uid);
  }

  public Optional<User> findByCorporateId(final long corporateId) {
    final Function<CorporateUser, User> saveCorporateUser = cp -> users.save(new User(cp.getId(), cp.getPrefix(),
      new UserDetail(cp.getEmail(), cp.getFirstName(), cp.getLastName(), cp.getCountry())));
    return this.users.findByCorporateId(corporateId).or(() -> this.corporateUsers.findById(corporateId).map(saveCorporateUser));
  }

  public List<User> findAllByUidIn(final List<UUID> uuidList) {
    return users.findAllByUidIn(uuidList);
  }

  public Optional<SellerSummary> findSellerSummaryByCorporateId(final Long ownerId) {
    return users.findSellerSummaryByCorporateId(ownerId);
  }

  public Optional<SellerSummary> findSellerSummaryByUid(final UUID uid) {
    return users.findSellerSummaryByUid(uid);
  }
}