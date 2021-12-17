package net.taus.data.marketplace.model.publication.views;

import net.taus.data.marketplace.model.publication.Publication;
import net.taus.data.marketplace.model.publication.PublicationEvent;

import java.time.LocalDateTime;
import java.util.UUID;

public interface EventSummary {
  UUID getUid();

  PublicationEvent.Type getType();

  String getDescription();

  Publication.Status getStatus();

  LocalDateTime getCreatedAt();
}
