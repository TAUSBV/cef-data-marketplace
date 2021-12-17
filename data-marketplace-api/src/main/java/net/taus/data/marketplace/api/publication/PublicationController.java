package net.taus.data.marketplace.api.publication;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.taus.data.language.api.DataLanguageClient;
import net.taus.data.language.api.model.ContentType;
import net.taus.data.language.api.model.Domain;
import net.taus.data.language.api.model.TranslationDocument;
import net.taus.data.language.api.model.TranslationDocumentStatistics;
import net.taus.data.marketplace.api.base.BaseController;
import net.taus.data.marketplace.api.crm.CRMService;
import net.taus.data.marketplace.api.price.PriceSuggestionService;
import net.taus.data.marketplace.api.security.UserSession;
import net.taus.data.marketplace.api.user.UserService;
import net.taus.data.marketplace.api.validation.DocumentPrice;
import net.taus.data.marketplace.model.BusinessException;
import net.taus.data.marketplace.model.SystemException;
import net.taus.data.marketplace.model.languagedata.TranslationDocumentPublishingSummary;
import net.taus.data.marketplace.model.notification.Notification;
import net.taus.data.marketplace.model.notification.NotificationRepository;
import net.taus.data.marketplace.model.publication.*;
import net.taus.data.marketplace.model.publication.views.PublicationSummary;
import net.taus.data.marketplace.model.user.User;
import org.apache.tomcat.util.http.fileupload.FileItemStream;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.WritableResource;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static net.taus.data.marketplace.model.notification.Notification.NotificationStatus.WAITING;
import static net.taus.data.marketplace.model.notification.Notification.NotificationType.EMAIL;
import static net.taus.data.marketplace.model.publication.Publication.Status.ANALYZING;
import static net.taus.data.marketplace.model.publication.Publication.Status.REVIEW;
import static net.taus.data.marketplace.model.publication.PublicationEvent.ComponentStatus.FINISHED;
import static org.springframework.http.ResponseEntity.*;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMethodName;

@RestController
@RequestMapping("/publications")
@Validated
public class PublicationController extends BaseController {

  private final ResourceLoader resourceLoader;
  private final PublicationRepository publications;
  private final NotificationRepository notifications;
  private final PublicationNotificationRepository publicationNotifications;
  private final PriceSuggestionService priceSuggestionService;
  private final UserService userService;
  private final CRMService crmService;
  private final DataLanguageClient dataLanguageClient;
  private final ObjectMapper objectMapper;
  private final Converter<UUID, String> uuidStringConverter;
  private final String assetsPathPrefix;
  private final String appUiUrl;

  @Autowired
  public PublicationController(final ResourceLoader resourceLoader,
                               final PublicationRepository publications,
                               final NotificationRepository notifications,
                               final PublicationNotificationRepository publicationNotifications,
                               final PriceSuggestionService priceSuggestionService,
                               final UserService userService,
                               final CRMService crmService,
                               final DataLanguageClient dataLanguageClient,
                               final ObjectMapper objectMapper,
                               final Converter<UUID, String> uuidStringConverter,
                               @Value("${corporate.service.data_language.assets.remote.path}") final String assetsPathPrefix,
                               @Value("${app.ui.url}") final String appUiUrl) {
    this.resourceLoader = resourceLoader;
    this.dataLanguageClient = dataLanguageClient;
    this.publications = publications;
    this.notifications = notifications;
    this.publicationNotifications = publicationNotifications;
    this.objectMapper = objectMapper;
    this.priceSuggestionService = priceSuggestionService;
    this.userService = userService;
    this.crmService = crmService;
    this.uuidStringConverter = uuidStringConverter;
    this.assetsPathPrefix = assetsPathPrefix;
    this.appUiUrl = appUiUrl;
  }

  @GetMapping(value = "/{id}")
  @ResponseBody
  public ResponseEntity<PublicationSummary> findByUid(@PathVariable UUID id) {
    final var summary = publications.findSummaryByUid(id);
    return summary.map(this::decorate).map(ResponseEntity::ok).orElseGet(() -> notFound().build());
  }

  @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  @ResponseBody
  public ResponseEntity<PublicationSummary> create(final HttpServletRequest request,
                                                   @AuthenticationPrincipal UserSession userSession) throws PublicationException, SystemException {
    boolean isMultipart = ServletFileUpload.isMultipartContent(request);
    if (!isMultipart) throw new PublicationException("Translation Document missing");
    ServletFileUpload upload = new ServletFileUpload();
    boolean uploaded = false;
    final Optional<User> user = Optional.ofNullable(userSession).flatMap(u -> userService.findByUid(userSession.getUserUid()));
    UUID documentUid = null;
    String fileName = null;
    Map<String, Object> metadata = new HashMap<>(4);
    try {
      final var iterator = upload.getItemIterator(request);
      while (iterator.hasNext()) {
        FileItemStream item = iterator.next();
        if (!item.isFormField()) {
          documentUid = UUID.randomUUID();
          fileName = item.getName();
          final var location = assetsPathPrefix + "/" + documentUid + ".zip";
          final var resource = (WritableResource) resourceLoader.getResource(location);
          try (final var stream = item.openStream();
               final var outputStream = resource.getOutputStream();
               final var zos = new ZipOutputStream(new BufferedOutputStream(outputStream))) {
            zos.putNextEntry(new ZipEntry(fileName));
            final long size = stream.transferTo(zos);
            zos.closeEntry();
            metadata.put("uid", documentUid.toString());
            metadata.put("name", fileName);
            metadata.put("size", size);
            user.ifPresent(u -> metadata.put("ownerCorporateId", u.getCorporateId()));
            zos.putNextEntry(new ZipEntry(documentUid + ".json"));
            zos.write(objectMapper.writeValueAsBytes(metadata));
            zos.closeEntry();
            uploaded = true;
          }
        }
      }
      if (!uploaded) throw new SystemException("Fail to transfer file to repository");
    }
    catch (IOException e) {
      throw new SystemException("Fail to transfer file to repository", e);
    }
    var publication = new Publication();
    final Publication.Status status = publication.getStatus();
    publication.setDocumentUid(documentUid);
    publication.moveToStatus(ANALYZING);
    publication = publications.save(publication);

    final String documentName = fileName;
    user.ifPresent(u -> crmService.publicationUploaded(u.getEmail(), status.name(), documentName));
    final var uri = fromMethodName(PublicationController.class, "findByUid", publication.getUid()).build().toUri();
    final var summary = publications.findSummaryByUid(publication.getUid());
    return summary.map(s -> created(uri).body(s)).orElseGet(() -> unprocessableEntity().build());
  }

  @PutMapping(value = { "/{uid}", "/{uid}/" })
  @ResponseBody
  public ResponseEntity<PublicationSummary> save(@PathVariable("uid") final UUID publicationId,
                                                 @RequestParam("domainId") final Long domainId,
                                                 @RequestParam("contentTypeId") final Long contentTypeId,
                                                 @RequestParam(value = "description", required = false) final String description,
                                                 @RequestParam(value = "price", required = false)
                                                 @DocumentPrice
                                                 @NumberFormat(pattern = "#.###") final Double pricePerWord,
                                                 @RequestParam(value = "anonymize", defaultValue = "false") final boolean anonymize,
                                                 @AuthenticationPrincipal final UserSession userSession) throws PublicationException {
    final var publication = publications.findByUid(publicationId);
    return publication.flatMap(p -> dataLanguageClient.findTranslationDocumentByUid(p.getDocumentUid()).flatMap(d -> {
      final var user = userService.findByUid(userSession.getUserUid()).orElseThrow(() -> new BusinessException("Missing owner."));
      if (!p.getStatus().equals(REVIEW)) {
        throw new PublicationException("Invalid publication state.");
      }
      Double documentPricePerWord = pricePerWord;
      if (Objects.isNull(documentPricePerWord)) {
        final Domain domain = dataLanguageClient.getDomains().stream().findFirst()
          .orElseThrow(() -> new BusinessException("Missing domain"));
        final ContentType contentType = dataLanguageClient.getContentTypes().stream().findFirst()
          .orElseThrow(() -> new BusinessException("Missing content type"));
        final var priceSuggestion = priceSuggestionService.getPriceSuggestion(d.getSourceLanguage().getId(),
          d.getTargetLanguage().getId(), domain.getId(), contentType.getId());
        documentPricePerWord = priceSuggestion.getSuggestedPricePerWord();
      }
      p.nextStatus();
      var documentPublished = dataLanguageClient.publish(p.getDocumentUid(), domainId,
        contentTypeId, user.getCorporateId(), description, documentPricePerWord, anonymize).get();
      p.nextStatus(FINISHED);
      publications.save(p);
      crmService.publicationPublished(user.getEmail(), p.getStatus().name(), documentPublished.getName(),
        documentPublished.getDomain().getDescription(), documentPublished.getContentType().getDescription(),
        documentPublished.getPricePerWord(), documentPublished.getCurrentStatistics().orElseGet(TranslationDocumentStatistics::new));
      var summary = publications.findSummaryByUid(publicationId);
      return summary.map(s -> decorate(s, documentPublished, user)).map(ResponseEntity::ok);
    })).orElseGet(() -> notFound().build());
  }

  @PutMapping("/{uid}/notify")
  public ResponseEntity<?> createNotification(@PathVariable("uid") final UUID uid,
                                              @RequestParam("r") final String recipient) {
    final var publication = publications.findByUid(uid);
    return publication.map(p -> {
      if (!(p.getStatus().equals(Publication.Status.RECEIVED) ||
        p.getStatus().equals(Publication.Status.ANALYZING))) {
        throw new PublicationException("Invalid publication state.");
      }

      publicationNotifications.findAllById_Publication(p)
        .stream()
        .filter(pn -> pn.getId().getNotification().getType().equals(EMAIL))
        .filter(pn -> pn.getId().getNotification().getStatus().equals(WAITING))
        .findAny()
        .ifPresentOrElse(pn -> {
            pn.getId().getNotification().setRecipient(recipient);
            publicationNotifications.save(pn);
          },
          () -> {
            var notification = new Notification(EMAIL, recipient);
            notification.addMetaData("publicationUrl", String.format("%s/publications/%s?r=true", this.appUiUrl, uuidStringConverter.convert(uid)));
            notification = notifications.save(notification);
            publicationNotifications.save(new PublicationNotification(p, notification));
          });
      return new ResponseEntity<>(HttpStatus.OK);
    }).orElseGet(() -> notFound().build());
  }

  private PublicationSummary decorate(final PublicationSummary publicationSummary) {
    final String documentStatus = "NEW";
    final String unitQuality = "LOW";
    final int numberOfUnits = 10;
    final boolean annotations = true;
    dataLanguageClient.findTranslationDocumentWithUnitsByDocumentUidAndDocumentStatusAndUnitQualityAndAnnotations(publicationSummary.getDocumentUid(), documentStatus, unitQuality, numberOfUnits, annotations)
      .ifPresent(d -> publicationSummary.setDocument(new TranslationDocumentPublishingSummary(d)));
    return publicationSummary;
  }

  private PublicationSummary decorate(final PublicationSummary publicationSummary, final TranslationDocument document, final User seller) {
    publicationSummary.setDocument(new TranslationDocumentPublishingSummary(document, seller));
    return publicationSummary;
  }

  @ExceptionHandler(MissingServletRequestPartException.class)
  public ResponseEntity<?> handleMissingFile(MissingServletRequestPartException ignored) {
    return unprocessableEntity().body("Translation document is mandatory.");
  }

  @ExceptionHandler(MultipartException.class)
  public ResponseEntity<?> handleNotMultipartException(MultipartException ignored) {
    return unprocessableEntity().body("Translation document is mandatory.");
  }

  @ExceptionHandler(PublicationException.class)
  public ResponseEntity<?> handlePublicationException(PublicationException exc) {
    return unprocessableEntity().body(exc.getMessage());
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException exc) {
    return unprocessableEntity().body(exc.getMessage());
  }

}