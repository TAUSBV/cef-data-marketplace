package net.taus.data.language.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "translation_documents_statistics")
@EntityListeners(AuditingEntityListener.class)
public class TranslationDocumentStatistics {

  @Id
  @Column(updatable = false, nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Exclude
  private Long id;

  @Column(columnDefinition = "bigint default 0")
  private Long totalUnits;
  @Column(columnDefinition = "bigint default 0")
  private Long totalUnitsOriginal;
  @Column(columnDefinition = "bigint default 0")
  private Long totalUnitsReplica;
  @Column(columnDefinition = "bigint default 0")
  private Long totalUnitsLowQuality;
  @Column(columnDefinition = "bigint default 0")
  private Long totalUnitsAnnotatedPii;

  @Column(columnDefinition = "bigint default 0")
  private Long totalSourceWordCount;
  @Column(columnDefinition = "bigint default 0")
  private Long totalSourceOriginalWordCount;
  @Column(columnDefinition = "bigint default 0")
  private Long totalSourceReplicaWordCount;
  @Column(columnDefinition = "bigint default 0")
  private Long totalSourceLowQualityWordCount;
  @Column(columnDefinition = "bigint default 0")
  private Long totalSourceWordCountAnnotatedPii;

  @Column(columnDefinition = "bigint default 0")
  private Long totalTargetWordCount;
  @Column(columnDefinition = "bigint default 0")
  private Long totalTargetOriginalWordCount;
  @Column(columnDefinition = "bigint default 0")
  private Long totalTargetReplicaWordCount;
  @Column(columnDefinition = "bigint default 0")
  private Long totalTargetLowQualityWordCount;
  @Column(columnDefinition = "bigint default 0")
  private Long totalTargetWordCountAnnotatedPii;
  @Column(columnDefinition = "varchar(1000) default ''")
  @EqualsAndHashCode.Exclude
  private String comment;

  @CreatedDate
  @EqualsAndHashCode.Exclude
  private LocalDateTime createdAt;

  public TranslationDocumentStatistics() {
    this.totalUnits = 0L;
    this.totalUnitsOriginal = 0L;
    this.totalUnitsReplica = 0L;
    this.totalUnitsLowQuality = 0L;
    this.totalSourceWordCount = 0L;
    this.totalSourceOriginalWordCount = 0L;
    this.totalSourceReplicaWordCount = 0L;
    this.totalSourceLowQualityWordCount = 0L;
    this.totalTargetWordCount = 0L;
    this.totalTargetOriginalWordCount = 0L;
    this.totalTargetReplicaWordCount = 0L;
    this.totalTargetLowQualityWordCount = 0L;
    this.createdAt = LocalDateTime.now();
    this.comment = "Baseline.";
  }

  public TranslationDocumentStatistics(final Long totalUnits,
                                       final Long totalUnitsOriginal,
                                       final Long totalUnitsReplica,
                                       final Long totalUnitsLowQuality,
                                       final Long totalUnitsAnnotatedPii,
                                       final Long totalSourceWordCount,
                                       final Long totalSourceOriginalWordCount,
                                       final Long totalSourceReplicaWordCount,
                                       final Long totalSourceLowQualityWordCount,
                                       final Long totalSourceWordCountAnnotatedPii,
                                       final Long totalTargetWordCount,
                                       final Long totalTargetOriginalWordCount,
                                       final Long totalTargetReplicaWordCount,
                                       final Long totalTargetLowQualityWordCount,
                                       final Long totalTargetWordCountAnnotatedPii) {
    this();
    this.totalUnits = totalUnits;
    this.totalUnitsOriginal = totalUnitsOriginal;
    this.totalUnitsReplica = totalUnitsReplica;
    this.totalUnitsLowQuality = totalUnitsLowQuality;
    this.totalUnitsAnnotatedPii = totalUnitsAnnotatedPii;
    this.totalSourceWordCount = totalSourceWordCount;
    this.totalSourceOriginalWordCount = totalSourceOriginalWordCount;
    this.totalSourceReplicaWordCount = totalSourceReplicaWordCount;
    this.totalSourceLowQualityWordCount = totalSourceLowQualityWordCount;
    this.totalSourceWordCountAnnotatedPii = totalSourceWordCountAnnotatedPii;
    this.totalTargetWordCount = totalTargetWordCount;
    this.totalTargetOriginalWordCount = totalTargetOriginalWordCount;
    this.totalTargetReplicaWordCount = totalTargetReplicaWordCount;
    this.totalTargetLowQualityWordCount = totalTargetLowQualityWordCount;
    this.totalTargetWordCountAnnotatedPii = totalTargetWordCountAnnotatedPii;
  }

  public Long getTotalOriginalWordCount() {
    return this.getTotalSourceOriginalWordCount() + this.getTotalTargetOriginalWordCount();
  }

}
