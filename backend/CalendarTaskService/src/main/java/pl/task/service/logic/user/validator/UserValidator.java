package pl.task.service.logic.user.validator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;
import pl.task.service.logic.user.dto.RegisterUserDto;
import pl.task.service.logic.user.exception.UserBadRequestException;
import pl.task.service.persistence.UserRepository;

import static pl.task.service.logic.shared.ExceptionConstants.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserValidator {

    public static void validateRegisterUser(final RegisterUserDto registerUser,
                                            final UserRepository userRepository) {

        if (!StringUtils.hasText(registerUser.getName())) {
            throw new UserBadRequestException(USER_NAME_REQUIRED);
        }

        if (!StringUtils.hasText(registerUser.getSurname())) {
            throw new UserBadRequestException(USER_SURNAME_REQUIRED);
        }

        if (!StringUtils.hasText(registerUser.getEmail())) {
            throw new UserBadRequestException(USER_EMAIL_REQUIRED);
        } else if (!isValidEmail(registerUser.getEmail())) {
            throw new UserBadRequestException(USER_EMAIL_INVALID);
        } else if (userRepository.existsByEmail(registerUser.getEmail())) {
            throw new UserBadRequestException(USER_EMAIL_EXISTS);
        }

        if (!StringUtils.hasText(registerUser.getPassword())) {
            throw new UserBadRequestException(USER_PASSWORD_REQUIRED);
        } else if (!isValidPassword(registerUser.getPassword())) {
            throw new UserBadRequestException(USER_PASSWORD_INVALID);
        }
    }

    private static boolean isValidEmail(final String email) {
        return email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$");
    }

    private static boolean isValidPassword(final String password) {
        return password.length() >= 5 && password.matches(".*\\d.*");
    }
}