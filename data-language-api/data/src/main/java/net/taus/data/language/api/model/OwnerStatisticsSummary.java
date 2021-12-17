package net.taus.data.language.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OwnerStatisticsSummary {

  private Long ownerId;
  private Double price;
  private Long totalUnits;
  private Long totalSourceWords;
  private Long totalTargetWords;

}
