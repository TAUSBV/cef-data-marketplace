package net.taus.data.language.model;

import com.querydsl.core.types.Predicate;
import net.taus.data.language.model.TranslationDocument.Status;
import net.taus.data.language.model.TranslationUnit.Quality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;
import javax.persistence.QueryHint;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

import static net.taus.data.language.model.TranslationDocument.Status.PUBLISHED;
import static net.taus.data.language.model.TranslationUnit.Quality.LOW;
import static org.hibernate.annotations.QueryHints.READ_ONLY;
import static org.hibernate.jpa.QueryHints.HINT_CACHEABLE;
import static org.hibernate.jpa.QueryHints.HINT_FETCH_SIZE;

@Transactional
public interface TranslationUnitRepository extends TranslationUnitRepositoryCustom, JpaRepository<TranslationUnit, Long> {

  List<TranslationUnit> findAllByDocumentAndQualityAndSequenceBetweenOrderBySequence(final TranslationDocument document,
                                                                                     final Quality quality,
                                                                                     final long begin,
                                                                                     final long end);

  @Nonnull
  default List<TranslationUnit> saveOriginals(@Nonnull Iterable<TranslationUnit> entities) {
    return this.saveAll(entities);
  }

  @NonNull
  default TranslationUnit saveOriginal(@NonNull TranslationUnit unit) {
    unit.setSource(getTranslationVariantRepository().saveAndFlush(unit.getSource()));
    unit.setTarget(getTranslationVariantRepository().saveAndFlush(unit.getTarget()));
    return this.save(unit);
  }

  @Nonnull
  default List<TranslationUnit> saveDuplicates(@Nonnull Iterable<TranslationUnit> entities) {
    final List<TranslationUnit> result = new ArrayList<>();
    final Map<Long, TranslationUnit> originalsMap = new ConcurrentHashMap<>();
    for (TranslationUnit u : entities) {
      var original = originalsMap.computeIfAbsent(u.getHash(), k -> u);
      if (!Objects.equals(original, u)) {
        u.setReference(original);
      }
      final TranslationUnit save = saveDuplicate(u);
      result.add(save);
    }
    return result;

  }

  @NonNull
  default TranslationUnit saveDuplicate(@NonNull TranslationUnit unit) {
    var possibleOriginal = this.findByHashAndStatusAndReferenceIsNull(unit.getHash(), PUBLISHED)
      .or(() -> this.findByHashAndDocumentAndReferenceIsNull(unit.getHash(), unit.getDocument()).stream().findFirst());
    return possibleOriginal.map(original -> {
      TranslationUnit value;
      if (original.getId().equals(unit.getId())) {
        value = unit;
        unit.setSource(original.getSource());
        unit.setTarget(original.getTarget());
      }
      else if (original.isVirtual()) {
        original.setVirtual(false);
        original.setDocument(unit.getDocument());
        value = original;
      }
      else {
        if (unit.getDocument().getUid().equals(original.getDocument().getUid())) {
          unit.setQuality(LOW);
        }
        unit.setReference(original);
        unit.setSource(original.getSource());
        unit.setTarget(original.getTarget());
        value = unit;
      }
      return save(value);
    }).orElseGet(() -> {
      final TranslationVariantRepository variants = getTranslationVariantRepository();
      final TranslationVariant source = unit.getSource();
      final TranslationVariant target = unit.getTarget();
      final Map<Long, TranslationVariant> variantMap = variants.findByLanguageIsIn(List.of(source.getLanguage(), target.getLanguage()), 1_000_000);
      var t = variantMap.compute(source.getHash(), (k, v) -> Optional.ofNullable(v)
        .orElseGet(() -> variants.findByLanguageAndHash(source.getLanguage(), source.getHash()).orElseGet(() -> variants.save(source))));
      unit.setSource(t);
      t = variantMap.compute(target.getHash(), (k, v) -> Optional.ofNullable(v)
        .orElseGet(() -> variants.findByLanguageAndHash(target.getLanguage(), target.getHash()).orElseGet(() -> variants.save(target))));
      unit.setTarget(t);
      return save(unit);
    });
  }

}

@Transactional
interface TranslationUnitRepositoryCustom {

  TranslationVariantRepository getTranslationVariantRepository();

  Optional<TranslationUnit> findByHashAndStatusAndReferenceIsNull(final long hash, Status status);

  List<TranslationUnit> findByHashAndDocumentAndReferenceIsNull(final long hash, TranslationDocument document);

  List<net.taus.data.language.api.model.TranslationUnit> findPublishedAndHighQualityByDocumentUid(final UUID documentUid, final int numberOfUnits);

  List<net.taus.data.language.api.model.TranslationUnit> findByDocumentUidAndQuality(final UUID documentUid, final Quality quality, final int numberOfUnits);

  List<net.taus.data.language.api.model.TranslationUnit> findByDocumentUidAndStatusAndQuality(final UUID documentUid, final Status status, final Quality quality, final int numberOfUnits);

  List<net.taus.data.language.api.model.TranslationUnit> findByPredicate(final Predicate predicate, final int numberOfUnits);

  List<net.taus.data.language.api.model.TranslationUnit> findByIds(final Collection<Long> ids);
}