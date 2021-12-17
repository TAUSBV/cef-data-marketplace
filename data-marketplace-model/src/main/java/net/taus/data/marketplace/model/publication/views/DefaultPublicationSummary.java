package net.taus.data.marketplace.model.publication.views;

import lombok.*;
import net.taus.data.marketplace.model.languagedata.TranslationDocumentPublishingSummary;
import net.taus.data.marketplace.model.publication.Publication;
import net.taus.data.marketplace.model.publication.PublicationEvent;

import java.time.LocalDateTime;
import java.util.*;

import static java.util.stream.Collectors.toList;

@Getter
@RequiredArgsConstructor
@Data
public class DefaultPublicationSummary implements PublicationSummary {

  private final UUID uid;
  private final UUID documentUid;
  private final Publication.Status status;
  @EqualsAndHashCode.Exclude
  private final LocalDateTime createdAt;
  @EqualsAndHashCode.Exclude
  private final LocalDateTime updatedAt;
  @Getter(AccessLevel.NONE)
  @Setter
  @EqualsAndHashCode.Exclude
  private List<EventSummary> events;
  @Setter
  @EqualsAndHashCode.Exclude
  private TranslationDocumentPublishingSummary document;

  public DefaultPublicationSummary(final UUID uid,
                                   final UUID documentUid,
                                   final Publication.Status status,
                                   final LocalDateTime createdAt,
                                   final LocalDateTime updatedAt,
                                   final List<EventSummary> events) {
    this.uid = uid;
    this.documentUid = documentUid;
    this.status = status;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.events = events;
  }

  @Override
  public List<EventSummary> getErrors() {
    return Optional.ofNullable(this.events)
      .map(es -> es.stream()
        .filter(e -> Objects.nonNull(e.getUid()))
        .filter(e -> e.getType().equals(PublicationEvent.Type.ERROR))
        .collect(toList())
      )
      .orElseGet(ArrayList::new);
  }
}
