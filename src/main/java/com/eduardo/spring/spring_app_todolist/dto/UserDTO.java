package com.eduardo.spring.spring_app_todolist.dto;

import com.eduardo.spring.spring_app_todolist.model.Task;
import com.eduardo.spring.spring_app_todolist.model.User;
import lombok.Data;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class UserDTO {

    private String name;
    private String email;
    private List<TaskDTO> tasks;

    public UserDTO(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.tasks = user.getTasks()
                .stream()
                .map(TaskDTO::new)
                .collect(Collectors.toList());
    }
}
