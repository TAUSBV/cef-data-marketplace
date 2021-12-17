package net.taus.data.marketplace.model.publication;

import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLTemplates;
import com.querydsl.jpa.impl.JPAQuery;
import net.taus.data.marketplace.model.publication.views.DefaultEventSummary;
import net.taus.data.marketplace.model.publication.views.DefaultPublicationSummary;
import net.taus.data.marketplace.model.publication.views.EventSummary;
import net.taus.data.marketplace.model.publication.views.PublicationSummary;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static net.taus.data.marketplace.model.publication.PublicationEvent.Type.ERROR;

public class PublicationRepositoryViewExtension implements PublicationRepositoryView {

  private final EntityManager entityManager;
  private final QPublication publication;
  private final QPublicationEvent events;
  private final ConstructorExpression<PublicationSummary> publicationSummary;
  private final ConstructorExpression<EventSummary> eventSummaries;

  public PublicationRepositoryViewExtension(final EntityManager entityManager) {
    this.entityManager = entityManager;
    this.publication = new QPublication("p");
    this.events = new QPublicationEvent("pe");
    this.eventSummaries = Projections.constructor(
      DefaultEventSummary.class,
      events.uid,
      events.type,
      events.description,
      events.status,
      events.createdAt
    );
    this.publicationSummary = Projections.constructor(
      DefaultPublicationSummary.class,
      publication.uid,
      publication.documentUid,
      publication.status,
      publication.createdAt,
      publication.updatedAt
    );
  }

  @Override
  public Optional<PublicationSummary> findSummaryByUid(final UUID uid) {
    final Map<PublicationSummary, List<EventSummary>> map = newQuery()
      .from(publication)
      .leftJoin(publication.events, events).on(events.type.eq(ERROR))
      .where(publication.uid.eq(uid))
      .transform(GroupBy.groupBy(publicationSummary).as(GroupBy.list(eventSummaries)));
    return map.entrySet().stream().map(e -> {
      e.getKey().setEvents(e.getValue());
      return e.getKey();
    }).findFirst();
  }

  private JPAQuery<Void> newQuery() {
    return new JPAQuery<>(entityManager, JPQLTemplates.DEFAULT);
  }

}
