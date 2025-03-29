package com.eduardo.spring.spring_app_todolist.controller;

import com.eduardo.spring.spring_app_todolist.model.Category;
import com.eduardo.spring.spring_app_todolist.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Category> getCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public Optional<Category> getCategory(@PathVariable Long id) {
        return categoryService.getCategory(id);
    }

    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryService.saveCategory(category);
    }

    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.removeCategory(id);
        return "Category Deleted";
    }

    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable Long id, @RequestBody Category category) {
        return categoryService.updateCategory(id, category);
    }


}
