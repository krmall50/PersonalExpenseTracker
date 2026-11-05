package com.krmall50.service;

import java.sql.SQLException;

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
        try {
            int id = repo.add(expense);
            System.out.println("Expense created, id: " + id);
        } catch (SQLException e) {
            System.out.println("Category with id " + expense.getCategoryId() + " does not exist.");
        }
    }

    public void deleteExpense(int id) {
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
}
