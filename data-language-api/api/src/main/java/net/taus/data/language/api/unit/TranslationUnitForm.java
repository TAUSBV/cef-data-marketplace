package net.taus.data.language.api.unit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.taus.data.language.model.meta.LanguageDataCharacteristicsDomain;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TranslationUnitForm {

  Long id;
  String quality;
  String qualityFlags;
  TranslationVariantForm source;
  TranslationVariantForm target;
  List<TranslationUniDomain> domains = new ArrayList<>();

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class TranslationVariantForm {

    UUID uid;
    List<DataAnnotationForm> annotations = new ArrayList<>();

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DataAnnotationForm {
      Long id;
      Long categoryId;
      int offsetStart;
      int offsetEnd;
    }
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class TranslationUniDomain {

    Long id;
    double probability;
    String type;

    public static LanguageDataCharacteristicsDomain.Type getTypeParsed(final String type) {
      return type == null ? LanguageDataCharacteristicsDomain.Type.DEFINED :
        Arrays.stream(LanguageDataCharacteristicsDomain.Type.values())
          .filter(t -> t.name().toLowerCase(Locale.ROOT).equals(type.toLowerCase(Locale.ROOT)))
          .findFirst().orElse(LanguageDataCharacteristicsDomain.Type.DEFINED);
    }
  }


}
