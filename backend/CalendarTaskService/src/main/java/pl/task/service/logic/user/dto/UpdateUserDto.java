package pl.task.service.logic.user.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class UpdateUserDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -973103636828474821L;

    private String name;

    private String surname;
}
