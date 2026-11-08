package com.krmall50.render;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.krmall50.config.DatabaseConfig;
import com.krmall50.model.Category;
import com.krmall50.model.Expense;
import com.krmall50.repo.CategoryRepository;
import com.krmall50.repo.ExpenseRepository;
import com.krmall50.service.CategoryService;
import com.krmall50.service.ExpenseService;

public class UserInterface {
    public static void run() {
        CategoryRepository categoryRepository = new CategoryRepository(DatabaseConfig.getDataSource());
        ExpenseRepository expenseRepository = new ExpenseRepository(DatabaseConfig.getDataSource());
        CategoryService categoryService = new CategoryService(categoryRepository);
        ExpenseService expenseService = new ExpenseService(expenseRepository);
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        boolean ingore = false;

        while (choice != 6) {
            System.out.println(
                    "1. Get all expences \n2. Get all categories \n3. Get expences by category \n4. Add expense \n5. Add category \n6. Exit");
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Choose available option 1-6.");
                ingore = true;
            } catch (NoSuchElementException e) {
            }
            switch (choice) {
                case 1:
                    getExpenses(expenseService);
                    break;
                case 2:
                    getCategories(categoryService);
                    break;
                case 3:
                    System.out.println("Enter category Id:");
                    Integer categoryId = readInt(sc);
                    if (categoryId != null) {
                        getExpensesByCategory(categoryId, expenseService);
                    } else {
                        System.out.println("Enter valid categoryId");
                    }
                default:
                    if (!ingore && choice != 6) {
                        System.out.println("Choose available option 1-6.");
                        ingore = false;
                    }

                    break;
            }
            ingore = false;
        }
        sc.close();
    }

    public static void getExpensesByCategory(Integer id, ExpenseService expenseService) {
        List<Expense> expenses = expenseService.findAllExpensesByCategoryId(id);
        if (expenses != null) {
            System.out.println("All expenses in category with id " + id + ":");
            expenses.stream().forEach(s -> System.out.println(s.toString()));
        }
    }

    public static void getExpenses(ExpenseService expenseService) {
        List<Expense> expenses = expenseService.findAllExpenses();
        if (expenses != null) {
            System.out.println("All expenses:");
            expenses.stream().forEach(s -> System.out.println(s.toString()));
        }
    }

    public static void getCategories(CategoryService categoryService) {
        List<Category> categories = categoryService.findAllCategories();
        if (categories != null) {
            System.out.println("All categories:");
            categories.stream().forEach(s -> System.out.println(s.toString()));
        }
    }

    public static Integer readInt(Scanner sc) {
        Integer integer = null;
        try {
            integer = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            return null;
        } catch (NoSuchElementException e) {
            return null;
        }
        return integer;
    }
}
