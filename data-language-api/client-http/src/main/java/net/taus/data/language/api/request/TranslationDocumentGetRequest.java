package net.taus.data.language.api.request;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder(setterPrefix = "with")
@Getter
public class TranslationDocumentGetRequest {

  private final UUID documentUid;
  private final String nameLike;
  private final String numberOfUnits;
  private final String documentStatus;
  private final String unitQuality;

}
