package pl.task.service.logic.user.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class ChangePasswordDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -1401334483975734940L;

    private String currentPassword;

    private String newPassword;

    private String confirmationPassword;
}
