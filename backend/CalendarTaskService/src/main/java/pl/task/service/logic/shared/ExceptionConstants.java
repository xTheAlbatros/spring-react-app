package pl.task.service.logic.shared;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ExceptionConstants {

    // TASK
    public static final String TASK_NOT_FOUND = "TASK_NOT_FOUND";
    public static final String TASK_TITLE_REQUIRED = "TASK_TITLE_REQUIRED";
    public static final String TASK_DATE_REQUIRED = "TASK_DATE_REQUIRED";

    // USER
    public static final String USER_NOT_FOUND = "USER_NOT_FOUND";
    public static final String USER_NAME_REQUIRED = "USER_NAME_REQUIRED";
    public static final String USER_SURNAME_REQUIRED = "USER_SURNAME_REQUIRED";
    public static final String USER_EMAIL_REQUIRED = "USER_EMAIL_REQUIRED";
    public static final String USER_EMAIL_INVALID = "USER_EMAIL_INVALID";
    public static final String USER_EMAIL_EXISTS = "USER_EMAIL_EXISTS";
    public static final String USER_PASSWORD_REQUIRED = "USER_PASSWORD_REQUIRED";
    public static final String USER_PASSWORD_INVALID = "USER_PASSWORD_INVALID";
    public static final String USER_PASSWORD_INCORRECT = "USER_PASSWORD_INCORRECT";
    public static final String USER_PASSWORDS_NOT_MATCH = "USER_PASSWORDS_NOT_MATCH";
}