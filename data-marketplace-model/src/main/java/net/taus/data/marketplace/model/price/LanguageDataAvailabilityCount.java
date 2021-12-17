package net.taus.data.marketplace.model.price;

import lombok.Data;
import net.taus.data.language.api.model.ContentType;
import net.taus.data.language.api.model.Domain;
import net.taus.data.language.api.model.Language;

import javax.persistence.*;

@Entity
@Table(name = "language_data_availability_count")
@Data
public class LanguageDataAvailabilityCount implements LanguageDataAvailabilityCountable {

  @Id
  @Column(updatable = false, nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long sourceLanguageId;
  @Column(columnDefinition = "varchar(255)")
  private String sourceLanguageName;
  @Column(columnDefinition = "varchar(255)")
  private String sourceLanguageDisplayName;
  @Column(columnDefinition = "varchar(255)")
  private String sourceLanguageCountry;
  @Column(columnDefinition = "varchar(255)")
  private String sourceLanguageDisplayCountry;
  @Column(columnDefinition = "varchar(255)")
  private String sourceLanguageTag;

  private Long   targetLanguageId;
  @Column(columnDefinition = "varchar(255)")
  private String targetLanguageName;
  @Column(columnDefinition = "varchar(255)")
  private String targetLanguageDisplayName;
  @Column(columnDefinition = "varchar(255)")
  private String targetLanguageCountry;
  @Column(columnDefinition = "varchar(255)")
  private String targetLanguageDisplayCountry;
  @Column(columnDefinition = "varchar(255)")
  private String targetLanguageTag;

  private Long domainId;
  @Column(columnDefinition = "varchar(255)")
  private String domainDescription;

  private Long contentTypeId;
  @Column(columnDefinition = "varchar(255)")
  private String contentTypeDescription;

  @Column(name = "word_count")
  private long wordCount;

  public LanguageDataAvailabilityCount() {
  }

  public LanguageDataAvailabilityCount(final Language sourceLanguage, final Language targetLanguage, final Domain domain, final ContentType contentType, final Long wordCount) {
    this.sourceLanguageId = sourceLanguage.getId();
    this.sourceLanguageName  = sourceLanguage.getName();
    this.sourceLanguageCountry = sourceLanguage.getCountry();
    this.sourceLanguageTag = sourceLanguage.getTag();

    this.targetLanguageId = targetLanguage.getId();
    this.targetLanguageName  = targetLanguage.getName();
    this.targetLanguageCountry = targetLanguage.getCountry();
    this.targetLanguageTag = targetLanguage.getTag();

    this.domainId = domain.getId();
    this.domainDescription = domain.getDescription();

    this.contentTypeId = contentType.getId();
    this.contentTypeDescription = contentType.getDescription();

    this.wordCount = wordCount;
  }

  public LanguageDataAvailabilityCount addToWordCount(final long count) {
    var newWordCount = this.getWordCount() + count;
    this.setWordCount(newWordCount);
    return this;
  }

  public Language getSourceLanguage() {
    return new Language(
      sourceLanguageId,
      sourceLanguageTag,
      sourceLanguageName,
      sourceLanguageDisplayName,
      sourceLanguageCountry,
      sourceLanguageDisplayCountry,
      false);
  }

  public void setSourceLanguage(final Language language) {
    this.setSourceLanguageTag(language.getTag());
    this.setSourceLanguageName(language.getName());
    this.setSourceLanguageDisplayName(language.getDisplayName());
    this.setSourceLanguageCountry(language.getCountry());
    this.setSourceLanguageDisplayCountry(language.getDisplayCountry());
  }

  public Language getTargetLanguage() {
    return new Language(
      targetLanguageId,
      targetLanguageTag,
      targetLanguageName,
      targetLanguageDisplayName,
      targetLanguageCountry,
      targetLanguageDisplayCountry,
      false);
  }

  public void setTargetLanguage(final Language language) {
    this.setTargetLanguageTag(language.getTag());
    this.setTargetLanguageName(language.getName());
    this.setTargetLanguageDisplayName(language.getDisplayName());
    this.setTargetLanguageCountry(language.getCountry());
    this.setTargetLanguageDisplayCountry(language.getDisplayCountry());
  }

  public Domain getDomain() {
    return new Domain(domainId, domainDescription);
  }

  public void setDomain(final Domain domain) {
    this.setDomainId(domain.getId());
    this.setDomainDescription(domain.getDescription());
  }

  public ContentType getContentType() {
    return new ContentType(contentTypeId, contentTypeDescription);
  }

  public void setContentType(final ContentType contentType) {
    this.setContentTypeId(contentType.getId());
    this.setContentTypeDescription(contentType.getDescription());
  }
}