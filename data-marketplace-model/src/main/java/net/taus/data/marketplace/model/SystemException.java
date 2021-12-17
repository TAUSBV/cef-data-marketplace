package net.taus.data.marketplace.model;

public class SystemException extends RuntimeException {

  public static final String GENERIC_MESSAGE = "Something went wrong on our side, please try again in a few minutes.";

  public SystemException(final String message, final Exception exception) {
    super(message, exception);
  }

  public SystemException(final String message) {
    super(message);
  }
}
