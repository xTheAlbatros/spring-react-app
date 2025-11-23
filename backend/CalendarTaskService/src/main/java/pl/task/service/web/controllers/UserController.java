package pl.task.service.web.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.task.service.logic.user.UserService;
import pl.task.service.logic.user.dto.ChangePasswordDto;
import pl.task.service.logic.user.dto.UserDto;
import pl.task.service.model.User;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @GetMapping("/user/{id}")
    public UserDto getUserById(@PathVariable final Integer id) {
        return userService.findUserById(id);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable final Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/users")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/user/token")
    public UserDto getUserFromToken() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final User userPrincipal = (User) authentication.getPrincipal();
        final Integer userId = userPrincipal.getId();

        return userService.findUserById(userId);
    }

    @PatchMapping("/user/new-password")
    public ResponseEntity<?> changePassword(@RequestBody final ChangePasswordDto request, final Principal connectedUser) {
        userService.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }

}
