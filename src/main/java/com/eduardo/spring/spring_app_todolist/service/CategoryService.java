package com.eduardo.spring.spring_app_todolist.service;

import com.eduardo.spring.spring_app_todolist.dto.CategoryDTO;
import com.eduardo.spring.spring_app_todolist.model.Category;
import com.eduardo.spring.spring_app_todolist.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream().map(CategoryDTO::new)
                .collect(Collectors.toList());
    }

    public Optional<CategoryDTO> getCategory(Long id) {
        return categoryRepository.findById(id).map(CategoryDTO::new);
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    public void removeCategory(Long id) {
        if(categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
        } else {
            throw new RuntimeException("Category not found");
        }
    }

    public CategoryDTO updateCategory(Long id, Category category) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(optionalCategory.isPresent()) {
            Category categoryPresent = optionalCategory.get();
            categoryPresent.setName(category.getName());
            Category categoryAux = categoryRepository.save(categoryPresent);
            return new CategoryDTO(categoryAux);
        } else {
            throw new RuntimeException("Category not found");
        }
    }
}
