package pl.task.service.logic.task.exception;

import org.springframework.http.HttpStatus;
import pl.task.service.logic.shared.CustomException;

import static pl.task.service.logic.shared.ExceptionConstants.TASK_NOT_FOUND;

public class TaskNotFoundException extends CustomException {

    public TaskNotFoundException() {
        super(TASK_NOT_FOUND, HttpStatus.NOT_FOUND);
    }
}
