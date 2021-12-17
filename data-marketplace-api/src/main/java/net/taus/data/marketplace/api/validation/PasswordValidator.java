package net.taus.data.marketplace.api.validation;

import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

  @Override
  public void initialize(final ValidPassword arg0) {

  }

  @Override
  public boolean isValid(final String password, final ConstraintValidatorContext context) {
    // @formatter:off
    var isValid = true;
    if (password != null) {
      final org.passay.PasswordValidator validator = new org.passay.PasswordValidator(
              Arrays.asList(
                      new LengthRule(8, 30),
                      //            new CharacterRule(EnglishCharacterData.UpperCase, 1),
                      new CharacterRule(EnglishCharacterData.Digit, 1),
                      new CharacterRule(EnglishCharacterData.Special, 1),
                      new IllegalSequenceRule(EnglishSequenceData.Numerical, 3, false),
                      new IllegalSequenceRule(EnglishSequenceData.Alphabetical, 3, false),
                      //            new IllegalSequenceRule(EnglishSequenceData.USQwerty, 3, false),
                      new WhitespaceRule()));
      final RuleResult result = validator.validate(new PasswordData(password));
      if (!result.isValid()) {
        isValid = false;
        context.disableDefaultConstraintViolation();
        for (String message : validator.getMessages(result)) {
          context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
        }
      }
    }
    return isValid;
  }
}