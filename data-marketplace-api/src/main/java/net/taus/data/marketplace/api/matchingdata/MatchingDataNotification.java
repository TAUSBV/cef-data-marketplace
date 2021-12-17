package net.taus.data.marketplace.api.matchingdata;

import lombok.Data;
import net.taus.data.marketplace.model.SystemException;
import net.taus.data.marketplace.model.matchingdata.MatchingDataRequest;
import net.taus.data.marketplace.model.matchingdata.MatchingDataRequestEvent;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class MatchingDataNotification {

  private String documentUid;
  private MatchingDataRequestEvent.Type type;
  private LocalDateTime createdAt;
  private Map<String, Long> analysisResult;
  private String matchingResult;

  // Error specific fields
  private String message;
  private String kind;

  /**
   * @return a user friendly message describing the event that took place
   */
  public String getFriendlyMessage() {
    switch (getType()) {
      case INFO -> {
        if (analysisResult != null)
          return "Analysis completed";
        else if (matchingResult != null)
          return "Matching completed";
        else
          return "Unknown state";
      }
      case WARNING -> {
        return message;
      }
      case ERROR -> {
        return (message != null && "BUSINESS".equals(kind)) ? message : SystemException.GENERIC_MESSAGE;
      }
      default -> throw new IllegalArgumentException("Unknown notification type");
    }
  }

  /**
   * Checks the notification's payload and decides what the next state should be.
   *
   * @param currentStatus the current status of the {@link MatchingDataRequest}
   * @return the new status of the {@link MatchingDataRequest}
   */
  public MatchingDataRequest.Status getNextStatus(MatchingDataRequest.Status currentStatus) {
    switch (getType()) {
      case INFO -> {
        if (analysisResult != null)
          return MatchingDataRequest.Status.MATCHING;
        else if (matchingResult != null)
          return MatchingDataRequest.Status.COMPLETED;
        else
          throw new IllegalStateException("Could not define the next state");
      }
      case WARNING -> {
        return currentStatus;
      }
      case ERROR -> {
        return MatchingDataRequest.Status.FAILED;
      }
      default -> throw new IllegalArgumentException("Unknown notification type");
    }
  }

  /**
   * Checks if the use should get notified based on the notification message contents.
   * Currently we notify on every error notification and or when the matching data results are ready
   *
   * @return true if the user should get notified
   */
  public boolean shouldNotifyUser() {
    return MatchingDataRequestEvent.Type.ERROR.equals(type) || matchingResult != null;
  }
}

