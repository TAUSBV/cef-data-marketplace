package net.taus.data.language.api.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.lang.String.format;
import static java.net.http.HttpResponse.BodyHandlers.ofString;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.stream.Collectors.joining;

public class WebClient {

  private final String url;
  private final HttpClient client;
  private final ObjectMapper mapper;

  public WebClient(final String url) {
    this.url = url;
    this.client = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(5)).build();
    this.mapper = new ObjectMapper();
    final JavaTimeModule javaTimeModule = new JavaTimeModule();
//      final DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssss");
    final LocalDateTimeSerializer localDateTimeSerializer = new LocalDateTimeSerializer(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    final LocalDateTimeDeserializer localDateTimeDeserializer = new LocalDateTimeDeserializer(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    javaTimeModule.addDeserializer(LocalDateTime.class, localDateTimeDeserializer);
    javaTimeModule.addSerializer(LocalDateTime.class, localDateTimeSerializer);
    this.mapper.registerModule(javaTimeModule);
    SimpleModule module = new SimpleModule();
    module.addDeserializer(UUID.class, new com.fasterxml.jackson.databind.deser.std.UUIDDeserializer());
    module.addSerializer(UUID.class, new com.fasterxml.jackson.databind.ser.std.UUIDSerializer());
    mapper.registerModule(module);
    this.mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    this.mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    this.mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
  }

  public WebClient(final String url, final HttpClient client, final ObjectMapper mapper) {
    this.url = url;
    this.client = client;
    this.mapper = mapper;
  }

  public <T> T put(final String endpoint, final List<QueryParam> queryParams, final Class<T> responseType) throws WebRequestException {
    final String uri = queryParams.stream().map(QueryParam::serialize).collect(joining("&", format("%s%s?", this.url, endpoint), ""));
    final HttpRequest request = HttpRequest.newBuilder(URI.create(uri))
      .PUT(HttpRequest.BodyPublishers.noBody())
      .header("Content-Type", "application/x-www-form-urlencoded")
      .build();
    return send(request, responseType);
  }

  public <T> T post(final String endpoint, final List<QueryParam> queryParams, final Class<T> responseType) throws WebRequestException {
    final String uri = queryParams.stream().map(QueryParam::serialize).collect(joining("&", format("%s%s?", this.url, endpoint), ""));
    final HttpRequest request = HttpRequest.newBuilder(URI.create(uri))
      .POST(HttpRequest.BodyPublishers.noBody())
      .header("Content-Type", "application/x-www-form-urlencoded")
      .build();
    return send(request, responseType);
  }

  public <T> T get(final String endpoint, final List<QueryParam> queryParams, final Class<T> responseType) throws WebRequestException {
    final String uri = queryParams.stream().map(QueryParam::serialize).collect(joining("&", format("%s%s?", this.url, endpoint), ""));
    final HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(uri))
      .header("Content-Type", "text/plain")
      .build();
    return send(request, responseType);
  }

  public <T> List<T> list(final String endpoint, final List<QueryParam> queryParams, final Class<T> responseType) throws WebRequestException {
    final String uri = queryParams.stream().map(QueryParam::serialize).collect(joining("&", format("%s%s?", this.url, endpoint), ""));
    final HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(uri))
      .header("Content-Type", "text/plain")
      .build();
    Optional<HttpResponse<String>> response = Optional.ofNullable(send(request));
    return response.map(r -> {
      try {
        return mapper.<List<T>>readValue(r.body(), mapper.getTypeFactory().constructCollectionType(List.class, responseType));
      }
      catch (IOException e) {
        throw new WebRequestException(500, e.getMessage(), e);
      }
    }).orElseGet(List::of);
  }

  public <T> List<T> list(final String endpoint, final Object requestBody, final Class<T> responseType) throws WebRequestException, JsonProcessingException {
    final HttpRequest request = HttpRequest.newBuilder(URI.create(this.url + endpoint))
      .header("Content-Type", "application/json")
      .method("GET", HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(requestBody)))
      .build();
    Optional<HttpResponse<String>> response = Optional.ofNullable(send(request));
    return response.map(r -> {
      try {
        return mapper.<List<T>>readValue(r.body(), mapper.getTypeFactory().constructCollectionType(List.class, responseType));
      }
      catch (IOException e) {
        throw new WebRequestException(500, e.getMessage(), e);
      }
    }).orElseGet(List::of);
  }

  public <T> Page<T> page(final String endpoint, List<QueryParam> queryParams, final Class<T> responseType) throws WebRequestException {
    final String uri = queryParams.stream().map(QueryParam::serialize).collect(joining("&", format("%s%s?", this.url, endpoint), ""));
    final HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(uri))
      .header("Content-Type", "text/plain")
      .build();
    Optional<HttpResponse<String>> response = Optional.ofNullable(send(request));
    return response.map(r -> {
      try {
        return mapper.<Page<T>>readValue(r.body(), mapper.getTypeFactory().constructParametricType(net.taus.data.language.api.model.Page.class, responseType));
      }
      catch (IOException e) {
        throw new WebRequestException(500, e.getMessage(), e);
      }
    }).orElseGet(Page::empty);
  }

  private <T> T send(final HttpRequest request, final Class<T> responseType) throws WebRequestException {
    Optional<HttpResponse<String>> response = Optional.ofNullable(send(request));
    return response.map(r -> {
      try {
        return mapper.readValue(r.body(), responseType);
      }
      catch (IOException e) {
        throw new WebRequestException(500, e.getMessage(), e);
      }
    }).orElse(null);
  }

  private HttpResponse<String> send(final HttpRequest request) throws WebRequestException {
    HttpResponse<String> response;
    try {
      response = client.send(request, ofString(UTF_8));
      if (response.statusCode() == 404) {
        response = null;
      }
      else if (response.statusCode() < 200 || response.statusCode() > 299) {
        throw new WebRequestException(response.statusCode(), response.headers().map(), response.body());
      }
    }
    catch (IOException | InterruptedException e) {
      throw new WebRequestException(500, e.getMessage(), e);
    }
    return response;
  }

}
