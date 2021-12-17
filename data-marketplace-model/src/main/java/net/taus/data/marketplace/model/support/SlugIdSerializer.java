package net.taus.data.marketplace.model.support;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import net.taus.data.marketplace.model.user.SlugId;
import org.springframework.core.convert.converter.Converter;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.Objects;

public class SlugIdSerializer extends StdSerializer<SlugId> implements Converter<SlugId, String> {

  private final UUIDSerializer uuidSerializer;

  public SlugIdSerializer(UUIDSerializer uuidSerializer) {
    super(SlugId.class);
    this.uuidSerializer = uuidSerializer;
  }

  @Override
  public void serialize(final SlugId value, final JsonGenerator gen, final SerializerProvider provider) throws IOException {
    gen.writeString(doConvert(value));
  }

  @Override
  public String convert(@Nonnull final SlugId source) {
    return doConvert(source);
  }

  public String doConvert(final SlugId slugId) {
    var firstName = slugId.getFirstName();
    var lastName = slugId.getLastName();
    final var uid = uuidSerializer.doConvert(slugId.getUid());
    String slugIdString = null;
    if (Objects.nonNull(firstName) && Objects.nonNull(lastName) && Objects.nonNull(uid)) {
      firstName = firstName.strip().replaceAll("\\s", "");
      lastName = lastName.strip().replaceAll("\\s", "");
      slugIdString = String.format("%s___%s___%s", firstName, lastName, uid);
    }
    return slugIdString;
  }
}
