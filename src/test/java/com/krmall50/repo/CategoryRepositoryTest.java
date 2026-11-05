package com.krmall50.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
}
