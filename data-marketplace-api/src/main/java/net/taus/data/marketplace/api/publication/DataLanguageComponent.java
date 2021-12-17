package net.taus.data.marketplace.api.publication;

import lombok.Value;


@Value
public class DataLanguageComponent {

  String name;
  boolean required;
  boolean continueOnError;

  public interface Converter extends org.springframework.core.convert.converter.Converter<String, DataLanguageComponent> {
  }
}
