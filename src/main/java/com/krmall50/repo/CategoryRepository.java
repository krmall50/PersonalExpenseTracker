package com.krmall50.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import javax.sql.DataSource;
import java.sql.Statement;

import com.krmall50.model.Category;

public class CategoryRepository implements Repository<Category, Integer> {
    private DataSource dataSource;

    public CategoryRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Category findById(Integer id) throws SQLException {
        String sql = "select * from categories where id = ?";
        try (Connection conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Category category = new Category(id, rs.getString("name"), rs.getString("description"));
                return category;
            } else {
                return null;
            }
        }
    };

    public List<Category> findAll() throws SQLException {
        String sql = "select * from categories";
        List<Category> categories = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                categories.add(new Category(rs.getInt("id"), rs.getString("name"), rs.getString("description")));
            }
            return categories;
        }
    };

    public int add(Category entity) throws SQLException {
        String sql = "insert into categories (name, description) values (?, ?)";
        try (Connection conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getDescription());
            int rows = ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next())
                    entity.setId(rs.getInt("id"));
            }
            return rows;
        }
    };

    public int update(Category entity) throws SQLException {
        String sql = "update categories set name = ?, description = ? where id = ?";
        try (Connection conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getDescription());
            ps.setInt(3, entity.getId());
            int rows = ps.executeUpdate();
            return rows;
        }
    };

    public int delete(Integer id) throws SQLException {
        String sql = "delete from categories where id = ?";
        try (Connection conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            return rows;
        }
    };
}
