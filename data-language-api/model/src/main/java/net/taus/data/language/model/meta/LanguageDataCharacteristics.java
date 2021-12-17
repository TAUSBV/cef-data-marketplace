package net.taus.data.language.model.meta;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static net.taus.data.language.model.meta.LanguageDataCharacteristicsDomain.Type.DEFINED;

@Entity
@Getter
@Setter
@Table(name = "language_data_characteristics")
@EntityListeners(AuditingEntityListener.class)
public class LanguageDataCharacteristics {

  @Id
  @Column(updatable = false, nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Exclude
  private Long id;

  // FIXME Move this information to Marketplace URGENTLY!!!
  @Deprecated(forRemoval = true)
  @Column(columnDefinition = "decimal(6,4) default 0")
  private double pricePerWord;

  @CreatedDate
  @EqualsAndHashCode.Exclude
  private LocalDateTime createdAt;

  @ManyToMany
  @JoinTable(name = "language_data_characteristics_content_types",
    joinColumns = @JoinColumn(name = "characteristic_id"),
    inverseJoinColumns = @JoinColumn(name = "content_type_id"))
  @ToString.Exclude
  private List<ContentType> contentTypes;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "characteristics_id")
  private Set<LanguageDataCharacteristicsDomain> domains;

  public LanguageDataCharacteristics() {
    this.domains = new HashSet<>();
    this.contentTypes = new ArrayList<>();
  }

  public LanguageDataCharacteristics(final Double pricePerWord) {
    this();
    this.pricePerWord = pricePerWord;
  }

  public LanguageDataCharacteristics(final BigDecimal pricePerWord) {
    this();
    this.pricePerWord = pricePerWord.doubleValue();
  }

  public LanguageDataCharacteristics(final Double pricePerWord,
                                     final Set<Domain> domains,
                                     final List<ContentType> contentTypes) {
    this();
    this.pricePerWord = pricePerWord;
    this.domains = domains.stream().map(d -> new LanguageDataCharacteristicsDomain(this, d, DEFINED, 1.0)).collect(Collectors.toSet());
    this.contentTypes = contentTypes;
  }

  public LanguageDataCharacteristics(final BigDecimal pricePerWord,
                                     final Set<Domain> domains,
                                     final List<ContentType> contentTypes) {
    this();
    this.pricePerWord = pricePerWord.doubleValue();
    this.domains = domains.stream().map(d -> new LanguageDataCharacteristicsDomain(this, d, DEFINED, 1.0)).collect(Collectors.toSet());
    this.contentTypes = contentTypes;
  }

  public LanguageDataCharacteristics addDomain(final Domain domain) {
    this.addDomain(domain, DEFINED, 1.0);
    return this;
  }

  public LanguageDataCharacteristics addDomains(final List<Domain> newDomains) {
    newDomains.forEach(d -> this.addDomain(d, DEFINED, 1.0));
    return this;
  }

  public LanguageDataCharacteristics addDomains(final List<Domain> newDomains,
                                                final LanguageDataCharacteristicsDomain.Type type,
                                                final double probability) {
    newDomains.forEach(d -> this.addDomain(d, type, probability));
    return this;
  }

  public LanguageDataCharacteristics addDomain(final Domain domain,
                                               final LanguageDataCharacteristicsDomain.Type type,
                                               final double probability) {
    this.domains.stream().filter(d -> d.getDomain().equals(domain)).findFirst()
      .ifPresentOrElse(d -> {
        d.setType(type);
        d.setProbability(probability);
      }, () -> {
        this.domains.add(new LanguageDataCharacteristicsDomain(this, domain, type, probability));
      });
    return this;
  }

  public LanguageDataCharacteristics addContentTypes(final List<ContentType> newContentTypes) {
    this.contentTypes.removeAll(newContentTypes);
    this.contentTypes.addAll(newContentTypes);
    return this;
  }

  public LanguageDataCharacteristics addContentType(final ContentType contentType) {
    this.contentTypes.remove(contentType);
    this.contentTypes.add(contentType);
    return this;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    final LanguageDataCharacteristics that = (LanguageDataCharacteristics) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return 0;
  }
}
