package net.taus.data.marketplace.model.support;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.core.convert.converter.Converter;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.UUID;

public class UUIDDeserializer extends StdDeserializer<UUID> implements Converter<String, UUID> {
  public UUIDDeserializer() {
    super(UUID.class);
  }

  @Override
  public UUID deserialize(final JsonParser p, final DeserializationContext context) throws IOException {
    return doConvert(p.readValueAs(String.class));
  }

  @Override
  public UUID convert(final @Nonnull String source) {
    return doConvert(source);
  }

  public UUID doConvert(final String source) {
    UUID uuid;
    try {
      uuid = UUIDUtil.fromUrlEncoded(source);
    }
    catch (Exception e) {
      uuid = null;
    }
    return uuid;
  }
}
