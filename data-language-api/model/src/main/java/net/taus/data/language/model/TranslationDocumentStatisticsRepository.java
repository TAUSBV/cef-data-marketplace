package net.taus.data.language.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface TranslationDocumentStatisticsRepository extends JpaRepository<TranslationDocumentStatistics, Long> {

  @Query("select new net.taus.data.language.api.model.TranslationDocumentStatistics(s.id, s.totalUnits, s.totalUnitsOriginal," +
    " s.totalUnitsReplica, s.totalUnitsLowQuality, s.totalUnitsAnnotatedPii, s.totalSourceWordCount, s.totalSourceOriginalWordCount," +
    " s.totalSourceReplicaWordCount, s.totalSourceLowQualityWordCount, s.totalSourceWordCountAnnotatedPii, s.totalTargetWordCount," +
    " s.totalTargetOriginalWordCount, s.totalTargetReplicaWordCount, s.totalTargetLowQualityWordCount, s.totalTargetWordCountAnnotatedPii, " +
    " s.createdAt) " +
    " from TranslationDocumentStatistics s")
  List<net.taus.data.language.api.model.TranslationDocumentStatistics> findSummaryBy();

}