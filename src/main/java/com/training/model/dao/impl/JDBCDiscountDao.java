package com.training.model.dao.impl;

import com.training.model.dao.ISqlStatements;
import com.training.model.dao.interfaces.DiscountDao;
import com.training.model.dao.mappers.DiscountMapper;
import com.training.model.dao.mappers.UserMapper;
import com.training.model.entity.Discount;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class JDBCDiscountDao implements DiscountDao {
    DiscountMapper mapper = new DiscountMapper();
    private static final Logger log = Logger.getLogger(JDBCDiscountDao.class);
    private Connection connection;

    public JDBCDiscountDao(Connection connection) {
        this.connection = connection;
    }

    private void setIntOrNull(PreparedStatement prepStatement, int id,
                              Optional<Integer> value) throws SQLException {
        try{
            prepStatement.setInt(id, value.get());
        } catch (NoSuchElementException e){
            prepStatement.setNull(id, Types.INTEGER);
        }
    }

    private void setLongOrNull(PreparedStatement prepStatement, int id,
                               Optional<Long> value) throws SQLException {
        try{
            // TODO: 4/20/19 change to bigint (sql`s Long)
            prepStatement.setInt(id, Integer.valueOf(String.valueOf(value.get())));
        } catch (NoSuchElementException e){
            // TODO: 4/20/19 change to bigint (sql`s Long)
            prepStatement.setNull(id, Types.INTEGER);
        }
    }


    @Override
    public boolean create(Discount entity) {

        boolean isSuccessful = false;

        try (PreparedStatement prepStatement = connection.prepareStatement(
                ISqlStatements.INSERT_INTO_DISCOUNT)) {
            setIntOrNull(prepStatement, 1, entity.getCarClass());
            setIntOrNull(prepStatement, 2, entity.getSourceStreetId());
            setIntOrNull(prepStatement, 3, entity.getDestinationStreetId());
            setLongOrNull(prepStatement, 4, entity.getMinimalBill());
            setLongOrNull(prepStatement, 5, entity.getMinimalThreshold());
            prepStatement.setLong(6, entity.getDiscount());

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
    public Optional<Discount> findById(int id) {
        Optional<Discount> discount = Optional.empty();

        try( PreparedStatement statement = connection
                .prepareStatement(ISqlStatements.READ_DISCOUNT_BY_ID) ){
            statement.setInt(1, id);
            discount = mapper.extractFromResultSet(statement);
        } catch ( SQLException e ) {
            log.warn("there is a SQLException in findById");
            log.debug(e.getMessage(), e);
        }
        return discount;
    }

    @Override
    public List<Discount> findAll() {
        List<Discount> list;

        try( PreparedStatement statement = connection
                .prepareStatement(ISqlStatements.READ_DISCOUNT) ){
            list = mapper.extractAllFromResultSet(statement);
        } catch ( SQLException e ) {
            log.warn("there is a SQLException in findAll");
            log.debug(e.getMessage(), e);
            list = new ArrayList<>();
        }
        return list;
    }

    @Override
    public void update(Discount entity) {

    }

    @Override
    public void delete(int id) {
        try( PreparedStatement statement = connection
                .prepareStatement(ISqlStatements.DELETE_DISCOUNT) ){
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
