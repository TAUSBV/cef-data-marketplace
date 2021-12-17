package net.taus.data.language.api.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class TranslationDocumentStatistics {

  @EqualsAndHashCode.Include
  Long id;
  Long totalUnits;
  Long totalUnitsOriginal;
  Long totalUnitsReplica;
  Long totalUnitsLowQuality;
  Long totalUnitsAnnotatedPii;
  Long totalSourceWordCount;
  Long totalSourceOriginalWordCount;
  Long totalSourceReplicaWordCount;
  Long totalSourceLowQualityWordCount;
  Long totalSourceWordCountAnnotatedPii;
  Long totalTargetWordCount;
  Long totalTargetOriginalWordCount;
  Long totalTargetReplicaWordCount;
  Long totalTargetLowQualityWordCount;
  Long totalTargetWordCountAnnotatedPii;
  LocalDateTime createdAt;

  public TranslationDocumentStatistics(final long totalUnits,
                                       final long totalUnitsOriginal,
                                       final long totalUnitsReplica,
                                       final long totalUnitsLowQuality,
                                       final long totalUnitsAnnotatedPii,
                                       final long totalSourceWordCount,
                                       final long totalSourceOriginalWordCount,
                                       final long totalSourceReplicaWordCount,
                                       final long totalSourceLowQualityWordCount,
                                       final long totalSourceWordCountAnnotatedPii,
                                       final long totalTargetWordCount,
                                       final long totalTargetOriginalWordCount,
                                       final long totalTargetReplicaWordCount,
                                       final long totalTargetLowQualityWordCount,
                                       final long totalTargetWordCountAnnotatedPii) {
    this.totalUnits = totalUnits;
    this.totalUnitsOriginal = totalUnitsOriginal;
    this.totalUnitsReplica = totalUnitsReplica;
    this.totalUnitsAnnotatedPii = totalUnitsAnnotatedPii;
    this.totalUnitsLowQuality = totalUnitsLowQuality;
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
                                       final Long totalTargetWordCountAnnotatedPii,
                                       final Timestamp createdAt) {
    this.totalUnits = totalUnits;
    this.totalUnitsOriginal = totalUnitsOriginal;
    this.totalUnitsReplica = totalUnitsReplica;
    this.totalUnitsAnnotatedPii = totalUnitsAnnotatedPii;
    this.totalUnitsLowQuality = totalUnitsLowQuality;
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
    this.createdAt = createdAt.toLocalDateTime();
  }

  public Long getTotalOriginalWordCount() {
    final Long sourceOriginalWordCount = Optional.ofNullable(this.getTotalSourceOriginalWordCount()).orElse(0L);
    final Long targetOriginalWordCount = Optional.ofNullable(this.getTotalTargetOriginalWordCount()).orElse(0L);
    return sourceOriginalWordCount + targetOriginalWordCount;
  }

}
