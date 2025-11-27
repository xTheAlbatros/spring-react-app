package pl.task.service.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.task.service.model.Task;
import pl.task.service.model.User;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    Optional<Task> findByIdAndUser(Integer id, User user);
}
