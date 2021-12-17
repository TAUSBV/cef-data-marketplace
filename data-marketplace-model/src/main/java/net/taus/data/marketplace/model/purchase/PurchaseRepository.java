package net.taus.data.marketplace.model.purchase;

import net.taus.data.marketplace.model.purchase.views.PurchaseSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Transactional
public interface PurchaseRepository extends JpaRepository<Purchase, Long>, PurchaseRepositoryCustom {

}

@Transactional
interface PurchaseRepositoryCustom {

  Optional<Purchase> findByUid(@Param("uid") final UUID uid);

  Optional<PurchaseSummary> findSummaryByUid(final UUID uid);

}
