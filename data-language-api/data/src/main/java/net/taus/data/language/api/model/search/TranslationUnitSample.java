package net.taus.data.language.api.model.search;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class TranslationUnitSample {

  private String sourceValue;
  private long sourceWordCount;
  private String targetValue;
  private long targetWordCount;
  private UUID documentUid;
  private Long ownerId;
  private String domain;
  private String contentType;

  public TranslationUnitSample(
    final Long ownerId,
    final byte[] documentUid,
    final String sourceValue,
    final Long sourceWordCount,
    final String targetValue,
    final Long targetWordCount,
    final String domainDescription,
    final String contentTypeDescription
  ) {
    this.ownerId = ownerId;
    this.documentUid = UUIDUtil.fromBytes(documentUid);
    this.sourceValue = sourceValue;
    this.sourceWordCount = sourceWordCount;
    this.targetValue = targetValue;
    this.targetWordCount = targetWordCount;
    this.domain = domainDescription;
    this.contentType = contentTypeDescription;
  }
}
