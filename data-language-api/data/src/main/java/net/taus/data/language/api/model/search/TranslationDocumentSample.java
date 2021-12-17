package net.taus.data.language.api.model.search;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.taus.data.language.api.model.Language;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class TranslationDocumentSample {

  private long ownerId;
  private UUID uid;
  private String name;
  private Language sourceLanguage;
  private Language targetLanguage;
  private long unitCount;
  private long sourceWordCount;
  private long targetWordCount;
  private double pricePerWord;
  private double price;
  private String domain;
  private String contentType;
  private List<TranslationUnitSample> units;

  public TranslationDocumentSample(final Long ownerId,
                                   final byte[] uid,
                                   final String name,
                                   final Language sourceLanguage,
                                   final Language targetLanguage,
                                   final Long unitCount,
                                   final Long sourceWordCount,
                                   final Long targetWordCount,
                                   final BigDecimal pricePerWord,
                                   final String domainDescription,
                                   final String contentTypeDescription) {
    this.ownerId = ownerId;
    this.uid = UUIDUtil.fromBytes(uid);
    this.name = name;
    this.sourceLanguage = sourceLanguage;
    this.targetLanguage = targetLanguage;
    this.unitCount = unitCount;
    this.sourceWordCount = sourceWordCount;
    this.targetWordCount = targetWordCount;
    this.pricePerWord = pricePerWord.doubleValue();
    this.domain = domainDescription;
    this.contentType = contentTypeDescription;
    this.price = this.sourceWordCount * this.pricePerWord;
    this.units = new ArrayList<>();
  }

}
