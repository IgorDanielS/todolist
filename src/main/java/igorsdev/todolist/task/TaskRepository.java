package igorsdev.todolist.task;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskModel, UUID>{

    Optional<TaskModel> findByTitle(String title);
}