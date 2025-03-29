package com.eduardo.spring.spring_app_todolist.repository;

import com.eduardo.spring.spring_app_todolist.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
