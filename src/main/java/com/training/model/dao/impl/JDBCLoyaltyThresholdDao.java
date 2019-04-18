package com.training.model.dao.impl;

import com.training.model.dao.interfaces.LoyaltyThresholdDao;
import com.training.model.entity.Discount;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class JDBCLoyaltyThresholdDao implements LoyaltyThresholdDao {
    public JDBCLoyaltyThresholdDao(Connection connection) {
    }

    @Override
    public boolean create(Discount entity) {

        return false;
    }

    @Override
    public Optional<Discount> findById(int id) {
        return null;
    }

    @Override
    public List<Discount> findAll() {
        return null;
    }

    @Override
    public void update(Discount entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {

    }
}
