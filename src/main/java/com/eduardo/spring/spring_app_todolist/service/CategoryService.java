package com.eduardo.spring.spring_app_todolist.service;

import com.eduardo.spring.spring_app_todolist.model.Category;
import com.eduardo.spring.spring_app_todolist.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories(){return categoryRepository.findAll();}

    public Optional<Category> getCategory(Long id) {return categoryRepository.findById(id);}

    public Category saveCategory(Category category) {return categoryRepository.save(category);}

    public void removeCategory(Long id) {
        if(categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
        } else {
            throw new RuntimeException("Category not found");
        }
    }

    public Category updateCategory(Long id, Category category) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(optionalCategory.isPresent()) {
            Category updatedCategory = optionalCategory.get();
            if(category.getName() != null) {
                updatedCategory.setName(category.getName());
            }
            return updatedCategory;
        } else {
            throw new RuntimeException("Category not found");
        }
    }
}
