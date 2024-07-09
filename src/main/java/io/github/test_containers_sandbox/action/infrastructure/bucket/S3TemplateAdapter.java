package io.github.test_containers_sandbox.action.infrastructure.bucket;

import com.amazonaws.services.s3.AmazonS3;
import io.github.test_containers_sandbox.action.domain.TemplatePort;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class S3TemplateAdapter implements TemplatePort {

    private final AmazonS3 amazonS3Client;

    @Value("${bucket.name}")
    private String bucketName;

    @Override
    public byte[] get(String fileName) {
        try {
            return amazonS3Client.getObject(bucketName, fileName)
                .getObjectContent()
                .readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
