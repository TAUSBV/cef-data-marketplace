package net.taus.data.language.api.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class LanguageDataCharacteristics {

  private Long id;

  private double pricePerWord;

  private LocalDateTime createdAt;

  private TranslationDocumentStatistics statistics;

  private List<ContentType> contentTypes;

  private List<Domain> domains;


  public LanguageDataCharacteristics() {
    this.domains = new ArrayList<>();
    this.contentTypes = new ArrayList<>();
  }

  public long getWordCount() {
    return getStatistics().getTotalOriginalWordCount();
  }
}
