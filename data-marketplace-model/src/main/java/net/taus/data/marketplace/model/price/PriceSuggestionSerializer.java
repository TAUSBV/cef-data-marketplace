package net.taus.data.marketplace.model.price;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_EVEN;

public class PriceSuggestionSerializer extends StdSerializer<PriceSuggestion> {

  public PriceSuggestionSerializer() {
    super(PriceSuggestion.class);
  }

  @Override
  public void serialize(final PriceSuggestion value, final JsonGenerator gen, final SerializerProvider provider) throws IOException {
    gen.writeStartObject();
    gen.writeFieldName("suggestedPricePerWord");
    gen.writeString(BigDecimal.valueOf(value.getSuggestedPricePerWord()).setScale(4, HALF_EVEN).toPlainString());
    gen.writeFieldName("transactionFee");
    gen.writeString(BigDecimal.valueOf(value.getTransactionFee()).setScale(2, HALF_EVEN).toPlainString());
    gen.writeFieldName("commissionFee");
    gen.writeString(BigDecimal.valueOf(value.getCommissionFee()).setScale(2, HALF_EVEN).toPlainString());
    gen.writeFieldName("categoryId");
    gen.writeNumber(value.getCategoryId());
    gen.writeFieldName("categoryLabel");
    gen.writeString(value.getCategoryLabel());
    gen.writeFieldName("categorySort");
    gen.writeNumber(value.getCategorySort());
    gen.writeEndObject();
  }

}