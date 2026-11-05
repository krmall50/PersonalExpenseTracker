package com.krmall50.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.postgresql.util.PSQLException;

import com.krmall50.config.DatabaseConfig;
import com.krmall50.model.Category;

public class CategoryRepositoryTest {
    CategoryRepository repo;

    @BeforeEach
    void setUp() {
        repo = new CategoryRepository(DatabaseConfig.getDataSourceTest());
    }

    @AfterEach
    void cleanUp() throws SQLException {
        repo.clearTable();
    }

    @Test
    void addTest() throws SQLException {
        Category category = new Category("test", "test description");
        int result = repo.add(category);
        assertTrue(result >= 0);
    }

    @Test
    void addExistingTest() throws SQLException {
        Category category = new Category("test", "test description");
        Category categoryCopy = new Category("test", "test description");
        repo.add(category);

        assertThrows(PSQLException.class, () -> {
            repo.add(categoryCopy);
        });
    }

    @Test
    void deleteTest() throws SQLException {
        Category category = new Category("test", "test description");
        repo.add(category);
        int rows = repo.delete(category.getId());
        assertEquals(1, rows);
    }

    @Test
    void deleteNonExistingTest() throws SQLException {
        int rows = repo.delete(1);
        assertEquals(0, rows);
    }

    @Test
    void updateTest() throws SQLException {
        Category category = new Category("test", "test description");
        repo.add(category);

        category.setName("updated test");
        category.setdescription("updated test description");
        repo.add(category);
        int result = repo.update(category);
        assertEquals(1, result);
    }

    @Test
    void updateNonExistingTest() throws SQLException {
        Category category = new Category("test", "test description");
        int result = repo.update(category);
        assertEquals(0, result);
    }

    @Test
    void findAllTest() throws SQLException {
        Category category = new Category("test", "test description");
        Category category1 = new Category("test1", "test1 description");
        repo.add(category);
        repo.add(category1);

        int result = repo.findAll().size();
        assertEquals(2, result);
    }
}
