package net.taus.data.marketplace.api.languagedata;

import net.taus.data.language.api.DataLanguageClient;
import net.taus.data.marketplace.api.user.UserService;
import net.taus.data.marketplace.model.languagedata.TranslationDocumentPublishedSummary;
import net.taus.data.marketplace.model.languagedata.TranslationDocumentReviewRepository;
import net.taus.data.marketplace.model.languagedata.TranslationDocumentReviewStatistics;
import net.taus.data.marketplace.model.languagedata.views.TranslationDocumentReviewSummary;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;
import static net.taus.data.marketplace.model.languagedata.TranslationDocumentReview.Status.APPROVED;

@RestController
@RequestMapping("/documents")
public class TranslationDocumentController {

  private final DataLanguageClient dataLanguageClient;
  private final UserService users;
  private final TranslationDocumentReviewRepository reviews;

  public TranslationDocumentController(final DataLanguageClient dataLanguageClient,
                                       final UserService users,
                                       final TranslationDocumentReviewRepository reviews) {
    this.dataLanguageClient = dataLanguageClient;
    this.users = users;
    this.reviews = reviews;
  }

  @GetMapping(value = { "/featured", "/featured/" })
  @ResponseBody
  public ResponseEntity<List<TranslationDocumentPublishedSummary>> getFeatured() {
    final List<TranslationDocumentPublishedSummary> list = dataLanguageClient.getFeaturedTranslationDocument(10, 1)
      .stream().map(document -> {
        final TranslationDocumentReviewStatistics reviewStatistics = reviews.getStatistics(document.getUid());
        return new TranslationDocumentPublishedSummary(document, users.findByCorporateId(document.getOwnerId()).orElse(null), reviewStatistics);
      })
      .collect(toList());
    return ResponseEntity.ok(list);
  }

  @GetMapping("/{documentUid}/reviews")
  public ResponseEntity<List<TranslationDocumentReviewSummary>> list(@PathVariable final UUID documentUid) {
    return ResponseEntity.ok(reviews.findAllSummaryByDocumentUidAndStatus(documentUid, APPROVED));
  }

}
