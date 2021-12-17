package net.taus.data.marketplace.model.languagedata.views;

import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.util.UUID;

public interface TranslationDocumentReviewSummary {

  UUID getDocumentUid();

  LocalDateTime getCreatedAt();

  double getScore();

  String getComment();

  @Value("#{target.anonymous ? 'Anonymous' : target.reviewer.firstName}")
  String getReviewerFirstName();

  @Value("#{target.anonymous ? '' : target.reviewer.lastName}")
  String getReviewerLastName();

}
