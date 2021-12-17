package net.taus.data.language.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import net.taus.data.language.model.meta.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

import static javax.persistence.CascadeType.ALL;

@Data
@Entity
@Table(name = "translation_documents")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@EntityListeners(AuditingEntityListener.class)
public class TranslationDocument {

  @Id
  @Column(updatable = false, nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @ToString.Include
  private Long id;

  @EqualsAndHashCode.Include
  @ToString.Include
  @Column(columnDefinition = "BINARY(16)")
  private UUID uid;

  @EqualsAndHashCode.Include
  @ToString.Include
  @Column(columnDefinition = "varchar(255) default 'undefined'")
  private String name;

  @EqualsAndHashCode.Include
  @ToString.Include
  @Enumerated(EnumType.STRING)
  @Column(columnDefinition = "varchar(50) default 'TMX'")
  private Format format;

  @Column(columnDefinition = "integer default 0")
  private long size;

  @Column(columnDefinition = "tinyint default 0")
  private boolean anonymize;

  @Column(columnDefinition = "text")
  private String description;

  @ToString.Include
  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 25)
  private Status status;

  @OneToMany(cascade = ALL, orphanRemoval = true)
  @JoinTable(name = "translation_documents_characteristics",
    joinColumns = @JoinColumn(name = "document_id"), inverseJoinColumns = @JoinColumn(name = "characteristic_id"))
  @EqualsAndHashCode.Include
  private List<LanguageDataCharacteristics> characteristics;

  @OneToMany(cascade = ALL, orphanRemoval = true)
  @JoinColumn(name = "document_id")
  @OrderBy("createdAt")
  private List<TranslationDocumentStatistics> statisticsList;

  @EqualsAndHashCode.Include
  private Long ownerId;

  @EqualsAndHashCode.Include
  private UUID ownerUid;

  @ManyToOne
  @EqualsAndHashCode.Include
  private Language sourceLanguage;

  @ManyToOne
  @EqualsAndHashCode.Include
  private Language targetLanguage;

  @CreatedDate
  private LocalDateTime createdAt;

  @LastModifiedDate
  private LocalDateTime updatedAt;

  public TranslationDocument() {
    this.format = Format.TMX;
    this.status = Status.NEW;
    this.uid = UUID.randomUUID();
    this.ownerUid = UUID.randomUUID();
    this.characteristics = new ArrayList<>();
    this.statisticsList = new ArrayList<>();
  }

  public TranslationDocument(final String name) {
    this();
    this.name = name;
  }

  public TranslationDocument(final UUID uid, final String name, final long size) {
    this();
    this.uid = uid;
    this.name = name;
    this.size = size;
  }

  public TranslationDocument addCharacteristics(final LanguageDataCharacteristics characteristics) {
    this.getCharacteristics().add(characteristics);
    return this;
  }

  public TranslationDocument addStatistics(final TranslationDocumentStatistics statistics) {
    this.getStatisticsList().add(statistics);
    return this;
  }

  public Optional<LanguageDataCharacteristics> getCurrentCharacteristics() {
    return getCharacteristics().stream().max(Comparator.comparing(LanguageDataCharacteristics::getCreatedAt));
  }

  public Optional<TranslationDocumentStatistics> getCurrentStatistics() {
    return getStatisticsList().stream().max(Comparator.comparing(TranslationDocumentStatistics::getCreatedAt));
  }

  public long getWordCount() {
    return getCurrentStatistics()
      .map(TranslationDocumentStatistics::getTotalOriginalWordCount)
      .orElse(0L);
  }

  public Domain getDomain() {
    return this.getCurrentCharacteristics().flatMap(c -> c.getDomains().stream().findFirst()).map(LanguageDataCharacteristicsDomain::getDomain).orElse(null);
  }

  public ContentType getContentType() {
    return this.getCurrentCharacteristics().flatMap(c -> c.getContentTypes().stream().findFirst()).orElse(null);
  }

  public boolean hasEnoughOriginals() {
    return getCurrentStatistics()
      .map(s -> s.getTotalUnitsOriginal() > 0)
      .orElse(false);
  }

  public String getDescription() {
    String newDescription = description;
    if (Objects.isNull(newDescription) || newDescription.isBlank()) {
      newDescription = String.format("%s - %s, %s, %s",
        this.getSourceLanguage().getDisplayName(),
        this.getTargetLanguage().getDisplayName(),
        this.getDomain().getDescription(),
        this.getContentType().getDescription());
    }
    return newDescription;
  }

  public enum Format {
    TMX
  }

  public enum Status {
    NEW, PUBLISHED, READY, ANONYMIZED
  }
}
