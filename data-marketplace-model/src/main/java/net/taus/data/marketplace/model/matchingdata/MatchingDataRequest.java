package net.taus.data.marketplace.model.matchingdata;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Entity
@Table(name = "matching_data_request")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@EntityListeners(AuditingEntityListener.class)
@SuppressWarnings("unused")
public class MatchingDataRequest {

  @Id
  @Column(insertable = false, updatable = false, nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(columnDefinition = "BINARY(16)")
  @EqualsAndHashCode.Include
  private UUID uid;

  @Enumerated(EnumType.STRING)
  private Status status;

  private Long sourceLanguageId;

  private Long targetLanguageId;

  private String email;

  private String fileName;

  private Long fileSize;

  private Long totalUnits;

  @Enumerated(EnumType.STRING)
  @Column(length = 20)
  private Type type;

  @Enumerated(EnumType.STRING)
  @Column(length = 20)
  private SampleLanguage sampleLanguage;

  private Long domainId;

  @Column(columnDefinition = "json")
  private String analysisResult;

  @EqualsAndHashCode.Include
  @CreatedDate
  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @LastModifiedDate
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
  @OrderBy("createdAt")
  private List<MatchingDataRequestEvent> events;

  public MatchingDataRequest(Long sourceLanguageId,
                             Long targetLanguageId,
                             String email,
                             String fileName,
                             Long fileSize,
                             Long totalUnits,
                             Type type,
                             SampleLanguage sampleLanguage,
                             Long domainId) {
    this();

    this.sourceLanguageId = sourceLanguageId;
    this.targetLanguageId = targetLanguageId;
    this.email = email;
    this.fileName = fileName;
    this.fileSize = fileSize;
    this.totalUnits = totalUnits;
    this.type = type;
    this.sampleLanguage = sampleLanguage;
    this.domainId = domainId;
  }

  public MatchingDataRequest() {
    this.uid = UUID.randomUUID();
    this.status = Status.RECEIVED;
    this.events = new ArrayList<>();
  }

  public String getUrl(String appUiUrl) {
    return String.format("%s/matching-data/%s", appUiUrl, this.getUid().toString());
  }

  public static String getUrl(String appUiUrl, String encodedUrl) {
    return String.format("%s/matching-data/%s", appUiUrl, encodedUrl);
  }

  public void addEvent(MatchingDataRequestEvent.Type type, String description) {
    final var event = new MatchingDataRequestEvent(this, type, description);
    this.events.add(event);
    this.updatedAt = LocalDateTime.now();
  }

  public List<MatchingDataRequestEvent> getErrors() {
    return this.getEvents().stream()
      .filter(e -> e.getType().equals(MatchingDataRequestEvent.Type.ERROR))
      .collect(toList());
  }

  public boolean hasErrors() {
    return !this.getErrors().isEmpty();
  }

  public enum Status {
    RECEIVED, ANALYZING, MATCHING, FAILED, COMPLETED
  }

  public enum Type {
    MONOLINGUAL("monolingual"),
    BILINGUAL("bilingual");

    private final String name;

    Type(String name) {
      this.name = name;
    }

    @Override
    public String toString() {
      return name;
    }
  }

  public enum SampleLanguage {
    SOURCE("source"),
    TARGET("target");

    private final String name;

    SampleLanguage(String name) {
      this.name = name;
    }

    @Override
    public String toString() {
      return name;
    }
  }


}
