package com.krmall50.repo;

import java.util.List;

interface Repository<T, ID> {
    void findById(ID id);

    List<T> findAll();

    void add(T entity);

    void update(T entity);

    void delete(T entity);
}
