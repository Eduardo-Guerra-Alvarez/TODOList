package com.eduardo.spring.spring_app_todolist.service;

import com.eduardo.spring.spring_app_todolist.dto.TaskDTO;
import com.eduardo.spring.spring_app_todolist.model.Task;
import com.eduardo.spring.spring_app_todolist.repository.CategoryRepository;
import com.eduardo.spring.spring_app_todolist.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<TaskDTO> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(TaskDTO::new)
                .collect(Collectors.toList());
    }

    public TaskDTO getTask(Long id) {
        Task task = taskRepository.findById(id).orElseThrow();
        return new TaskDTO(task);
    }

    public TaskDTO saveTask(Long categoryId, Task task) {
        return categoryRepository.findById(categoryId).map(category -> {
            task.setCategory(category);
            Task auxTask = taskRepository.save(task);
            return new TaskDTO(auxTask);
        }).orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public void deleteTask(Long id) {
        if(taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
        } else {
            throw new RuntimeException("Task not found");
        }
    }

    public TaskDTO updateTask(Long id, Task task) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if(optionalTask.isPresent()) {
            Task updateTask = optionalTask.get();
            updateTask.setTitle(task.getTitle());
            updateTask.setDescription(task.getDescription());
            updateTask.setIsCompleted(task.getIsCompleted());
            Task taskAux = taskRepository.save(updateTask);
            return new TaskDTO(taskAux);
        } else {
            throw new RuntimeException("Task not found");
        }
    }
}
