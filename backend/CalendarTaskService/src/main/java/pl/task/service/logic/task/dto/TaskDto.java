package pl.task.service.logic.task.dto;

import jakarta.persistence.Column;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class TaskDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 7230539695287796228L;

    private Integer id;

    private String title;

    private String description;

    private LocalDate day;

    private LocalTime atTime;

    private String color;

    private boolean completed = false;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
