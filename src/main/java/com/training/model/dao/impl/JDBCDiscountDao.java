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
