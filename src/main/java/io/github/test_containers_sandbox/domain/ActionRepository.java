package io.github.test_containers_sandbox.domain;

import io.github.test_containers_sandbox.domain.model.Action;

public interface ActionRepository {

    Action create(Action action);

}
