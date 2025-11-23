package pl.task.service.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.task.service.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
