package net.taus.data.language.api.web;

import java.util.List;
import java.util.Map;

public class WebRequestException extends RuntimeException {
  private final int statusCode;
  private final Map<String, List<String>> headers;
  private final Object body;

  public WebRequestException(final int statusCode, final Map<String, List<String>> headers, final Object body) {
    super(String.format("%s - %s", statusCode, body));
    this.statusCode = statusCode;
    this.headers = headers;
    this.body = body;
  }

  public WebRequestException(final int statusCode, final String message, Throwable throwable) {
    super(message, throwable);
    this.statusCode = statusCode;
    this.headers = Map.of();
    this.body = throwable;
  }

  public int getStatusCode() {
    return statusCode;
  }

  public Map<String, List<String>> getHeaders() {
    return headers;
  }

  public Object getBody() {
    return body;
  }
}
