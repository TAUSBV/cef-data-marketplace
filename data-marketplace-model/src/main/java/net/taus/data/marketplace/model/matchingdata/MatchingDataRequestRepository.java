package net.taus.data.marketplace.model.matchingdata;

import net.taus.data.marketplace.model.seller.payment.SellerPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Transactional
public interface MatchingDataRequestRepository extends JpaRepository<MatchingDataRequest, Long>, MatchingDataRequestRepositoryCustom {

}

@Transactional
interface MatchingDataRequestRepositoryCustom {

  Optional<MatchingDataRequest> findByUid(@Param("uid") final UUID uid);

}
