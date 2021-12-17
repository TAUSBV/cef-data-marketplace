package net.taus.data.language.model.meta;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import static net.taus.data.language.model.meta.LanguageDataCharacteristicsDomain.Type.DEFINED;

@Entity
@Table(name = "language_data_characteristics_domains")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class LanguageDataCharacteristicsDomain {

  public enum Type {
    PREDICTED, DEFINED
  }

  @EmbeddedId
  private LanguageDataCharacteristicsDomainId id;

  @ManyToOne
  @MapsId("characteristicsId")
  private LanguageDataCharacteristics characteristics;
  @ManyToOne
  @MapsId("domainId")
  private Domain domain;

  @Enumerated(EnumType.STRING)
  @Column(length = 100)
  private Type type;
  private double probability;

  @CreatedDate
  @EqualsAndHashCode.Exclude
  private LocalDateTime createdAt;

  public LanguageDataCharacteristicsDomain() {
    this.id = new LanguageDataCharacteristicsDomainId();
    this.type = DEFINED;
    this.probability = 1.0;
  }

  public LanguageDataCharacteristicsDomain(final LanguageDataCharacteristics characteristics,
                                           final Domain domain,
                                           final LanguageDataCharacteristicsDomain.Type type,
                                           final double probability) {
    this();
    this.characteristics = characteristics;
    this.domain = domain;
    this.type = type;
    this.probability = probability;
  }

  @Override
  public boolean equals(final Object o) {
    boolean equals = false;
    if (this == o) {
      equals = true;
    }
    else if ((o instanceof LanguageDataCharacteristicsDomain)) {
      final LanguageDataCharacteristicsDomain that = (LanguageDataCharacteristicsDomain) o;
      equals = Objects.equals(this.getDomain(), that.getDomain())
        && this.getProbability() == that.getProbability()
        && Objects.equals(this.getType(), that.getType());
    }
    return equals;
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId());
  }

  @Embeddable
  @Data
  public static class LanguageDataCharacteristicsDomainId implements Serializable {
    private long characteristicsId;
    private long domainId;
  }

}
