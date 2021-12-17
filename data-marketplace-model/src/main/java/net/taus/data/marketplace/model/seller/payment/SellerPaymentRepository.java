package net.taus.data.marketplace.model.seller.payment;

import net.taus.data.marketplace.model.purchase.Purchase;
import net.taus.data.marketplace.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Transactional
public interface SellerPaymentRepository extends JpaRepository<SellerPayment, Long>, SellerPaymentRepositoryCustom {

}

@Transactional
interface SellerPaymentRepositoryCustom {

  Optional<SellerPayment> find(@Param("uid") final UUID uid);

  Optional<SellerPayment> find(@Param("purchase") Purchase purchase, @Param("user")User user);
}