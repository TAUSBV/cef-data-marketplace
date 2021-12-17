package net.taus.data.marketplace.model.notification;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class NotificationMetadata {

  @Id
  @Column(updatable = false, nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String value;

  public NotificationMetadata() {}

  public NotificationMetadata(final String name, final String value) {
    this.name = name;
    this.value = value;
  }
}