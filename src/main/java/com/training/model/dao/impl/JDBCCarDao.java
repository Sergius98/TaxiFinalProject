package com.training.model.dao.impl;

import com.training.model.dao.ISqlStatements;
import com.training.model.dao.interfaces.CarDao;
import com.training.model.dao.interfaces.TaxiDao;
import com.training.model.dao.mappers.CarMapper;
import com.training.model.dao.mappers.StreetMapper;
import com.training.model.entity.Car;
import com.training.model.entity.Street;
import com.training.model.entity.Taxi;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class JDBCCarDao implements CarDao {
    CarMapper mapper = new CarMapper();
    private static final Logger log = Logger.getLogger(JDBCStreetDao.class);
    Connection connection;

    JDBCCarDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(Car entity) {

        return false;
    }

    @Override
    public Optional<Car> findById(int id) {
        Optional<Car> car = Optional.empty();
        try( PreparedStatement statement = connection
                .prepareStatement(ISqlStatements.READ_CAR_BY_ID) ){
            statement.setInt(1, id);
            car = mapper.extractFromResultSet(statement);
        } catch ( SQLException e ) {
            log.warn("there is a SQLException in findById");
            log.debug(e.getMessage(), e);
        }
        return car;
    }

    @Override
    public List<Car> findAll() {
        List<Car> list;

        try( PreparedStatement statement = connection
                .prepareStatement(ISqlStatements.READ_CAR) ){
            list = mapper.extractAllFromResultSet(statement);
        } catch ( SQLException e ) {
            log.warn("there is a SQLException in findAll");
            log.debug(e.getMessage(), e);
            list = new ArrayList<>();
        }
        return list;
    }

    @Override
    public void update(Car entity) {

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
