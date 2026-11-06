package com.krmall50.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.krmall50.config.DatabaseConfig;
import com.krmall50.model.Category;
import com.krmall50.repo.CategoryRepository;

public class CategoryServiceTest {
    CategoryRepository categoryRepo;
    CategoryService categoryService;

    @BeforeEach
    void setUp() {
        categoryRepo = new CategoryRepository(DatabaseConfig.getDataSourceTest());
        categoryService = new CategoryService(categoryRepo);
    }

    @AfterEach
    void cleanUp() throws SQLException {
        categoryRepo.clearTable();
    }

    @Test
    void addCategoryTest() {
        Category category = new Category("test", "test");
        Integer id = categoryService.addCategory(category);

        assertTrue(id != null);
    }

    @Test
    void addExistingCategoryTest() {
        Category category = new Category("test", "test");
        Category category1 = new Category("test", "test");

        categoryService.addCategory(category);

        Integer id = categoryService.addCategory(category1);

        assertTrue(id == null);
    }

    @Test
    void deleteCategoryTest() {
        Category category = new Category("test", "test");

        Integer idAdded = categoryService.addCategory(category);
        Integer idDeleted = categoryService.deleteCategory(idAdded);

        assertEquals(idAdded, idDeleted);
    }

    @Test
    void deleteNonExistingCategoryTest() {

        Integer id = categoryService.deleteCategory(1);
        assertEquals(null, id);
    }

    @Test
    void findCategoryByIdTest() {
        Category category = new Category("test", "test");
        Integer idAdded = categoryService.addCategory(category);
        Integer idFound = categoryService.findCategoryById(idAdded).getId();
        assertEquals(idAdded, idFound);
    }

    @Test
    void findNonExistingCategoryByIdTest() {
        Category category = categoryService.findCategoryById(1);
        assertEquals(null, category);
    }

    @Test
    void updateCategoryTest() {
        Category category = new Category("test", "test");
        Integer idAdded = categoryService.addCategory(category);

        category.setName("updated");
        category.setdescription("updated");

        Integer idUpdated = categoryService.updateCategory(category);

        assertEquals(idAdded, idUpdated);
    }

    @Test
    void updateNonExistingCategoryTest() {
        Category category = new Category("test", "test");

        category.setName("updated");
        category.setdescription("updated");

        Integer idUpdated = categoryService.updateCategory(category);

        assertEquals(null, idUpdated);
    }

    @Test
    void findAllCategoriesTest() {
        Category category = new Category("test", "test");

        categoryService.addCategory(category);
        Integer size = categoryService.findAllCategories().size();

        assertEquals(1, size);
    }
}
