package com.krmall50;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;

import com.krmall50.config.DatabaseConfig;
import com.krmall50.model.Category;
import com.krmall50.model.Expense;
import com.krmall50.repo.CategoryRepository;
import com.krmall50.repo.ExpenseRepository;

public class Main {
    public static void main(String[] args) {
        LocalDate date = LocalDate.now();
        BigDecimal amount = new BigDecimal(7560);
        CategoryRepository repoC = new CategoryRepository(DatabaseConfig.getDataSource());
        ExpenseRepository repoE = new ExpenseRepository(DatabaseConfig.getDataSource());
        Category category = new Category("c4", "d4");
        Expense expense = new Expense("asdfs", amount, date, 51);
        try {
            repoE.add(expense);
            // Category updatecategory = repo.findById(4);
            // System.out.println(updatecategory.getId());
            // updatecategory.setName("updatedName4");
            // updatecategory.setdescription("updatedDesc4");
            // int rows = repo.update(updatecategory);
            // System.out.println(rows);
            // System.out.println(category.getId());
            // repo.findAll().stream().forEach(s -> System.out.println(s.getId() + " " +
            // s.getName()));
            // System.out.println(repo.delete(2));
            // System.out.println(repo.findById(4).getName());

            // int rows = repoE.add(expense);
            // System.out.println(rows);
            // System.out.println(repoE.findById(1).getDescription());
            // repoE.findAll().stream()
            // .forEach(s -> System.out.println(s.getId() + " " + s.getDescription() + " " +
            // s.getAmount()));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
