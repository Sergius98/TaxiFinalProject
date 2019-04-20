package com.training.model.dao.impl;

import com.training.model.dao.*;
import com.training.model.dao.interfaces.*;
import com.training.model.entity.Car;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {
    private DataSource dataSource = ConnectionPool.getDataSource();

    private Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CarDao createCarDao() {
        return new DummyCarDao(null);
    }

    @Override
    public DiscountDao createDiscountDao() {
        return new JDBCDiscountDao(getConnection());
    }

    @Override
    public LoyaltyThresholdDao createLoyaltyThresholdDao() {
        return new JDBCLoyaltyThresholdDao(getConnection());
    }

    @Override
    public StreetDao createStreetDao() {
        return new JDBCStreetDao(getConnection());
    }

    @Override
    public TaxiDao createTaxiDao() {
        return new JDBCTaxiDao(getConnection());
    }

    @Override
    public UserDao createUserDao() {
        return new JDBCUserDao(getConnection());
    }
}
