package net.taus.data.language.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnerSummary {

  private Long ownerId;
  private Set<String> languages;
  private Set<String> domains;
  private Set<String> contentTypes;
  private Long documentCount;
  private List<TranslationDocument> documents;

  public OwnerSummary(final Long ownerId,
                      final Set<String> languages,
                      final Set<String> domains,
                      final Set<String> contentTypes,
                      final Long documentCount) {
    this.ownerId = ownerId;
    this.languages = languages;
    this.domains = domains;
    this.contentTypes = contentTypes;
    this.documentCount = documentCount;
  }
}
