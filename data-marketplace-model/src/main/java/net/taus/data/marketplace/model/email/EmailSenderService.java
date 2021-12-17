package net.taus.data.marketplace.model.email;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.nio.charset.StandardCharsets;


public class EmailSenderService {

  private final JavaMailSender emailSender;
  private final SpringTemplateEngine templateEngine;

  public EmailSenderService(final JavaMailSender javaMailSender,
                            final SpringTemplateEngine templateEngine) {
    this.emailSender = javaMailSender;
    this.templateEngine = templateEngine;
  }

  @Async
  public void sendAsync(Email mail) throws Exception {
    send(mail);
  }

  public void send(Email mail) throws Exception {
    var message = emailSender.createMimeMessage();
    var helper = new MimeMessageHelper(
      message,
      MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
      StandardCharsets.UTF_8.name()
    );
    helper.setFrom(mail.getSender());
    helper.setTo(mail.getRecipients().toArray(String[]::new));
    helper.setReplyTo(mail.getReplyTo());
    helper.setSubject(mail.getSubject());
    var context = new Context();
    context.setVariables(mail.getTemplateData());
    var html = templateEngine.process(mail.getTemplate().getName(), context);
    helper.setText(html, true);
    emailSender.send(message);
  }
}