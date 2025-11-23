package pl.task.service.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.task.service.model.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {
}
