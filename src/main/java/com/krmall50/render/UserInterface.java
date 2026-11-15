package com.krmall50.render;

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
        int choice = -1;
        boolean ingoreMessage = false;
        boolean ignoreChoice = false;

        while (choice != 0) {

            if (!ignoreChoice) {
                UIFunctions.displayMenu();

                try {
                    choice = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Choose available option 1-6.");
                    ingoreMessage = true;
                } catch (NoSuchElementException e) {
                    System.out.println("Choose available option 1-6.");
                    ingoreMessage = true;
                }
            }

            switch (choice) {
                case 1:
                    UIFunctions.getExpenses(expenseService);
                    break;
                case 2:
                    UIFunctions.getCategories(categoryService);
                    break;
                case 3:
                    System.out.println("Enter category Id:");
                    Integer categoryId = UIFunctions.readInt(sc);
                    if (categoryId != null) {
                        UIFunctions.getExpensesByCategory(categoryId, expenseService);
                    } else {
                        System.out.println("Enter valid category Id");
                        choice = 3;
                        ignoreChoice = true;
                        continue;
                    }
                    break;
                case 4:
                    Integer id = UIFunctions.addExpense(sc, expenseService);
                    if (id == null) {
                        System.out.println("Enter valid data.");
                        choice = 4;
                        ignoreChoice = true;
                        continue;
                    }
                    break;
                case 5:
                    UIFunctions.addCategory(sc, categoryService);
                    break;
                case 6:
                    System.out.println("Enter expense Id:");
                    Integer expenseId = UIFunctions.readInt(sc);

                    if (expenseId != null) {
                        Expense expense = UIFunctions.getExpenseById(expenseService, expenseId);
                        if (expense != null) {
                            System.out.println(expense.toString());
                        }
                    } else {
                        System.out.println("Enter valid Expense Id");
                        choice = 6;
                        ignoreChoice = true;
                        continue;
                    }
                    break;
                case 7:
                    System.out.println("Enter category Id:");
                    Integer categoryGetId = UIFunctions.readInt(sc);

                    if (categoryGetId != null) {
                        Category category = UIFunctions.getCategoryById(categoryService, categoryGetId);
                        if (category != null) {
                            System.out.println(category.toString());
                        }
                    } else {
                        System.out.println("Enter valid category Id");
                        choice = 7;
                        ignoreChoice = true;
                        continue;
                    }
                    break;
                case 8:
                    System.out.println("Enter expense Id:");
                    Integer expenseDeleteId = UIFunctions.readInt(sc);
                    if (expenseDeleteId != null) {
                        expenseService.deleteExpense(expenseDeleteId);
                    } else {
                        System.out.println("Enter valid expense Id");
                        choice = 8;
                        ignoreChoice = true;
                        continue;
                    }
                    break;
                case 9:
                    System.out.println("Enter category Id:");
                    Integer categoryDeleteId = UIFunctions.readInt(sc);
                    if (categoryDeleteId != null) {
                        categoryService.deleteCategory(categoryDeleteId);
                    } else {
                        System.out.println("Enter valid category Id");
                        choice = 9;
                        ignoreChoice = true;
                        continue;
                    }
                    break;
                case 10:
                    System.out.println("Enter expense Id:");
                    Integer expenseUpdateId = UIFunctions.readInt(sc);
                    if (expenseUpdateId != null) {
                        UIFunctions.updateExpense(sc, expenseService, expenseUpdateId);
                    } else {
                        System.out.println("Enter valid expense Id");
                        choice = 10;
                        ignoreChoice = true;
                        continue;
                    }
                    break;
                case 11:
                    System.out.println("Enter category Id:");
                    Integer categoryUpdateId = UIFunctions.readInt(sc);
                    if (categoryUpdateId != null) {
                        UIFunctions.updateCategory(sc, categoryService, categoryUpdateId);
                    } else {
                        System.out.println("Enter valid expense Id");
                        choice = 11;
                        ignoreChoice = true;
                        continue;
                    }
                    break;
                case 12:
                    System.out.println("Total expenses last week: " + expenseService.totalExpensesLastWeek());
                    break;
                case 13:
                    System.out.println("Total expenses last month: " + expenseService.totalExpensesLastMonth());
                    break;
                case 14:
                    System.out.println("Total: " + expenseService.totalExpenses());
                    break;
                case 15:
                    System.out.println("Enter category Id:");
                    Integer categoryId1 = UIFunctions.readInt(sc);
                    if (categoryId1 != null) {
                        System.out.println("Total in category " + categoryId1 + ": "
                                + expenseService.totalExpensesByCategory(categoryId1));
                    } else {
                        System.out.println("Enter valid categoryId");
                        choice = 8;
                        ignoreChoice = true;
                        continue;
                    }
                    break;
                default:
                    if (!ingoreMessage && choice != 0) {
                        System.out.println("Choose available option 1-6.");
                        ingoreMessage = false;
                    }

                    break;
            }
            ingoreMessage = false;
            ignoreChoice = false;
        }
        sc.close();
    }
}
