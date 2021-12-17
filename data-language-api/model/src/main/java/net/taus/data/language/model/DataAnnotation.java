package net.taus.data.language.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "data_annotations")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class DataAnnotation {

  @Id
  @Column(updatable = false, nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Exclude
  private Long id;
  private int offsetStart;
  private int offsetEnd;

  @OneToOne
  private DataAnnotationCategory category;
  @CreatedDate
  @EqualsAndHashCode.Exclude
  private LocalDateTime createdAt;

  public DataAnnotation(final DataAnnotationCategory category, final int offsetStart, final int offsetEnd) {
    this.category = category;
    this.offsetStart = offsetStart;
    this.offsetEnd = offsetEnd;
    this.createdAt = LocalDateTime.now();
  }

  public DataAnnotation(final DataAnnotationCategory category) {
    this.category = category;
    this.createdAt = LocalDateTime.now();
  }

  public void setOffsets(final int offsetStart, final int offsetEnd) {
    this.offsetStart = offsetStart;
    this.offsetEnd = offsetEnd;
  }
}
