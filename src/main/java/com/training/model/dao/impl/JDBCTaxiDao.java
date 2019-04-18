package com.training.model.dao.impl;

import com.training.model.dao.interfaces.TaxiDao;
import com.training.model.entity.Taxi;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class JDBCTaxiDao implements TaxiDao {
    public JDBCTaxiDao(Connection connection) {
    }

    @Override
    public boolean create(Taxi entity) {

        return false;
    }

    @Override
    public Optional<Taxi> findById(int id) {
        return null;
    }

    @Override
    public List<Taxi> findAll() {
        return null;
    }

    @Override
    public void update(Taxi entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {

    }
}
