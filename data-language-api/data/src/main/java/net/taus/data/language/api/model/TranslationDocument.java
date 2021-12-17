package net.taus.data.language.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.taus.data.language.api.model.search.UUIDUtil;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@Data
@AllArgsConstructor
public class TranslationDocument {

  private Long ownerId;
  private UUID uid;
  private Long size;
  private String name;
  private String status;
  private String description;
  private String format;
  @Deprecated(forRemoval = true)
  private Double pricePerWord;
  private Domain domain;
  private ContentType contentType;
  private Language sourceLanguage;
  private Language targetLanguage;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private List<TranslationUnit> units;
  private List<TranslationDocumentStatistics> statisticsList;
  private List<DataAnnotationCategoryCount> annotations;

  public TranslationDocument() {
    this.units = new ArrayList<>();
    this.statisticsList = new ArrayList<>();
  }

  public TranslationDocument(final Long ownerId,
                             final UUID uid,
                             final String name,
                             final String status,
                             final String description,
                             final String format,
                             final Long size,
                             final Double pricePerWord,
                             final LocalDateTime createdAt,
                             final LocalDateTime updatedAt,
                             final Domain domain,
                             final ContentType contentType,
                             final Language sourceLanguage,
                             final Language targetLanguage) {
    this.ownerId = ownerId;
    this.uid = uid;
    this.name = name;
    this.status = status;
    this.description = description;
    this.format = format;
    this.size = size;
    this.pricePerWord = pricePerWord;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.domain = domain;
    this.contentType = contentType;
    this.sourceLanguage = sourceLanguage;
    this.targetLanguage = targetLanguage;
    this.statisticsList = new ArrayList<>();
  }

  public TranslationDocument(final Long ownerId,
                             final byte[] uid,
                             final String name,
                             final String status,
                             final String description,
                             final String format,
                             final Long size,
                             final Double pricePerWord,
                             final Timestamp createdAt,
                             final Timestamp updatedAt,
                             final Domain domain,
                             final ContentType contentType,
                             final Language sourceLanguage,
                             final Language targetLanguage) {
    this.ownerId = ownerId;
    this.uid = UUIDUtil.fromBytes(uid);
    this.name = name;
    this.status = status;
    this.description = description;
    this.format = format;
    this.size = size;
    this.pricePerWord = pricePerWord;
    this.createdAt = createdAt.toLocalDateTime();
    this.updatedAt = updatedAt.toLocalDateTime();
    this.domain = domain;
    this.contentType = contentType;
    this.sourceLanguage = sourceLanguage;
    this.targetLanguage = targetLanguage;
    this.statisticsList = new ArrayList<>();
  }

  public TranslationDocument(final Long ownerId,
                             final UUID uid,
                             final String name,
                             final String status,
                             final String description,
                             final String format,
                             final Long size,
                             final Double pricePerWord,
                             final LocalDateTime createdAt,
                             final LocalDateTime updatedAt,
                             final Domain domain,
                             final ContentType contentType,
                             final Language sourceLanguage,
                             final Language targetLanguage,
                             final List<TranslationDocumentStatistics> statisticsList) {
    this(ownerId, uid, name, status, description, format, size, pricePerWord, createdAt, updatedAt, domain, contentType, sourceLanguage, targetLanguage);
    this.statisticsList = statisticsList;
  }

  public TranslationDocument(final Long ownerId,
                             final byte[] uid,
                             final String name,
                             final String status,
                             final String description,
                             final String format,
                             final Long size,
                             final Double pricePerWord,
                             final Timestamp createdAt,
                             final Timestamp updatedAt,
                             final Domain domain,
                             final ContentType contentType,
                             final Language sourceLanguage,
                             final Language targetLanguage,
                             final List<TranslationDocumentStatistics> statisticsList) {
    this(ownerId, uid, name, status, description, format, size, pricePerWord, createdAt, updatedAt, domain, contentType, sourceLanguage, targetLanguage);
    this.statisticsList = statisticsList;
  }

  public String getDescription() {
    String newDescription = description;
    if ((Objects.isNull(newDescription) || newDescription.isBlank())
      && Objects.nonNull(this.getDomain()) && Objects.nonNull(this.getContentType())) {
      newDescription = String.format("%s (%s) - %s (%s), %s, %s",
        this.getSourceLanguage().getName(),
        this.getSourceLanguage().getCountry(),
        this.getTargetLanguage().getName(),
        this.getTargetLanguage().getCountry(),
        this.getDomain().getDescription(),
        this.getContentType().getDescription());
    }
    return newDescription;
  }

  public Optional<TranslationDocumentStatistics> getCurrentStatistics() {
    return this.getStatisticsList().stream().max(Comparator.comparing(TranslationDocumentStatistics::getCreatedAt));
  }

  public Long getUnitCount() {
    return this.getCurrentStatistics().flatMap(s -> Optional.ofNullable(s.getTotalUnitsOriginal())
      .flatMap(o -> Optional.ofNullable(s.getTotalUnitsReplica()).map(t -> o + t))).orElse(0L);
  }

  public Long getSourceWordCount() {
    return this.getCurrentStatistics().flatMap(s -> Optional.ofNullable(s.getTotalSourceOriginalWordCount())
      .flatMap(o -> Optional.ofNullable(s.getTotalSourceReplicaWordCount()).map(t -> o + t))).orElse(0L);
  }

  public Long getTargetWordCount() {
    return this.getCurrentStatistics().flatMap(s -> Optional.ofNullable(s.getTotalTargetOriginalWordCount())
      .flatMap(o -> Optional.ofNullable(s.getTotalTargetReplicaWordCount()).map(t -> o + t))).orElse(0L);
  }

  @Deprecated(forRemoval = true)
  public Double getPrice() {
    return Optional.ofNullable(this.getPricePerWord()).map(p -> p * getSourceWordCount()).orElse(0.0);
  }

}
