package net.taus.data.marketplace.api.crm.models;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Getter;
import net.taus.support.crm.hubspot.models.TimelineEvent;

import java.util.Collections;
import java.util.List;

/**
 * Data Marketplace event when a user makes a new publication.
 */
@Getter
public class NewPublicationEvent implements TimelineEvent {

  int eventTemplateId;
  String email;
  String documentName;
  String status;
  String publicationUrl;
  Long unitsTotal;
  Long unitsOriginal;
  Long unitsReplica;
  Long unitsLowQuality;
  String domain;
  String contentType;
  Double pricePerWord;
  List<String> errors;
  String analysisResult;

  /**
   * Use this constructor when no further meta-data are available for the publication's document
   *
   * @param eventTemplateId The hubspot event template we want to use
   * @param email           The email of the user who made the publication
   * @param status          Then current status of the publication (ex. Uploaded, Analyzing etc.)
   * @param documentName    The new publication's document name
   */
  public NewPublicationEvent(int eventTemplateId, String email, String status, String documentName) {
    this.eventTemplateId = eventTemplateId;
    this.email = email;
    this.status = status;
    this.documentName = documentName;
    this.analysisResult = "";
    publicationUrl = "";
    unitsTotal = 0L;
    unitsOriginal = 0L;
    unitsReplica = 0L;
    unitsLowQuality = 0L;
    domain = "";
    contentType = "";
    pricePerWord = 0D;
    errors = Collections.emptyList();
  }

  /**
   * Use this constructor when all of the publication's document meta-data are available
   *
   * @param eventTemplateId The hubspot event template we want to use
   * @param email           The email of the user who made the publication
   * @param status          Then current status of the publication (ex. Uploaded, Analyzing etc.)
   * @param documentName    The new publication's document name
   * @param domain          The document's domain
   * @param contentType     The document's content tye
   * @param pricePerWord    The document's specified price per word
   * @param unitsTotal      The total number of translation units in the document
   * @param unitsOriginal   The number of original translation units
   * @param unitsReplica    The number of replica units
   * @param unitsLowQuality The number of low quality units
   */
  public NewPublicationEvent(int eventTemplateId,
                             String email,
                             String status,
                             String documentName,
                             String domain,
                             String contentType,
                             double pricePerWord,
                             long unitsTotal,
                             long unitsOriginal,
                             long unitsReplica,
                             long unitsLowQuality) {
    this(eventTemplateId, email, status, documentName);
    this.domain = domain;
    this.contentType = contentType;
    this.pricePerWord = pricePerWord;
    this.unitsTotal = unitsTotal;
    this.unitsOriginal = unitsOriginal;
    this.unitsReplica = unitsReplica;
    this.unitsLowQuality = unitsLowQuality;
    this.analysisResult = "SUCCESS";
  }


  /**
   * Use this constructor when document analysis fails
   *
   * @param eventTemplateId The hubspot event template we want to use
   * @param status          Then current status of the publication (ex. Uploaded, Analyzing etc.)
   * @param email           The email of the user who made the publication
   * @param documentName    The new publication's document name
   * @param publicationUrl  The link to the publication analysis report
   * @param errors          Any errors that occurred when publishing
   */
  public NewPublicationEvent(int eventTemplateId, String email, String status, String documentName, String publicationUrl, List<String> errors) {
    this(eventTemplateId, email, status, documentName);
    this.publicationUrl = publicationUrl;
    this.errors = errors;
    this.analysisResult = "FAIL";
  }

  /**
   * Use this constructor when document analysis succeeds
   *
   * @param eventTemplateId The hubspot event template we want to use
   * @param email           The email of the user who made the publication
   * @param status          Then current status of the publication (ex. Uploaded, Analyzing etc.)
   * @param documentName    The new publication's document name
   * @param publicationUrl  The link to the publication analysis report
   * @param unitsTotal      The total number of translation units in the document
   * @param unitsOriginal   The number of original translation units
   * @param unitsReplica    The number of replica units
   * @param unitsLowQuality The number of low quality units
   */
  public NewPublicationEvent(int eventTemplateId,
                             String email,
                             String status,
                             String documentName,
                             String publicationUrl,
                             long unitsTotal,
                             long unitsOriginal,
                             long unitsReplica,
                             long unitsLowQuality) {
    this(eventTemplateId, email, status, documentName);
    this.publicationUrl = publicationUrl;
    this.unitsTotal = unitsTotal;
    this.unitsOriginal = unitsOriginal;
    this.unitsReplica = unitsReplica;
    this.unitsLowQuality = unitsLowQuality;
    this.analysisResult = "SUCCESS";
  }


  @Override
  public JsonNode getTokens() {
    ObjectNode node = objectMapper.createObjectNode();
    node.put("status", status);
    node.put("documentName", documentName);
    node.put("publicationUrl", publicationUrl);
    node.put("unitsTotal", unitsTotal);
    node.put("unitsOriginal", unitsOriginal);
    node.put("unitsReplica", unitsReplica);
    node.put("unitsLowQuality", unitsLowQuality);
    node.put("documentDomain", domain);
    node.put("documentContentType", contentType);
    // We want to cast to int so that HS will ignore the Numeric field if it is zero. Does not work with 0 as Double
    if (pricePerWord == 0D) {
      node.put("pricePerWord", pricePerWord.intValue());
    }
    else {
      node.put("pricePerWord", pricePerWord);
    }
    node.put("analysisResult", analysisResult);
    node.put("errors", String.join("  \n", errors));

    return node;
  }

  @Override
  public JsonNode getExtraData() {
    return null;
  }
}
