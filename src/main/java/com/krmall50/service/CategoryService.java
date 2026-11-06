package com.krmall50.service;

import java.sql.SQLException;
import java.util.List;

import com.krmall50.model.Category;
import com.krmall50.repo.CategoryRepository;

public class CategoryService {
    private CategoryRepository repo;

    public CategoryService(CategoryRepository repo) {
        this.repo = repo;
    }

    public Integer addCategory(Category category) {
        if (category.getName() == null) {
            System.out.println("Category name cannot be null.");
            return null;
        }
        if (category.getName().length() > 100) {
            System.out.println("Category name cannot be over 100 characters.");
            return null;
        }
        try {
            int id = repo.add(category);
            System.out.println("Category created, id: " + id);
            return id;
        } catch (SQLException e) {
            System.out.println("Category with name " + category.getName() + " already exists.");
            return null;
        }
    }

    public Integer deleteCategory(int id) {
        if (id < 0) {
            System.out.println("Enter valid id.");
            return null;
        }
        try {
            int rows = repo.delete(id);
            if (rows == 1) {
                System.out.println("Category with id " + id + " was deleted.");
                return id;
            } else {
                System.out.println("Category with id " + id + " does not exist.");
                return null;
            }
        } catch (SQLException e) {
            System.out.println("To delete this category, delete its expenses first.");
            return null;
        }

    }

    public Category findCategoryById(int id) {
        if (id < 0) {
            System.out.println("Enter valid id.");
            return null;
        }
        try {
            Category category = repo.findById(id);
            if (category == null) {
                System.out.println("Category with id " + id + " does not exist.");
                return null;
            }
            return category;
        } catch (SQLException e) {
            System.out.println("Couldn't find category.");
            return null;
        }
    }

    public Integer updateCategory(Category category) {
        if (category.getName() == null) {
            System.out.println("Category name cannot be null");
            return null;
        }
        if (category.getName().length() > 100) {
            System.out.println("Category name cannot be over 100 characters.");
            return null;
        }
        try {
            int rows = repo.update(category);
            if (rows == 0) {
                System.out.println("Category with id " + category.getId() + " does not exist.");
                return null;
            }
            System.out.println("Category with id " + category.getId() + " was updated.");
            return category.getId();
        } catch (SQLException e) {
            System.out.println("Category with name " + category.getName() + " already exists.");
            return null;
        }
    }

    public List<Category> findAllCategories() {
        try {
            List<Category> categories = repo.findAll();
            if (categories.size() == 0) {
                System.out.println("No categories were added yet.");
                return null;
            }
            return categories;
        } catch (SQLException e) {
            System.out.println("Couldn't get all categories");
            return null;
        }
    }
}
