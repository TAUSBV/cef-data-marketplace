package net.taus.data.marketplace.model.user.views;

import lombok.Getter;
import lombok.Setter;
import net.taus.data.marketplace.model.languagedata.TranslationDocumentPublishedSummary;
import net.taus.data.marketplace.model.user.SlugId;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
public class DefaultSellerSummary implements SellerSummary {
  private final UUID uid;
  private final long corporateId;
  private final String firstName;
  private final String lastName;
  private final String country;
  private final boolean verified;
  private final LocalDateTime joinedAt;

  @Setter
  private Set<String> languages;
  @Setter
  private Set<String> domains;
  @Setter
  private Set<String> contentTypes;
  @Setter
  private long documentCount;
  @Setter
  private List<TranslationDocumentPublishedSummary> documents;

  public DefaultSellerSummary(final UUID uid,
                              final long corporateId,
                              final String firstName,
                              final String lastName,
                              final String country,
                              final boolean verified,
                              final LocalDateTime joinedAt) {
    this.uid = uid;
    this.corporateId = corporateId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.country = country;
    this.verified = verified;
    this.joinedAt = joinedAt;
  }

  @Override
  public SlugId getSlugId() {
    return new SlugId(uid, firstName, lastName);
  }
}
