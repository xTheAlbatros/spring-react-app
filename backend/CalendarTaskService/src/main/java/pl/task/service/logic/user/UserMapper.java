package pl.task.service.logic.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import pl.task.service.logic.user.dto.RegisterUserDto;
import pl.task.service.logic.user.dto.UserDto;
import pl.task.service.model.User;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdTime", ignore = true)
    @Mapping(target = "password", ignore = true)
    User mapToUser(final RegisterUserDto registerUserDto);

    UserDto mapToUserDto(final User user);
}
