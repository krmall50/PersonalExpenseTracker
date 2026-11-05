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
}
