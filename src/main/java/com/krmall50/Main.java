package com.krmall50;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;

import com.krmall50.config.DatabaseConfig;
import com.krmall50.model.Category;
import com.krmall50.model.Expense;
import com.krmall50.repo.CategoryRepository;
import com.krmall50.repo.ExpenseRepository;
import com.krmall50.service.CategoryService;
import com.krmall50.service.ExpenseService;

public class Main {
    public static void main(String[] args) {
        LocalDate date = LocalDate.now();
        BigDecimal amount = new BigDecimal(7560);

        CategoryRepository repoC = new CategoryRepository(DatabaseConfig.getDataSource());
        ExpenseRepository repoE = new ExpenseRepository(DatabaseConfig.getDataSource());

        Category category = new Category("c1", "d1");
        Category category1 = new Category("c2", "d2");
        Category category2 = new Category("c3", "d3");

        Expense expense = new Expense(99, "e1", amount, date, 21);
        Expense expense1 = new Expense("e2", amount, date, 21);
        Expense expense2 = new Expense("e3", amount, date, 22);

        CategoryService cs = new CategoryService(repoC);
        ExpenseService es = new ExpenseService(repoE);

        expense.setdescription("updated d1 2");
        expense.setCategoryId(23);
        es.updateExpense(expense);
    }
}
