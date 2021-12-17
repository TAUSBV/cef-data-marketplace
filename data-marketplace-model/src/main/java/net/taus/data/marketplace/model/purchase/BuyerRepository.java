package net.taus.data.marketplace.model.purchase;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BuyerRepository extends JpaRepository<Buyer, Long> {

  Optional<Buyer> findByDetails_Email(final String email);

}
