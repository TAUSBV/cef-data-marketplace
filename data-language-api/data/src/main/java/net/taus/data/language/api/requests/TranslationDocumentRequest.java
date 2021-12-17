package net.taus.data.language.api.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TranslationDocumentRequest {

  private int pageNumber;
  private int pageSize;

  private String status;
  private String nameLike;
  private String unitQuality;
  private int numberOfUnits;
  private boolean annotations;
}
