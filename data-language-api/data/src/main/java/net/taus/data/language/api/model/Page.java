package net.taus.data.language.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Page<T> extends org.springframework.data.domain.PageImpl<T> {

  @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
  public Page(@JsonProperty("content") List<T> content,
              @JsonProperty("number") int number,
              @JsonProperty("size") int size,
              @JsonProperty("totalElements") Long totalElements,
              @JsonProperty("pageable") JsonNode pageable,
              @JsonProperty("last") boolean last,
              @JsonProperty("totalPages") int totalPages,
              @JsonProperty("sort") JsonNode sort,
              @JsonProperty("first") boolean first,
              @JsonProperty("numberOfElements") int numberOfElements) {
    super(content, PageRequest.of(number, size), totalElements);
  }

  public Page(List<T> content, Pageable pageable, Long totalElements) {
    super(content, pageable, totalElements);
  }

  public Page(List<T> content) {
    super(content);
  }

  public static <T> Page<T> empty(Pageable pageable) {
    return new Page<>(Collections.emptyList(), pageable, 0L);
  }
}
