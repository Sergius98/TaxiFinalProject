package com.training.model.dao.impl;

import com.training.model.dao.interfaces.StreetDao;
import com.training.model.entity.Street;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class JDBCStreetDao implements StreetDao {
    public JDBCStreetDao(Connection connection) {
    }

    @Override
    public boolean create(Street entity) {

        return false;
    }

    @Override
    public Optional<Street> findById(int id) {
        return null;
    }

    @Override
    public List<Street> findAll() {
        return null;
    }

    @Override
    public void update(Street entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {

    }
}
