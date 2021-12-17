package net.taus.data.marketplace.api.base;

import net.taus.corporate.user.client.exceptions.CorporateUserException;
import net.taus.corporate.user.client.exceptions.CorporateUserValidationException;
import net.taus.data.marketplace.api.seller.provider.CorporatePaymentService;
import net.taus.data.marketplace.model.BusinessException;
import net.taus.data.marketplace.model.SystemException;
import net.taus.payment.PaymentBrokerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.*;
import static org.springframework.http.ResponseEntity.unprocessableEntity;

public class BaseController {

  private final Logger logger = LoggerFactory.getLogger(BaseController.class);

  @ResponseBody
  @ExceptionHandler({ BindException.class })
  public ResponseEntity<Map<String, List<String>>> handleBindException(BindException e) {
    return unprocessableEntity().body(parserBindResult(e.getBindingResult()));
  }

  @ResponseBody
  @ExceptionHandler({ MethodArgumentNotValidException.class })
  public ResponseEntity<Map<String, List<String>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    return unprocessableEntity().body(parserBindResult(e.getBindingResult()));
  }

  @ResponseBody
  @ExceptionHandler({ CorporateUserValidationException.class })
  public ResponseEntity<Map<String, List<String>>> handleCorporateUserCreationException(CorporateUserValidationException e) {
    return unprocessableEntity().body(e.getViolations());
  }

  @ResponseBody
  @ExceptionHandler({ CorporateUserException.class })
  public ResponseEntity<String> handleCorporateUserException(CorporateUserException e) {
    return unprocessableEntity().body(e.getMessage());
  }

  @ResponseBody
  @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
  public ResponseEntity<String> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
    return unprocessableEntity().body(String.format("Invalid type for %s", e.getParameter()));
  }

  @ResponseBody
  @ExceptionHandler({ CorporatePaymentService.CorporatePaymentServiceException.class })
  public ResponseEntity<String> handleCorporatePaymentException(CorporatePaymentService.CorporatePaymentServiceException e) {
    return new ResponseEntity<>(e.getMessage(), Optional.ofNullable(HttpStatus.resolve(e.getStatusCode())).orElse(HttpStatus.UNPROCESSABLE_ENTITY));
  }

  @ResponseBody
  @ExceptionHandler({ BusinessException.class })
  public ResponseEntity<?> handleBusinessException(BusinessException e) {
    return unprocessableEntity().body(e.getMessage());
  }

  @ResponseBody
  @ExceptionHandler(MissingServletRequestParameterException.class)
  public ResponseEntity<?> handleMissingParams(MissingServletRequestParameterException e) {
    return unprocessableEntity().body(String.format("Missing required %s parameter", e.getParameterName()));
  }

  @ResponseBody
  @ExceptionHandler(MissingPathVariableException.class)
  public ResponseEntity<?> handleMissingParams(MissingPathVariableException e) {
    return unprocessableEntity().body(String.format("Missing required %s parameter", e.getVariableName()));
  }

  @ResponseBody
  @ExceptionHandler({ Exception.class, SystemException.class, PaymentBrokerException.class })
  public ResponseEntity<?> handleException(Exception e) {
    logger.error(e.getMessage(), e);
    final String message = SystemException.GENERIC_MESSAGE;
    return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
  @ResponseBody
  @ExceptionHandler(HttpMessageNotReadableException.class) // TODO messages
  protected String handleHttpMessageNotReadable(HttpMessageNotReadableException e) {
    logger.error(e.getMessage());
    return "Invalid request body";
  }

  private Map<String, List<String>> parserBindResult(BindingResult result) {
    final var parsed = result.getGlobalErrors().stream()
      .collect(groupingBy(ObjectError::getObjectName, mapping(ObjectError::getDefaultMessage, toList())));
    parsed.putAll(result.getFieldErrors().stream()
      .collect(groupingBy(FieldError::getField, mapping(ObjectError::getDefaultMessage, toList()))));
    return parsed;
  }

}
