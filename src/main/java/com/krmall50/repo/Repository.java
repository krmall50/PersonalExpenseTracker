package com.krmall50.repo;

import java.sql.SQLException;
import java.util.List;

interface Repository<T, ID> {
    T findById(ID id) throws SQLException;

    List<T> findAll() throws SQLException;

    int add(T entity) throws SQLException;

    int update(T entity) throws SQLException;

    int delete(ID id) throws SQLException;
}
