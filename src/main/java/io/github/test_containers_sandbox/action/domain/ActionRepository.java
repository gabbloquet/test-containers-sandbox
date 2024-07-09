package io.github.test_containers_sandbox.action.domain;

import io.github.test_containers_sandbox.action.domain.model.Action;

public interface ActionRepository {

    Action create(Action action);

}
