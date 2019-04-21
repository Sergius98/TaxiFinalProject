package com.training.model.dao.impl;

import com.training.model.dao.ISqlStatements;
import com.training.model.dao.interfaces.LoyaltyThresholdDao;
import com.training.model.dao.mappers.DiscountMapper;
import com.training.model.dao.mappers.LoyaltyThresholdMapper;
import com.training.model.entity.Discount;
import com.training.model.entity.LoyaltyThreshold;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCLoyaltyThresholdDao implements LoyaltyThresholdDao {
    LoyaltyThresholdMapper mapper = new LoyaltyThresholdMapper();
    private static final Logger log = Logger.getLogger(JDBCDiscountDao.class);
    private Connection connection;

    public JDBCLoyaltyThresholdDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(LoyaltyThreshold entity) {

        return false;
    }

    @Override
    public Optional<LoyaltyThreshold> findById(int id) {
        return null;
    }

    @Override
    public List<LoyaltyThreshold> findAll() {
        List<LoyaltyThreshold> list;

        try( PreparedStatement statement = connection
                .prepareStatement(ISqlStatements.READ_LOYALTY_THRESHOLD) ){
            list = mapper.extractAllFromResultSet(statement);
        } catch ( SQLException e ) {
            log.warn("there is a SQLException in findAll");
            log.debug(e.getMessage(), e);
            list = new ArrayList<>();
        }
        return list;
    }

    @Override
    public void update(LoyaltyThreshold entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {

    }
}
