package com.krmall50.service;

import java.sql.SQLException;

import com.krmall50.model.Category;
import com.krmall50.repo.CategoryRepository;

public class CategoryService {
    private CategoryRepository repo;

    public CategoryService(CategoryRepository repo) {
        this.repo = repo;
    }

    public void addCategory(Category category) {
        if (category.getName() == null) {
            System.out.println("Category name cannot be null.");
            return;
        }
        if (category.getName().length() > 100) {
            System.out.println("Category name cannot be over 100 characters.");
            return;
        }
        try {
            int id = repo.add(category);
            System.out.println("Category created, id: " + id);
        } catch (SQLException e) {
            System.out.println("Category with name " + category.getName() + " already exists.");
        }
    }

    public void deleteCategory(int id) {

    }
}
