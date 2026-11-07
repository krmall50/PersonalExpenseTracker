package com.krmall50.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.krmall50.config.DatabaseConfig;
import com.krmall50.model.Category;
import com.krmall50.model.Expense;
import com.krmall50.repo.CategoryRepository;
import com.krmall50.repo.ExpenseRepository;

public class ExpenseServiceTest {
    CategoryRepository categoryRepo;
    CategoryService categoryService;
    ExpenseRepository expenseRepo;
    ExpenseService expenseService;

    @BeforeEach
    void setUp() {
        categoryRepo = new CategoryRepository(DatabaseConfig.getDataSourceTest());
        categoryService = new CategoryService(categoryRepo);
        expenseRepo = new ExpenseRepository(DatabaseConfig.getDataSourceTest());
        expenseService = new ExpenseService(expenseRepo);
    }

    @AfterEach
    void cleanUp() throws SQLException {
        categoryRepo.clearTable();
        expenseRepo.clearTable();
    }

    @Test
    void addExpenseTest() {
        Category category = new Category("test", "test");
        Integer categoryid = categoryService.addCategory(category);

        LocalDate date = LocalDate.now();
        BigDecimal amount = new BigDecimal(1);
        Expense expense = new Expense("test", amount, date, categoryid);
        Integer id = expenseService.addExpense(expense);

        assertTrue(id != null);
    }

    @Test
    void deleteExpenseTest() {
        Category category = new Category("test", "test");
        Integer categoryid = categoryService.addCategory(category);

        LocalDate date = LocalDate.now();
        BigDecimal amount = new BigDecimal(1);
        Expense expense = new Expense("test", amount, date, categoryid);
        Integer idAdded = expenseService.addExpense(expense);
        Integer idDeleted = expenseService.deleteExpense(idAdded);
        assertEquals(idAdded, idDeleted);
    }

    @Test
    void deleteNonExistingExpenseTest() {
        Integer idDeleted = expenseService.deleteExpense(1);
        assertEquals(null, idDeleted);
    }

    @Test
    void findExpenseByIdTest() {
        Category category = new Category("test", "test");
        Integer categoryid = categoryService.addCategory(category);

        LocalDate date = LocalDate.now();
        BigDecimal amount = new BigDecimal(1);
        Expense expense = new Expense("test", amount, date, categoryid);
        Integer idAdded = expenseService.addExpense(expense);

        Integer idFound = expenseService.findExpenseById(idAdded).getId();
        assertEquals(idAdded, idFound);
    }

    @Test
    void findAllExpensesTest() {
        Category category = new Category("test", "test");
        Integer categoryid = categoryService.addCategory(category);

        LocalDate date = LocalDate.now();
        BigDecimal amount = new BigDecimal(1);
        Expense expense = new Expense("test", amount, date, categoryid);
        expenseService.addExpense(expense);

        int size = expenseService.findAllExpenses().size();
        assertEquals(1, size);
    }

    @Test
    void findAllExpensesByCategoryIdTest() {
        Category category = new Category("test", "test");
        Integer categoryid = categoryService.addCategory(category);

        LocalDate date = LocalDate.now();
        BigDecimal amount = new BigDecimal(1);
        Expense expense = new Expense("test", amount, date, categoryid);
        expenseService.addExpense(expense);

        int size = expenseService.findAllExpensesByCategoryId(categoryid).size();
        assertEquals(1, size);
    }
}
