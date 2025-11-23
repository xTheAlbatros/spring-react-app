package pl.task.service.logic.task;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import pl.task.service.logic.task.dto.TaskDto;
import pl.task.service.logic.task.dto.UpsertTaskDto;
import pl.task.service.model.Task;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface TaskMapper {

    TaskDto mapToTaskDto(final Task task);

    Task mapToTask(final UpsertTaskDto upsertTaskDto);
}
