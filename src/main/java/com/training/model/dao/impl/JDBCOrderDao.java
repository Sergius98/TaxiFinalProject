package com.training.model.dao.impl;

import com.training.model.dao.ISqlStatements;
import com.training.model.dao.interfaces.OrderDao;
import com.training.model.dao.interfaces.StreetDao;
import com.training.model.dao.mappers.OrderMapper;
import com.training.model.dao.mappers.StreetMapper;
import com.training.model.entity.Order;
import com.training.model.entity.Street;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCOrderDao implements OrderDao {
    OrderMapper mapper = new OrderMapper();
    private static final Logger log = Logger.getLogger(JDBCOrderDao.class);
    private Connection connection;

    public JDBCOrderDao(Connection connection) {
        this.connection = connection;

    }

    @Override
    public Optional<Order> getOrder(int src, int dst, int user, int car) {
        Optional<Order> order = Optional.empty();
        try( PreparedStatement statement = connection
                .prepareStatement(ISqlStatements.GET_ORDER) ){
            statement.setInt(1, src);
            statement.setInt(2, dst);
            statement.setInt(3, user);
            statement.setInt(4, car);
            order = mapper.extractFromResultSet(statement);
            order.get().setIds(src, dst, car);
        } catch ( SQLException e ) {
            log.warn("there is a SQLException in findById");
            log.debug(e.getMessage(), e);
        }
        return order;
    }

    @Override
    public boolean create(Order entity) {

        return false;
    }

    @Override
    public Optional<Order> findById(int id) {
        return Optional.empty();
    }

    @Override
    public List<Order> findAll() {
        return new ArrayList<>();
    }

    @Override
    public void update(Order entity) {
// TODO: 4/21/19 transaction
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
