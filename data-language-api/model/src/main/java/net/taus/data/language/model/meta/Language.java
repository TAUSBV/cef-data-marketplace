package net.taus.data.language.model.meta;

import lombok.Data;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Locale;
import java.util.Optional;

@Entity
@Data
@Table(name = "languages")
@NamedQueries({
  @NamedQuery(
    name = Language.FIND_BY_TAG,
    query = "select lang from Language lang where lang.tag = :tag"
  )
})
public class Language implements Comparable<Language> {
  public static final String FIND_BY_TAG = "findByTag";

  @Id
  @Column(updatable = false, nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column
  private String displayName;
  @Column(length = 100)
  private String name;
  @Column(length = 100)
  private String script;
  @Column(length = 100)
  private String country;
  @Column
  private String displayCountry;
  @Column(length = 100)
  private String variant;
  private String tag; // to facilitate search
  private boolean enabledPii;

  public Language() {
  }

  public Language(final Long id, final String displayName, final String tag) {
    this.id = id;
    this.displayName = displayName;
    this.tag = tag;
  }

  public Language(final String displayName,
                  final String name,
                  final String script,
                  final String country,
                  final String displayCountry,
                  final String variant,
                  final String tag) {
    this.displayName = displayName;
    this.name = name;
    this.script = script;
    this.country = country;
    this.displayCountry = displayCountry;
    this.variant = variant;
    this.tag = tag;
  }

  public static Optional<Language> fromTag(final String tag) {
    var optional = tryGetLocaleFromTag(tag);
    return optional.map(l -> new Language(l.getDisplayName(), l.getLanguage(), l.getScript(), l.getCountry(), l.getDisplayCountry(), l.getVariant(), tag));
  }

  private static Optional<Locale> tryGetLocaleFromTag(final String tag) {
    Optional<Locale> optional;
    try {
      optional = Optional.of(Locale.forLanguageTag(tag));
    }
    catch (Exception e) {
      try {
        optional = Optional.of(new Locale.Builder().setLanguageTag(tag).build());
      }
      catch (Exception exception) {
        optional = Optional.empty();
      }
    }
    return optional;
  }

  @Override
  public int compareTo(@NonNull Language o) {
    return this.getTag().compareTo(o.getTag());
  }

}