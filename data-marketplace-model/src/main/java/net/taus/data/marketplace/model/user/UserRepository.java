package net.taus.data.marketplace.model.user;

import net.taus.data.marketplace.model.user.views.SellerSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

  Optional<User> findByUid(final UUID uid);

  Optional<User> findByDetails_Email(final String email);

  Optional<User> findByCorporateId(final Long corporateId);

  List<User> findAllByUidIn(final List<UUID> uids);

}

interface UserRepositoryCustom {

  Optional<SellerSummary> findSellerSummaryByCorporateId(final long corporateId);
  Optional<SellerSummary> findSellerSummaryByUid(final UUID uid);

}
