package net.taus.data.marketplace.api;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.awspring.cloud.messaging.config.QueueMessageHandlerFactory;
import io.awspring.cloud.messaging.config.SimpleMessageListenerContainerFactory;
import io.awspring.cloud.messaging.core.QueueMessagingTemplate;
import io.awspring.cloud.messaging.listener.SqsMessageDeletionPolicy;
import net.taus.corporate.user.client.CorporateUserService;
import net.taus.data.language.api.DataLanguageClient;
import net.taus.data.language.api.DataLanguageSearchClient;
import net.taus.data.language.api.web.WebClient;
import net.taus.data.marketplace.api.base.CountrySerializer;
import net.taus.data.marketplace.api.crm.CRMService;
import net.taus.data.marketplace.api.matchingdata.MatchingDataNotificationListener;
import net.taus.data.marketplace.api.matchingdata.MatchingDataService;
import net.taus.data.marketplace.api.price.PriceIndexService;
import net.taus.data.marketplace.api.price.PriceSuggestionService;
import net.taus.data.marketplace.api.publication.DataLanguageComponent;
import net.taus.data.marketplace.api.publication.DataLanguageNotificationListener;
import net.taus.data.marketplace.api.publication.PublicationNotificationService;
import net.taus.data.marketplace.api.purchase.TranslationUnitStreamer;
import net.taus.data.marketplace.api.purchase.events.*;
import net.taus.data.marketplace.api.purchase.services.*;
import net.taus.data.marketplace.api.security.auth.JwtService;
import net.taus.data.marketplace.api.security.auth.SessionAuthenticationFilter;
import net.taus.data.marketplace.api.security.auth.SessionService;
import net.taus.data.marketplace.api.security.auth.provider.CorporateAuthenticationService;
import net.taus.data.marketplace.api.seller.provider.CorporatePaymentService;
import net.taus.data.marketplace.api.user.UserPaymentService;
import net.taus.data.marketplace.api.user.UserService;
import net.taus.data.marketplace.api.utils.sse.SecureSimpleStorageProtocolResolver;
import net.taus.data.marketplace.api.utils.sse.SecureSimpleStorageProtocolResolverConfigurer;
import net.taus.data.marketplace.api.validation.Country;
import net.taus.data.marketplace.api.validation.VATValidator;
import net.taus.data.marketplace.model.Model;
import net.taus.data.marketplace.model.email.EmailSenderService;
import net.taus.data.marketplace.model.matchingdata.MatchingDataRequestRepository;
import net.taus.data.marketplace.model.price.*;
import net.taus.data.marketplace.model.product.ProductRepository;
import net.taus.data.marketplace.model.publication.PublicationNotificationRepository;
import net.taus.data.marketplace.model.publication.PublicationRepository;
import net.taus.data.marketplace.model.purchase.DownloadRepository;
import net.taus.data.marketplace.model.purchase.PurchaseRepository;
import net.taus.data.marketplace.model.seller.payment.SellerPaymentRepository;
import net.taus.data.marketplace.model.support.SlugIdDeserializer;
import net.taus.data.marketplace.model.support.SlugIdSerializer;
import net.taus.data.marketplace.model.support.UUIDDeserializer;
import net.taus.data.marketplace.model.support.UUIDSerializer;
import net.taus.data.marketplace.model.user.SlugId;
import net.taus.data.marketplace.model.user.UserRepository;
import net.taus.payment.paypal.client.PaypalClient;
import net.taus.payment.stripe.client.StripeClient;
import net.taus.support.crm.CRMClient;
import net.taus.support.crm.hubspot.HubSpotClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.io.ResourceLoader;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.lang.NonNull;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.handler.annotation.support.PayloadMethodArgumentResolver;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.net.http.HttpClient;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static io.awspring.cloud.messaging.listener.SqsMessageDeletionPolicy.NO_REDRIVE;
import static java.util.Collections.singletonList;
import static org.springframework.http.HttpMethod.*;

@SpringBootApplication(scanBasePackageClasses = { Application.class, Application.SecurityConfigurer.class, Model.class })
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Configuration
  @EnableAsync
  public static class ApplicationConfig {

    private final String[] allowedOrigins;

    public ApplicationConfig(@Value("${app.allowed_origins}") final String allowedOrigins) {
      this.allowedOrigins = allowedOrigins.split(",");
    }

    @Bean
    public DataLanguageClient dataLanguageClient(@Value("${corporate.service.data_language.url:http://localhost:8090}") final String url) {
      return new DataLanguageClient(new WebClient(url, HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(30)).build(), internalMapper()));
    }

    @Bean
    public DataLanguageSearchClient dataLanguageSearchClient(@Value("${corporate.service.data_language.url:http://localhost:8090}") final String url) {
      return new DataLanguageSearchClient(new WebClient(url, HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(30)).build(), internalMapper()));
    }

    @Bean
    public PriceSuggestionService priceSuggestionService(LanguageDataAvailabilityCountRepository languageDataAvailabilityCounts,
                                                         LanguageDataAvailabilityCategoryRepository languageDataAvailabilityCategories,
                                                         PriceConfigurationRepository priceConfigurations) {
      return new PriceSuggestionService(languageDataAvailabilityCounts, languageDataAvailabilityCategories, priceConfigurations);
    }

    @Bean
    public PriceIndexService priceIndexService(final LanguageDataAvailabilityCountRepository languageDataAvailabilityCounts,
                                               final LanguageDataAvailabilityCategoryRepository availabilityCategories,
                                               final PriceIndexRepository priceIndexes,
                                               final PriceConfigurationRepository priceConfigurations,
                                               final DataLanguageClient dataLanguageClient) {
      return new PriceIndexService(languageDataAvailabilityCounts, availabilityCategories, priceIndexes, priceConfigurations, dataLanguageClient);
    }

    @Bean
    public TranslationUnitStreamer translationUnitStreamer(final DataLanguageClient dataLanguageClient,
                                                           final MatchingDataService matchingDataService,
                                                           @Value("${app.download.page_size:2000}") final int pageSize) {
      return new TranslationUnitStreamer(dataLanguageClient, matchingDataService, pageSize);
    }

    @Bean
    public CorporateUserService corporateUserService(@Value("${corporate.service.user.api_key}") final String apiKey,
                                                     @Value("${corporate.service.user.url}") final String url) {
      return new CorporateUserService(apiKey, url);
    }

    @Bean
    public UserService userService(final UserRepository userRepository,
                                   final CorporateUserService corporateUserService,
                                   final CRMService crmService
    ) {
      return new UserService(userRepository, corporateUserService, crmService);
    }

    @Bean
    public StripeService stripeService(final StripeClient stripeClient) {
      return new StripeService(stripeClient);
    }

    @Bean
    public PayPalService payPalService(final PaypalClient paypalClient) {
      return new PayPalService(paypalClient);
    }

    @Bean
    public BankTransferService bankTransferService() {
      return new BankTransferService();
    }

    @Bean
    public VATSenseService vatSenseService(
      @Value("${app.payment.vat_sense.url}") final String url,
      @Value("${app.payment.vat_sense.api_key}") final String vatSenseKey) {
      return VATSenseService.create(url, vatSenseKey);
    }

    @Bean
    public VATValidator vatValidator(VATSenseService vatSenseService) {
      return new VATValidator(vatSenseService);
    }

    @Bean
    public PurchaseService purchaseService(final PurchaseRepository purchaseRepository,
                                           final ProductRepository productRepository,
                                           final TaxService taxService,
                                           final StripeService stripe,
                                           final PayPalService payPal,
                                           final BankTransferService bankTransferService,
                                           final DownloadRepository downloadRepository,
                                           @Value("${app.ui.url}") final String appUiUrl
    ) {
      return new PurchaseService(stripe, payPal, bankTransferService, taxService, purchaseRepository, productRepository, downloadRepository, appUiUrl);
    }

    @Bean
    public ProductService productService(final DataLanguageClient dataLanguageClient,
                                         final UserService users,
                                         final MatchingDataService matchingDataService,
                                         final MatchingDataRequestRepository matchingDataRequestRepository,
                                         UUIDSerializer uuidSerializer
    ) {
      return new ProductService(users, dataLanguageClient, matchingDataService, matchingDataRequestRepository, uuidSerializer);
    }

    @Bean
    public PurchaseDownloadCreatedListener purchaseDownloadCreatedListener(final CRMService crmService) {
      return new PurchaseDownloadCreatedListener(crmService);
    }

    @Bean
    public PurchaseStatusChangeNotifierListener purchaseStatusChangeNotifierListener(final CRMService crmService) {
      return new PurchaseStatusChangeNotifierListener(crmService);
    }

    @Bean
    public PurchaseInvoiceIssueListener purchaseStatusChangeInvoiceListener(final AccountingService accountingService,
                                                                            final PurchaseRepository purchases) {
      return new PurchaseInvoiceIssueListener(accountingService, purchases);
    }

    @Bean
    public PurchasePaymentPaidListener purchasePaymentPaidListener(final AccountingService accountingService,
                                                                   final PriceConfigurationRepository priceConfigurationRepository,
                                                                   final PurchaseRepository purchases) {
      return new PurchasePaymentPaidListener(accountingService, priceConfigurationRepository, purchases);
    }

    @Bean
    public PurchaseItemReviewedListener purchaseItemReviewedListener(final EmailSenderService emailService,
                                                                     final Converter<UUID, String> converter,
                                                                     @Value("${app.ui.url}") final String appUiUrl) {
      return new PurchaseItemReviewedListener(emailService, converter, appUiUrl);
    }

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
      return new MethodValidationPostProcessor();
    }

    @Bean
    public RestTemplate restTemplate() {
      return new RestTemplate();
    }

    @Bean
    public JwtService jwtService(
      @Value("${app.security.auth.jwt.secret_key}") final String secretKeyBase64,
      final Converter<UUID, String> uuidStringConverter,
      final Converter<String, UUID> stringUuidConverter
    ) {
      return new JwtService(secretKeyBase64, uuidStringConverter, stringUuidConverter);
    }

    @Bean
    public SessionService sessionService(@Value("${app.security.auth.provider.session_lifetime_milliseconds}") final Long jwtLifetime,
                                         final UserService userService,
                                         final CorporateAuthenticationService corporateAuthenticationService,
                                         final JwtService jwtService
    ) {
      return new SessionService(jwtLifetime, userService, corporateAuthenticationService, jwtService);
    }

    @Bean
    public CorporateAuthenticationService corporateAuthenticationService(
      RestTemplate restTemplate,
      @Value("${app.security.auth.provider.login_endpoint}") final String loginEndpoint,
      @Value("${app.security.auth.provider.logout_endpoint}") final String logoutEndpoint,
      @Value("${app.security.auth.provider.authenticate_endpoint}") final String validateEndpoint,
      @Value("${app.security.auth.provider.api_key}") final String apiKey,
      @Value("${app.security.auth.provider.crypto_key}") final String cryptoKey
    ) {
      return new CorporateAuthenticationService(restTemplate, loginEndpoint, logoutEndpoint, validateEndpoint,
        apiKey, cryptoKey);
    }

    @Bean
    public TaxService taxService(@Value("${corporate.service.payment.api_key}") final String apiKey,
                                 @Value("${corporate.service.payment.sales_taxes_endpoint}") final String taxesEndpoint) {
      return new TaxService(apiKey, taxesEndpoint);
    }

    @Bean
    public RecordMessageConverter messageConverter() {
      return new StringJsonMessageConverter();
    }

    @Bean
    public InvoiceStatusConsumerService invoiceStatusConsumerService(final PurchaseService purchaseService) {
      return new InvoiceStatusConsumerService(purchaseService);
    }

    @Bean
    public KafkaTemplate<String, AccountingService.Transaction> kafkaTemplate(ProducerFactory<String, AccountingService.Transaction> producerFactory) {
      return new KafkaTemplate<>(producerFactory);
    }

    @Bean
    public AccountingService accountingService(final SellerPaymentRepository sellerPaymentRepository,
                                               final EmailSenderService emailService,
                                               final KafkaTemplate<String, AccountingService.Transaction> kafkaTemplate,
                                               @Value("${app.kafka.producer.reconnect.warning.ms}") int reconnectWarning,
                                               @Value("${app.kafka.producer.topics.sale_invoice}") String topicSaleInvoice,
                                               @Value("${app.kafka.producer.topics.payment_create}") String topicPaymentCreate,
                                               @Value("${corporate.service.payment.api_key}") String paymentServiceApiKey,
                                               @Value("${corporate.service.payment.days_to_refund}") int daysToRefund) {
      return new AccountingService(sellerPaymentRepository, emailService, kafkaTemplate, reconnectWarning, topicSaleInvoice, topicPaymentCreate, paymentServiceApiKey, daysToRefund);
    }

    @Bean
    public CRMService crmService(final CRMClient crmClient,
                                 final PurchaseService purchaseService,
                                 @Value("${corporate.service.crm.newPublicationTemplateId}") int newPublicationTemplateId,
                                 @Value("${corporate.service.crm.newPurchaseTemplateId}") int newPurchaseTemplateId,
                                 @Value("${corporate.service.crm.newDownloadTemplateId}") int newDownloadTemplateId,
                                 @Value("${corporate.service.crm.matchingDataTemplateId}") int matchingDataTemplateId,
                                 final Converter<UUID, String> uuidStringConverter,
                                 @Value("${app.ui.url}") final String appUiUrl) {
      return new CRMService(crmClient, purchaseService, newPublicationTemplateId, newPurchaseTemplateId, newDownloadTemplateId, matchingDataTemplateId, uuidStringConverter, appUiUrl);
    }

    @Bean
    public CRMClient crmClient(@Value("${corporate.service.crm.oauthEndpoint}") String oauthEndpoint,
                               @Value("${corporate.service.crm.eventEndpoint}") String eventEndpoint,
                               @Value("${corporate.service.crm.contactEndpoint}") String contactEndpoint,
                               @Value("${corporate.service.crm.formEndpoint}") String formEndpoint,
                               @Value("${corporate.service.crm.clientId}") String clientId,
                               @Value("${corporate.service.crm.clientSecret}") String clientSecret,
                               @Value("${corporate.service.crm.refreshToken}") String refreshToken) {
      return new HubSpotClient(oauthEndpoint, eventEndpoint, contactEndpoint, formEndpoint, clientId, clientSecret, refreshToken);
    }

    @Bean
    public SessionAuthenticationFilter sessionAuthenticationFilter(final SessionService sessionService) {
      return new SessionAuthenticationFilter(sessionService);
    }

    @Bean
    public Converter<String, UUID> stringUUIDConverter() {
      return new UUIDDeserializer();
    }

    @Bean
    public Converter<UUID, String> uuidStringConverter() {
      return new UUIDSerializer();
    }

    @Bean
    public Converter<String, SlugId> stringSlugIdConverter() {
      return new SlugIdDeserializer(new UUIDDeserializer());
    }

    @Bean
    public Converter<SlugId, String> slugIdStringConverter() {
      return new SlugIdSerializer(new UUIDSerializer());
    }

    @Bean
    public ObjectMapper objectMapper() {
      ObjectMapper mapper = new ObjectMapper();
      mapper.registerModule(new Jdk8Module());
      SimpleModule module = new SimpleModule();
      final var uuidSerializer = new UUIDSerializer();
      module.addSerializer(UUID.class, uuidSerializer);
      module.addSerializer(SlugId.class, new SlugIdSerializer(uuidSerializer));
      module.addSerializer(Country.class, new CountrySerializer());
      module.addSerializer(PriceSuggestion.class, new PriceSuggestionSerializer());
      final var uuidDeserializer = new UUIDDeserializer();
      module.addDeserializer(UUID.class, uuidDeserializer);
      module.addDeserializer(SlugId.class, new SlugIdDeserializer(uuidDeserializer));
      mapper.registerModule(module);
      mapper.registerModule(new JavaTimeModule());
      mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
      return mapper;
    }

    @Bean
    public SimpleMessageListenerContainerFactory simpleMessageListenerContainerFactory(final AmazonSQSAsync amazonSQSAsync) {
      SimpleMessageListenerContainerFactory factory = new SimpleMessageListenerContainerFactory();
      factory.setWaitTimeOut(10);
      factory.setMaxNumberOfMessages(10);
      factory.setAmazonSqs(amazonSQSAsync);
      return factory;
    }

    @Bean
    public QueueMessageHandlerFactory queueMessageHandlerFactory(final AmazonSQSAsync amazonSQSAsync) {
      final MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
      converter.setObjectMapper(internalMapper());
      QueueMessageHandlerFactory factory = new QueueMessageHandlerFactory();
      factory.setAmazonSqs(amazonSQSAsync);
      factory.setArgumentResolvers(singletonList(new PayloadMethodArgumentResolver(converter, null, false)));
      factory.setSqsMessageDeletionPolicy(NO_REDRIVE);
      return factory;
    }

    @Bean
    @Qualifier("internalMapper")
    public ObjectMapper internalMapper() {
      ObjectMapper mapper = new ObjectMapper();
      mapper.registerModule(new JavaTimeModule());
      SimpleModule module = new SimpleModule();
      module.addDeserializer(UUID.class, new com.fasterxml.jackson.databind.deser.std.UUIDDeserializer());
      module.addSerializer(UUID.class, new com.fasterxml.jackson.databind.ser.std.UUIDSerializer());
      mapper.registerModule(module);
      mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
      mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
      mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
      return mapper;
    }

    @Bean
    public PublicationNotificationService notificationService(final PublicationNotificationRepository notifications,
                                                              final DataLanguageClient dataLanguageClient,
                                                              final UserService users,
                                                              final CRMService crmService,
                                                              @Value("${app.ui.url}") final String appUiUrl,
                                                              final UUIDSerializer uuidSerializer) {
      return new PublicationNotificationService(notifications, dataLanguageClient, users, crmService, appUiUrl, uuidSerializer);
    }

    @Bean
    @ConfigurationPropertiesBinding
    public DataLanguageComponent.Converter dataLanguageComponentConverter() {
      return source -> {
        final String[] data = source.split(",");
        return new DataLanguageComponent(data[0], Boolean.parseBoolean(data[1]), Boolean.parseBoolean(data[2]));
      };
    }

    @Bean
    @ConfigurationProperties(prefix = "corporate.service.data-language.components")
    public List<DataLanguageComponent> dataLanguageComponents() {
      return new ArrayList<>();
    }

    @Bean
    public DataLanguageNotificationListener translationDocumentNotificationListener(final PublicationRepository publications,
                                                                                    final PublicationNotificationService notificationService,
                                                                                    final List<DataLanguageComponent> components) {
      return new DataLanguageNotificationListener(publications, notificationService, internalMapper(), components);
    }

    @Bean
    public MatchingDataNotificationListener matchingDataNotificationListener(MatchingDataRequestRepository matchingDataRequestRepository,
                                                                             MatchingDataService matchingDataService,
                                                                             CRMService crmService) {
      return new MatchingDataNotificationListener(matchingDataRequestRepository, matchingDataService, crmService, internalMapper());
    }

    @Bean
    public MatchingDataService matchingDataService(DataLanguageClient dataLanguageClient,
                                                   UserService userService,
                                                   ResourceLoader resourceLoader,
                                                   @Value("${corporate.service.matching_data.validations.query.rows.min}") int minNumberOfRows,
                                                   @Value("${corporate.service.matching_data.validations.query.rows.max}") int maxNumberOfRows,
                                                   @Value("${corporate.service.matching_data.assets.remote.path.requests}") String dmpAssetsPathPrefix,
                                                   @Value("${corporate.service.matching_data.assets.remote.path.results}") String mdAssetsPathPrefix) {
      return new MatchingDataService(dataLanguageClient, userService, resourceLoader, minNumberOfRows, maxNumberOfRows, dmpAssetsPathPrefix, mdAssetsPathPrefix, internalMapper());
    }

    @Bean
    public SecureSimpleStorageProtocolResolverConfigurer secureSimpleStorageProtocolResolverConfigurer(final AmazonS3 amazonS3Client) {
      var resolver = new SecureSimpleStorageProtocolResolver(amazonS3Client);
      return new SecureSimpleStorageProtocolResolverConfigurer(resolver);
    }

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
      CommonsMultipartResolver resolver = new CommonsMultipartResolver();
      resolver.setDefaultEncoding("UTF-8");
      resolver.setResolveLazily(true);
      return resolver;
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
      return new WebMvcConfigurer() {
        @Override
        public void addCorsMappings(@NonNull CorsRegistry registry) {
          registry.addMapping("/**")
            .allowedOrigins(allowedOrigins)
            .allowCredentials(true)
            .allowedMethods(DELETE.toString(), GET.toString(), OPTIONS.toString(), POST.toString(), PUT.toString(), HEAD.toString());
        }
      };
    }

    @Bean
    public CorporatePaymentService corporatePaymentService(
      @Value("${corporate.service.payment.api_key}") final String apiKey,
      @Value("${corporate.service.payment.profile_endpoint}") final String profileEndpoint,
      @Value("${corporate.service.payment.stripe.standard_endpoint}") final String stripeStandardEndpoint,
      @Value("${corporate.service.payment.stripe.forget_endpoint}") final String stripeForgetEndpoint,
      @Value("${corporate.service.payment.list_payments_endpoint}") final String listPaymentsEndpoint
    ) {
      return new CorporatePaymentService(HttpClient.newBuilder().build(), apiKey, profileEndpoint, stripeStandardEndpoint, stripeForgetEndpoint, listPaymentsEndpoint);
    }

    @Bean
    public UserPaymentService userProfileService(CorporatePaymentService corporatePaymentService,
                                                 UserRepository userRepository) {
      return new UserPaymentService(corporatePaymentService, userRepository);
    }

    @Bean
    public StripeClient stripeClient(@Value("${app.payment.method.stripe.api_key}") final String apiKey) {
      return StripeClient.create(apiKey);
    }

    @Bean
    @Profile("!production")
    public PaypalClient paypalClientDev(@Value("${app.payment.method.paypal.client_id}") final String clientId,
                                        @Value("${app.payment.method.paypal.client_secret}") final String clientSecret) {
      return PaypalClient.create(clientId, clientSecret, PaypalClient.PayPalEnvironment.SANDBOX);
    }

    @Bean
    @Profile("production")
    public PaypalClient paypalClientProduction(
      @Value("${app.payment.method.paypal.client_id}") final String clientId,
      @Value("${app.payment.method.paypal.client_secret}") final String clientSecret) {
      return PaypalClient.create(clientId, clientSecret, PaypalClient.PayPalEnvironment.LIVE);
    }
  }

  @Configuration
  @EnableWebSecurity
  public static class SecurityConfigurer extends WebSecurityConfigurerAdapter {

    private final SessionService sessionService;
    private final SessionAuthenticationFilter sessionAuthenticationFilter;

    public SecurityConfigurer(final SessionService sessionService,
                              final SessionAuthenticationFilter sessionAuthenticationFilter) {
      this.sessionService = sessionService;
      this.sessionAuthenticationFilter = sessionAuthenticationFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
      auth.authenticationProvider(sessionService).eraseCredentials(true);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http.csrf().disable().cors().disable();
      http
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // No session will be created or used by spring security
        .and()
        .authorizeRequests()
        .antMatchers(POST, "/sessions", "/sessions/").permitAll()
        .antMatchers(PUT, "/sessions", "/sessions/").permitAll()
        .antMatchers(OPTIONS, "/sessions", "/sessions/").permitAll()
        .antMatchers(POST, "/users", "/users/").permitAll()
        .antMatchers(OPTIONS, "/users", "/users/**").permitAll()
        .antMatchers("/sellers/**").permitAll()
        .antMatchers("/explorer/**").permitAll()
        .antMatchers("/search/**").permitAll()
        .antMatchers("/price/**").permitAll()
        .antMatchers("/price_index/**").permitAll()
        .antMatchers("/base/countries").permitAll()
        .antMatchers("/base/content_types/**").permitAll()
        .antMatchers("/base/domains/**").permitAll()
        .antMatchers("/base/languages/**").permitAll()
        .antMatchers("/base/document_statistics/**").permitAll()
        .antMatchers("/cart/**").permitAll()
        .antMatchers("/documents/**").permitAll()
        .antMatchers("/documents/**/reviews").permitAll()
        .antMatchers("/checkout/**").permitAll()
        .antMatchers("/downloads/**").permitAll()
        .antMatchers("/purchases/**").permitAll()
        .antMatchers(OPTIONS, "/publications/**").permitAll()
        .antMatchers(POST, "/publications/").permitAll()
        .antMatchers(POST, "/publications/upload").permitAll()
        .antMatchers(PUT, "/publications/**/notify").permitAll()
        .antMatchers(GET, "/publications/**").permitAll()
        .antMatchers(GET, "/publications/**/status").permitAll()
        .antMatchers(PUT, "/publications/**").authenticated()
        .antMatchers(GET, "/crm/form/**").permitAll()
        .antMatchers(POST, "/matching-data").permitAll()
        .antMatchers(GET, "/matching-data/**").permitAll()
        .antMatchers(GET, "/matching-data/examples/download").permitAll()
        .anyRequest().authenticated()
        .and()
        .addFilterBefore(sessionAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
      return super.authenticationManagerBean();
    }
  }
}


