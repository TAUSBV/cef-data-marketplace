package net.taus.data.marketplace.model.price;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceConfigurationRepository extends JpaRepository<PriceConfiguration, Long> {

  @Query(value = "select p from PriceConfiguration p where p.id = 1")
  PriceConfiguration getDefault();
}