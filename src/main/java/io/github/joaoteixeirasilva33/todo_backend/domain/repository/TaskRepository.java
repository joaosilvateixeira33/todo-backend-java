package io.github.joaoteixeirasilva33.todo_backend.domain.repository;

import io.github.joaoteixeirasilva33.todo_backend.domain.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{
}
