package net.taus.data.language.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Language {

  Long id;
  String tag;
  String name;
  String displayName;
  String country;
  String displayCountry;
  boolean enabledPii;

  public String getNameAndCountry() {
    return Optional.ofNullable(displayCountry).filter(s -> !s.isBlank())
      .map(c -> String.format("%s (%s)", displayName, c)).orElse(String.format("%s", displayName));
  }

}
