package io.github.joaoteixeirasilva33.todo_backend.controller;

import io.github.joaoteixeirasilva33.todo_backend.domain.entity.Task;
import io.github.joaoteixeirasilva33.todo_backend.domain.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskRepository repository;

    @GetMapping
    public List<Task> getAllTasks(){
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task){
        try{
            Task newTask = repository.save(task);
            return new ResponseEntity<>(newTask, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
        Optional<Task> taskData = repository.findById(id);

        if (taskData.isPresent()) {
            Task _task = taskData.get();
            _task.setTitle(task.getTitle());
            _task.setDescription(task.getDescription());
            _task.setStatus(task.getStatus());
            return new ResponseEntity<>(repository.save(_task), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTask(@PathVariable Long id) {
        try {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
