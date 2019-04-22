package com.training.model.dao.impl;

import com.training.model.dao.ISqlStatements;
import com.training.model.dao.interfaces.TaxiDao;
import com.training.model.dao.interfaces.UserDao;
import com.training.model.dao.mappers.StreetMapper;
import com.training.model.entity.Order;
import com.training.model.entity.Street;
import com.training.model.entity.Taxi;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class JDBCTaxiDao implements TaxiDao {
    private static final Logger log = Logger.getLogger(JDBCStreetDao.class);
    private Connection connection;

    public JDBCTaxiDao(Connection connection) {
        this.connection = connection;
        try {
            startTransaction();
        } catch (SQLException e) {
            log.warn("can't start trsnsaction in constructor");
        }
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
    public Optional<Integer> confirmOrderForClosestTaxiWithCarClass(
            int userId, int carClass, int destinationStreetId,
            int sourceStreetId, int bill) {
        Optional<Integer> delay = Optional.empty();
        ResultSet resultSet;
        int taxiId;
        try( PreparedStatement selectStatement = connection.prepareStatement(
                ISqlStatements.FINT_TAXY_ID_AND_DELAY_BY_CAR_CLASS_AND_SRC_STREET);
             PreparedStatement updateTaxiStatement = connection
                     .prepareStatement(ISqlStatements.UPDATE_TAXI_LOCATION);
             PreparedStatement updateUserStatement = connection.prepareStatement(
                     ISqlStatements.UPDATE_USER_SPENDINGS)){
            selectStatement.setInt(1, sourceStreetId);
            selectStatement.setInt(2, carClass);
            resultSet = selectStatement.executeQuery();
            resultSet.next();
            taxiId = resultSet.getInt("taxiId");
            delay = Optional.of(resultSet.getInt("delay"));
            resultSet.close();

            updateUserStatement.setInt(1, bill);
            updateUserStatement.setInt(2, userId);
            updateUserStatement.executeUpdate();

            updateTaxiStatement.setInt(1, destinationStreetId);
            updateTaxiStatement.setInt(2, taxiId);
            updateTaxiStatement.executeUpdate();
        } catch ( SQLException e ) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                log.warn("can't rollback");
            }
            log.warn("there is a SQLException in confirmOrderForClosestTaxiWithCarClass");
            log.debug(e.getMessage(), e);
        }
        return delay;
    }

    @Override
    public void startTransaction() throws SQLException {
        connection.setAutoCommit(false);
    }

    @Override
    public void commit() throws SQLException {
        connection.commit();

    }

    @Override
    public void close() {
        try {
            commit();
        } catch (SQLException e) {
            log.warn("can't commit in close");
        }
        try {
            connection.close();
        } catch (SQLException e) {
            log.info("can't close in close");
        }
    }
}
