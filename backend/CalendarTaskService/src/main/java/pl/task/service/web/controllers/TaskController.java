package pl.task.service.web.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.task.service.logic.task.TaskService;
import pl.task.service.logic.task.dto.TaskCriteriaDto;
import pl.task.service.logic.task.dto.TaskDto;
import pl.task.service.logic.task.dto.UpsertTaskDto;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public TaskDto createTask(@RequestBody final UpsertTaskDto upsertTaskDto,
                              final Principal principal) {
        return taskService.createTask(upsertTaskDto, principal);
    }

    @GetMapping
    public List<TaskDto> listTasks(final TaskCriteriaDto criteria,
                                   final Principal principal) {
        return taskService.findTasksForUser(criteria, principal);
    }

    @GetMapping("/{taskId}")
    public TaskDto getTaskById(@PathVariable final Integer taskId,
                               final Principal principal) {
        return taskService.getTaskById(taskId, principal);
    }

    @PutMapping("/{taskId}")
    public TaskDto updateTask(@PathVariable final Integer taskId,
                              @RequestBody final UpsertTaskDto upsertTaskDto,
                              final Principal principal) {
        return taskService.updateTask(taskId, upsertTaskDto, principal);
    }

    @DeleteMapping("/{taskId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable final Integer taskId,
                           final Principal principal) {
        taskService.deleteTask(taskId, principal);
    }
}