package net.taus.data.marketplace.api;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.lang.NonNull;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.lifecycle.Startables;
import org.testcontainers.utility.DockerImageName;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;

import static java.lang.String.format;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = IntegrationTest.ApplicationInitializer.class)
@AutoConfigureTestEntityManager
public class IntegrationTest {


  static class ApplicationInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private static final MySQLContainer<?> mysql = new MySQLContainer<>(DockerImageName.parse("mysql/mysql-server:8.0.23").asCompatibleSubstituteFor("mysql"));
//    private static final KafkaContainer kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:5.5.3"));
    private static final GenericContainer<?> queue = new GenericContainer<>(DockerImageName.parse("softwaremill/elasticmq-native")).withExposedPorts(9324);

    @Override
    public void initialize(@NonNull ConfigurableApplicationContext context) {
      mysql.setTmpFsMapping(Map.of("/var/lib/mysql", "rw"));
      final var activeProfiles = context.getEnvironment().getActiveProfiles();
      if (Arrays.stream(activeProfiles).noneMatch(p -> p.equals("test-local"))) {
        Startables.deepStart(Stream.of(mysql, queue)).join();
        TestPropertyValues.of(
          format("spring.datasource.url=%s", mysql.getJdbcUrl()),
          format("spring.datasource.username=%s", mysql.getUsername()),
          format("spring.datasource.password=%s", mysql.getPassword()),
          format("spring.flyway.url=%s", mysql.getJdbcUrl()),
          format("spring.flyway.user=%s", mysql.getUsername()),
          format("spring.flyway.password=%s", mysql.getPassword()),
//          format("spring.kafka.producer.bootstrap-servers=%s", kafka.getBootstrapServers()),
//          format("spring.kafka.consumer.bootstrap-servers=%s", kafka.getBootstrapServers()),
          format("cloud.aws.sqs.endpoint=http://%s:%s", queue.getHost(), queue.getMappedPort(9324)),
          format("cloud.aws.credentials.accessKey=AKIA%s", "DUMMY"),
          format("cloud.aws.credentials.secretKey=%S", "DUMMY")
        ).applyTo(context.getEnvironment());
      }
    }
  }
}
