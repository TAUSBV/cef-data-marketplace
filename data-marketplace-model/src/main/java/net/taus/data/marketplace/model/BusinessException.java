package net.taus.data.marketplace.model;

public class BusinessException extends RuntimeException {

  public BusinessException() {
  }

  public BusinessException(final String message) {
    super(message);
  }

  public BusinessException(final Exception cause) {
    super(cause);
  }

  public static BusinessException missingDomain() {
    return new BusinessException("Domain is mandatory");
  }

  public static BusinessException missingContentType() {
    return new BusinessException("Content Type is mandatory");
  }
}
