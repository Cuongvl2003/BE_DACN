package com.backend.book.Service;

import java.util.List;

import com.backend.book.Model.Entity.Category;

public interface CategoryService {
    public List<Category> getAllCategory();
    public Category saveCategory(Category category);
    public Category updateCategory(String id, Category category);
    public String deleteCategory(String id);
    
}
