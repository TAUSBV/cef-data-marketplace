package net.taus.data.language.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TranslationUnitAnnotation {

  private Long id;
  private Long sourceId;
  private String sourceValue;
  private Long sourceWordCount;
  private Long targetId;
  private String targetValue;
  private Long targetWordCount;
  private List<DataAnnotation> sourceAnnotations;
  private List<DataAnnotation> targetAnnotations;

  public TranslationUnitAnnotation(final Long id,
                                   final Long sourceId,
                                   final String sourceValue,
                                   final Long sourceWordCount,
                                   final Long targetId,
                                   final String targetValue,
                                   final Long targetWordCount) {
    this.id = id;
    this.sourceId = sourceId;
    this.sourceValue = sourceValue;
    this.sourceWordCount = sourceWordCount;
    this.targetId = targetId;
    this.targetValue = targetValue;
    this.targetWordCount = targetWordCount;
  }
}
