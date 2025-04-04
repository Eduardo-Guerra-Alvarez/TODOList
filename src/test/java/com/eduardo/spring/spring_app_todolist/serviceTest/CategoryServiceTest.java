package com.eduardo.spring.spring_app_todolist.serviceTest;

import com.eduardo.spring.spring_app_todolist.dto.CategoryDTO;
import com.eduardo.spring.spring_app_todolist.model.Category;
import com.eduardo.spring.spring_app_todolist.model.Task;
import com.eduardo.spring.spring_app_todolist.repository.CategoryRepository;
import com.eduardo.spring.spring_app_todolist.service.CategoryService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CategoryServiceTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;

    @BeforeEach
    void cleanDatabase() {
        categoryRepository.deleteAll(); // Clean before to use
    }

    @Test
    @Order(1)
    void testCreateCategory() {
        Category category = new Category();
        category.setName("Work");

        // Act
        Category saved = categoryService.saveCategory(category);

        // Assert
        assertNotNull(saved.getId()); // Check if was saved.
        assertEquals("Work", saved.getName());
    }

    @Test
    @Order(2)
    @Transactional
    void testGetCategory() {
        Category category = new Category();
        category.setName("Home");
        List<Task> tasks = new ArrayList<>();
        category.setTasks(tasks);
        Category saved = categoryRepository.save(category);

        Optional<CategoryDTO> result = categoryService.getCategory(saved.getId());

        assertTrue(result.isPresent());
        assertEquals("Home", result.get().getName());
    }

    @Test
    @Order(3)
    void testDeleteCategory() {
        Category category = new Category();
        category.setName("To Delete");
        Category saved = categoryRepository.save(category);

        categoryService.removeCategory(saved.getId());

        Optional<Category> result = categoryRepository.findById(saved.getId());
        assertFalse(result.isPresent());
    }

    @Test
    @Order(4)
    void testUpdateCategory() {
        Category category = new Category();
        category.setName("To Update");
        Category saved = categoryRepository.save(category);
        saved.setName("To Update two");
        CategoryDTO updated = categoryService.updateCategory(saved.getId(), saved);

        assertNotNull(updated.getId());
        assertEquals("To Update two", updated.getName());
    }
}
