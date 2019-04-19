package com.training.model.dao.impl;

import com.training.model.dao.ISqlStatements;
import com.training.model.dao.interfaces.DiscountDao;
import com.training.model.dao.mappers.DiscountMapper;
import com.training.model.dao.mappers.UserMapper;
import com.training.model.entity.Discount;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCDiscountDao implements DiscountDao {
    DiscountMapper mapper = new DiscountMapper();
    private static final Logger log = Logger.getLogger(JDBCDiscountDao.class);
    private Connection connection;

    public JDBCDiscountDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(Discount entity) {
        return false;
    }

    @Override
    public Optional<Discount> findById(int id) {
        try( PreparedStatement statement = connection
                .prepareStatement(ISqlStatements.READ_DISCOUNT_BY_ID) ){
            statement.setInt(1, id);
            return mapper.extractFromResultSet(statement);
        } catch ( SQLException e ) {
            log.warn("there is a SQLException in JDBCDiscountDao:findById");
            log.debug(e.getMessage(), e);
            return Optional.empty();
        }
    }

    @Override
    public List<Discount> findAll() {
        try( PreparedStatement statement = connection
                .prepareStatement(ISqlStatements.READ_DISCOUNT) ){
            return mapper.extractAllFromResultSet(statement);
        } catch ( SQLException e ) {
            log.warn("there is a SQLException in JDBCDiscountDao:findById");
            log.debug(e.getMessage(), e);
            return new ArrayList<>();
        }
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
