package com.krmall50;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;

import com.krmall50.config.DatabaseConfig;
import com.krmall50.model.Category;
import com.krmall50.model.Expense;
import com.krmall50.render.UserInterface;
import com.krmall50.repo.CategoryRepository;
import com.krmall50.repo.ExpenseRepository;
import com.krmall50.service.CategoryService;
import com.krmall50.service.ExpenseService;

public class Main {
    public static void main(String[] args) {
        UserInterface.run();
    }
}
