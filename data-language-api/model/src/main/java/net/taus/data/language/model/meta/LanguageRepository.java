package net.taus.data.language.model.meta;

import net.taus.data.language.api.model.CompositeLanguageSourceTargetCount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface LanguageRepository extends CrudRepository<Language, Long>, LanguageRepositoryCustom {

  @Query("select new net.taus.data.language.api.model.Language(l.id, l.tag, l.name, l.displayName, l.country, l.displayCountry, l.enabledPii) from Language l")
  List<net.taus.data.language.api.model.Language> findSummaryBy();

}

interface LanguageRepositoryCustom {
  Optional<Language> findOrCreateIfExistsInIanaByTag(final String tag);

  CompositeLanguageSourceTargetCount findSourceTargetGroups();
}