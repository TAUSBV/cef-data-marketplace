package net.taus.data.marketplace.model.price;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PriceIndexRepository extends JpaRepository<PriceIndex, Long> {

  Optional<PriceIndex> findByLanguageDataAvailabilityCount(LanguageDataAvailabilityCount languageDataAvailabilityCount);

}