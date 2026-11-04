package com.krmall50.repo;

import java.sql.SQLException;
import java.util.List;

interface Repository<T, ID> {
    void findById(ID id) throws SQLException;

    List<T> findAll() throws SQLException;

    int add(T entity) throws SQLException;

    void update(T entity) throws SQLException;

    void delete(T entity) throws SQLException;
}
