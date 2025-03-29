package com.eduardo.spring.spring_app_todolist.service;

import com.eduardo.spring.spring_app_todolist.model.Task;
import com.eduardo.spring.spring_app_todolist.repository.CategoryRepository;
import com.eduardo.spring.spring_app_todolist.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTask(Long id) {
        return taskRepository.findById(id);
    }

    public Task saveTask(Long categoryId, Task task) {
        return categoryRepository.findById(categoryId).map(category -> {
            task.setCategory(category);
            return taskRepository.save(task);
        }).orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public void deleteTask(Long id) {
        if(taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
        } else {
            throw new RuntimeException("Task not found");
        }
    }

    public Task updateTask(Long id, Task task) {
        Optional<Task> optionalTask = taskRepository.findById(id);

        if(optionalTask.isPresent()) {
            Task updateTask = optionalTask.get();
            if(task.getTitle() != null){
                updateTask.setTitle(task.getTitle());
            }
            if (task.getDescription() != null) {
                updateTask.setDescription(task.getDescription());
            }
            return taskRepository.save(updateTask);
        } else {
            throw new RuntimeException("Task not found");
        }
    }
}
