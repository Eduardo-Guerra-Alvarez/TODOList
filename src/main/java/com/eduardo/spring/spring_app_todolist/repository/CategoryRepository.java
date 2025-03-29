package com.eduardo.spring.spring_app_todolist.repository;

import com.eduardo.spring.spring_app_todolist.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
