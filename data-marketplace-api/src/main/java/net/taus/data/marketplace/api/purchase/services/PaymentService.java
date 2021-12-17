package net.taus.data.marketplace.api.purchase.services;

import net.taus.data.marketplace.model.purchase.Purchase;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

import javax.annotation.Nonnull;
import java.util.Map;

public abstract class PaymentService implements ApplicationEventPublisherAware {

  protected ApplicationEventPublisher publisher;

  abstract Purchase prepare(Purchase purchase, Map<String, String> context);

  @Override
  public void setApplicationEventPublisher(@Nonnull final ApplicationEventPublisher applicationEventPublisher) {
    this.publisher = applicationEventPublisher;
  }
}
