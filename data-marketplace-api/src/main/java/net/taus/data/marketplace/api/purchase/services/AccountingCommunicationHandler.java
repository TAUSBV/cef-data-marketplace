package net.taus.data.marketplace.api.purchase.services;

import net.taus.data.marketplace.model.email.Email;
import net.taus.data.marketplace.model.email.EmailSenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.SendResult;
import org.springframework.lang.NonNull;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class AccountingCommunicationHandler implements ListenableFutureCallback<SendResult<String, AccountingService.Transaction>> {

  protected final Logger logger = LoggerFactory.getLogger(AccountingCommunicationHandler.class.getCanonicalName());
  private final ScheduledExecutorService scheduler;
  private final EmailSenderService emailService;
  private final AccountingService.Transaction transaction;
  private Consumer<SendResult<String, AccountingService.Transaction>> onSuccess;

  public AccountingCommunicationHandler(final AccountingService.Transaction transaction,
                                        final EmailSenderService emailService,
                                        final int reconnectWarning) {
    this.scheduler = Executors.newScheduledThreadPool(1);
    this.scheduler.schedule(this::warnAdminAboutIntegrationDelay, reconnectWarning, TimeUnit.MILLISECONDS);
    this.emailService = emailService;
    this.transaction = transaction;
    this.onSuccess = null;
  }

  public AccountingCommunicationHandler(final AccountingService.Transaction transaction,
                                        final EmailSenderService emailService,
                                        final int reconnectWarning,
                                        final Consumer<SendResult<String, AccountingService.Transaction>> onSuccess) {
    this(transaction, emailService, reconnectWarning);
    this.onSuccess = onSuccess;
  }

  @Override
  public void onSuccess(final SendResult<String, AccountingService.Transaction> result) {
    logger.info("Successfully published transaction {}.", result);
    if (!scheduler.isShutdown()) scheduler.shutdownNow();
    Optional.ofNullable(onSuccess).ifPresent(f -> f.accept(result));
  }

  @Override
  public void onFailure(@NonNull Throwable ex) {
    logger.error(ex.getMessage());
    warnAdminAboutIntegrationIssue(ex.getMessage());
  }

  /**
   * Send a warning email about Kafka to DMP administrators
   */
  public void warnAdminAboutIntegrationDelay() {
    logger.warn("Sending warning email! Kafka broker down?");
    final var email = new Email.Builder()
      .subject("Kafka producer failure")
      .addRecipient("datamarketplace@taus.net")
      .template(Email.Template.PURCHASE_INVOICE_ISSUE_REQUEST_WARNING_ADMIN)
      .build();
    try {
      emailService.send(email);
    }
    catch (Exception e) {
      logger.error("Failed to send email as Kafka produce fallback!", e);
    }
  }

  /**
   * Send an error email to DMP admin
   *
   * @param exceptionMessage The reason why publish failed
   */
  public void warnAdminAboutIntegrationIssue(final String exceptionMessage) {
    final Map<String, Object> metadata = Map.of(
      "saleBody", transaction,
      "exceptionMessage", exceptionMessage
    );
    final var email = new Email.Builder()
      .subject(String.format("Failure for Transaction %s", transaction.getStateValue()))
      .addRecipient("datamarketplace@taus.net")
      .replyTo(Email.NO_REPLY)
      .template(Email.Template.PURCHASE_INVOICE_ISSUE_REQUEST_ERROR_ADMIN)
      .templateData(metadata)
      .build();
    try {
      emailService.send(email);
    }
    catch (Exception e) {
      logger.error("Failed to send email as Kafka produce fallback!", e);
    }
  }

}
