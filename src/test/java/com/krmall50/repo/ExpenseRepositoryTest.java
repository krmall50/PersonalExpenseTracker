package com.krmall50.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.postgresql.util.PSQLException;

import com.krmall50.config.DatabaseConfig;
import com.krmall50.model.Category;
import com.krmall50.model.Expense;

public class ExpenseRepositoryTest {
    ExpenseRepository repoExpense;
    CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        repoExpense = new ExpenseRepository(DatabaseConfig.getDataSourceTest());
        categoryRepository = new CategoryRepository(DatabaseConfig.getDataSourceTest());
    }

    @AfterEach
    void cleanUp() throws SQLException {
        repoExpense.clearTable();
        categoryRepository.clearTable();
    }

    @Test
    void addTest() throws SQLException {
        LocalDate date = LocalDate.now();
        BigDecimal amount = new BigDecimal(123);
        Category category = new Category("test", "test description");
        int Categoryid = categoryRepository.add(category);

        Expense expense = new Expense("test description", amount, date, Categoryid);
        int result = repoExpense.add(expense);

        assertTrue(result >= 0);
    }

    @Test
    void addNonExistingCategoryTest() throws SQLException {
        LocalDate date = LocalDate.now();
        BigDecimal amount = new BigDecimal(123);
        Expense expense = new Expense("test description", amount, date, 1);

        assertThrows(PSQLException.class, () -> {
            repoExpense.add(expense);
        });
    }
}
