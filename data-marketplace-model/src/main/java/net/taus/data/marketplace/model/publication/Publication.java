package net.taus.data.marketplace.model.publication;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.taus.data.marketplace.model.publication.PublicationEvent.ComponentStatus;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static javax.persistence.CascadeType.ALL;
import static net.taus.data.marketplace.model.publication.PublicationEvent.ComponentStatus.FINISHED;
import static net.taus.data.marketplace.model.publication.PublicationEvent.ComponentStatus.PROGRESS;
import static net.taus.data.marketplace.model.publication.PublicationEvent.Type.ERROR;
import static net.taus.data.marketplace.model.publication.PublicationEvent.Type.INFO;

@Entity
@Table(name = "publication")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@EntityListeners(AuditingEntityListener.class)
public class Publication {

  @Id
  @Column(updatable = false, nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(columnDefinition = "BINARY(16)")
  @EqualsAndHashCode.Include
  private UUID uid;

  private UUID documentUid;

  @OneToMany(cascade = ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  @JoinColumn(name = "publication_id")
  @OrderBy("createdAt")
  private List<PublicationEvent> events;

  @EqualsAndHashCode.Include
  @CreatedDate
  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @LastModifiedDate
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @Enumerated(EnumType.STRING)
  @Column(length = 20)
  private Status status;

  public Publication() {
    this.status = Status.RECEIVED;
    this.events = new ArrayList<>();
    this.uid = UUID.randomUUID();
    this.addEvent(INFO, "Publication created");
  }

  public Publication(final UUID documentUid) {
    this();
    this.documentUid = documentUid;
  }

  public static String prepareUrl(final String appUiUrl, final String uid) {
    return String.format("%s/publications/%s", appUiUrl, uid);
  }

  public Publication moveToStatus(Status status) {
    while (!this.getStatus().equals(Status.PUBLISHED) && this.getStatus() != status) {
      this.nextStatus();
    }
    return this;
  }

  public Status nextStatus() {
    var current = status;
    status = this.status.next();
    final var description = String.format("Transitioned from %s to %s", current, status);
    this.addEvent(PublicationEvent.Type.INFO, description);
    return status;
  }

  public Status nextStatus(ComponentStatus componentStatus) {
    var current = status;
    status = this.status.next();
    final var description = String.format("Transitioned from %s to %s", current, status);
    this.addEvent("MARKETPLACE", componentStatus, PublicationEvent.Type.INFO, description);
    return status;
  }

  public void addEvent(final PublicationEvent.Type type, final String description) {
    addEvent("MARKETPLACE", PROGRESS, type, description);
  }

  public void addEvent(final String component, ComponentStatus componentStatus, final PublicationEvent.Type type, final String description) {
    final var event = new PublicationEvent(component, componentStatus, this.status, type, description);
    this.getEvents().add(event);
    this.updatedAt = LocalDateTime.now();
  }

  public boolean hasAllRequiredAnalysisComponentsFinished(final List<String> required) {
    final List<String> finished = this.getEvents().stream()
      .filter(e -> e.getComponentStatus().equals(FINISHED))
      .map(PublicationEvent::getComponent).distinct()
      .collect(toList());
    return finished.containsAll(required);
  }

  public List<PublicationEvent> getErrors() {
    return this.getEvents().stream()
      .filter(e -> e.getType().equals(ERROR))
      .sorted(comparing(PublicationEvent::getCreatedAt))
      .collect(toList());
  }

  public boolean hasErrors() {
    return !this.getErrors().isEmpty();
  }

  public List<PublicationEvent> getEventsSortedAsc() {
    return getEvents().stream().sorted(comparing(PublicationEvent::getCreatedAt)).collect(toList());
  }

  public String toString() {
    return String.format("Publication(uid: %s - state: %s - document: %s - last_update= %s - has_errors: %s)",
      this.uid, this.status, this.documentUid, this.updatedAt, this.hasErrors());
  }

  public enum Status {
    RECEIVED {
      @Override
      public Status next() {
        return ANALYZING;
      }

      public Status previous() {
        return this;
      }
    },
    ANALYZING {
      @Override
      public Status next() {
        return REVIEW;
      }

      public Status previous() {
        return RECEIVED;
      }
    },
    REVIEW {
      @Override
      public Status next() {
        return PUBLISHING;
      }

      public Status previous() {
        return ANALYZING;
      }
    },
    PUBLISHING {
      @Override
      public Status next() {
        return PUBLISHED;
      }

      public Status previous() {
        return REVIEW;
      }
    },
    PUBLISHED {
      @Override
      public Status next() {
        return this;
      }

      public Status previous() {
        return PUBLISHING;
      }
    };

    public abstract Status next();

    public abstract Status previous();
  }

}
