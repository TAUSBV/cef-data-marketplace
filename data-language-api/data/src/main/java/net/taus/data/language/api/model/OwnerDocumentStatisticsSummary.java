package net.taus.data.language.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OwnerDocumentStatisticsSummary {

  private Long ownerId;
  private Set<TranslationDocumentStatisticsSummaryShort> documents;

}
