package com.training.model.dao.impl;

import com.training.model.dao.ISqlStatements;
import com.training.model.dao.interfaces.StreetDao;
import com.training.model.dao.mappers.StreetMapper;
import com.training.model.entity.Street;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCStreetDao implements StreetDao {
    StreetMapper mapper = new StreetMapper();
    private static final Logger log = Logger.getLogger(JDBCStreetDao.class);
    private Connection connection;

    public JDBCStreetDao(Connection connection) {
        this.connection = connection;

    }

    @Override
    public boolean create(Street entity) {

        return false;
    }

    @Override
    public Optional<Street> findById(int id) {
        Optional<Street> street = Optional.empty();
        try( PreparedStatement statement = connection
                .prepareStatement(ISqlStatements.READ_STREET_BY_ID) ){
            statement.setInt(1, id);
            street = mapper.extractFromResultSet(statement);
        } catch ( SQLException e ) {
            log.warn("there is a SQLException in findById");
            log.debug(e.getMessage(), e);
        }
        return street;
    }

    @Override
    public List<Street> findAll() {
        List<Street> list;

        try( PreparedStatement statement = connection
                .prepareStatement(ISqlStatements.READ_STREET) ){
            list = mapper.extractAllFromResultSet(statement);
        } catch ( SQLException e ) {
            log.warn("there is a SQLException in findAll");
            log.debug(e.getMessage(), e);
            list = new ArrayList<>();
        }
        return list;
    }

    @Override
    public void update(Street entity) {

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
