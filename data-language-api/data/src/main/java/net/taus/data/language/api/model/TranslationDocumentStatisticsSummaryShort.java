package net.taus.data.language.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TranslationDocumentStatisticsSummaryShort {

  private UUID documentUid;
  private String documentName;
  private Double price;
  private Long totalUnits;
  private Long totalSourceWords;
  private Long totalTargetWords;

}
