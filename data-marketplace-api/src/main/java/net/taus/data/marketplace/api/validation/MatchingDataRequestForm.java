package net.taus.data.marketplace.api.validation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import net.taus.data.marketplace.model.matchingdata.MatchingDataRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class MatchingDataRequestForm implements Serializable {

  @NotNull(message = "Input query file is required")
  private final MultipartFile file;

  @ValidEmail
  @NotEmpty(message = "is required")
  private final String email;

  @NotNull(message = "is required")
  @Min(1)
  private final Long sourceLanguageId;

  @NotNull(message = "is required")
  @Min(1)
  private final Long targetLanguageId;

  @ValueOfEnum(enumClass = MatchingDataRequest.Type.class)
  @NotEmpty(message = "is required")
  final String type;

  @Min(1)
  private final Long domainId;

  @ValueOfEnum(enumClass = MatchingDataRequest.SampleLanguage.class)
  final String sampleLanguage;

  public MatchingDataRequestForm(MultipartFile file,
                                 String email,
                                 Long sourceLanguageId,
                                 Long targetLanguageId,
                                 String type,
                                 Long domainId,
                                 String sampleLanguage
  ) {
    this.file = file;
    this.email = email;
    this.sourceLanguageId = sourceLanguageId;
    this.targetLanguageId = targetLanguageId;
    this.type = type;
    this.domainId = domainId;
    this.sampleLanguage = sampleLanguage;
  }

  @JsonIgnore
  public MultipartFile getFile() {
    return file;
  }

  public String getOriginalFilename() {
    return file.getOriginalFilename();
  }

  public long getFileSize() {
    return file.getSize();
  }

  public String getEmail() {
    return this.email;
  }

  public Long getSourceLanguageId() {
    return sourceLanguageId;
  }

  public Long getTargetLanguageId() {
    return targetLanguageId;
  }

  public MatchingDataRequest.Type getType() {
    return MatchingDataRequest.Type.valueOf(type.toUpperCase());
  }

  public Long getDomainId() {
    return domainId;
  }

  public MatchingDataRequest.SampleLanguage getSampleLanguage() {
    return sampleLanguage == null ? null : MatchingDataRequest.SampleLanguage.valueOf(sampleLanguage.toUpperCase());
  }

  @AssertTrue(message = "is required")
  private boolean isSampleLanguage() {
    return MatchingDataRequest.Type.BILINGUAL.toString().equals(type)
      || sampleLanguage != null && MatchingDataRequest.Type.MONOLINGUAL.toString().equals(type);
  }

}