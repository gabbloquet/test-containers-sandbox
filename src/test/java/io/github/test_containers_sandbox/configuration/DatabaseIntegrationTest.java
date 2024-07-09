package io.github.test_containers_sandbox.configuration;

import static org.testcontainers.shaded.org.awaitility.Awaitility.await;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterAll;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;

public class DatabaseIntegrationTest {

    static final PostgreSQLContainer<?> postgreSQLContainer;

    @AfterAll
    static void afterAll() {
        postgreSQLContainer.stop();
    }

    static {
        postgreSQLContainer = new PostgreSQLContainer<>("postgres:16.0-alpine")
            .waitingFor(Wait.forListeningPort());
        postgreSQLContainer.start();

        await().until(postgreSQLContainer::isRunning);
        DataSourceProperties dataSourceProperties = new DataSourceProperties();
        dataSourceProperties.setUrl(postgreSQLContainer.getJdbcUrl());
        dataSourceProperties.setUsername(postgreSQLContainer.getUsername());
        dataSourceProperties.setPassword(postgreSQLContainer.getPassword());

        Flyway flyway = Flyway.configure()
            .defaultSchema("sandbox")
            .dataSource(postgreSQLContainer.getJdbcUrl(),
                postgreSQLContainer.getUsername(),
                postgreSQLContainer.getPassword()).load();
        flyway.migrate();
    }

}
