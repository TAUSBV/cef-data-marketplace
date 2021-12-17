package net.taus.data.marketplace.model.user.views;



import net.taus.data.marketplace.model.languagedata.TranslationDocumentPublishedSummary;

import java.util.List;

public interface SellerSummary extends UserSummary {
  List<TranslationDocumentPublishedSummary> getDocuments();

  void setDocuments(final List<TranslationDocumentPublishedSummary> documents);

}
