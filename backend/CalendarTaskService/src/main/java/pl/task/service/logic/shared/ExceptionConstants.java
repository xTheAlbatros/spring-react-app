package pl.task.service.logic.shared;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ExceptionConstants {

    public static final String TASK_NOT_FOUND = "TASK_NOT_FOUND";
    public static final String TASK_TITLE_REQUIRED = "TASK_TITLE_REQUIRED";
    public static final String TASK_DATE_REQUIRED = "TASK_DATE_REQUIRED";

}
