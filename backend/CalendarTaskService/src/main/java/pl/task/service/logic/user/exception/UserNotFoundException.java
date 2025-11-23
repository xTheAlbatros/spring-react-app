package pl.task.service.logic.user.exception;

import org.springframework.http.HttpStatus;
import pl.task.service.logic.shared.CustomException;

public class UserNotFoundException extends CustomException {

    public UserNotFoundException() {
        super("USER_NOT_FOUND", HttpStatus.NOT_FOUND);
    }
}
