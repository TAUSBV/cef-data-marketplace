package net.taus.data.language.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import net.taus.data.language.model.meta.Language;
import net.taus.data.language.model.meta.LanguageDataCharacteristicsDomain;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "translation_variants", uniqueConstraints = @UniqueConstraint(columnNames = { "language_id", "hash" }))
@EntityListeners(AuditingEntityListener.class)
public class TranslationVariant {

  @Id
  @Column(updatable = false, nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @EqualsAndHashCode.Include
  @ToString.Include
  @Column(columnDefinition = "BINARY(16)")
  private UUID uid;
  @Column(columnDefinition = "text")
  private String value;
  private long wordCount;
  private long hash;

  @CreatedDate
  private LocalDateTime createdAt;
  @LastModifiedDate
  private LocalDateTime updatedAt;

  @ManyToOne
  private Language language;

  @ManyToMany(cascade = CascadeType.ALL)
  private Set<DataAnnotation> annotations;

  public TranslationVariant() {
    this.uid = UUID.randomUUID();
    this.annotations = new HashSet<>();
  }

  public TranslationVariant(final Long id) {
    this();
    this.id = id;
  }

  public void addAnnotation(final DataAnnotationCategory category, final int offsetStart, final int offsetEnd) {
    this.annotations.stream().filter(d -> d.getCategory().equals(category)).findFirst().ifPresentOrElse(
      d -> d.setOffsets(offsetStart, offsetEnd),
      () -> this.annotations.add(new DataAnnotation(category, offsetStart, offsetEnd))
    );
  }
}
