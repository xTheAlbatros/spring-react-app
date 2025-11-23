package pl.task.service.logic.task.exception;

import org.springframework.http.HttpStatus;
import pl.task.service.logic.shared.CustomException;

public class TaskNotFoundException extends CustomException {

    public TaskNotFoundException(final String message) {
        super("TASK_NOT_FOUND", HttpStatus.NOT_FOUND);
    }
}
