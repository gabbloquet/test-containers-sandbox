package io.github.test_containers_sandbox.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories({"io.github.test_containers_sandbox.action.infrastructure"})
public class JpaConfig {}
