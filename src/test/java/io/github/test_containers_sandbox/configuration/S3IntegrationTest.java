package io.github.test_containers_sandbox.configuration;

import static com.amazonaws.regions.ServiceAbbreviations.S3;
import static org.testcontainers.shaded.org.awaitility.Awaitility.await;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterAll;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.containers.localstack.LocalStackContainer.Service;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.DockerImageName;

public class S3IntegrationTest {

    static final DockerImageName localstackImage = DockerImageName.parse("localstack/localstack");
    static final LocalStackContainer localStackContainer = new LocalStackContainer(localstackImage)
        .withServices(Service.S3);

    @DynamicPropertySource
    static void registerS3Properties(DynamicPropertyRegistry registry) {
        localStackContainer.start();

        await().until(localStackContainer::isRunning);

        registry.add("bucket.name", () -> "testing-bucket");
        registry.add("bucket.endpoint", () -> localStackContainer.getEndpoint().toString());
        registry.add("bucket.access-key", localStackContainer::getAccessKey);
        registry.add("bucket.secret-key", localStackContainer::getSecretKey);
        registry.add("bucket.region", localStackContainer::getRegion);
    }

}
