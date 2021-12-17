package net.taus.data.marketplace.api.utils.sse;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.io.ProtocolResolver;

public class SecureSimpleStorageProtocolResolverConfigurer implements ApplicationContextAware, Ordered {

  private final ProtocolResolver resourceLoader;

  public SecureSimpleStorageProtocolResolverConfigurer(
    SecureSimpleStorageProtocolResolver simpleStorageProtocolResolver) {
    this.resourceLoader = simpleStorageProtocolResolver;
  }

  @Override
  public int getOrder() {
    return Ordered.HIGHEST_PRECEDENCE;
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext)
    throws BeansException {
    if (applicationContext instanceof ConfigurableApplicationContext) {
      ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) applicationContext;
      configurableApplicationContext.addProtocolResolver(this.resourceLoader);
    }
  }
}
