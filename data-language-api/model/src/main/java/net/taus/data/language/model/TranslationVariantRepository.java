package net.taus.data.language.model;

import net.taus.data.language.model.meta.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Transactional
public interface TranslationVariantRepository extends JpaRepository<TranslationVariant, Long>, TranslationVariantRepositoryCustom {

  Optional<TranslationVariant> findByLanguageAndHash(final Language language, final long hash);
}

@Transactional
interface TranslationVariantRepositoryCustom {

  Map<Long, TranslationVariant> findByLanguageIsIn(final List<Language> languages, final long limit);

}
