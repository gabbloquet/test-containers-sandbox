package io.github.test_containers_sandbox.configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3ClientConfig {

    @Value("${bucket.name}")
    private String name;

    @Value("${bucket.endpoint}")
    private String endpoint;

    @Value("${bucket.access-key}")
    private String accessKey;

    @Value("${bucket.secret-key}")
    private String secretKey;

    @Bean
    public AmazonS3 amazonS3Client() {
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        AmazonS3 client = AmazonS3ClientBuilder
                .standard()
                .withEndpointConfiguration(new EndpointConfiguration(endpoint, "null"))
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withPathStyleAccessEnabled(true)
                .build();

        if(!client.doesBucketExistV2(name)){
            client.createBucket(name);
        }

        return client;
    }
}
