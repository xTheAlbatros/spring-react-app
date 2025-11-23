package pl.task.service.logic.user;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.task.service.logic.user.dto.ChangePasswordDto;
import pl.task.service.logic.user.dto.RegisterUserDto;
import pl.task.service.logic.user.dto.UserDto;
import pl.task.service.logic.user.exception.UserBadRequestException;
import pl.task.service.logic.user.exception.UserNotFoundException;
import pl.task.service.model.User;
import pl.task.service.persistence.TokenRepository;
import pl.task.service.persistence.UserRepository;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static pl.task.service.logic.user.validator.UserValidator.validateRegisterUser;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;


    @Transactional
    public User registerUser(RegisterUserDto registerUser) {

        validateRegisterUser(registerUser, userRepository);

        User user = userMapper.mapToUser(registerUser);

        String encodedPassword = passwordEncoder.encode(registerUser.getPassword());
        user.setPassword(encodedPassword);

        return userRepository.save(user);
    }

    public List<UserDto> getAllUsers(){
        List<User> users = userRepository.findAll();

        return Optional.ofNullable(users)
                .filter(list -> !list.isEmpty())
                .map(list -> list.stream()
                        .map(userMapper::mapToUserDto)
                        .collect(Collectors.toList()))
                .orElseThrow(UserNotFoundException::new);
    }

    public UserDto findUserById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        return userMapper.mapToUserDto(user);
    }

    @Transactional
    public void deleteUser(Integer id) {
        Optional<User> foundUser = Optional.of(userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new));
        tokenRepository.deleteAllByUser(foundUser.get());
        foundUser.ifPresent(userRepository::delete);
    }

    @Transactional
    public void changePassword(ChangePasswordDto request, Principal connectedUser){
        User user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new UserBadRequestException("INCORRECT_PASSWORD");
        }
        if(!request.getNewPassword().equals(request.getConfirmationPassword())){
            throw new UserBadRequestException("PROVIDED_PASSWORDS_ARE_NOT_SAME");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        userRepository.save(user);
    }
}

