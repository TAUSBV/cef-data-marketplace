package net.taus.data.marketplace.model.notification;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Data
@Table(name = "notification")
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@EntityListeners(AuditingEntityListener.class)
public class Notification {

  @Id
  @Column(updatable = false, nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "recipient")
  private String recipient;

  @OneToMany(fetch=FetchType.LAZY, cascade = ALL)
  @JoinColumn(name = "notification_id", referencedColumnName = "id")
  private List<NotificationMetadata> metadataList;

  @Enumerated(EnumType.STRING)
  @Column(length = 20)
  private NotificationType type;

  @Enumerated(EnumType.STRING)
  @Column(length = 20)
  private NotificationStatus status;

  @CreatedDate
  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @LastModifiedDate
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  public Notification() {
    this.status = NotificationStatus.WAITING;
    this.metadataList = new ArrayList<>();
  }

  public Notification(NotificationType type, String recipient) {
    this();
    this.type = type;
    this.recipient = recipient;
  }

  public NotificationStatus nextStatus() {
    status = this.status.next();
    return status;
  }

  public void addMetaData(final String name, final String value) {
    final var metadata = new NotificationMetadata(name, value);
    this.getMetadataList().add(metadata);
    this.updatedAt = LocalDateTime.now();
  }

  public enum NotificationStatus {
    WAITING {
      @Override
      public NotificationStatus next() {
        return NOTIFIED;
      }
    },
    NOTIFIED {
      @Override
      public NotificationStatus next() {
        return READ;
      }
    },
    READ {
      @Override
      public NotificationStatus next() {
        return this;
      }
    };

    public abstract NotificationStatus next();
  }

  public enum NotificationType {
    EMAIL
  }
}