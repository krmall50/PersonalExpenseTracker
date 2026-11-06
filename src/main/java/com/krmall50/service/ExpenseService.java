package com.krmall50.service;

import java.sql.SQLException;
import java.util.List;

import com.krmall50.model.Expense;
import com.krmall50.repo.ExpenseRepository;

public class ExpenseService {
    ExpenseRepository repo;

    public ExpenseService(ExpenseRepository repo) {
        this.repo = repo;
    }

    public void addExpense(Expense expense) {
        if (expense.getAmount() == null) {
            System.out.println("Amount cannot be null.");
            return;
        }
        if (expense.getDate() == null) {
            System.out.println("Date cannot be null.");
            return;
        }
        if (expense.getDescription() == null) {
            System.out.println("Description cannot be null.");
            return;
        }
        if (expense.getDescription().length() > 255) {
            System.out.println("Description cannot be over 255 characters.");
            return;
        }
        try {
            int id = repo.add(expense);
            System.out.println("Expense created, id: " + id);
        } catch (SQLException e) {
            System.out.println("Category with id " + expense.getCategoryId() + " does not exist.");
        }
    }

    public void deleteExpense(int id) {
        if (id < 0) {
            System.out.println("Enter valid id.");
            return;
        }
        try {
            int rows = repo.delete(id);
            if (rows == 1) {
                System.out.println("Expense with id " + id + " was deleted.");
            } else {
                System.out.println("Expense with id " + id + " does not exist.");
            }
        } catch (SQLException e) {
            System.out.println("Couldn't delete expense.");
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
}
