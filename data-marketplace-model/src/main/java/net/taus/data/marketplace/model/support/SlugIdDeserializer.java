package net.taus.data.marketplace.model.support;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import net.taus.data.marketplace.model.user.SlugId;
import org.springframework.core.convert.converter.Converter;

import javax.annotation.Nonnull;
import java.io.IOException;

public class SlugIdDeserializer extends StdDeserializer<SlugId> implements Converter<String, SlugId> {

  private final UUIDDeserializer uuidDeserializer;

  public SlugIdDeserializer(UUIDDeserializer uuidDeserializer) {
    super(SlugId.class);
    this.uuidDeserializer = uuidDeserializer;
  }

  @Override
  public SlugId deserialize(final JsonParser p, final DeserializationContext ctxt) throws IOException {
    return doConvert(p.readValueAs(String.class));
  }

  @Override
  public SlugId convert(@Nonnull final String source) {
    return doConvert(source);
  }

  public SlugId doConvert(final String source) {
    SlugId slugId = null;
    final var data = source.split("___");
    if (data.length >= 3) {
      final var firstName = data[0];
      final var lastName = data[1];
      final var uid = uuidDeserializer.doConvert(data[2]);
      if (uid != null) {
        slugId = new SlugId(uid, firstName, lastName);
      }
    }
    return slugId;
  }
}
