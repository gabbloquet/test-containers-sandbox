package io.github.test_containers_sandbox.infrastructure;

import io.github.test_containers_sandbox.domain.Action;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name="actions")
@AllArgsConstructor
@NoArgsConstructor
public class ActionJpaDto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "actions_generator")
    @SequenceGenerator(name = "actions_generator", sequenceName = "actions_sequence", allocationSize = 1)
    private Integer id;

    @Column(name="title", nullable=false)
    private String title;

    @Column(name="description", nullable=false)
    private String description;

    public static ActionJpaDto from(Action action) {
        return new ActionJpaDto(null, action.title(), action.description());
    }

    public Action toDomain() {
        return new Action(title, description);
    }
}
