package net.taus.data.language.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataAnnotation {

  private Long id;
  private int offsetStart;
  private int offsetEnd;
  private Long categoryId;
  private String categoryDescription;
  private String categoryCode;
  private String categoryKind;

}
