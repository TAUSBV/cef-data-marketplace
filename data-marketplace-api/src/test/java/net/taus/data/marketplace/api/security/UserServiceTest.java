package net.taus.data.marketplace.api.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.taus.corporate.user.client.CorporateUserService;
import net.taus.corporate.user.client.exceptions.CorporateUserException;
import net.taus.corporate.user.models.CorporateUser;
import net.taus.data.marketplace.api.IntegrationTest;
import net.taus.data.marketplace.api.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


class UserServiceTest extends IntegrationTest {

  @Autowired
  private final LocalValidatorFactoryBean springValidator = ValidationAutoConfiguration.defaultValidator();
  @MockBean
  private CorporateUserService corporateUserService;
  @Autowired
  private UserService userService;
  private RegistrationRequest request;

  @BeforeEach
  public void setUp() {
    this.request = new RegistrationRequest(
      "mp_test_email@test.net",
      "mp_test_first_name",
      "mp_test_last_name",
      "mp_test_new_useR1*",
      "mp_test_new_useR1*",
      "Mr.",
      "Greece"
    );
    request.setPrivacyPolicyConsent(true);
    request.setTermsOfUseConsent(true);
  }

  @Test
  @DisplayName("Should validate RegistrationRequest with no constraint violations")
  public void getNoErrors_whenRegistrationRequestIsValid() {
    Errors errors = new BeanPropertyBindingResult(request, request.getClass().getName());
    springValidator.validate(request, errors);
    assertTrue(errors.getAllErrors().isEmpty());
  }

  @ParameterizedTest
  @DisplayName("Should validate registrationRequest with correct error messages")
  @CsvSource(value = {
    "[\"email\"]|''|'mp_test_first_name'|'mp_test_last_name'|'mp_test_new_useR1*'|'mp_test_new_useR1*'|'Mr.'|'Greece'|[\"Invalid Email\",\"Email is required\"]",
    "[\"email\"]|'mp_test_invalid@test'|'mp_test_first_name'|'mp_test_last_name'|'mp_test_new_useR1*'|'mp_test_new_useR1*'|'Mr.'|'Greece'|[\"Invalid Email\"]",
    "[\"firstName\"]|'mp_test@test.net'|''|'mp_test_last_name'|'mp_test_new_useR1*'|'mp_test_new_useR1*'|'Mr.'|'Greece'|[\"First name needs to be at least 3 characters long\",\"First name is required\"]",
    "[\"lastName\"]|'mp_test@test.net'|'mp_test_first_name'|''|'mp_test_new_useR1*'|'mp_test_new_useR1*'|'Mr.'|'Greece'|[\"Last name needs to be at least 3 characters long\",\"Last name is required\"]",
    "[\"password\",\"net.taus.data.marketplace.api.security.RegistrationRequest\"]|'mp_test@test.net'|'mp_test_first_name'|'mp_test_last_name'|''|'mp_test_new_useR1*'|'Mr.'|'Greece'|[\"Password is required\",\"Password must be 8 or more characters in length.\",\"Password must contain 1 or more digit characters.\",\"Password must contain 1 or more special characters.\",\"Passwords don'/t match\"]",
    "[\"matchingPassword\",\"net.taus.data.marketplace.api.security.RegistrationRequest\"]|'mp_test@test.net'|'mp_test_first_name'|'mp_test_last_name'|'mp_test_new_useR1*'|''|'Mr.'|'Greece'|[\"Matching Password is required\",\"Passwords don'/t match\"]",
    "[\"prefix\"]|'mp_test@test.net'|'mp_test_first_name'|'mp_test_last_name'|'mp_test_new_useR1*'|'mp_test_new_useR1*'|''|'Greece'|[\"Not accepted value\",\"Prefix is required\"]",
    "[\"prefix\"]|'mp_test@test.net'|'mp_test_first_name'|'mp_test_last_name'|'mp_test_new_useR1*'|'mp_test_new_useR1*'|'invalid'|'Greece'|[\"Not accepted value\"]",
    "[\"country\"]|'mp_test@test.net'|'mp_test_first_name'|'mp_test_last_name'|'mp_test_new_useR1*'|'mp_test_new_useR1*'|'Mr.'|''|[\"Not accepted value\",\"Country is required\"]",
    "[\"country\"]|'mp_test@test.net'|'mp_test_first_name'|'mp_test_last_name'|'mp_test_new_useR1*'|'mp_test_new_useR1*'|'Mr.'|'invalid'|[\"Not accepted value\"]",
    "[\"email\",\"lastName\",\"firstName\",\"password\",\"matchingPassword\",\"prefix\",\"country\"]|''|''|''|''|''|''|''|[\"Email is required\",\"Invalid Email\",\"First name needs to be at least 3 characters long\",\"First name is required\",\"Last name is required\",\"Last name needs to be at least 3 characters long\",\"Prefix is required\",\"Not accepted value\",\"Country is required\",\"Not accepted value\",\"Password is required\",\"Password must be 8 or more characters in length.\",\"Password must contain 1 or more digit characters.\",\"Password must contain 1 or more special characters.\",\"Matching Password is required\"]",
    "[\"net.taus.data.marketplace.api.security.RegistrationRequest\",\"email\",\"lastName\",\"firstName\",\"password\",\"matchingPassword\",\"prefix\",\"country\"]||||||||[\"Email is required\",\"Invalid Email\",\"First name is required\",\"Last name is required\",\"Password is required\",\"Matching Password is required\",\"Passwords don't match\",\"Prefix is required\",\"Country is required\"]",
    "[]|'mp_test@test.net'|'mp_test_first_name'|'mp_test_last_name'|'mp_test_new_useR1*'|'mp_test_new_useR1*'|'Mr.'|'Greece'|[]"
  }, delimiter = '|')
  public void getErrors_whenRegistrationRequestIsInvalid(String fieldNames, String email, String firstName, String lastName, String password, String matchingPassword, String prefix, String country, String jsonErrorMessages) throws JsonProcessingException {

    var expectedMessages = new ObjectMapper().readValue(jsonErrorMessages.replaceAll("'/", "'"), List.class);
    var expectedFieldNames = new ObjectMapper().readValue(fieldNames.replaceAll("'/", "'"), List.class);

    request.setEmail(email);
    request.setFirstName(firstName);
    request.setLastName(lastName);
    request.setPassword(password);
    request.setMatchingPassword(matchingPassword);
    request.setPrefix(prefix);
    request.setCountry(country);
    request.setPrivacyPolicyConsent(true);
    request.setTermsOfUseConsent(true);

    Errors errors = new BeanPropertyBindingResult(request, request.getClass().getName());
    springValidator.validate(request, errors);

    errors.getFieldErrors()
      .forEach(error -> assertTrue(expectedFieldNames.contains(error.getField()),
        () -> format("Should contain %s but instead contains %s", expectedFieldNames, error.getField())));

    errors.getGlobalErrors()
      .forEach(error -> assertTrue(expectedFieldNames.contains(error.getObjectName()),
        () -> format("Should contain %s but instead contains %s", expectedFieldNames, error.getObjectName())));

    var actualMessages = errors.getAllErrors().stream()
      .map(DefaultMessageSourceResolvable::getDefaultMessage).filter(Objects::nonNull).sorted().collect(toList());

    Collections.sort(expectedMessages);

    assertEquals(actualMessages, expectedMessages);
  }

  @Test
  @DisplayName("Should repass exception from corporate user service.")
  public void getRegistrationException_whenRegistrationServiceRegistrationRequestReturnsReturns422() {
    var expectedException = new CorporateUserException();
    var actualException = assertThrows(RuntimeException.class, () -> {
      when(corporateUserService.create(ArgumentMatchers.any(CorporateUser.class))).thenThrow(expectedException);
      userService.register(request);
    });
    assertEquals(expectedException.getClass(), actualException.getClass());
    assertEquals(expectedException.getMessage(), actualException.getMessage());
  }

}