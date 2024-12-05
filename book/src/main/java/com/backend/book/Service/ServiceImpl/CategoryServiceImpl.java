package com.backend.book.Service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.book.Model.Entity.Category;
import com.backend.book.Repository.CategoryRepository;
import com.backend.book.Service.CategoryService;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(String id, Category updatedCategory) {
        return categoryRepository.findById(id).map(category -> {
            category.setCategory(updatedCategory.getCategory());
            return categoryRepository.save(category);
        }).orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + id));
    }

    public String deleteCategory(String id) {
        categoryRepository.deleteById(id);
        return "Delete successfully";
    }
}

