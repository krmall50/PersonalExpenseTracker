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

        Category category = new Category(10, "have fun 2", "fun");
        category.setName(null);
        category.setdescription(null);

        Expense expense = new Expense("bought a cake again", amount, date, 1);

        CategoryService cs = new CategoryService(repoC);
        ExpenseService es = new ExpenseService(repoE);
        // cs.addCategory(category);
        // es.addExpense(expense);
        cs.updateCategory(category);

    }
}
