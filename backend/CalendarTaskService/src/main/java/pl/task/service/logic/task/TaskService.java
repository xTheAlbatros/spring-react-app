package pl.task.service.logic.task;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import pl.task.service.logic.task.dto.TaskCriteriaDto;
import pl.task.service.logic.task.dto.TaskDto;
import pl.task.service.logic.task.dto.UpsertTaskDto;
import pl.task.service.logic.task.exception.TaskNotFoundException;
import pl.task.service.model.Task;
import pl.task.service.model.User;
import pl.task.service.persistence.TaskQueryRepository;
import pl.task.service.persistence.TaskRepository;

import java.security.Principal;
import java.util.List;

import static pl.task.service.logic.task.validator.TaskValidator.validateUpsert;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskQueryRepository taskQueryRepository;
    private final TaskMapper taskMapper;

    public List<TaskDto> findTasksForUser(final TaskCriteriaDto criteria, final Principal principal) {
        final User user = getCurrentUser(principal);
        criteria.setUser(user);

        final List<Task> foundTasks = taskQueryRepository.findTasksForUserByCriteria(criteria);

        return foundTasks.stream()
                .map(taskMapper::mapToTaskDto)
                .toList();
    }

    public TaskDto createTask(final UpsertTaskDto upsertTaskDto, final Principal principal) {
        validateUpsert(upsertTaskDto);

        final User user = getCurrentUser(principal);

        final Task task = taskMapper.mapToTask(upsertTaskDto);
        task.setId(null);
        task.setUser(user);

        final Task saved = taskRepository.save(task);
        return taskMapper.mapToTaskDto(saved);
    }

    public TaskDto getTaskById(final Integer taskId, final Principal principal) {
        final User user = getCurrentUser(principal);
        final Task task = getTaskForUserOrThrow(taskId, user);
        return taskMapper.mapToTaskDto(task);
    }

    public TaskDto updateTask(final Integer taskId,
                              final UpsertTaskDto upsertTaskDto,
                              final Principal principal) {
        validateUpsert(upsertTaskDto);

        final User user = getCurrentUser(principal);
        final Task task = getTaskForUserOrThrow(taskId, user);

        task.setTitle(upsertTaskDto.getTitle());
        task.setDescription(upsertTaskDto.getDescription());
        task.setDay(upsertTaskDto.getDay());
        task.setAtTime(upsertTaskDto.getAtTime());
        task.setColor(upsertTaskDto.getColor());
        task.setCompleted(upsertTaskDto.isCompleted());

        final Task updated = taskRepository.save(task);
        return taskMapper.mapToTaskDto(updated);
    }

    public void deleteTask(final Integer taskId, final Principal principal) {
        final User user = getCurrentUser(principal);
        final Task task = getTaskForUserOrThrow(taskId, user);
        taskRepository.delete(task);
    }

    private User getCurrentUser(final Principal principal) {
        return (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
    }

    private Task getTaskForUserOrThrow(final Integer taskId, final User user) {
        return taskRepository.findByIdAndUser(taskId, user)
                .orElseThrow(TaskNotFoundException::new);
    }
}