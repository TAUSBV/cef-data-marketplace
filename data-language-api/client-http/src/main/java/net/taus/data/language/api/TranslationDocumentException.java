package net.taus.data.language.api;

public class TranslationDocumentException extends RuntimeException {

  // TODO externalize these messages in a messages.properties
  public static final String INVALID_LANGUAGE_MESSAGE = "The document contains an invalid %s language %s.";

  public TranslationDocumentException(final String message) {
    super(message);
  }

}
