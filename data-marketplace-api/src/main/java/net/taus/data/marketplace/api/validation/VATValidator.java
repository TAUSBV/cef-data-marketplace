package net.taus.data.marketplace.api.validation;

import net.taus.data.marketplace.api.purchase.services.VATSenseService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class VATValidator implements ConstraintValidator<VAT, String> {

  private final VATSenseService vatSenseService;

  public VATValidator(final VATSenseService vatSenseService) {
    this.vatSenseService = vatSenseService;
  }

  @Override
  public boolean isValid(final String vat, final ConstraintValidatorContext context) {
    boolean valid = true;
    if (Objects.nonNull(vat)) {
      final var checkResponse = vatSenseService.checkVAT(vat);
      valid = !checkResponse.isInvalid();
    }
    return valid;
  }
}
