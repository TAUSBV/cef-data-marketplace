package net.taus.data.marketplace.model.languagedata.views;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TranslationUnitSimple {

  private long id;
  private long sequence;
  String sourceValue;
  String sourceLanguageTag;
  String targetValue;
  String targetLanguageTag;

}
