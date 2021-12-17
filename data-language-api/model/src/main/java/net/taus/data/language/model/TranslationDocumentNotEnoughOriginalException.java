package net.taus.data.language.model;

public class TranslationDocumentNotEnoughOriginalException extends TranslationDocumentException {

  public static final String NOT_ENOUGH_ORIGINAL_DATA_MESSAGE = "The document does not have enough high quality data.";

  public TranslationDocumentNotEnoughOriginalException(final String message) {
    super(message);
  }

}
