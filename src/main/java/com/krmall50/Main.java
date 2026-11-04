package com.krmall50;

import java.sql.SQLException;

import com.krmall50.config.DatabaseConfig;
import com.krmall50.model.Category;
import com.krmall50.repo.CategoryRepository;

public class Main {
    public static void main(String[] args) {
        CategoryRepository repo = new CategoryRepository(DatabaseConfig.getDataSource());
        Category category = new Category("c3", "d3");
        try {
            repo.add(category);
            System.out.println(category.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
