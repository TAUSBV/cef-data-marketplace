package net.taus.data.language.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "data_annotations_categories")
@EntityListeners(AuditingEntityListener.class)
public class DataAnnotationCategory {

  @Id
  @Column(updatable = false, nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @ToString.Include
  @EqualsAndHashCode.Exclude
  private Long id;
  private String code;
  private String description;
  @Enumerated(EnumType.STRING)
  private Kind kind;
  @LastModifiedDate
  @EqualsAndHashCode.Exclude
  private LocalDateTime updatedAt;
  public DataAnnotationCategory() {
  }

  public DataAnnotationCategory(final Long id) {
    this.id = id;
  }

  public enum Kind {
    PII, EMBED
  }
}
