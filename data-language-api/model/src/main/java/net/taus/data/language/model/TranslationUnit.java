package net.taus.data.language.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import net.taus.data.language.model.meta.LanguageDataCharacteristics;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.LAZY;

@Data
@Entity
@Table(name = "translation_units")
@EntityListeners(AuditingEntityListener.class)
public class TranslationUnit {

  public enum Quality {
    HIGH, LOW
  }

  @Id
  @Column(updatable = false, nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @EqualsAndHashCode.Include
  @ToString.Include
  @Column(columnDefinition = "BINARY(16)")
  private UUID uid;

  @Column(name = "is_virtual", nullable = false, columnDefinition = "bit default 0")
  private boolean virtual;

  @Column(nullable = false)
  private long hash;

  @Column(nullable = false, length = 25)
  @Enumerated(EnumType.STRING)
  private Quality quality;

  @Column(nullable = false, length = 1000)
  private String qualityFlags;

  @Column(nullable = false, length = 25)
  @Enumerated(EnumType.STRING)
  private TranslationDocument.Status status;

  @Column(columnDefinition = "int default 1")
  private Long sequence;

  @CreatedDate
  @Column(nullable = false)
  private LocalDateTime createdAt;

  @LastModifiedDate
  private LocalDateTime updatedAt;

  @ManyToOne(cascade = { MERGE, DETACH, REFRESH }, optional = false)
  private TranslationVariant source;

  @ManyToOne(cascade = { MERGE, DETACH, REFRESH }, optional = false)
  private TranslationVariant target;

  @OneToOne(cascade = { PERSIST, MERGE, DETACH, REFRESH }, fetch = LAZY)
  private TranslationUnit reference;

  @ManyToOne(optional = false)
  private TranslationDocument document;

  @OneToMany(cascade = ALL, orphanRemoval = true)
  @JoinTable(name = "translation_units_characteristics",
    joinColumns = @JoinColumn(name = "unit_id"),
    inverseJoinColumns = @JoinColumn(name = "characteristic_id"))
  @EqualsAndHashCode.Include
  private List<LanguageDataCharacteristics> characteristics;

  @EqualsAndHashCode.Include
  private Long ownerId;

  @EqualsAndHashCode.Include
  private UUID ownerUid;

  //  @OneToMany(cascade = REMOVE)
//  @JoinTable
//  @ToString.Exclude
  @Transient
  private List<TranslationMetadata> metadata;

  public TranslationUnit() {
    this.uid = UUID.randomUUID();
    this.status = TranslationDocument.Status.NEW;
    this.quality = Quality.HIGH;
    this.qualityFlags = "OK";
    this.metadata = new ArrayList<>();
    this.ownerUid = UUID.randomUUID();
    this.characteristics = new ArrayList<>();
    this.createdAt = LocalDateTime.now();
  }

  public TranslationUnit(TranslationDocument document, TranslationVariant source, TranslationVariant target) {
    this();
    this.document = document;
    this.source = source;
    this.target = target;
  }

  public void addMetadata(final String name, final String value) {
    this.getMetadata().add(new TranslationMetadata(name, value));
  }

  public boolean isLowQuality() {
    return Quality.LOW.equals(this.getQuality());
  }

  public boolean isHighQuality() {
    return Quality.HIGH.equals(this.getQuality());
  }

  public boolean isReplica() {
    final var reference = this.getReference();
    return Objects.nonNull(reference) && reference.isHighQuality() && this.isHighQuality();
  }

  public boolean isOriginal() {
    return Objects.isNull(this.getReference()) && this.isHighQuality();
  }

  public Optional<LanguageDataCharacteristics> getCurrentCharacteristics() {
    return this.characteristics.stream().max(Comparator.comparing(LanguageDataCharacteristics::getCreatedAt));
  }

  public LanguageDataCharacteristics getCurrentOrCreateCharacteristics() {
    return this.getCurrentCharacteristics().orElseGet(() -> {
      final LanguageDataCharacteristics c = new LanguageDataCharacteristics();
      this.addCharacteristics(c);
      return c;
    });
  }

  public TranslationUnit addCharacteristics(final LanguageDataCharacteristics characteristics) {
    this.getCharacteristics().add(characteristics);
    return this;
  }


}
