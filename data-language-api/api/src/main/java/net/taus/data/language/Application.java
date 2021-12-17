package net.taus.data.language;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.time.format.DateTimeFormatter;

@SpringBootApplication(scanBasePackageClasses = { Application.class, Model.class })
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Configuration
  public static class ApplicationConfig {

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
      final ObjectMapper mapper = new ObjectMapper();
      mapper.registerModule(new JavaTimeModule());
      mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
      mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
      mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
      return mapper;
    }
  }

  @Configuration
  public static class DateTimeConfig extends WebMvcConfigurationSupport {

    @Bean
    @Override
    public FormattingConversionService mvcConversionService() {
      DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService(false);

      DateTimeFormatterRegistrar dateTimeRegistrar = new DateTimeFormatterRegistrar();
      dateTimeRegistrar.setDateFormatter(DateTimeFormatter.ISO_LOCAL_DATE);
      dateTimeRegistrar.setDateTimeFormatter(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
      dateTimeRegistrar.registerFormatters(conversionService);

      DateFormatterRegistrar dateRegistrar = new DateFormatterRegistrar();
      dateRegistrar.setFormatter(new DateFormatter("yyyy-MM-dd HH.mm.sss"));
      dateRegistrar.registerFormatters(conversionService);

      return conversionService;
    }
  }
}
