package pl.task.service.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.hibernate.jpa.QueryHints;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import pl.task.service.logic.task.TaskSpecification;
import pl.task.service.logic.task.dto.TaskCriteriaDto;
import pl.task.service.model.Task;

import java.util.List;

import static java.util.Objects.nonNull;
import static java.util.Objects.requireNonNull;

@Repository
@RequiredArgsConstructor
public class TaskQueryRepository {

    private final EntityManager entityManager;

    public List<Task> findTasksForUserByCriteria(final TaskCriteriaDto criteria) {
        final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Task> query = builder.createQuery(Task.class);
        final Root<Task> root = query.from(Task.class);

        final Specification<Task> specification = createTaskSpecification(criteria);
        final Predicate restriction = requireNonNull(specification).toPredicate(root, query, builder);

        query.select(root);
        query.where(restriction);

        final Expression<Object> atTimeIsNull = builder.selectCase()
                .when(builder.isNull(root.get("atTime")), 1)
                .otherwise(0);

        query.orderBy(
                builder.asc(root.get("day")),
                builder.asc(atTimeIsNull),
                builder.asc(root.get("atTime"))
        );

        return entityManager.createQuery(query)
                .setHint(QueryHints.HINT_CACHEABLE, "true")
                .getResultList();
    }

    private Specification<Task> createTaskSpecification(final TaskCriteriaDto criteria) {
        return nonNull(criteria) ? new TaskSpecification(criteria) : null;
    }
}