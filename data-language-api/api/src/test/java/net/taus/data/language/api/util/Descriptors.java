package net.taus.data.language.api.util;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import net.taus.data.language.model.DataAnnotationCategory;
import net.taus.data.language.model.TranslationDocument;
import net.taus.data.language.model.TranslationUnit;
import net.taus.data.language.model.meta.LanguageDataCharacteristicsDomain;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;

import java.util.Arrays;

import static java.lang.String.format;
import static java.util.stream.Collectors.joining;
import static net.taus.data.language.model.meta.LanguageDataCharacteristicsDomain.Type.DEFINED;
import static net.taus.data.language.model.meta.LanguageDataCharacteristicsDomain.Type.PREDICTED;
import static org.springframework.restdocs.payload.JsonFieldType.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.snippet.Attributes.key;

public class Descriptors {

  public static FieldDescriptor[] translationDocument() {
    return new FieldDescriptor[]{
      fieldWithPath("uid").type(STRING).description("The document uid."),
      fieldWithPath("name").type(STRING).description("The document name"),
      fieldWithPath("size").type(NUMBER).description("The size in bytes of the document."),
      fieldWithPath("status").type(STRING).description(String.format("The status of the document can be one of %s.",
        Arrays.stream(TranslationDocument.Status.values()).map(Enum::name).collect(joining(", ")))),
      fieldWithPath("format").type(STRING).description(String.format("The format of the document can be one of %s.",
        Arrays.stream(TranslationDocument.Format.values()).map(Enum::name).collect(joining(", ")))),
      fieldWithPath("createdAt").type(ARRAY).description("ISO Timestamp of when the document was created."), // FIXME formatting is making this field be an array where it should be STRING
      fieldWithPath("updatedAt").type(ARRAY).description("ISO Timestamp of when was the last time that the document was updated."), // FIXME formatting is making this field be an array where it should be STRING
      fieldWithPath("description").type(STRING).description("The description of the document, this can be the description provided by the owner or the .").optional(),
      fieldWithPath("ownerId").type(NUMBER).description("The document owner corporate id.").optional(),
      fieldWithPath("pricePerWord").type(NUMBER).description("**DEPRECATED** The document price per word in `#.####` format.").optional(),
      fieldWithPath("sourceWordCount").type(NUMBER).description("The sum of originals and replicas source word count.").optional(),
      fieldWithPath("targetWordCount").type(NUMBER).description("The sum of originals and replicas target word count.").optional(),
      fieldWithPath("unitCount").type(NUMBER).description("The sum of originals and replicas unit count.").optional(),
      fieldWithPath("price").type(NUMBER).description("**DEPRECATED** The `sourceWordCount * pricePerWord`.").optional(),
    };
  }

  public static FieldDescriptor[] unit() {
    return new FieldDescriptor[]{
      fieldWithPath("id").type(NUMBER).description("The unit local id.").optional(),
      fieldWithPath("uid").type(STRING).description("The unit universal identifier.").optional(),
      fieldWithPath("documentUid").type(STRING).description("The document universal identifier.").optional(),
      fieldWithPath("ownerId").type(NUMBER).description("The document owner corporate id.").optional(),
      fieldWithPath("hash").type(NUMBER).description("The unit generate hash based on its source language, value, target language and value.").optional(),
      fieldWithPath("sequence").type(NUMBER).description("The sequential number of the unit within the its document starting from 1.").optional(),
    };
  }

  public static FieldDescriptor[] variant() {
    return new FieldDescriptor[]{
      fieldWithPath("uid").type(STRING).description("The variant universal identifier.").optional(),
      fieldWithPath("value").type(STRING).description("The actual sentence value.").optional(),
      fieldWithPath("wordCount").type(NUMBER).description("The total of tokens in the `value`.").optional(),
      fieldWithPath("hash").type(NUMBER).description("The variant generate hash based on its language and value.").optional(),
    };
  }

  public static FieldDescriptor[] domain() {
    return new FieldDescriptor[]{
      fieldWithPath("id").type(NUMBER).description("The data domain id.").optional(),
      fieldWithPath("description").type(STRING).description("The data domain description.").optional(),
      fieldWithPath("probability").type(NUMBER).description("The probability that this domain is of unit or document.").optional(),
      fieldWithPath("type").type(STRING).description(format("How this domain was assigned to the unit or document, can one of %s.",
        Arrays.stream(LanguageDataCharacteristicsDomain.Type.values()).map(Enum::name).collect(joining(", ")))).optional(),
    };
  }

  public static FieldDescriptor[] contentType() {
    return new FieldDescriptor[]{
      fieldWithPath("id").type(NUMBER).description("The data content type id.").optional(),
      fieldWithPath("description").type(STRING).description("The content type description.").optional(),
    };
  }

  public static FieldDescriptor[] language() {
    return new FieldDescriptor[]{
      fieldWithPath("id").type(NUMBER).description("The language id.").optional(),
      fieldWithPath("tag").type(STRING).description("The ISO language tag.").optional(),
      fieldWithPath("name").type(STRING).description("The ISO language code.").optional(),
      fieldWithPath("displayName").type(STRING).description("The ISO language name.").optional(),
      fieldWithPath("country").type(STRING).description("The ISO language country code.").optional(),
      fieldWithPath("displayCountry").type(STRING).description("The ISO country name.").optional(),
      fieldWithPath("enabledPii").type(BOOLEAN).description("If the language is supported by the `annotator-pii`.").optional(),
      fieldWithPath("nameAndCountry").type(STRING).description("The concatenation of `name` and `country` separated by a space.").optional(),
    };
  }

  public static FieldDescriptor[] documentStatistics() {
    return new FieldDescriptor[]{
      fieldWithPath("id").type(NUMBER).description("The data content type id."),
      fieldWithPath("totalUnits").type(NUMBER).description("The overall total of units in the document."),
      fieldWithPath("totalUnitsOriginal").type(NUMBER).description("The total of high quality units that at the moment of the publishing were unique in the Marketplace."),
      fieldWithPath("totalUnitsReplica").type(NUMBER).description("The total of high quality units that at the moment of the publishing existed in another document in the Marketplace."),
      fieldWithPath("totalUnitsLowQuality").type(NUMBER).description("The total of units that were flagged with one or more cleaning filters.."),
      fieldWithPath("totalUnitsAnnotatedPii").type(NUMBER).description("The total of units annotated by the `annotator-pii`."),
      fieldWithPath("totalSourceWordCount").type(NUMBER).description("The overall total of source words in the document."),
      fieldWithPath("totalSourceOriginalWordCount").type(NUMBER).description("The total of original words in the document."),
      fieldWithPath("totalSourceReplicaWordCount").type(NUMBER).description("The total of replica words in the document."),
      fieldWithPath("totalSourceLowQualityWordCount").type(NUMBER).description("The total of low quality words in the document."),
      fieldWithPath("totalSourceWordCountAnnotatedPii").type(NUMBER).description("The total of source words of an unit annotated with at least one PII category in the document."),
      fieldWithPath("totalTargetWordCount").type(NUMBER).description("The total of target words in the document."),
      fieldWithPath("totalTargetOriginalWordCount").type(NUMBER).description("The total of original target words in the document."),
      fieldWithPath("totalTargetReplicaWordCount").type(NUMBER).description("The total of replica words in the document."),
      fieldWithPath("totalTargetLowQualityWordCount").type(NUMBER).description("The total of target low quality words in the document."),
      fieldWithPath("totalTargetWordCountAnnotatedPii").type(NUMBER).description("The total of target words of an unit annotated with at least one PII category in the document."),
      fieldWithPath("createdAt").type(ARRAY).description("ISO timestamp of when this statistics were calculated and saved."), // FIXME formatting is making this field be an array where it should be STRING
      fieldWithPath("totalOriginalWordCount").type(NUMBER).description("The `totalSourceOriginalWordCount` + `totalTargetOriginalWordCount`."),
    };
  }

  public static FieldDescriptor[] unitForm() {
    return new FieldDescriptor[] {
      fieldWithPath("id").type(NUMBER).description("The unit id.").attributes(key("constraints").value("Must not be null. Must not be empty")),
      fieldWithPath("quality").type(STRING).description(format("The status of review can be only one of %s.", Arrays.stream(TranslationUnit.Quality.values()).map(Enum::name).collect(joining(", ")))),
      fieldWithPath("qualityFlags").type(STRING).description(format("The comma separated list of flags that caused the quality be one of %s, mandatory when quality is present.", Arrays.stream(TranslationUnit.Quality.values()).map(Enum::name).collect(joining(", ")))),
    };
  }

  public static FieldDescriptor[] variantForm() {
    return new FieldDescriptor[] {
      fieldWithPath("uid").type(STRING).description("The unit's variant uid."),
    };
  }

  public static FieldDescriptor[] variantAnnotationForm() {
    return new FieldDescriptor[] {
      fieldWithPath("id").type(NUMBER).description("The annotation id.").optional(),
      fieldWithPath("categoryId").type(NUMBER).description("The annotation category id.").optional(),
      fieldWithPath("offsetStart").type(NUMBER).description("The annotation start offset for annotations that are text position based.").optional(),
      fieldWithPath("offsetEnd").type(NUMBER).description("The annotation end offset for annotations that are text position based.").optional(),
    };
  }

}
