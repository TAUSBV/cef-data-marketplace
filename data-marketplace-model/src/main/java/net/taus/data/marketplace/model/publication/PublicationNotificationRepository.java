package net.taus.data.marketplace.model.publication;

import net.taus.data.marketplace.model.notification.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import static net.taus.data.marketplace.model.notification.Notification.NotificationStatus.WAITING;
import static net.taus.data.marketplace.model.notification.Notification.NotificationType.EMAIL;

@Repository
public interface PublicationNotificationRepository extends JpaRepository<PublicationNotification, Long> {

  List<PublicationNotification> findAllById_Publication(final Publication publication);
  List<PublicationNotification> findAllById_PublicationAndId_Notification_StatusAndId_Notification_Type(final Publication publication,
                                                                                                        final Notification.NotificationStatus status,
                                                                                                        final Notification.NotificationType type);

  default void markAllSentFor(Publication publication) {
    this.findAllById_PublicationAndId_Notification_StatusAndId_Notification_Type(publication, WAITING, EMAIL).forEach(pn ->{
      pn.getId().getNotification().nextStatus();
      save(pn);
    });
  }
}