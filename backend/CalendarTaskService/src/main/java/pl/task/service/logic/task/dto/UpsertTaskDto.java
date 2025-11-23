package pl.task.service.logic.task.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class UpsertTaskDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -4624721576386751188L;

    private String title;

    private String description;

    private LocalDate day;

    private LocalTime atTime;

    private String color;

    private boolean completed;
}
