package com.training.model.dao.impl;

import com.training.model.dao.ISqlStatements;
import com.training.model.dao.interfaces.LoyaltyThresholdDao;
import com.training.model.dao.mappers.LoyaltyThresholdMapper;
import com.training.model.entity.LoyaltyThreshold;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
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


        boolean isSuccessful = false;

        try (PreparedStatement prepStatement = connection.prepareStatement(
                ISqlStatements.INSERT_INTO_LOYALTY_THRESHOLD)) {

            prepStatement.setLong(1, entity.getThreshold());
            prepStatement.setDouble(2, entity.getDiscount());

            prepStatement.executeUpdate();
            isSuccessful = true;
        } catch (SQLIntegrityConstraintViolationException e) {
            log.error("there is a problem in create");
            log.debug(e.getMessage(), e);
        } catch (SQLException e) {
            log.warn("there is a SQLException in create");
            log.debug(e.getMessage(), e);
        }
        return isSuccessful;
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
        // TODO: 4/21/19 exceptions for empty methods 
    }

    @Override
    public void delete(int id) {
        try( PreparedStatement statement = connection
                .prepareStatement(ISqlStatements.DELETE_LOYALTY_THRESHOLD) ){
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch ( SQLException e ) {
            log.warn("there is a SQLException in delete");
            log.debug(e.getMessage(), e);
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            log.warn("there is a SQLException in close");
            log.debug(e.getMessage(), e);
        }
    }
}
