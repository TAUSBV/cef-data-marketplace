package net.taus.data.marketplace.api.validation;

import net.taus.corporate.user.models.CorporateUser;
import net.taus.data.marketplace.api.security.RegistrationRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

  @Override
  public void initialize(final PasswordMatches constraintAnnotation) {
  }

  @Override
  public boolean isValid(final Object obj, final ConstraintValidatorContext context) {
    var isValid = true;
    final RegistrationRequest registrationRequest = (RegistrationRequest) obj;
    final CorporateUser corporateUser = registrationRequest.getCorporateUser();
    if (corporateUser.getPassword() == null || corporateUser.getMatchingPassword() == null) {
      isValid = false;
    } else if (!corporateUser.getPassword().equals(corporateUser.getMatchingPassword())) {
      isValid = false;
    }
    return isValid;
  }

}