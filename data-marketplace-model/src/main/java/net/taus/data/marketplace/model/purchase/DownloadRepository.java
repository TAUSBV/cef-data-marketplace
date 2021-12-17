package net.taus.data.marketplace.model.purchase;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DownloadRepository extends JpaRepository<Download, Long> {

  Optional<Download> findByUidAndStatus(final UUID uid, final Download.Status status);

  Optional<Download> findByItemAndStatus(final PurchaseItem item, final Download.Status status);

}
