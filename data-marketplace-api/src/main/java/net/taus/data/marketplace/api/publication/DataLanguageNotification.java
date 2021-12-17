package net.taus.data.marketplace.api.publication;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.taus.data.language.api.model.TranslationDocumentStatistics;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class DataLanguageNotification {
  public enum Status {
    PROGRESS, FINISHED
  }

  public enum Type {
    INFO, ERROR
  }

  public enum ErrorKind {
    BUSINESS, SYSTEM
  }

  private UUID uid;
  private UUID documentUid;
  private Type type;
  private Status status;
  private String component;
  private LocalDateTime createdAt;
  // Payload
  // INFO
  private long ownerCorporateId;
  private String documentName;
  private TranslationDocumentStatistics statistics = new TranslationDocumentStatistics();

  // ERROR
  private String message;
  private ErrorKind kind;
  private Map<String, String> detail;

}
