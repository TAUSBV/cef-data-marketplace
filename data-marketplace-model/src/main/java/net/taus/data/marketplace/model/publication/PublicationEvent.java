package net.taus.data.marketplace.model.publication;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "publication_event")
@EntityListeners(AuditingEntityListener.class)
public class PublicationEvent {

  @Id
  @Column(updatable = false, nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(columnDefinition = "BINARY(16)")
  @EqualsAndHashCode.Include
  private UUID uid;

  @Column(length = 50)
  private String component;

  @Column(length = 50)
  @Enumerated(EnumType.STRING)
  private ComponentStatus componentStatus;

  @Enumerated(EnumType.STRING)
  @Column(length = 20)
  private Publication.Status status;

  @Enumerated(EnumType.STRING)
  @Column(length = 20)
  private Type type;

  @Column(length = 1000)
  private String description;

  @CreatedDate
  @Column(name = "created_at")
  private LocalDateTime createdAt;

  public PublicationEvent() {
    this.uid = UUID.randomUUID();
  }

  public PublicationEvent(final String component,
                          final ComponentStatus componentStatus,
                          final Publication.Status status,
                          final Type type,
                          final String description) {
    this();
    this.component = component;
    this.componentStatus = componentStatus;
    this.status = status;
    this.type = type;
    this.description = description;
    this.createdAt = LocalDateTime.now();
  }

  public enum Type {
    UNKNOWN, ERROR, INFO, WARNING
  }

  public enum ComponentStatus {
    PROGRESS, FINISHED
  }

}
