package net.taus.data.marketplace.model.price;

import net.taus.data.language.api.model.ContentType;
import net.taus.data.language.api.model.Domain;
import net.taus.data.language.api.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LanguageDataAvailabilityCountRepository extends JpaRepository<LanguageDataAvailabilityCount, Long> {

  Optional<LanguageDataAvailabilityCount> findOneBySourceLanguageIdAndTargetLanguageIdAndDomainIdAndContentTypeId(Long sourceLanguageId, Long targetLanguageId, Long domainId, Long contentTypeId);

  @Query("select l.wordCount from LanguageDataAvailabilityCount l")
  List<Long> findWordCounts();

  default LanguageDataAvailabilityCount updateCount(final Language sourceLanguage,
                                                    final Language targetLanguage,
                                                    final Domain domain,
                                                    final ContentType contentType,
                                                    final Long wordCount) {
    var optional = findOneBySourceLanguageIdAndTargetLanguageIdAndDomainIdAndContentTypeId(sourceLanguage.getId(), targetLanguage.getId(), domain.getId(), contentType.getId());
    var availabilityCount = optional.map(c -> c.addToWordCount(wordCount))
      .orElseGet(() -> new LanguageDataAvailabilityCount(sourceLanguage, targetLanguage, domain, contentType, wordCount));
    return save(availabilityCount);
  }
}