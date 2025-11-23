package pl.task.service.logic.user.exception;

import org.springframework.http.HttpStatus;
import pl.task.service.logic.shared.CustomException;

public class UserBadRequestException extends CustomException {

    public UserBadRequestException(final String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
