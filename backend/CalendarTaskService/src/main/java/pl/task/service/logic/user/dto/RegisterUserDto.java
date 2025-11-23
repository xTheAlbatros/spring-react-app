package pl.task.service.logic.user.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class RegisterUserDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -6466775220141342058L;

    private String name;
    private String surname;
    private String email;
    private String password;
}
