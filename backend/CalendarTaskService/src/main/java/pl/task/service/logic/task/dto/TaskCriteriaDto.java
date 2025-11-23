package pl.task.service.logic.task.dto;

import lombok.Data;
import pl.task.service.model.User;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
public class TaskCriteriaDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 4607493643485901042L;

    private LocalDate day;

    private Boolean completed;

    private String month;

    private User user;
}
