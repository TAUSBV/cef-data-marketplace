package net.taus.data.marketplace.api.base;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import net.taus.data.marketplace.api.validation.Country;

import java.io.IOException;

public class CountrySerializer extends StdSerializer<Country> {

  public CountrySerializer() {
    super(Country.class);
  }

  public CountrySerializer(Class<Country> t) {
    super(t);
  }

  public void serialize(Country distance, JsonGenerator generator, SerializerProvider provider) throws IOException {
    generator.writeStartObject();
    generator.writeFieldName("name");
    generator.writeString(distance.toString());
    generator.writeFieldName("code");
    generator.writeString(distance.getCode());
    generator.writeFieldName("isEuropeanUnion");
    generator.writeString(String.valueOf(distance.isEuropeanUnion()));
    generator.writeFieldName("isAcceptedPayPal");
    generator.writeString(String.valueOf(distance.isAcceptedPayPal()));
    generator.writeFieldName("isAcceptedStripe");
    generator.writeString(String.valueOf(distance.isAcceptedStripe()));
    generator.writeEndObject();
  }
}