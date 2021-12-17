package net.taus.data.language.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataAnnotationCategoryCount {
  private Long id;
  private String code;
  private String description;
  private Long count;
  private List<TranslationUnitAnnotation> units;

  public DataAnnotationCategoryCount(final Long id,
                                     final String code,
                                     final String description,
                                     final Long count) {
    this.id = id;
    this.code = code;
    this.description = description;
    this.count = count;
  }
}
