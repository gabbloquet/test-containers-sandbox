package io.github.test_containers_sandbox.action.domain;

import static org.assertj.core.api.Assertions.assertThat;

import io.github.test_containers_sandbox.action.domain.model.Action;
import io.github.test_containers_sandbox.configuration.DatabaseIntegrationTest;
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

        assertThat(action).isEqualTo(savedAction);
    }
}