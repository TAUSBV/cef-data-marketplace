package net.taus.data.language.model.meta;

public class LanguageException extends RuntimeException {

  public LanguageException(final String message) {
    super(message);
  }

  public LanguageException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public static LanguageException invalidLanguageTag(String languageTag) {
    return new LanguageException("Invalid Language " + languageTag);
  }
}
