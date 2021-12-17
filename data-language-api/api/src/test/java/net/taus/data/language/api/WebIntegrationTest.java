package net.taus.data.language.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.net.URI;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.templates.TemplateFormats.markdown;

@ExtendWith(RestDocumentationExtension.class)
public class WebIntegrationTest extends IntegrationTest {

  //  @RegisterExtension
//  final RestDocumentationExtension restDocumentation = new RestDocumentationExtension("../docs/api");
  protected MockMvc mockMvc;
  @Value("${app.url.base:http://data-language.local.taus.net:8090}")
  private URI uri;

  @BeforeEach
  public void setUp(final WebApplicationContext webApplicationContext,
                    final RestDocumentationContextProvider restDocumentation) {
    // @formatter:off
    this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
      .apply(documentationConfiguration(restDocumentation)
        .uris()
          .withScheme(uri.getScheme())
          .withHost(uri.getHost())
          .withPort(uri.getPort())
        .and()
        .operationPreprocessors()
          .withRequestDefaults(prettyPrint())
          .withResponseDefaults(prettyPrint())
        .and()
        .snippets()
          .withTemplateFormat(markdown()))
      .build();
    // @formatter:on
  }

}
