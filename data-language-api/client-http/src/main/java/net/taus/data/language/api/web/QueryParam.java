package net.taus.data.language.api.web;

import net.taus.data.language.api.model.LanguageDataParams;

import java.net.URLEncoder;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.stream.Collectors.joining;

public class QueryParam {
  private final String name;
  private final Set<String> values;

  public QueryParam(final String name, final Set<String> values) {
    this.name = name;
    this.values = values;
  }

  public QueryParam(final String name, final String value) {
    this.name = name;
    this.values = Set.of(value);
  }

  public String serialize() {
    return this.values.stream().map(v -> format("%s=%s", name, URLEncoder.encode(v, UTF_8))).collect(joining("&"));
  }

  public static List<QueryParam> transform(final LanguageDataParams languageDataParams) {
    final var params = new ArrayList<QueryParam>();
    if (Objects.nonNull(languageDataParams.getSourceLanguageId())) {
      params.add(new QueryParam("sourceLanguageId", String.valueOf(languageDataParams.getSourceLanguageId())));
    }
    if (Objects.nonNull(languageDataParams.getTargetLanguageId())) {
      params.add(new QueryParam("targetLanguageId", String.valueOf(languageDataParams.getTargetLanguageId())));
    }
    if (Objects.nonNull(languageDataParams.getPriceMin()) && Objects.nonNull(languageDataParams.getPriceMax())) {
      params.add(new QueryParam("priceMin", String.valueOf(languageDataParams.getPriceMin())));
      params.add(new QueryParam("priceMax", String.valueOf(languageDataParams.getPriceMax())));
    }
    if (Objects.nonNull(languageDataParams.getReferenceDateTime())) {
      params.add(new QueryParam("referenceDateTime", DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(languageDataParams.getReferenceDateTime())));
    }
    if (Objects.nonNull(languageDataParams.getUnitUnicity())) {
      params.add(new QueryParam("unitUnicity", String.valueOf(languageDataParams.getUnitUnicity())));
    }
    Optional.ofNullable(languageDataParams.getDocumentUids()).ifPresent(ds -> params.add(new QueryParam("documentUids", Arrays.stream(ds).map(String::valueOf).collect(Collectors.toSet()))));
    Optional.ofNullable(languageDataParams.getDomainIds()).ifPresent(ds -> params.add(new QueryParam("domainIds", Arrays.stream(ds).map(String::valueOf).collect(Collectors.toSet()))));
    Optional.ofNullable(languageDataParams.getContentTypeIds()).ifPresent(ds -> params.add(new QueryParam("contentTypeIds", Arrays.stream(ds).map(String::valueOf).collect(Collectors.toSet()))));
    Optional.ofNullable(languageDataParams.getOwnerIds()).ifPresent(ds -> params.add(new QueryParam("ownerIds", Arrays.stream(ds).map(String::valueOf).collect(Collectors.toSet()))));
    return params;
  }
}
