package net.taus.data.marketplace.model.matchingdata;

import com.querydsl.jpa.JPQLTemplates;
import com.querydsl.jpa.impl.JPAQuery;

import javax.persistence.EntityManager;
import java.util.Optional;
import java.util.UUID;


public class MatchingDataRepositoryExtension implements MatchingDataRequestRepositoryCustom {

  private final EntityManager entityManager;

  public MatchingDataRepositoryExtension(final EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  private JPAQuery<Void> newQuery() {
    return new JPAQuery<>(entityManager, JPQLTemplates.DEFAULT);
  }

  @Override
  public Optional<MatchingDataRequest> findByUid(final UUID uid) {
    final var mdRequest = new QMatchingDataRequest("pay");
    return Optional.ofNullable(
      newQuery()
        .select(mdRequest)
        .from(mdRequest)
        .where(mdRequest.uid.eq(uid))
        .fetchOne()
    );
  }

}
