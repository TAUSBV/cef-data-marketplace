package net.taus.data.marketplace.api.matchingdata;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.messaging.listener.SqsMessageDeletionPolicy;
import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import net.taus.data.marketplace.api.crm.CRMService;
import net.taus.data.marketplace.model.matchingdata.MatchingDataRequest;
import net.taus.data.marketplace.model.matchingdata.MatchingDataRequestException;
import net.taus.data.marketplace.model.matchingdata.MatchingDataRequestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Transactional
public class MatchingDataNotificationListener {

  private final Logger logger = LoggerFactory.getLogger(MatchingDataNotificationListener.class.getCanonicalName());

  private final MatchingDataRequestRepository matchingDataRequestRepository;
  private final MatchingDataService matchingDataService;
  private final CRMService crmService;
  private final ObjectMapper objectMapper;

  public MatchingDataNotificationListener(MatchingDataRequestRepository matchingDataRequestRepository,
                                          final MatchingDataService service, final CRMService crmService, final ObjectMapper objectMapper) {
    this.matchingDataRequestRepository = matchingDataRequestRepository;
    this.matchingDataService = service;
    this.crmService = crmService;
    this.objectMapper = objectMapper;
  }

  @SqsListener(value = "${corporate.service.matching_data.queue.notifications}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
  public void receive(@Payload final MatchingDataNotification payload) throws JsonProcessingException {
    logger.info(payload.toString());

    if (payload.getDocumentUid() == null) {
      logger.warn("Cannot handle message because of missing required property 'documentUid'");
    }
    else {
      // Find the matching data request entry
      UUID requestUid = UUID.fromString(payload.getDocumentUid());
      MatchingDataRequest matchingDataRequest = matchingDataRequestRepository.findByUid(requestUid)
        .orElseThrow(() -> new MatchingDataRequestException("Could not find matching data request with uid " + payload.getDocumentUid()));

      // Check if there is an analysisResult (both ERROR and INFO) and set it to the request
      // We don't want to reset to null on events that don't have analysis results
      if (payload.getAnalysisResult() != null)
        matchingDataRequest.setAnalysisResult(objectMapper.writeValueAsString(payload.getAnalysisResult()));

      // We need to set the request status after we add the event as we want the event to have the status
      // before we received the notification
      matchingDataRequest.addEvent(payload.getType(), payload.getFriendlyMessage());
      matchingDataRequest.setStatus(payload.getNextStatus(matchingDataRequest.getStatus()));
      matchingDataRequestRepository.save(matchingDataRequest);

      // If the results are ready, do a preemptive fetch and cache of results so the user will access them faster he first time
      if (payload.getMatchingResult() != null)
        matchingDataService.getMatchingDataResultAsync(matchingDataRequest);

      // Send a new Event to the CRM Service if the user should be notified
      if (payload.shouldNotifyUser())
        crmService.matchingDataRequestEvent(matchingDataRequest);

      logger.info("Notification for request " + payload.getDocumentUid() + " handled successfully");
    }
  }

  @MessageExceptionHandler(Throwable.class)
  void handleException(Throwable e) {
    logger.error(e.getMessage(), e);
  }

}
