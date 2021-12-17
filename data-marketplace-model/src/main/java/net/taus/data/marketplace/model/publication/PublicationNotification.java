package net.taus.data.marketplace.model.publication;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.taus.data.marketplace.model.notification.Notification;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "publication_notification")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PublicationNotification {

  @EmbeddedId
  private PublicationNotificationId id;

  public PublicationNotification() {
  }

  public PublicationNotification(Publication publication, Notification notification) {
    this.id = new PublicationNotificationId(publication, notification);
  }

  @Data
  @EqualsAndHashCode(onlyExplicitlyIncluded = true)
  @Embeddable
  public static class PublicationNotificationId implements Serializable {
    @ManyToOne
    private Publication publication;

    @ManyToOne(cascade = CascadeType.ALL)
    private Notification notification;

    public PublicationNotificationId() {
    }

    public PublicationNotificationId(final Publication publication, final Notification notification) {
      this.publication = publication;
      this.notification = notification;
    }
  }
}