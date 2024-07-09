package io.github.test_containers_sandbox.domain;

import io.github.test_containers_sandbox.configuration.DatabaseIntegrationTest;
import io.github.test_containers_sandbox.domain.model.Action;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ActionRepositoryIntegrationTest extends DatabaseIntegrationTest {

    @Autowired
    ActionRepository actionRepository;

    @Test
    void it_saves_action() {
        Action action = new Action("Take coffee", "Take coffee with grandma");

        Action savedAction = actionRepository.create(action);

        Assertions.assertEquals(action, savedAction);
    }
}