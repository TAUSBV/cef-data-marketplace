package net.taus.data.language.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "translation_metadata")
@Data
public class TranslationMetadata {

  @Id
  @Column(updatable = false, nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(length = 100)
  private String name;
  @Column(length = 300, columnDefinition = "text")
  private String value;

  public TranslationMetadata() {
  }

  public TranslationMetadata(final String name, final String value) {
    this.name = name;
    this.value = value;
  }
}
