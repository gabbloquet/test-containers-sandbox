package io.github.test_containers_sandbox.infrastructure;

import io.github.test_containers_sandbox.domain.model.Action;
import io.github.test_containers_sandbox.domain.ActionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostgresActionRepository implements ActionRepository {

    private final ActionJpaRepository actionJpaRepository;


    @Override
    public Action create(Action action) {
        return actionJpaRepository
            .save(ActionJpaDto.from(action))
            .toDomain();
    }
}
