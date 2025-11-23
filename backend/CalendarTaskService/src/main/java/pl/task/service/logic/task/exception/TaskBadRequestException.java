package pl.task.service.logic.task.exception;

import org.springframework.http.HttpStatus;
import pl.task.service.logic.shared.CustomException;

public class TaskBadRequestException extends CustomException {

    public TaskBadRequestException(final String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
