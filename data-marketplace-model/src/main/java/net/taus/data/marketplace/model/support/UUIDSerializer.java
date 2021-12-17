package net.taus.data.marketplace.model.support;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.core.convert.converter.Converter;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.UUID;

public class UUIDSerializer extends StdSerializer<UUID> implements Converter<UUID, String> {
  public UUIDSerializer() {
    super(UUID.class);
  }

  @Override
  public void serialize(final UUID uuid, final JsonGenerator gen, final SerializerProvider provider) throws IOException {
    gen.writeString(doConvert(uuid));
  }

  @Override
  public String convert(final @Nonnull UUID source) {
    return doConvert(source);
  }

  public String doConvert(final UUID uuid) {
    return UUIDUtil.toUrlEncoded(uuid);
  }
}
