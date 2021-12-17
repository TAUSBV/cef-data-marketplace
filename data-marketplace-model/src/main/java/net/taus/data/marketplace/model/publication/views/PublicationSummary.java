package net.taus.data.marketplace.model.publication.views;

import net.taus.data.marketplace.model.languagedata.TranslationDocumentPublishingSummary;
import net.taus.data.marketplace.model.publication.Publication;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

public interface PublicationSummary {

  UUID getUid();

  UUID getDocumentUid();

  TranslationDocumentPublishingSummary getDocument();
  void setDocument(final TranslationDocumentPublishingSummary document);

  Publication.Status getStatus();

  LocalDateTime getCreatedAt();

  LocalDateTime getUpdatedAt();

  void setEvents(List<EventSummary> events);

  List<EventSummary> getErrors();

  static PublicationSummary fromPublication(final Publication publication) {
    final List<EventSummary> eventSummaries = publication.getErrors().stream().map(e -> new DefaultEventSummary(e.getUid(), e.getType(), e.getDescription(), e.getStatus(), e.getCreatedAt())).collect(toList());
    return new DefaultPublicationSummary(publication.getUid(), publication.getDocumentUid(), publication.getStatus(), publication.getCreatedAt(), publication.getUpdatedAt(), eventSummaries);
  }

}
