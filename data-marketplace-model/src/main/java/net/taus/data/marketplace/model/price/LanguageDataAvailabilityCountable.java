package net.taus.data.marketplace.model.price;


public interface LanguageDataAvailabilityCountable {

  Long getSourceLanguageId();
  String getSourceLanguageName();
  String getSourceLanguageCountry();
  String getSourceLanguageTag();
  Long   getTargetLanguageId();
  String getTargetLanguageName();
  String getTargetLanguageCountry();
  String getTargetLanguageTag();
  Long getDomainId();
  String getDomainDescription();
  Long getContentTypeId();
  String getContentTypeDescription();
  long getWordCount();
}