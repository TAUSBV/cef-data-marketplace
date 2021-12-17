package net.taus.data.language;

import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.lang.NonNull;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.lifecycle.Startables;
import org.testcontainers.utility.DockerImageName;

import java.util.Arrays;
import java.util.stream.Stream;

import static java.lang.String.format;

@SpringBootTest
@ContextConfiguration(classes = { Model.ModelConfiguration.class },
  initializers = IntegrationTest.ApplicationInitializer.class)
@AutoConfigureDataJpa
@AutoConfigureTestEntityManager
public class IntegrationTest {

  static class ApplicationInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private final MySQLContainer<?> mysql = new MySQLContainer<>(DockerImageName.parse("mysql/mysql-server:8.0.23").asCompatibleSubstituteFor("mysql"));

    @Override
    public void initialize(@NonNull ConfigurableApplicationContext context) {
      final var activeProfiles = context.getEnvironment().getActiveProfiles();
      if (Arrays.stream(activeProfiles).noneMatch(p -> p.equals("test-local"))) {
        Startables.deepStart(Stream.of(mysql)).join();
        TestPropertyValues.of(
          format("spring.datasource.url=%s", mysql.getJdbcUrl()),
          format("spring.datasource.username=%s", mysql.getUsername()),
          format("spring.datasource.password=%s", mysql.getPassword()),
          format("spring.flyway.url=%s", mysql.getJdbcUrl()),
          format("spring.flyway.user=%s", mysql.getUsername()),
          format("spring.flyway.password=%s", mysql.getPassword())
        ).applyTo(context.getEnvironment());
      }
    }
  }
}
