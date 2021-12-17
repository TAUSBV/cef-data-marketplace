package net.taus.data.marketplace.api.publication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import net.taus.data.marketplace.model.publication.PublicationEvent;
import net.taus.data.marketplace.model.publication.PublicationEvent.ComponentStatus;
import net.taus.data.marketplace.model.publication.PublicationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Stream;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;
import static net.taus.data.marketplace.api.publication.DataLanguageNotification.Status.FINISHED;
import static net.taus.data.marketplace.api.publication.DataLanguageNotification.Status.PROGRESS;
import static net.taus.data.marketplace.model.SystemException.GENERIC_MESSAGE;
import static net.taus.data.marketplace.model.publication.Publication.Status.REVIEW;
import static net.taus.data.marketplace.model.publication.PublicationEvent.Type.*;

@Transactional
public class DataLanguageNotificationListener {

  private final Logger logger = LoggerFactory.getLogger(DataLanguageNotificationListener.class.getCanonicalName());

  private final PublicationRepository publications;
  private final PublicationNotificationService notificationService;
  private final ObjectMapper mapper;
  private final List<DataLanguageComponent> components;

  public DataLanguageNotificationListener(final PublicationRepository publications,
                                          final PublicationNotificationService notificationService,
                                          final ObjectMapper mapper,
                                          final List<DataLanguageComponent> components) {
    this.publications = publications;
    this.notificationService = notificationService;
    this.mapper = mapper;
    this.components = components;
  }

  @SqsListener("${corporate.service.data_language.queue.notifications}")
  public void handle(final String payload) throws JsonProcessingException {
    logger.debug("Received data_language_listener_payload={}", payload);
    final DataLanguageNotification notification = mapper.readValue(payload, DataLanguageNotification.class);
    logger.info("Deserialized notification={}", notification);
    publications.findByDocumentUid(notification.getDocumentUid()).ifPresent(publication -> {
      final String componentName = notification.getComponent();
      final DataLanguageComponent component = components.stream().filter(c -> c.getName().equals(componentName))
        .findFirst().orElseGet(() -> new DataLanguageComponent(componentName, false, true));
      final ComponentStatus componentStatus = ComponentStatus.valueOf(notification.getStatus().name());
      switch (notification.getType()) {
        case ERROR -> {
          final PublicationEvent.Type type = component.isContinueOnError() ? WARNING : ERROR;
          switch (notification.getKind()) {
            case SYSTEM -> publication.addEvent(component.getName(), componentStatus, type, GENERIC_MESSAGE);
            case BUSINESS -> Stream.of(notification.getMessage().split("\n\t"))
              .forEach(m -> publication.addEvent(component.getName(), componentStatus, type, m));
          }
        }
        case INFO -> {
          if (notification.getStatus().equals(FINISHED)) {
            publication.addEvent(component.getName(), componentStatus, INFO, format("%s has finished", component));
          }
        }
      }
      final List<String> required = components.stream().filter(DataLanguageComponent::isRequired)
        .map(DataLanguageComponent::getName).distinct().collect(toList());
      if (publication.hasErrors()) {
        notificationService.notifyErrorIfNecessary(publication, notification);
      }
      else if (publication.hasAllRequiredAnalysisComponentsFinished(required)) {
        publication.moveToStatus(REVIEW);
        notificationService.notifySuccessIfNecessary(publication, notification);
      }
      else if (notification.getStatus().equals(PROGRESS)) {
        notificationService.notifyScreenAboutSuccessIfNecessary(publication, notification);
      }
      publications.save(publication);
    });
  }

  @MessageExceptionHandler(Throwable.class)
  void handleException(Throwable e) {
    logger.error(e.getMessage(), e);
  }

}
