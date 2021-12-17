package net.taus.data.marketplace.api.publication;

import net.taus.data.language.api.DataLanguageClient;
import net.taus.data.language.api.model.TranslationDocument;
import net.taus.data.marketplace.api.crm.CRMService;
import net.taus.data.marketplace.api.user.UserService;
import net.taus.data.marketplace.api.utils.text.StringUtil;
import net.taus.data.marketplace.model.languagedata.TranslationDocumentPublishingSummary;
import net.taus.data.marketplace.model.publication.Publication;
import net.taus.data.marketplace.model.publication.PublicationEvent;
import net.taus.data.marketplace.model.publication.PublicationNotificationRepository;
import net.taus.data.marketplace.model.publication.views.PublicationSummary;
import net.taus.data.marketplace.model.support.UUIDSerializer;
import net.taus.data.marketplace.model.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.stream.Collectors.toList;
import static net.taus.data.marketplace.model.notification.Notification.NotificationStatus.WAITING;
import static net.taus.data.marketplace.model.notification.Notification.NotificationType.EMAIL;
import static net.taus.data.marketplace.model.publication.Publication.Status.REVIEW;

public class PublicationNotificationService {

  private final Logger logger = LoggerFactory.getLogger(PublicationNotificationService.class.getCanonicalName());

  private final PublicationNotificationRepository notifications;
  private final DataLanguageClient dataLanguageClient;
  private final UserService users;
  private final CRMService crmService;
  private final String appUiUrl;
  private final UUIDSerializer uuidSerializer;
  private final Map<UUID, DeferredResult<ResponseEntity<PublicationSummary>>> screens;

  public PublicationNotificationService(final PublicationNotificationRepository notifications,
                                        final DataLanguageClient dataLanguageClient,
                                        final UserService users,
                                        final CRMService crmService,
                                        final String appUiUrl,
                                        final UUIDSerializer uuidSerializer) {
    this.notifications = notifications;
    this.dataLanguageClient = dataLanguageClient;
    this.users = users;
    this.crmService = crmService;
    this.appUiUrl = appUiUrl;
    this.uuidSerializer = uuidSerializer;
    this.screens = new ConcurrentHashMap<>();
  }

  public void addScreen(final UUID publicationUid, final DeferredResult<ResponseEntity<PublicationSummary>> screen) {
    this.screens.put(publicationUid, screen);
  }

  public DeferredResult<ResponseEntity<PublicationSummary>> removeScreen(final UUID publicationUid) {
    return this.screens.remove(publicationUid);
  }

  public void notifySuccessIfNecessary(final Publication publication, final DataLanguageNotification notification) {
    this.notifyScreenAboutSuccessIfNecessary(publication, notification);
    this.notifyOwnerAboutSuccessIfNecessary(publication, notification);
  }

  public void notifyErrorIfNecessary(final Publication publication, final DataLanguageNotification notification) {
    this.notifyScreenAboutErrorIfNecessary(publication);
    this.notifyOwnerAboutFailureIfNecessary(publication, notification);
  }

  public void notifyScreenAboutErrorIfNecessary(final Publication publication) {
    this.screens.computeIfPresent(publication.getUid(), (k, v) -> {
      final PublicationSummary summary = PublicationSummary.fromPublication(publication);
      getDocument(summary.getDocumentUid()).ifPresent(d -> summary.setDocument(new TranslationDocumentPublishingSummary(d)));
      v.setResult(ResponseEntity.ok(summary));
      return null;
    });
  }

  public void notifyScreenAboutSuccessIfNecessary(final Publication publication, final DataLanguageNotification notification) {
    this.screens.computeIfPresent(publication.getUid(), (k, v) -> {
      final PublicationSummary summary = PublicationSummary.fromPublication(publication);
      if (publication.getStatus().equals(REVIEW)) {
        getDocument(summary.getDocumentUid()).ifPresent(d -> summary.setDocument(new TranslationDocumentPublishingSummary(d)));
      }
      else {
        final TranslationDocument document = new TranslationDocument();
        document.setUid(notification.getDocumentUid());
        document.setName(notification.getDocumentName());
        document.setStatisticsList(List.of(notification.getStatistics()));
        summary.setDocument(new TranslationDocumentPublishingSummary(document));
      }
      v.setResult(ResponseEntity.ok(summary));
      return null;
    });
  }

  public void notifyOwnerAboutFailureIfNecessary(final Publication publication, final DataLanguageNotification notification) {
    getRecipient(publication, notification.getOwnerCorporateId()).ifPresent(recipient -> {
      final var publicationUrl = Publication.prepareUrl(appUiUrl, uuidSerializer.convert(publication.getUid()));
      var errorList = publication.getErrors().stream().map(PublicationEvent::getDescription).collect(toList());
      crmService.newPublicationAnalysisFailure(recipient, publication.getStatus().name(), notification.getDocumentName(), publicationUrl, errorList);
      notifications.markAllSentFor(publication);
      logger.info(String.format("Notification sent to %s.", StringUtil.mask(recipient)));
    });
  }

  public void notifyOwnerAboutSuccessIfNecessary(final Publication publication, final DataLanguageNotification notification) {
    if (publication.getStatus().equals(REVIEW)) {
      getRecipient(publication, notification.getOwnerCorporateId()).ifPresent(recipient -> getDocument(publication.getDocumentUid())
        .flatMap(TranslationDocument::getCurrentStatistics)
        .ifPresent(statistics -> {
          final var publicationUrl = Publication.prepareUrl(appUiUrl, uuidSerializer.convert(publication.getUid()));
          crmService.newPublicationAnalysisSuccess(recipient,
            publication.getStatus().name(),
            notification.getDocumentName(),
            publicationUrl,
            statistics.getTotalUnits(),
            statistics.getTotalUnitsOriginal(),
            statistics.getTotalUnitsReplica(),
            statistics.getTotalUnitsLowQuality());
          notifications.markAllSentFor(publication);
          logger.info(String.format("Notification sent to %s.", StringUtil.mask(recipient)));
        }));
    }
  }

  public Optional<String> getRecipient(final Publication publication, final long ownerCorporateId) {
    return notifications.findAllById_PublicationAndId_Notification_StatusAndId_Notification_Type(publication, WAITING, EMAIL)
      .stream().map(n -> n.getId().getNotification().getRecipient()).findFirst()
      .or(() -> Optional.of(ownerCorporateId).filter(id -> id != 0).flatMap(id -> users.findByCorporateId(id).map(User::getEmail)));
  }

  private Optional<TranslationDocument> getDocument(final UUID documentUid) {
    final String documentStatus = "NEW";
    final String unitQuality = "LOW";
    final int numberOfUnits = 10;
    final boolean annotations = true;
    return dataLanguageClient.findTranslationDocumentWithUnitsByDocumentUidAndDocumentStatusAndUnitQualityAndAnnotations(
      documentUid, documentStatus, unitQuality, numberOfUnits, annotations);
  }

}
