package com.eduardo.spring.spring_app_todolist.dto;

import com.eduardo.spring.spring_app_todolist.model.Task;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

// Create DTO to avoid recursive infinitive
@Data
public class TaskDTO {
    private Long id;
    private String title;
    private String description;
    private Boolean isCompleted;
    private LocalDateTime createdAt;

    private String categoryName;

    // Create object only with the necessary
    public TaskDTO(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.isCompleted = task.getIsCompleted();
        this.createdAt = task.getCreatedAt();
        this.categoryName = task.getCategory().getName();
    }
}
