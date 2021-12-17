package net.taus.data.marketplace.api.security.auth.provider;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Scanner;

public class CorporateAuthenticationResponseErrorHandler implements ResponseErrorHandler {

  @Override
  public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
    HttpStatus status = clientHttpResponse.getStatusCode();
    return status.is4xxClientError() || status.is5xxServerError();
  }

  @Override
  public void handleError(ClientHttpResponse response) throws IOException {
    String bodyAsJsonString = toString(response.getBody());
    String exceptionMessage = getExceptionMessage(bodyAsJsonString);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    if (response.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR) {
      // TODO Log response
      throw new HttpServerErrorException(exceptionMessage, response.getStatusCode(), response.getStatusText(), headers, null, null);
    }
    else if (response.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR) {
      // TODO Log response
      throw new HttpClientErrorException(exceptionMessage, response.getStatusCode(), response.getStatusText(), headers, null, null);
    }
  }

  String toString(InputStream inputStream) {
    Scanner s = new Scanner(inputStream).useDelimiter("\\A");
    return s.hasNext() ? s.next() : "";
  }

  String getExceptionMessage(String bodyAsJsonString) throws JsonProcessingException {
    Map<String, Object> bodyAsMap = new ObjectMapper().readValue(bodyAsJsonString, new TypeReference<>() {
    });
    return bodyAsMap.get("exceptionMessage").toString();
  }
}