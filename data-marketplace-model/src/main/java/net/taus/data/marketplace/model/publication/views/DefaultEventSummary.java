package net.taus.data.marketplace.model.publication.views;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.taus.data.marketplace.model.publication.Publication;
import net.taus.data.marketplace.model.publication.PublicationEvent;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DefaultEventSummary implements EventSummary {

  @EqualsAndHashCode.Include
  private final UUID uid;

  private final PublicationEvent.Type type;

  private final String description;

  private final Publication.Status status;

  private final LocalDateTime createdAt;
}
