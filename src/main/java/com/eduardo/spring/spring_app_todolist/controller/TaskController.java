package com.eduardo.spring.spring_app_todolist.controller;

import com.eduardo.spring.spring_app_todolist.dto.TaskDTO;
import com.eduardo.spring.spring_app_todolist.model.Task;
import com.eduardo.spring.spring_app_todolist.service.TaskService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Indicates that is a Controller
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired // Injection is necessary to not use new TaskService
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTask(@PathVariable Long id){
        return ResponseEntity.ok(taskService.getTask(id));
    }

    @PostMapping("/{categoryId}")
    public TaskDTO createTask(@PathVariable Long categoryId, @RequestBody Task task) {
        return taskService.saveTask(categoryId, task);
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "Task Deleted";
    }

    @PutMapping("/{id}")
    public TaskDTO updateTask(@PathVariable Long id, @RequestBody Task task) {
        return taskService.updateTask(id, task);
    }
}
