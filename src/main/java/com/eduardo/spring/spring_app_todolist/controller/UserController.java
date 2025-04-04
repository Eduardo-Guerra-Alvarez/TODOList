package com.eduardo.spring.spring_app_todolist.controller;

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
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PostMapping("/{userId}/assign/{taskId}")
    public User assignTaskToUser(@PathVariable Long userId, @PathVariable Long taskId) {
        return userService.assignTaskToUser(userId, taskId);
    }

    public String removeUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "User deleted Successfully!!";
    }

}
