package net.taus.data.language.api.document;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;
import java.util.Objects;

import static java.math.RoundingMode.HALF_EVEN;

public class DocumentPriceValidator implements ConstraintValidator<DocumentPrice, Double> {

  @Override
  public boolean isValid(final Double price, final ConstraintValidatorContext context) {
    boolean valid = true;
    double pricePerWord;
    if (Objects.nonNull(price)) {
      try {
        pricePerWord = BigDecimal.valueOf(price).setScale(3, HALF_EVEN).doubleValue();
        valid = pricePerWord >= 0.0005 && pricePerWord <= 0.15;
      }
      catch (NumberFormatException e) {
        valid = false;
      }
    }
    return valid;
  }
}
