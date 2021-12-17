package net.taus.data.marketplace.model.purchase;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Transactional
public interface PurchaseItemRepository extends JpaRepository<PurchaseItem, Long> {

  Optional<PurchaseItem> findByUid(final UUID uuid);
}
