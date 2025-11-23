package pl.task.service.logic.user.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class UserDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -4569237174709606536L;

    private Integer id;
    private String name;
    private String surname;
    private String email;
}
