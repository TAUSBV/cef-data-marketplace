package net.taus.data.marketplace.model.languagedata;

import net.taus.data.marketplace.model.languagedata.views.TranslationDocumentReviewSummary;
import net.taus.data.marketplace.model.purchase.Purchase;
import net.taus.data.marketplace.model.purchase.PurchaseItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Transactional
public interface TranslationDocumentReviewRepository extends JpaRepository<TranslationDocumentReview, Long> {

  List<TranslationDocumentReviewSummary> findAllSummaryByDocumentUidAndStatus(final UUID documentUid,
                                                                              final TranslationDocumentReview.Status status);

  List<TranslationDocumentReviewSummary> findAllSummaryByPurchaseItem(final PurchaseItem purchaseItem);

  boolean existsByPurchaseItem(final PurchaseItem purchaseItem);

  @Query("select new net.taus.data.marketplace.model.languagedata.TranslationDocumentReviewStatistics(count(r), (sum(r.score) / count(r))) " +
    "from TranslationDocumentReview r where r.documentUid = :documentUid and r.status = 'APPROVED'")
  TranslationDocumentReviewStatistics getStatistics(@Param("documentUid") final UUID documentUid);

  boolean existsByPurchaseItem_PurchaseAndModerationCode(final Purchase purchase, final UUID moderationCode);

  @Modifying
  @Query("update TranslationDocumentReview r set r.status = :status where r.purchaseItem = :item")
  void updateStatus(@Param(value = "item") final PurchaseItem item, @Param(value = "status") TranslationDocumentReview.Status status);

}
