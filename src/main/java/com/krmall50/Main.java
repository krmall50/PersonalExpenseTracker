package com.krmall50;

import java.sql.SQLException;

import com.krmall50.config.DatabaseConfig;
import com.krmall50.model.Category;
import com.krmall50.repo.CategoryRepository;

public class Main {
    public static void main(String[] args) {
        CategoryRepository repo = new CategoryRepository(DatabaseConfig.getDataSource());
        Category category = new Category("c4", "d4");
        try {
            // repo.add(category);
            Category updatecategory = repo.findById(4);
            // System.out.println(updatecategory.getId());
            updatecategory.setName("updatedName4");
            updatecategory.setdescription("updatedDesc4");
            int rows = repo.update(updatecategory);
            System.out.println(rows);
            // System.out.println(category.getId());
            // repo.findAll().stream().forEach(s -> System.out.println(s.getId() + " " +
            // s.getName()));
            // System.out.println(repo.delete(2));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
