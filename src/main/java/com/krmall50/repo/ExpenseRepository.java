package com.krmall50.repo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.krmall50.model.Expense;

public class ExpenseRepository implements Repository<Expense, Integer> {
    private DataSource dataSource;

    public ExpenseRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Expense findById(Integer id) throws SQLException {
        String sql = "select * from expenses where id = ?";
        try (Connection conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                LocalDate date = rs.getDate("date").toLocalDate();
                Expense expense = new Expense(id, rs.getString("description"), rs.getBigDecimal("amount"), date,
                        rs.getInt("categoryId"));
                return expense;
            }
            return null;
        }
    };

    public List<Expense> findAll() throws SQLException {
        List<Expense> expenses = new ArrayList<>();
        String sql = "select * from expenses";
        try (Connection conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();) {
            while (rs.next()) {
                LocalDate date = rs.getDate("date").toLocalDate();
                Expense expense = new Expense(rs.getInt("id"), rs.getString("description"), rs.getBigDecimal("amount"),
                        date, rs.getInt("categoryId"));
                expenses.add(expense);
            }
            return expenses;
        }
    };

    public int add(Expense entity) throws SQLException {
        String sql = "insert into expenses (description, amount, date, categoryId) values (?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, entity.getDescription());
            ps.setBigDecimal(2, entity.getAmount());
            ps.setDate(3, Date.valueOf(entity.getDate()));
            ps.setInt(4, entity.getCategoryId());
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    entity.setId(keys.getInt("id"));
                }
            }

            return entity.getId();
        }
    };

    public int update(Expense entity) throws SQLException {
        String sql = "update expenses set description = ?, amount = ?, date = ?, categoryId = ? where id = ?";
        try (Connection conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, entity.getDescription());
            ps.setBigDecimal(2, entity.getAmount());
            ps.setDate(3, Date.valueOf(entity.getDate()));
            ps.setInt(4, entity.getCategoryId());
            ps.setInt(5, entity.getId());

            int rows = ps.executeUpdate();
            return rows;
        }
    };

    public int delete(Integer id) throws SQLException {
        String sql = "delete from expenses where id = ?";
        try (Connection conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            return rows;
        }
    };

    // careful this is for testing only
    public void clearTable() throws SQLException {
        String sql = "truncate table expenses cascade";
        try (Connection conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.executeUpdate();
        }
    }
}
