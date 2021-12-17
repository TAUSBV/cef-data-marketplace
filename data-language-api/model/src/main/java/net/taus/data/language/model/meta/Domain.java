package net.taus.data.language.model.meta;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.taus.data.language.model.TranslationMetadataOrigin;

import javax.persistence.*;

@Entity
@Data
@Table(name = "domains")
public class Domain {

  @Id
  @Column(updatable = false, nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Exclude
  private Long id;
  private String description;
  @Enumerated(EnumType.STRING)
  @Column(length = 20)
  private TranslationMetadataOrigin origin;

  public Domain() {
    this.origin = TranslationMetadataOrigin.MARKETPLACE;
  }

  public Domain(final String description) {
    this();
    this.description = description;
  }

  public Domain(final Long id, final String description) {
    this(description);
    this.id = id;
  }
}
