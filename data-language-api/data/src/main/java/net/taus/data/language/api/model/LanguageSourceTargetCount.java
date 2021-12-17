package net.taus.data.language.api.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LanguageSourceTargetCount {

  public static final Comparator<LanguageCount> LANGUAGE_DISPLAY_NAME = (o1, o2) -> o1.getLanguage().getName().compareToIgnoreCase(o2.getLanguage().getName());

  Language source;
  List<LanguageCount> targets;

  public List<LanguageCount> getTargets() {
    return targets.stream().sorted(LANGUAGE_DISPLAY_NAME).collect(toList());
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class LanguageCount {
    Language language;
    long count;
  }

}
