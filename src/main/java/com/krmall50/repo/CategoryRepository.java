package com.krmall50.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;
import java.sql.Statement;

import com.krmall50.model.Category;

public class CategoryRepository implements Repository<Category, Integer> {
    private DataSource dataSource;

    public CategoryRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void findById(Integer id) throws SQLException {

    };

    public List<Category> findAll() throws SQLException {
        return null;
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

    public void update(Category entity) throws SQLException {
    };

    public void delete(Category entity) throws SQLException {
    };
}
