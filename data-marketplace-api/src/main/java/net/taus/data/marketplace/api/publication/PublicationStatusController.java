package net.taus.data.marketplace.api.publication;

import net.taus.data.language.api.DataLanguageClient;
import net.taus.data.marketplace.api.base.BaseController;
import net.taus.data.marketplace.model.languagedata.TranslationDocumentPublishingSummary;
import net.taus.data.marketplace.model.publication.PublicationRepository;
import net.taus.data.marketplace.model.publication.views.PublicationSummary;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static java.util.stream.Collectors.toList;
import static net.taus.data.marketplace.model.publication.Publication.Status.PUBLISHED;
import static net.taus.data.marketplace.model.publication.Publication.Status.REVIEW;

@RestController
@RequestMapping("/publications/{uid}/status")
public class PublicationStatusController extends BaseController {

  private final PublicationRepository publications;
  private final DataLanguageClient dataLanguageClient;
  private final PublicationNotificationService notificationService;
  private final List<DataLanguageComponent> components;

  public PublicationStatusController(final PublicationRepository publications,
                                     final DataLanguageClient dataLanguageClient,
                                     final PublicationNotificationService notificationService,
                                     final List<DataLanguageComponent> components) {
    this.publications = publications;
    this.dataLanguageClient = dataLanguageClient;
    this.notificationService = notificationService;
    this.components = components;
  }

  @GetMapping("")
  public DeferredResult<ResponseEntity<PublicationSummary>> checkStatus(@PathVariable("uid") final UUID id) {
    final var current = publications.findByUid(id);
    return current.map(p -> {
      final List<String> required = components.stream()
        .filter(DataLanguageComponent::isRequired)
        .map(DataLanguageComponent::getName)
        .distinct().collect(toList());
      if (p.hasAllRequiredAnalysisComponentsFinished(required)) {
        p.moveToStatus(REVIEW);
        publications.save(p);
      }
      final PublicationSummary summary = PublicationSummary.fromPublication(p);
      final var result = new DeferredResult<ResponseEntity<PublicationSummary>>(TimeUnit.SECONDS.toMillis(10));
      if (p.getStatus().equals(PUBLISHED) || p.getStatus().equals(REVIEW) || p.hasErrors()) {
        result.setResult(ResponseEntity.ok(decorate(summary)));
      }
      else {
        result.onCompletion(() -> notificationService.removeScreen(p.getUid()));
        result.onTimeout(() -> Optional.ofNullable(notificationService.removeScreen(p.getUid()))
          .ifPresent(r -> r.setResult((ResponseEntity.noContent().build()))));
        result.onError(e -> Optional.ofNullable(notificationService.removeScreen(p.getUid()))
          .ifPresent(r -> r.setResult((ResponseEntity.noContent().build()))));
        notificationService.addScreen(p.getUid(), result);
      }
      return result;
    }).orElseGet(() -> new DeferredResult<>(0L, () -> ResponseEntity.notFound().build()));
  }

  private PublicationSummary decorate(final PublicationSummary summary) {
    final String documentStatus = "NEW";
    final String unitQuality = "LOW";
    final int numberOfUnits = 10;
    final boolean annotations = true;
    dataLanguageClient.findTranslationDocumentWithUnitsByDocumentUidAndDocumentStatusAndUnitQualityAndAnnotations(summary.getDocumentUid(), documentStatus, unitQuality, numberOfUnits, annotations)
      .ifPresent(d -> summary.setDocument(new TranslationDocumentPublishingSummary(d)));
    return summary;
  }

}
