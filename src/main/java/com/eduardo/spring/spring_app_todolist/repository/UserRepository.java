package com.eduardo.spring.spring_app_todolist.repository;

import com.eduardo.spring.spring_app_todolist.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
