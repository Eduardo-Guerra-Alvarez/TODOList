package com.eduardo.spring.spring_app_todolist.dto;

import com.eduardo.spring.spring_app_todolist.model.Category;
import com.eduardo.spring.spring_app_todolist.model.Task;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class CategoryDTO {
    private Long id;
    private String name;
    private List<TaskDTO> tasks;

    // Create object with the necessary
    public CategoryDTO(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.tasks = category.getTasks().stream()
                .map(TaskDTO::new)
                .collect(Collectors.toList()).reversed();
    }
}
