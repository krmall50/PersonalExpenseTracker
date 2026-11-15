package com.krmall50.render;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.krmall50.model.Expense;
import com.krmall50.service.CategoryService;
import com.krmall50.service.ExpenseService;

import com.krmall50.model.Category;

public class UIFunctions {
    public static void displayMenu() {
        System.out.println(
                "1. Get all expences \n2. Get all categories" +
                        "\n3. Get expences by category \n4. Add expense \n5. Add category" +
                        "\n6. Find expense by id \n7. Find category by id" +
                        "\n8. Delete Expense \n9. Delete Category" +
                        "\n10. Update Expense \n11. Update Category" +
                        "\n12. Calculate last week expenses" +
                        "\n13. Calculate last month expences" +
                        "\n14. Calculate total expences" +
                        "\n15. Calculate total by category" +
                        "\n0. Exit");
    }

    public static Expense getExpenseById(ExpenseService expenseService, Integer id) {
        Expense expense = expenseService.findExpenseById(id);
        if (expense != null) {
            return expense;
        }
        return null;
    }

    public static Category getCategoryById(CategoryService categoryService, Integer id) {
        Category category = categoryService.findCategoryById(id);
        if (category != null) {
            return category;
        }
        return null;
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

    public static Integer updateExpense(Scanner sc, ExpenseService expenseService, Integer id) {
        Expense expense = expenseService.findExpenseById(id);
        if (expense == null) {
            return null;
        }
        BigDecimal amount;
        System.out.println("Enter updated description: ");
        String desc = sc.nextLine();
        System.out.println("Enter updated amount: ");

        Double doubleAmount = readDouble(sc);
        if (doubleAmount != null) {
            amount = new BigDecimal(doubleAmount);
        } else {
            amount = null;
        }

        System.out.println("Enter updated category Id:");
        Integer categoryId = readInt(sc);
        if (amount == null || categoryId == null) {
            return null;
        }
        expense.setAmount(amount);
        expense.setdescription(desc);
        expense.setCategoryId(categoryId);
        expenseService.updateExpense(expense);

        return expense.getId();
    }

    public static Integer updateCategory(Scanner sc, CategoryService categoryService, Integer id) {
        Category category = categoryService.findCategoryById(id);
        if (category == null) {
            return null;
        }
        System.out.println("Enter updated name: ");
        String name = sc.nextLine();
        System.out.println("Enter updated description: ");
        String desc = sc.nextLine();
        category.setName(name);
        category.setdescription(desc);
        Integer result = categoryService.updateCategory(category);
        if (result == null) {
            return null;
        }

        return category.getId();
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
