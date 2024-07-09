package io.github.test_containers_sandbox.infrastructure;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActionJpaRepository extends JpaRepository<ActionJpaDto, Integer> {}
