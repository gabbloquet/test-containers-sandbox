package io.github.test_containers_sandbox.action.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.amazonaws.services.s3.AmazonS3;
import io.github.test_containers_sandbox.configuration.S3IntegrationTest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

@SpringBootTest
class TemplatePortIntegrationTest extends S3IntegrationTest {

    @Autowired
    TemplatePort templatePort;

    @Autowired
    AmazonS3 amazonS3;

    @Test
    void it_retrieve_template() throws IOException {
        String path = ResourceUtils.getURL("classpath:templates") + "/";
        File mainFile = ResourceUtils.getFile(path + "main.html");
        amazonS3.putObject("testing-bucket", "main.html", mainFile);

        byte[] file = templatePort.get("main.html");

        byte[] expectedBytes = Files.readAllBytes(mainFile.toPath());
        assertThat(file)
            .isNotNull()
            .isEqualTo(expectedBytes);
    }

}