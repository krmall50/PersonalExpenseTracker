package com.krmall50.render;

import java.math.BigDecimal;
import java.time.LocalDate;
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
        int choice = -1;
        boolean ingoreMessage = false;
        boolean ignoreChoice = false;

        while (choice != 0) {

            if (!ignoreChoice) {
                System.out.println(
                        "1. Get all expences \n2. Get all categories" +
                                "\n3. Get expences by category \n4. Add expense \n5. Add category" +
                                "\n6. Find expense by id \n7. Find category by id" +
                                "\n8. Delete Expense \n9. Delete Category" +
                                "\n10. Update Expense \n11. Update Category" +
                                "\n12. Calculate last week expenses" +
                                "\n13. Calculate last month expences" +
                                "\n14. Calculate total expences" +
                                "\n15. Calculate total by category");

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
                        choice = 3;
                        ignoreChoice = true;
                        continue;
                    }
                    break;
                case 4:
                    Integer id = addExpense(sc, expenseService);
                    if (id == null) {
                        System.out.println("Enter valid data.");
                        choice = 4;
                        ignoreChoice = true;
                        continue;
                    }
                    break;
                case 5:
                    addCategory(sc, categoryService);
                    break;
                case 14:
                    System.out.println("Total: " + expenseService.totalExpenses());
                    break;
                case 15:
                    System.out.println("Enter category Id:");
                    Integer categoryId1 = readInt(sc);
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

    public static Integer addExpense(Scanner sc, ExpenseService expenseService) {
        LocalDate date = LocalDate.now();
        BigDecimal amount;
        System.out.println("Enter description: ");
        String desc = sc.nextLine();
        System.out.println("Enter amount: ");

        Double doubleAmount = readDouble(sc);
        if (doubleAmount != null) {
            amount = new BigDecimal(doubleAmount);
        } else {
            amount = null;
        }

        System.out.println("Enter category Id:");
        Integer categoryId = readInt(sc);
        if (amount == null || categoryId == null) {
            return null;
        }
        Expense expense = new Expense(desc, amount, date, categoryId);
        Integer id = expenseService.addExpense(expense);
        return id;
    }

    public static Integer addCategory(Scanner sc, CategoryService categoryService) {
        System.out.println("Enter name: ");
        String name = sc.nextLine();
        System.out.println("Enter description: ");
        String desc = sc.nextLine();

        Category category = new Category(name, desc);
        Integer id = categoryService.addCategory(category);
        return id;
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

    public static Double readDouble(Scanner sc) {
        Double d = null;
        try {
            d = Double.parseDouble(sc.nextLine());
        } catch (NumberFormatException e) {
            return null;
        } catch (NoSuchElementException e) {
            return null;
        }
        return d;
    }
}
