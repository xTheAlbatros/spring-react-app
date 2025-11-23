package pl.task.service.logic.task;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import pl.task.service.logic.task.dto.TaskCriteriaDto;
import pl.task.service.logic.task.dto.TaskDto;
import pl.task.service.model.Task;
import pl.task.service.model.User;
import pl.task.service.persistence.TaskQueryRepository;
import pl.task.service.persistence.TaskRepository;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskQueryRepository taskQueryRepository;
    private final TaskMapper taskMapper;

    public List<TaskDto> findTasksForUser(final TaskCriteriaDto criteria, final Principal principal) {
        final User user = (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        criteria.setUser(user);

        final List<Task> foundTasks = taskQueryRepository.findTasksForUserByCriteria(criteria);

        return foundTasks.stream()
                .map(taskMapper::mapToTaskDto)
                .toList();
    }
}
