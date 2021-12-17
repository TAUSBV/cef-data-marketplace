package net.taus.data.language.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompositeLanguageSourceTargetCount {

  Collection<LanguageSourceTargetCount> sources;
  Collection<LanguageSourceTargetCount> targets;

}
