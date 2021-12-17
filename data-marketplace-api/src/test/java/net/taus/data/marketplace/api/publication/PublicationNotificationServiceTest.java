package net.taus.data.marketplace.api.publication;

import net.taus.corporate.user.client.CorporateUserService;
import net.taus.corporate.user.models.CorporateUser;
import net.taus.data.language.api.DataLanguageClient;
import net.taus.data.language.api.model.TranslationDocument;
import net.taus.data.language.api.model.TranslationDocumentStatistics;
import net.taus.data.marketplace.api.IntegrationTest;
import net.taus.data.marketplace.api.crm.CRMService;
import net.taus.data.marketplace.model.SystemException;
import net.taus.data.marketplace.model.notification.Notification;
import net.taus.data.marketplace.model.publication.Publication;
import net.taus.data.marketplace.model.publication.PublicationEvent;
import net.taus.data.marketplace.model.publication.PublicationNotification;
import net.taus.data.marketplace.model.user.User;
import net.taus.data.marketplace.model.user.UserDetail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static net.taus.data.marketplace.api.publication.DataLanguageNotification.ErrorKind.SYSTEM;
import static net.taus.data.marketplace.api.publication.DataLanguageNotification.Status.FINISHED;
import static net.taus.data.marketplace.api.publication.DataLanguageNotification.Type.ERROR;
import static net.taus.data.marketplace.api.publication.DataLanguageNotification.Type.INFO;
import static net.taus.data.marketplace.model.notification.Notification.NotificationStatus.NOTIFIED;
import static net.taus.data.marketplace.model.notification.Notification.NotificationType.EMAIL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@Transactional
class PublicationNotificationServiceTest extends IntegrationTest {

  String ownerEmail;
  Publication publication;
  Notification notification;
  PublicationNotification publicationNotification;
  DataLanguageNotification documentNotification;
  User user;
  private TranslationDocumentStatistics statistics;

  @Autowired
  private TestEntityManager em;
  @Autowired
  private PublicationNotificationService service;
  @MockBean
  private CRMService crmService;
  @MockBean
  private CorporateUserService corporateUsers;
  @MockBean
  private DataLanguageClient dataLanguageClient;

  @BeforeEach
  public void setUp() {
    ownerEmail = "john.appleseed@taus.net";
    publication = em.persistAndFlush(new Publication(UUID.randomUUID()));
    documentNotification = new DataLanguageNotification();
    statistics = new TranslationDocumentStatistics(10L, 10L, 10L, 10L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L);
    user = em.persistAndFlush(new User(999L, "Mr", new UserDetail(ownerEmail, "John", "Appleseed", "Netherlands")));
  }

  @Test
  void Given_publication_finished_with_error_And_with_owner_notification_request_When_asked_to_notify_the_owner_Then_the_owner_should_be_notified_about_the_error() {
    documentNotification.setType(ERROR);
    documentNotification.setKind(SYSTEM);
    documentNotification.setDocumentUid(publication.getDocumentUid());
    documentNotification.setMessage(SystemException.GENERIC_MESSAGE);
    publication.addEvent(PublicationEvent.Type.ERROR, SystemException.GENERIC_MESSAGE);
    notification = em.persistAndFlush(new Notification(EMAIL, ownerEmail));
    publicationNotification = em.persistAndFlush(new PublicationNotification(publication, notification));

    service.notifyOwnerAboutFailureIfNecessary(publication, documentNotification);

    verify(crmService, times(1)).newPublicationAnalysisFailure(
      eq(ownerEmail),
      eq(publication.getStatus().name()),
      eq(documentNotification.getDocumentName()),
      anyString(),
      eq(publication.getErrors().stream().map(PublicationEvent::getDescription).collect(Collectors.toList()))
    );
    assertEquals(NOTIFIED, publicationNotification.getId().getNotification().getStatus());
  }

  @Test
  public void Given_publication_finished_with_error_And_document_has_owner_When_asked_to_notify_the_owner_Then_the_owner_should_be_notified_about_the_error() {
    documentNotification.setType(ERROR);
    documentNotification.setKind(SYSTEM);
    documentNotification.setDocumentUid(publication.getDocumentUid());
    documentNotification.setOwnerCorporateId(user.getCorporateId());
    documentNotification.setDocumentName("test.tmx");
    documentNotification.setMessage(SystemException.GENERIC_MESSAGE);
    publication.addEvent(PublicationEvent.Type.ERROR, SystemException.GENERIC_MESSAGE);

    service.notifyOwnerAboutFailureIfNecessary(publication, documentNotification);

    verify(crmService, times(1)).newPublicationAnalysisFailure(
      eq(ownerEmail),
      eq(publication.getStatus().name()),
      eq(documentNotification.getDocumentName()),
      anyString(),
      eq(publication.getErrors().stream().map(PublicationEvent::getDescription).collect(Collectors.toList()))
    );
  }

  @Test
  public void Given_publication_finished_with_success_And_with_owner_notification_request_When_asked_to_notify_the_owner_Then_the_owner_should_be_notified_about_the_analysis_result() {
    documentNotification.setType(INFO);
    documentNotification.setStatus(FINISHED);
    documentNotification.setDocumentUid(publication.getDocumentUid());
    documentNotification.setDocumentName("test.tmx");
    documentNotification.setCreatedAt(LocalDateTime.now());
    documentNotification.setStatistics(statistics);
    notification = em.persistAndFlush(new Notification(EMAIL, ownerEmail));
    publicationNotification = em.persistAndFlush(new PublicationNotification(publication, notification));
    publication.moveToStatus(Publication.Status.REVIEW);

    var document = new TranslationDocument();
    document.setStatisticsList(List.of(this.statistics));
    when(dataLanguageClient.findTranslationDocumentWithUnitsByDocumentUidAndDocumentStatusAndUnitQualityAndAnnotations(any(), anyString(), anyString(), anyInt(), anyBoolean())).thenReturn(Optional.of(document));

    service.notifyOwnerAboutSuccessIfNecessary(publication, documentNotification);

    verify(crmService, times(1)).newPublicationAnalysisSuccess(
      eq(ownerEmail),
      eq(publication.getStatus().name()),
      eq(documentNotification.getDocumentName()),
      anyString(),
      eq(documentNotification.getStatistics().getTotalUnits()),
      eq(documentNotification.getStatistics().getTotalUnitsOriginal()),
      eq(documentNotification.getStatistics().getTotalUnitsReplica()),
      eq(documentNotification.getStatistics().getTotalUnitsLowQuality())
    );
    assertEquals(NOTIFIED, publicationNotification.getId().getNotification().getStatus());
  }

  @Test
  public void Given_publication_finished_with_success_And_document_has_owner_When_asked_to_notify_the_owner_Then_the_owner_should_be_notified_about_the_analysis_result() {
    documentNotification.setType(INFO);
    documentNotification.setStatus(FINISHED);
    documentNotification.setDocumentUid(publication.getDocumentUid());
    documentNotification.setOwnerCorporateId(user.getCorporateId());
    documentNotification.setDocumentName("test.tmx");
    documentNotification.setCreatedAt(LocalDateTime.now());
    documentNotification.setStatistics(statistics);
    publication.moveToStatus(Publication.Status.REVIEW);
    var document = new TranslationDocument();
    document.setStatisticsList(List.of(this.statistics));
    when(dataLanguageClient.findTranslationDocumentWithUnitsByDocumentUidAndDocumentStatusAndUnitQualityAndAnnotations(any(), anyString(), anyString(), anyInt(), anyBoolean())).thenReturn(Optional.of(document));

    service.notifyOwnerAboutSuccessIfNecessary(publication, documentNotification);

    verify(crmService, times(1)).newPublicationAnalysisSuccess(
      eq(ownerEmail),
      eq(publication.getStatus().name()),
      eq(documentNotification.getDocumentName()),
      anyString(),
      eq(documentNotification.getStatistics().getTotalUnits()),
      eq(documentNotification.getStatistics().getTotalUnitsOriginal()),
      eq(documentNotification.getStatistics().getTotalUnitsReplica()),
      eq(documentNotification.getStatistics().getTotalUnitsLowQuality())
    );
  }

  @Test
  void Given_a_publication_that_has_a_notification_When_publication_asks_for_the_recipient_to_notify_Then_then_recipient_should_be_found() {
    notification = em.persistAndFlush(new Notification(EMAIL, ownerEmail));
    publicationNotification = em.persistAndFlush(new PublicationNotification(publication, notification));
    final Optional<String> recipient = service.getRecipient(publication, 0);
    assertFalse(recipient.isEmpty());
    recipient.ifPresent(r -> assertEquals(ownerEmail, r));
  }

  @Test
  void Given_an_owner_that_has_a_publication_When_publication_asks_for_the_recipient_to_notify_Then_then_owner_email_should_be_returned() {
    final Optional<String> recipient = service.getRecipient(publication, user.getCorporateId());
    assertFalse(recipient.isEmpty());
    recipient.ifPresent(r -> assertEquals(ownerEmail, r));
  }

  @Test
  void Given_a_publication_that_has_not_a_notification_nor_document_owner_When_asked_to_notify_the_owner_Then_no_notification_should_be_sent() {
    documentNotification.setType(INFO);
    documentNotification.setStatus(FINISHED);
    documentNotification.setDocumentUid(publication.getDocumentUid());
    documentNotification.setDocumentName("test.tmx");
    documentNotification.setCreatedAt(LocalDateTime.now());
    documentNotification.setStatistics(statistics);

    final CorporateUser user = new CorporateUser();
    user.setId(1L);
    when(corporateUsers.findById(anyLong())).thenReturn(Optional.of(user));
    service.notifyOwnerAboutSuccessIfNecessary(publication, documentNotification);

    verify(crmService, times(0)).newPublicationAnalysisSuccess(
      anyString(),
      anyString(),
      anyString(),
      anyString(),
      anyLong(),
      anyLong(),
      anyLong(),
      anyLong()
    );
  }
}