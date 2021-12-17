package net.taus.data.marketplace.model.price;

public class LanguageDataAvailabilityCountException extends RuntimeException {

  public LanguageDataAvailabilityCountException(final String message) {
    super(message);
  }

  public LanguageDataAvailabilityCountException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public static LanguageDataAvailabilityCountException noLanguageDataAvailabilityCount() {
    return new LanguageDataAvailabilityCountException("No data found");
  }
}
