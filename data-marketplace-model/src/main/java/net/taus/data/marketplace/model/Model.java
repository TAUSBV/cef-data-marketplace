package net.taus.data.marketplace.model;

import net.taus.data.language.api.DataLanguageClient;
import net.taus.data.marketplace.model.email.EmailSenderService;
import net.taus.data.marketplace.model.price.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;

public interface Model {

  @Configuration
  @EntityScan(basePackageClasses = { Model.class })
  @EnableJpaRepositories(repositoryImplementationPostfix = "Extension")
  @EnableJpaAuditing
  @EnableCaching
  class ModelConfiguration {

    @Bean
    public SpringTemplateEngine templateEngine() {
      SpringTemplateEngine templateEngine = new SpringTemplateEngine();
      templateEngine.setTemplateResolver(templateResolver());
      return templateEngine;
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
      SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
      templateResolver.setPrefix("classpath:/templates/");
      templateResolver.setSuffix(".html");
      templateResolver.setTemplateMode(TemplateMode.HTML);
      templateResolver.setCharacterEncoding("UTF-8");
      templateResolver.setCacheable(false);
      return templateResolver;
    }

    @Bean
    @Lazy
    public EmailSenderService emailSenderService(final JavaMailSender javaMailSender, final SpringTemplateEngine templateEngine) {
      return new EmailSenderService(javaMailSender, templateEngine);
    }
  }
}
