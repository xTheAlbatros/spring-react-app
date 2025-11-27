package pl.task.service.logic.task.validator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pl.task.service.logic.task.dto.UpsertTaskDto;
import pl.task.service.logic.task.exception.TaskBadRequestException;

import static pl.task.service.logic.shared.ExceptionConstants.TASK_DATE_REQUIRED;
import static pl.task.service.logic.shared.ExceptionConstants.TASK_TITLE_REQUIRED;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TaskValidator {

    public static void validateUpsert(final UpsertTaskDto dto) {
        if (dto.getTitle() == null || dto.getTitle().isBlank()) {
            throw new TaskBadRequestException(TASK_TITLE_REQUIRED);
        }
        if (dto.getDay() == null) {
            throw new TaskBadRequestException(TASK_DATE_REQUIRED);
        }
    }
}