package net.taus.data.marketplace.model.seller.payment;

import com.querydsl.jpa.JPQLTemplates;
import com.querydsl.jpa.impl.JPAQuery;
import net.taus.data.marketplace.model.purchase.Purchase;
import net.taus.data.marketplace.model.user.User;

import javax.persistence.EntityManager;
import java.util.Optional;
import java.util.UUID;

public class SellerPaymentRepositoryExtension implements SellerPaymentRepositoryCustom {

  private final EntityManager entityManager;

  public SellerPaymentRepositoryExtension(final EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public Optional<SellerPayment> find(final UUID uid) {
    final var sellerPayment = new QSellerPayment("sp");
    return Optional.ofNullable(
      newQuery().from(sellerPayment)
        .where(sellerPayment.uid.eq(uid))
        .select(sellerPayment)
        .fetchOne()
    );
  }

  @Override
  public Optional<SellerPayment> find(final Purchase purchase, final User user) {
    final var sellerPayment = new QSellerPayment("sp");
    return Optional.ofNullable(
      newQuery().from(sellerPayment)
        .where(sellerPayment.purchase.eq(purchase).and(sellerPayment.user.eq(user)))
        .select(sellerPayment)
        .fetchOne()
    );
  }

  private JPAQuery<Void> newQuery() {
    return new JPAQuery<>(entityManager, JPQLTemplates.DEFAULT);
  }
}
