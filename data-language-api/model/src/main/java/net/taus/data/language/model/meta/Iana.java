package net.taus.data.language.model.meta;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "iana")
public class Iana {

  @Id
  @Column(updatable = false, nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String type;
  private String subtag;
  private String description;
  private String script;
  private String scope;
  private String comments;
  private String macrolanguage;
  private String preferredValue;
  private String prefix;
  private String tag;
  private LocalDateTime added;
  private LocalDateTime deprecated;

  public Iana() {
  }
}
