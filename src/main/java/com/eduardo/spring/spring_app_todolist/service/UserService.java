package com.eduardo.spring.spring_app_todolist.service;

import com.eduardo.spring.spring_app_todolist.dto.UserDTO;
import com.eduardo.spring.spring_app_todolist.model.Task;
import com.eduardo.spring.spring_app_todolist.model.User;
import com.eduardo.spring.spring_app_todolist.repository.TaskRepository;
import com.eduardo.spring.spring_app_todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());
    }

    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        return new UserDTO(user);
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    // function to assign Task to User
    public UserDTO assignTaskToUser(Long userId, Long taskId) {
        // Check if exist User
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        // Check if exist Task
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        // Get list of Tasks and add it
        user.getTasks().add(task);
        // update user
        return new UserDTO(userRepository.save(user));
    }

    public void deleteUser(Long id) {
        if(userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public User updateUser(Long id, User user) {
        Optional<User> userById = userRepository.findById(id);

        if(userById.isPresent()) {
            User userUpdate = userById.get();
            userUpdate.setEmail(user.getEmail());
            userUpdate.setName(user.getName());

            return userRepository.save(userUpdate);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public User removeUserFromTask(Long userId, Long taskId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        user.getTasks().remove(task);

        task.getUsers().remove(user);

        userRepository.save(user);
        taskRepository.save(task);

        return user;
    }
}
