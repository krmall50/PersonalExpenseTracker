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

public class Main {
    public static void main(String[] args) {
        LocalDate date = LocalDate.now();
        BigDecimal amount = new BigDecimal(7560);

        CategoryRepository repoC = new CategoryRepository(DatabaseConfig.getDataSource());
        ExpenseRepository repoE = new ExpenseRepository(DatabaseConfig.getDataSource());

        Category category = new Category("have fun", "fun");

        CategoryService cs = new CategoryService(repoC);
        cs.addCategory(category);

    }
}
