package net.taus.data.language.model;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import net.taus.data.language.api.model.LanguageDataParams;
import net.taus.data.language.api.model.OwnerDocumentStatisticsSummary;
import net.taus.data.language.api.model.OwnerStatisticsSummary;
import net.taus.data.language.api.model.OwnerSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Transactional
public interface TranslationDocumentRepository extends JpaRepository<TranslationDocument, Long>, TranslationDocumentRepositoryCustom {

  @Query("select d from TranslationDocument d left join fetch d.characteristics where d.uid = :uid")
  Optional<TranslationDocument> findByUid(@Param("uid") final UUID uid);

  @Query("select d from TranslationDocument d left join fetch d.statisticsList where d.uid = :uid")
  Optional<TranslationDocument> findByUidWithStatistics(@Param("uid") final UUID uid);

  @Query("select d from TranslationDocument d left join fetch d.characteristics where d.uid = :uid")
  TranslationDocument getOneByUid(@Param("uid") final UUID uid);

  @Query("select d from TranslationDocument d left join fetch d.statisticsList where d.uid = :uid")
  TranslationDocument getOneByUidWithStatistics(@Param("uid") final UUID uid);

}

@Transactional(noRollbackFor = TranslationDocumentNotEnoughOriginalException.class)
interface TranslationDocumentRepositoryCustom {

  TranslationDocument publish(final TranslationDocument document) throws TranslationDocumentException;

  TranslationDocument updateStatistics(final TranslationDocument document);

  TranslationDocument updateStatistics(final TranslationDocument document, final String comment);

  Map<Long, List<net.taus.data.language.api.model.TranslationDocument>> findByOwnersIdGrouped(final Collection<Long> ownersId,
                                                                                              final int numberOfDocuments,
                                                                                              final int numberOfUnits);

  Optional<net.taus.data.language.api.model.TranslationDocument> findOneSummaryByUid(final UUID documentUid);

  Optional<net.taus.data.language.api.model.TranslationDocument> findOneSummaryByUidAndStatusAndUnitQualityAndAnnotations(final UUID documentUid,
                                                                                                                          final String documentStatus,
                                                                                                                          final String unitQuality,
                                                                                                                          final int numberOfUnits,
                                                                                                                          final boolean withAnnotations);

  Optional<net.taus.data.language.api.model.TranslationDocument> findOneSummaryByPredicate(final Predicate predicate,
                                                                                           final OrderSpecifier<?> order,
                                                                                           final int numberOfUnits);

  Optional<net.taus.data.language.api.model.TranslationDocument> findOneSummaryPublishedByUid(final UUID uuid);

  Page<net.taus.data.language.api.model.TranslationDocument> findPageSummaryByPredicateAndOrder(final Predicate predicate,
                                                                                                final OrderSpecifier<?> order,
                                                                                                final int numberOfUnits,
                                                                                                final Pageable pageable);

  Page<net.taus.data.language.api.model.TranslationDocument> findPageSummaryPublishedByOwnerId(final Long ownerId,
                                                                                               final int numberOfUnits,
                                                                                               final Pageable pageable);

  Page<net.taus.data.language.api.model.TranslationDocument> findPageSummaryByOwnerIdAndNameLike(final Long ownerId,
                                                                                                 final String documentStatus,
                                                                                                 final String nameLike,
                                                                                                 final int numberOfUnits,
                                                                                                 final Pageable pageable);

  List<net.taus.data.language.api.model.TranslationDocument> findListSummaryByPredicateOrderBy(final Predicate predicate,
                                                                                               final OrderSpecifier<?> order,
                                                                                               final int numberOfDocuments,
                                                                                               final int numberOfUnits);

  List<net.taus.data.language.api.model.TranslationDocument> findListSummaryPublishedByOwnerId(final Long ownerId,
                                                                                               final int numberOfDocuments,
                                                                                               final int numberOfUnits);

  List<net.taus.data.language.api.model.TranslationDocument> getFeatured(final int numberOfDocuments,
                                                                         final int numberOfUnits);


  Optional<OwnerSummary> findOwnerSummaryByOwnerId(Long ownerId);

  Page<net.taus.data.language.api.model.OwnerSummary> findOwnersSummary(int numberOfDocuments, int numberOfUnits, Pageable pageable);

  List<OwnerDocumentStatisticsSummary> findOwnerDocumentStatisticsSummaryByLanguageDataParamsGroupByOwnerId(LanguageDataParams params);

  List<OwnerStatisticsSummary> findOwnerStatisticsSummaryByLanguageDataParamsGroupByOwnerId(LanguageDataParams params);
}
