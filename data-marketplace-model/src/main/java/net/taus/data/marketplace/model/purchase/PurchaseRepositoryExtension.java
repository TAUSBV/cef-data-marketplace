package net.taus.data.marketplace.model.purchase;

import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLTemplates;
import com.querydsl.jpa.impl.JPAQuery;
import net.taus.data.marketplace.model.product.QProduct;
import net.taus.data.marketplace.model.purchase.views.PurchaseItemSummary;
import net.taus.data.marketplace.model.purchase.views.PurchaseSummary;

import javax.persistence.EntityManager;
import java.util.Optional;
import java.util.UUID;

public class PurchaseRepositoryExtension implements PurchaseRepositoryCustom {

  private final EntityManager entityManager;

  public PurchaseRepositoryExtension(final EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public Optional<Purchase> findByUid(final UUID uid) {
    final var purchase = new QPurchase("ph");
    final var purchaseItem = new QPurchaseItem("phi");
    final var payment = new QPayment("pay");
    return Optional.ofNullable(
      newQuery().from(purchase)
        .innerJoin(purchase.items, purchaseItem)
        .leftJoin(purchase.payments, payment)
        .where(purchase.uid.eq(uid)).select(purchase)
        .fetchOne()
    );
  }

  @Override
  public Optional<PurchaseSummary> findSummaryByUid(final UUID uid) {
    final var purchase = new QPurchase("ph");
    final var purchaseItem = new QPurchaseItem("phi");
    final var payment = new QPayment("pay");
    final var buyer = new QBuyer("buyer");
    final var product = new QProduct("pd");
    final var isTrue = Expressions.numberTemplate(Long.class, "1").eq(Expressions.numberTemplate(Long.class, "1"));
    return Optional.ofNullable(
      newQuery().from(purchase)
        .innerJoin(purchase.items, purchaseItem)
        .leftJoin(purchase.payments, payment)
        .leftJoin(buyer).on(purchase.buyer.eq(buyer))
        .leftJoin(product).on(purchaseItem.product.eq(product))
        .where(purchase.uid.eq(uid)
          .and(payment.status.ne(Payment.Status.CANCELED).or(isTrue))
          .and(
            payment.updatedAt.eq(
              JPAExpressions.select(payment.updatedAt.max()).leftJoin(purchase.payments, payment).where(payment.status.ne(Payment.Status.CANCELED))
            ).or(isTrue)
          ))
        .transform(GroupBy.groupBy(purchase.uid).as(
          Projections.constructor(
            PurchaseSummary.class,
            purchase.uid,
            purchase.status,
            purchase.createdAt,
            purchase.updatedAt,
            purchase.serviceFee,
            buyer.details,
            GroupBy.list(
              Projections.constructor(
                PurchaseItemSummary.class,
                purchaseItem.uid,
                Projections.constructor(
                  PurchaseItemSummary.ProductSummary.class,
                  product
                ),
                Projections.constructor(
                  PurchaseItemSummary.PriceSummary.class,
                  purchaseItem.totalUnits,
                  purchaseItem.totalSourceWordCount,
                  purchaseItem.totalTargetWordCount,
                  purchaseItem.price,
                  purchaseItem.amount
                )
              )
            ),
            payment
          )
        )).get(uid)
    );
  }

  private JPAQuery<Void> newQuery() {
    return new JPAQuery<>(entityManager, JPQLTemplates.DEFAULT);
  }
}
