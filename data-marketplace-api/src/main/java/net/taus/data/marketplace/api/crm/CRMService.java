package net.taus.data.marketplace.api.crm;

import net.taus.data.language.api.model.Language;
import net.taus.data.language.api.model.TranslationDocumentStatistics;
import net.taus.data.marketplace.api.crm.models.MatchingDataEvent;
import net.taus.data.marketplace.api.crm.models.NewDownloadEvent;
import net.taus.data.marketplace.api.crm.models.NewPublicationEvent;
import net.taus.data.marketplace.api.crm.models.NewPurchaseEvent;
import net.taus.data.marketplace.api.purchase.events.PurchaseDownloadEvent;
import net.taus.data.marketplace.api.purchase.services.PurchaseService;
import net.taus.data.marketplace.model.BusinessException;
import net.taus.data.marketplace.model.SystemException;
import net.taus.data.marketplace.model.matchingdata.MatchingDataRequest;
import net.taus.data.marketplace.model.matchingdata.MatchingDataRequestEvent;
import net.taus.data.marketplace.model.purchase.Download;
import net.taus.data.marketplace.model.purchase.Purchase;
import net.taus.data.marketplace.model.user.User;
import net.taus.support.crm.CRMClient;
import net.taus.support.crm.hubspot.models.Contact;
import net.taus.support.crm.models.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service handling communication with a CRM
 */
public class CRMService {

  private final Logger logger = LoggerFactory.getLogger(CRMService.class.getCanonicalName());
  private final CRMClient crmClient;
  private final PurchaseService purchaseService;
  private final int newPublicationTemplateId;
  private final int newPurchaseTemplateId;
  private final int newDownloadTemplateId;
  private final int matchingDataTemplateId;
  private final Converter<UUID, String> uuidStringConverter;
  private final String appUiUrl;

  // TODO Move this as dependency?
  private static final DecimalFormat EURO_FORMAT = new DecimalFormat("€0.00");

  /**
   * Default all-arg constructor
   *
   * @param crmClient                The CRM client implementation
   * @param purchaseService          Service responsible for purchase related actions such as generating downloads links
   * @param newPublicationTemplateId The template we want to use for new publication events
   * @param newPurchaseTemplateId    The template we want to use for new purchase events
   * @param newDownloadTemplateId    The template we want to use for new download request events
   */
  public CRMService(final CRMClient crmClient,
                    final PurchaseService purchaseService,
                    final int newPublicationTemplateId,
                    final int newPurchaseTemplateId,
                    final int newDownloadTemplateId,
                    final int matchingDataTemplateId,
                    final Converter<UUID, String> uuidStringConverter,
                    final String appUiUrl) {
    this.crmClient = crmClient;
    this.purchaseService = purchaseService;
    this.newPublicationTemplateId = newPublicationTemplateId;
    this.newPurchaseTemplateId = newPurchaseTemplateId;
    this.newDownloadTemplateId = newDownloadTemplateId;
    this.matchingDataTemplateId = matchingDataTemplateId;
    this.uuidStringConverter = uuidStringConverter;
    this.appUiUrl = appUiUrl;
  }

  /**
   * Create a user in the CRM
   *
   * @param user The user we want to create
   */
  public void createUser(User user) {
    Contact contact = new Contact(
      user.getEmail(),
      user.getPrefix(),
      user.getFirstName(),
      user.getLastName(),
      user.getDetails().getCountry(),
      user.getDetails().getCompany(),
      null,
      null,
      user.isTermsOfServiceConsent(),
      user.isPrivacyPolicyConsent(),
      user.isPaymentTermConsent()
    );
    crmClient.createUserAsync(contact).whenCompleteAsync((r, e) -> {
      if (e != null)
        logger.error(e.getMessage(), e);
      logger.info(r.body());
    });
  }

  /**
   * Communicate to CRM that a document has been uploaded during publication process
   *
   * @param email        The email of the user that is publishing
   * @param status       The status of the {@link net.taus.data.marketplace.model.publication.Publication}
   * @param documentName The document's name
   */
  public void publicationUploaded(String email, String status, String documentName) {
    NewPublicationEvent event = new NewPublicationEvent(newPublicationTemplateId, email, status, documentName);
    eventWrapper(event);
  }

  /**
   * Communicate to CRM that a document has been published during publication process
   *
   * @param email             The email of the user that is publishing
   * @param status            The status of the {@link net.taus.data.marketplace.model.publication.Publication}
   * @param documentName      The document's name
   * @param domain            The document's domain
   * @param contentType       The document's content type
   * @param pricePerWord      The specified price per wor for the document
   * @param currentStatistics The document's analysis statistics
   */
  public void publicationPublished(String email,
                                   String status,
                                   String documentName,
                                   String domain,
                                   String contentType,
                                   double pricePerWord,
                                   final TranslationDocumentStatistics currentStatistics) {
    NewPublicationEvent event = new NewPublicationEvent(newPublicationTemplateId, email, status, documentName, domain,
      contentType, pricePerWord, currentStatistics.getTotalUnits(), currentStatistics.getTotalUnitsOriginal(),
      currentStatistics.getTotalUnitsReplica(), currentStatistics.getTotalUnitsLowQuality());
    eventWrapper(event);
  }

  /**
   * Communicate to CRM about a purchase request and it's status
   *
   * @param purchase The purchase that is ongoing
   */
  public void purchaseStatusChanged(Purchase purchase) {
    final var purchaseUid = uuidStringConverter.convert(purchase.getUid());
    try {
      final List<Download> downloads = purchase.isPaid() ? purchaseService.createDownload(purchase) : new ArrayList<>();

      List<NewPurchaseEvent.Item> items = new ArrayList<>();
      purchase.getItems().forEach(i ->
      {
        Download download = downloads.stream().filter(d->d.getItem().equals(i)).findFirst().orElse(null);
        items.add(new NewPurchaseEvent.Item(i.getProduct().getDescription(),
          new DecimalFormat("#%").format(i.getAmount()),
          String.valueOf(i.getTotalSourceWordCount()),
          i.getProduct().getSourceLanguage().map(Language::getName).orElse(null),
          String.valueOf(i.getTotalUnits()),
          EURO_FORMAT.format(i.getPrice()),
          download==null?null:Download.getUrl(appUiUrl, uuidStringConverter.convert(download.getUid()))
        ));
      });

      final var status = purchase.getStatus().name();
      final var purchaseUrl = String.format("%s/purchases/%s", appUiUrl, purchaseUid);
      var statusLegible = status.replaceAll("_", " ");
      final var subject = switch (purchase.getStatus()) {
        case RECEIVED -> String.format("TAUS Data Marketplace purchase request %s, number: %s", statusLegible.toLowerCase(), purchaseUid);
        case AWAITING_PAYMENT -> String.format("TAUS Data Marketplace purchase %s", statusLegible.toLowerCase());
        case AWAITING_INVOICE -> "TAUS Data Marketplace purchase update";
        case COMPLETED -> String.format("TAUS Data Marketplace successful payment for purchase %s. Download your data!", purchaseUid);
      };

      var event = new NewPurchaseEvent(newPurchaseTemplateId,
        purchase.getBuyer().getEmail(),
        purchaseUid,
        subject,
        statusLegible,
        DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").format(purchase.getUpdatedAt()),
        purchaseUrl,
        String.format("%s", appUiUrl),
        items
      );

      eventWrapper(event);
    }
    catch (Exception e) {
      logger.error(String.format("Could not construct body for CRM NewPurchaseEvent for created purchase uid %s.", purchaseUid), e);
    }
  }

  /**
   * Communicate to CRM that a user has made a download request about purchased items
   *
   * @param downloadEvent The event that is triggered when a user makes such a request
   */
  public void purchaseDownload(final PurchaseDownloadEvent downloadEvent) {
    final var purchase = downloadEvent.getPurchase();
    final var purchaseUid = uuidStringConverter.convert(purchase.getUid());
    final var downloads = downloadEvent.getDownloads();
    final var subject = String.format("TAUS Data Marketplace download the data of your purchase %s", purchaseUid);

    try {
      final List<NewDownloadEvent.Item> items = downloads.stream().filter(Objects::nonNull).map(d ->
        new NewDownloadEvent.Item(d.getItem().getProduct().getDescription(),
          Download.getUrl(appUiUrl, uuidStringConverter.convert(d.getUid()))
        )
      ).collect(Collectors.toList());

      var event = new NewDownloadEvent(newDownloadTemplateId,
        purchase.getBuyer().getEmail(),
        purchaseUid,
        subject,
        String.format("%s/purchases/%s", appUiUrl, purchaseUid),
        String.format("%s", appUiUrl),
        items);

      eventWrapper(event);
    }
    catch (Exception e) {
      logger.error(String.format("Could not construct body for CRM NewDownloadEvent for created purchase uid %s.", purchaseUid), e);
    }
  }

  public void newPublicationAnalysisFailure(String email, String status, String documentName, String publicationUrl, List<String> errors) {
    NewPublicationEvent newPublicationEvent = new NewPublicationEvent(newPublicationTemplateId, email, status, documentName, publicationUrl, errors);
    eventWrapper(newPublicationEvent);
  }

  public void newPublicationAnalysisSuccess(String email, String status, String documentName, String publicationUrl, long unitsTotal, long unitsOriginal, long unitsReplica, long unitsLowQuality) {
    NewPublicationEvent newPublicationEvent = new NewPublicationEvent(newPublicationTemplateId, email, status, documentName, publicationUrl, unitsTotal, unitsOriginal, unitsReplica, unitsLowQuality);
    eventWrapper(newPublicationEvent);
  }

  public void matchingDataRequestEvent(MatchingDataRequest matchingDataRequest) {
    MatchingDataEvent event = new MatchingDataEvent(matchingDataTemplateId,
      matchingDataRequest.getEmail(),
      matchingDataRequest.getStatus().name(),
      matchingDataRequest.getFileName(),
      MatchingDataRequest.getUrl(appUiUrl, uuidStringConverter.convert(matchingDataRequest.getUid())),
      matchingDataRequest.getErrors().stream().map(MatchingDataRequestEvent::getDescription).collect(Collectors.toList()));
    eventWrapper(event);
  }

  public HttpResponse<String> getForm(String formId) throws SystemException, BusinessException {
    HttpResponse<String> form;
    try {
      form = crmClient.getForm(formId);
      if (form.statusCode() >= 400 && form.statusCode() < 499) {
        throw new BusinessException(form.body());
      }
      else if (form.statusCode() >= 500) {
        throw new SystemException(form.body());
      }
    }
    catch (IOException | InterruptedException e) {
      throw new SystemException(e.getMessage(), e);
    }
    return form;
  }


  private void eventWrapper(Event event) {
    crmClient.createEventAsync(event).whenCompleteAsync((r, e) -> {
      if (e != null)
        logger.error(("Execution Exception: " + e.getMessage()), e);
      if (!isSuccessful(r.statusCode()))
        logger.error(r.body());
    });
  }

  private boolean isSuccessful(int statusCode) {
    return (statusCode == 200 || statusCode == 201);
  }

}
