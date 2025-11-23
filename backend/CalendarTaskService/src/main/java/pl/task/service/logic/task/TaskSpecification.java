package pl.task.service.logic.task;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.domain.Specification;
import pl.task.service.logic.task.dto.TaskCriteriaDto;
import pl.task.service.model.Task;

import java.io.Serial;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

@RequiredArgsConstructor
public class TaskSpecification implements Specification<Task> {

    @Serial
    private static final long serialVersionUID = 2046920219694742785L;

    private final TaskCriteriaDto criteria;

    @Override
    public @Nullable Predicate toPredicate(final Root<Task> root,
                                           final CriteriaQuery<?> query,
                                           final CriteriaBuilder builder) {

        final List<Predicate> predicates = new ArrayList<>();

        if (nonNull(criteria.getDay())) {
            predicates.add(
                    builder.equal(root.get("day"), criteria.getDay())
            );
        }

        if (nonNull(criteria.getMonth()) && !criteria.getMonth().isBlank()) {
            final YearMonth yearMonth = YearMonth.parse(criteria.getMonth());
            final LocalDate from = yearMonth.atDay(1);
            final LocalDate to = yearMonth.atEndOfMonth();

            predicates.add(
                    builder.between(root.get("day"), from, to)
            );
        }

        if (nonNull(criteria.getCompleted())) {
            predicates.add(
                    builder.equal(root.get("completed"), criteria.getCompleted())
            );
        }

        if (nonNull(criteria.getUser())) {
            predicates.add(
                    builder.equal(root.get("user"), criteria.getUser())
            );
        }

        if (predicates.isEmpty()) {
            return builder.conjunction();
        }

        return builder.and(predicates.toArray(new Predicate[0]));
    }
}