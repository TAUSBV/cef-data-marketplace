package net.taus.data.marketplace.api.publication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static net.taus.data.marketplace.api.publication.DataLanguageNotification.Status.FINISHED;
import static net.taus.data.marketplace.api.publication.DataLanguageNotification.Type.INFO;
import static net.taus.data.marketplace.model.notification.Notification.NotificationStatus.NOTIFIED;
import static net.taus.data.marketplace.model.notification.Notification.NotificationType.EMAIL;
import static net.taus.data.marketplace.model.publication.Publication.Status.ANALYZING;
import static net.taus.data.marketplace.model.publication.Publication.Status.REVIEW;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@Transactional
class DataLanguageNotificationListenerTest extends IntegrationTest {

  String ownerEmail;
  Publication publication;
  Notification notification;
  PublicationNotification publicationNotification;
  DataLanguageNotification documentNotification;
  User user;
  private TranslationDocumentStatistics statistics;

  @Autowired
  private List<DataLanguageComponent> components;
  @Autowired
  private DataLanguageNotificationListener listener;
  @Autowired
  private TestEntityManager em;
  @Autowired
  @Qualifier("internalMapper")
  private ObjectMapper mapper;

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
    publication.moveToStatus(ANALYZING);
    documentNotification = new DataLanguageNotification();
    statistics = new TranslationDocumentStatistics(10L, 10L, 10L, 10L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L);
    user = em.persistAndFlush(new User(999L, "Mr", new UserDetail(ownerEmail, "John", "Appleseed", "Netherlands")));
  }

  @Test
  public void Given_a_null_notification_When_received_Then_an_exception_should_be_thrown() {
    assertThrows(IllegalArgumentException.class, () -> listener.handle(null));
  }

  @Test
  public void Given_document_analysis_finished_with_business_error_When_marketplace_is_notified_Then_should_mark_publication_as_error() throws JsonProcessingException {
    documentNotification.setType(DataLanguageNotification.Type.ERROR);
    documentNotification.setKind(DataLanguageNotification.ErrorKind.BUSINESS);
    documentNotification.setStatus(FINISHED);
    documentNotification.setComponent("PREPARATION");
    documentNotification.setDocumentUid(publication.getDocumentUid());
    final String message = "No donuts for your";
    documentNotification.setMessage(message);

    final CorporateUser user = new CorporateUser();
    user.setId(1L);
    when(corporateUsers.findById(anyLong())).thenReturn(Optional.of(user));
    listener.handle(mapper.writeValueAsString(documentNotification));

    assertTrue(publication.hasErrors());
    assertEquals(message, publication.getErrors().get(0).getDescription());
    assertEquals(ANALYZING, publication.getStatus());
  }

  @Test
  public void Given_document_analysis_finished_with_system_error_When_marketplace_is_notified_Then_should_mark_publication_as_error_with_a_generic_message() throws JsonProcessingException {
    documentNotification.setType(DataLanguageNotification.Type.ERROR);
    documentNotification.setKind(DataLanguageNotification.ErrorKind.SYSTEM);
    documentNotification.setStatus(FINISHED);
    documentNotification.setComponent("PREPARATION");
    documentNotification.setDocumentUid(publication.getDocumentUid());
    final String message = SystemException.GENERIC_MESSAGE;
    documentNotification.setMessage(message);

    final CorporateUser user = new CorporateUser();
    user.setId(1L);
    when(corporateUsers.findById(anyLong())).thenReturn(Optional.of(user));
    listener.handle(mapper.writeValueAsString(documentNotification));

    assertTrue(publication.hasErrors());
    assertEquals(message, publication.getErrors().get(0).getDescription());
    assertEquals(ANALYZING, publication.getStatus());
  }

  @Test
  public void Given_document_analysis_finished_with_error_When_marketplace_is_notified_And_owner_asked_to_be_notified_Then_should_mark_publication_as_error_and_notify_owner() throws JsonProcessingException {
    documentNotification.setType(DataLanguageNotification.Type.ERROR);
    documentNotification.setKind(DataLanguageNotification.ErrorKind.SYSTEM);
    documentNotification.setStatus(FINISHED);
    documentNotification.setComponent("PREPARATION");
    documentNotification.setDocumentUid(publication.getDocumentUid());
    final String message = SystemException.GENERIC_MESSAGE;
    documentNotification.setMessage(message);
    notification = em.persistAndFlush(new Notification(EMAIL, ownerEmail));
    publicationNotification = em.persistAndFlush(new PublicationNotification(publication, notification));

    listener.handle(mapper.writeValueAsString(documentNotification));

    assertTrue(publication.hasErrors());
    assertEquals(message, publication.getErrors().get(0).getDescription());
    assertEquals(ANALYZING, publication.getStatus());
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
  public void Given_document_analysis_finished_with_error_When_marketplace_is_notified_And_document_has_owner_Then_should_mark_publication_as_error_and_notify_owner() throws JsonProcessingException {
    documentNotification.setType(DataLanguageNotification.Type.ERROR);
    documentNotification.setKind(DataLanguageNotification.ErrorKind.SYSTEM);
    documentNotification.setStatus(FINISHED);
    documentNotification.setComponent("PREPARATION");
    documentNotification.setDocumentUid(publication.getDocumentUid());
    documentNotification.setOwnerCorporateId(user.getCorporateId());
    documentNotification.setDocumentName("test.tmx");
    final String message = SystemException.GENERIC_MESSAGE;
    documentNotification.setMessage(message);

    listener.handle(mapper.writeValueAsString(documentNotification));

    assertTrue(publication.hasErrors());
    assertEquals(message, publication.getErrors().get(0).getDescription());
    assertEquals(ANALYZING, publication.getStatus());
    verify(crmService, times(1)).newPublicationAnalysisFailure(
      eq(ownerEmail),
      eq(publication.getStatus().name()),
      eq(documentNotification.getDocumentName()),
      anyString(),
      eq(publication.getErrors().stream().map(PublicationEvent::getDescription).collect(Collectors.toList()))
    );
  }

  @Test
  public void Given_document_analysis_finished_with_success_When_marketplace_is_notified_Then_should_move_publication_status() {
    final CorporateUser corporateUser = new CorporateUser();
    corporateUser.setId(1L);
    when(corporateUsers.findById(anyLong())).thenReturn(Optional.of(corporateUser));
    components.stream().filter(DataLanguageComponent::isRequired).forEach(c -> {
      documentNotification.setType(INFO);
      documentNotification.setStatus(FINISHED);
      documentNotification.setComponent(c.getName());
      documentNotification.setDocumentUid(publication.getDocumentUid());
      documentNotification.setOwnerCorporateId(user.getCorporateId());
      documentNotification.setDocumentName("test.tmx");
      documentNotification.setCreatedAt(LocalDateTime.now());
      documentNotification.setStatistics(statistics);
      try {
        listener.handle(mapper.writeValueAsString(documentNotification));
      }
      catch (JsonProcessingException e) {
        e.printStackTrace();
      }
    });

    assertFalse(publication.hasErrors());
    assertEquals(REVIEW, publication.getStatus());
  }

  @Test
  public void Given_document_analysis_finished_with_success_When_marketplace_is_notified_And_owner_asked_to_be_notified_Then_should_move_publication_status_And_notify_owner() {
    notification = em.persistAndFlush(new Notification(EMAIL, ownerEmail));
    publicationNotification = em.persistAndFlush(new PublicationNotification(publication, notification));

    var document = new TranslationDocument();
    document.setStatisticsList(List.of(this.statistics));
    when(dataLanguageClient.findTranslationDocumentWithUnitsByDocumentUidAndDocumentStatusAndUnitQualityAndAnnotations(any(), anyString(), anyString(), anyInt(), anyBoolean())).thenReturn(Optional.of(document));

    components.stream().filter(DataLanguageComponent::isRequired).forEach(c -> {
      documentNotification.setType(INFO);
      documentNotification.setStatus(FINISHED);
      documentNotification.setComponent(c.getName());
      documentNotification.setDocumentUid(publication.getDocumentUid());
      documentNotification.setOwnerCorporateId(user.getCorporateId());
      documentNotification.setDocumentName("test.tmx");
      documentNotification.setCreatedAt(LocalDateTime.now());
      documentNotification.setStatistics(statistics);
      try {
        listener.handle(mapper.writeValueAsString(documentNotification));
      }
      catch (JsonProcessingException e) {
        e.printStackTrace();
      }
    });

    assertFalse(publication.hasErrors());
    assertEquals(REVIEW, publication.getStatus());
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
  public void Given_document_analysis_finished_with_success_When_marketplace_is_notified_And_document_has_owner_Then_should_move_publication_status_And_notify_owner() {
    var document = new TranslationDocument();
    document.setStatisticsList(List.of(this.statistics));
    when(dataLanguageClient.findTranslationDocumentWithUnitsByDocumentUidAndDocumentStatusAndUnitQualityAndAnnotations(any(), anyString(), anyString(), anyInt(), anyBoolean())).thenReturn(Optional.of(document));

    components.stream().filter(DataLanguageComponent::isRequired).forEach(c -> {
      documentNotification.setType(INFO);
      documentNotification.setStatus(FINISHED);
      documentNotification.setComponent(c.getName());
      documentNotification.setDocumentUid(publication.getDocumentUid());
      documentNotification.setOwnerCorporateId(user.getCorporateId());
      documentNotification.setDocumentName("test.tmx");
      documentNotification.setCreatedAt(LocalDateTime.now());
      documentNotification.setStatistics(statistics);
      try {
        listener.handle(mapper.writeValueAsString(documentNotification));
      }
      catch (JsonProcessingException e) {
        e.printStackTrace();
      }
    });

    assertFalse(publication.hasErrors());
    assertEquals(REVIEW, publication.getStatus());
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

}