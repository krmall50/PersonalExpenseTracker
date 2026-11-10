package com.krmall50.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.krmall50.model.Expense;
import com.krmall50.repo.ExpenseRepository;

public class ExpenseService {
    ExpenseRepository repo;

    public ExpenseService(ExpenseRepository repo) {
        this.repo = repo;
    }

    public Integer addExpense(Expense expense) {
        if (expense.getAmount() == null) {
            System.out.println("Amount cannot be null.");
            return null;
        }
        if (expense.getDate() == null) {
            System.out.println("Date cannot be null.");
            return null;
        }
        if (expense.getDescription() == null) {
            System.out.println("Description cannot be null.");
            return null;
        }
        if (expense.getDescription().length() > 255) {
            System.out.println("Description cannot be over 255 characters.");
            return null;
        }
        try {
            int id = repo.add(expense);
            System.out.println("Expense created, id: " + id);
            return id;
        } catch (SQLException e) {
            System.out.println("Category with id " + expense.getCategoryId() + " does not exist.");
            return null;

        }
    }

    public Integer deleteExpense(int id) {
        if (id < 0) {
            System.out.println("Enter valid id.");
            return null;
        }
        try {
            int rows = repo.delete(id);
            if (rows == 1) {
                System.out.println("Expense with id " + id + " was deleted.");
                return id;
            } else {
                System.out.println("Expense with id " + id + " does not exist.");
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Couldn't delete expense.");
            return null;

        }
    }

    public Expense findExpenseById(int id) {
        if (id < 0) {
            System.out.println("Enter valid id.");
            return null;
        }
        try {
            Expense expense = repo.findById(id);
            if (expense == null) {
                System.out.println("Expense with id " + id + " does not exist.");
                return null;
            }
            return expense;
        } catch (SQLException e) {
            System.out.println("Couldn't find expense.");
            return null;
        }
    }

    public List<Expense> findAllExpenses() {
        try {
            List<Expense> expenses = repo.findAll();
            if (expenses.size() == 0) {
                System.out.println("No expenses were added yet.");
                return null;
            }
            return expenses;
        } catch (SQLException e) {
            System.out.println("Couldn't get all expenses");
            return null;
        }
    }

    public List<Expense> findAllExpensesByCategoryId(Integer categoryId) {
        try {
            List<Expense> expenses = repo.findAllByCategoryId(categoryId);
            if (expenses.size() == 0) {
                System.out.println("No expenses were added yet to category with id " + categoryId + ".");
                return null;
            }
            return expenses;
        } catch (SQLException e) {
            System.out.println("Couldn't get all expenses");
            return null;
        }
    }

    public Integer updateExpense(Expense expense) {
        if (expense.getAmount() == null) {
            System.out.println("Amount cannot be null.");
            return null;
        }
        if (expense.getDate() == null) {
            System.out.println("Date cannot be null.");
            return null;
        }
        if (expense.getDescription() == null) {
            System.out.println("Description cannot be null.");
            return null;
        }
        if (expense.getDescription().length() > 255) {
            System.out.println("Description cannot be over 255 characters.");
            return null;
        }
        try {
            int rows = repo.update(expense);
            if (rows == 0) {
                System.out.println("Expense with id " + expense.getId() + " does not exist.");
                return null;
            }
            System.out.println("Expense with id " + expense.getId() + " was updated.");
            return expense.getId();
        } catch (SQLException e) {
            System.out.println("Category with id " + expense.getCategoryId() + " does not exist.");
            return null;
        }
    }

    public BigDecimal totalExpenses() {
        List<Expense> expenses = findAllExpenses();
        if (expenses == null)
            return null;

        double total = 0;

        for (Expense expense : expenses) {
            total += expense.getAmount().doubleValue();
        }

        BigDecimal totalBigDecimal = new BigDecimal(total);
        return totalBigDecimal;
    }

    public BigDecimal totalExpensesByCategory(Integer categoryId) {
        List<Expense> expenses = findAllExpensesByCategoryId(categoryId);
        if (expenses == null)
            return null;

        double total = 0;

        for (Expense expense : expenses) {
            total += expense.getAmount().doubleValue();
        }

        BigDecimal totalBigDecimal = new BigDecimal(total);
        return totalBigDecimal;
    }

    public BigDecimal totalExpensesLastMonth() {
        List<Expense> expenses = findAllExpenses();
        double total = 0;
        LocalDate nowDate = LocalDate.now();

        List<Expense> expensesLastMonth = expenses.stream()
                .filter(s -> java.time.temporal.ChronoUnit.DAYS.between(s.getDate(), nowDate) < 30).toList();

        for (Expense expense : expensesLastMonth) {
            total += expense.getAmount().doubleValue();
        }

        BigDecimal totalBigDecimal = new BigDecimal(total);
        return totalBigDecimal;
    }

    public BigDecimal totalExpensesLastWeek() {
        List<Expense> expenses = findAllExpenses();
        double total = 0;
        LocalDate nowDate = LocalDate.now();

        List<Expense> expensesLastMonth = expenses.stream()
                .filter(s -> java.time.temporal.ChronoUnit.DAYS.between(s.getDate(), nowDate) < 7).toList();

        for (Expense expense : expensesLastMonth) {
            total += expense.getAmount().doubleValue();
        }

        BigDecimal totalBigDecimal = new BigDecimal(total);
        return totalBigDecimal;
    }
}
