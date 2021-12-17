package net.taus.data.marketplace.model.user.views;

import net.taus.data.marketplace.model.user.SlugId;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;


public interface UserSummary {

  UUID getUid();

  long getCorporateId();

  SlugId getSlugId();

  String getFirstName();

  String getLastName();

  String getCountry();

  LocalDateTime getJoinedAt();

  boolean isVerified();

  Set<String> getLanguages();

  void setLanguages(final Set<String> languages);

  Set<String> getDomains();

  void setDomains(final Set<String> domains);

  Set<String> getContentTypes();

  void setContentTypes(final Set<String> contentTypes);

  long getDocumentCount();

  void setDocumentCount(final long documentCount);

}

