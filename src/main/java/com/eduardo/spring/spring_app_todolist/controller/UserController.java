package com.eduardo.spring.spring_app_todolist.controller;

import com.eduardo.spring.spring_app_todolist.dto.UserDTO;
import com.eduardo.spring.spring_app_todolist.model.User;
import com.eduardo.spring.spring_app_todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserDTO> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PostMapping("/{userId}/assign/{taskId}")
    public UserDTO assignTaskToUser(@PathVariable Long userId, @PathVariable Long taskId) {
        return userService.assignTaskToUser(userId, taskId);
    }

    public String removeUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "User deleted Successfully!!";
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{userId}/remove/{taskId}")
    public User removeUserFromTask(@PathVariable Long userId, @PathVariable Long taskId) {
        return userService.removeUserFromTask(userId, taskId);
    }

}
