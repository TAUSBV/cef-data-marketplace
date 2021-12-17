package net.taus.data.marketplace.model.user;

import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.jpa.JPQLTemplates;
import com.querydsl.jpa.impl.JPAQuery;
import net.taus.data.marketplace.model.user.views.DefaultSellerSummary;
import net.taus.data.marketplace.model.user.views.SellerSummary;

import javax.persistence.EntityManager;
import java.util.Optional;
import java.util.UUID;

import static com.querydsl.core.types.Projections.constructor;

public class UserRepositoryExtension implements UserRepositoryCustom {

  private final EntityManager entityManager;
  private final QUser user;
  private final ConstructorExpression<DefaultSellerSummary> sellerSummary;

  public UserRepositoryExtension(final EntityManager entityManager) {
    this.entityManager = entityManager;
    user = new QUser("u");
    sellerSummary = constructor(DefaultSellerSummary.class,
      user.uid,
      user.corporateId,
      user.details.firstName,
      user.details.lastName,
      user.details.country,
      user.verified,
      user.createdAt
    );
  }

  public Optional<SellerSummary> findSellerSummaryByCorporateId(final long corporateId) {
    var sellerSummary = newQuerySellerSummary().where(user.corporateId.eq(corporateId)).fetchOne();
    return Optional.ofNullable(sellerSummary);
  }

  public Optional<SellerSummary> findSellerSummaryByUid(final UUID uid) {
    var sellerSummary = newQuerySellerSummary().where(user.uid.eq(uid)).fetchOne();
    return Optional.ofNullable(sellerSummary);
  }

  private JPAQuery<DefaultSellerSummary> newQuerySellerSummary() {
    return newQuery().select(sellerSummary).from(user);
  }

  private JPAQuery<Void> newQuery() {
    return new JPAQuery<>(entityManager, JPQLTemplates.DEFAULT);
  }
}
