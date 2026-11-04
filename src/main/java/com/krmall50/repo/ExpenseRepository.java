package com.krmall50.repo;

import java.sql.SQLException;
import java.util.List;

import com.krmall50.model.Expense;

public class ExpenseRepository implements Repository<Expense, Integer> {
    public Expense findById(Integer id) throws SQLException {
        return null;
    };

    public List<Expense> findAll() throws SQLException {
        return null;
    };

    public int add(Expense entity) throws SQLException {
        return 0;
    };

    public int update(Expense entity) throws SQLException {
        return 0;
    };

    public int delete(Integer id) throws SQLException {
        return 0;
    };
}
