package io.github.test_containers_sandbox.action.infrastructure.database;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ActionJpaRepository extends JpaRepository<ActionJpaDto, Integer> {}
