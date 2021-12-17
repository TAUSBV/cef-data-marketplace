package net.taus.data.marketplace.model.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface ProductRepository extends JpaRepository<Product, Long> {

  Optional<Product> findBySku(final String sku);
}
