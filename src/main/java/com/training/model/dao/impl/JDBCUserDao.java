package com.training.model.dao.impl;

import com.training.model.dao.ISqlStatements;
import com.training.model.dao.interfaces.UserDao;
import com.training.model.dao.mappers.UserMapper;
import com.training.model.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.Servlet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

public class JDBCUserDao implements UserDao {
    UserMapper mapper = new UserMapper();
    private static final Logger log = Logger.getLogger(JDBCUserDao.class);
    private Connection connection;
    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(User entity) {

        boolean isSuccessful = false;

        try (PreparedStatement prepStatement = connection.prepareStatement(ISqlStatements.INSERT_INTO_USER)) {
            prepStatement.setString(1, entity.getNickname());
            prepStatement.setString(2, entity.getPassword());
            prepStatement.setInt(3, entity.getRole());

            prepStatement.execute();
            isSuccessful = true;
        } catch (SQLIntegrityConstraintViolationException e) {
            log.error("there is a problem in JDBCUserDao:create");
            log.debug(e.getMessage(), e);
        } catch (SQLException e) {
            log.warn("there is a SQLException in JDBCUserDao:create");
            log.debug(e.getMessage(), e);
        }
        return isSuccessful;
    }

    @Override
    public Optional<User> findById(int id) {
        try( PreparedStatement statement = connection.prepareStatement(ISqlStatements.READ_USER_BY_ID) ){
            statement.setInt(1, id);
            return mapper.extractFromResultSet(statement);
        } catch ( SQLException e ) {
            log.warn("there is a SQLException in JDBCUserDao:findById");
            log.debug(e.getMessage(), e);
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findByNickname(String nickname) {
        try( PreparedStatement statement = connection.prepareStatement(ISqlStatements.READ_USER_BY_NICKNAME) ){
            statement.setString(1, nickname);
            return mapper.extractFromResultSet(statement);
        } catch ( SQLException e ) {
            log.warn("there is a SQLException in JDBCUserDao:findByNickname");
            log.debug(e.getMessage(), e);
            return Optional.empty();
        }
    }

    @Override
    public List<User> findAll() {
        // TODO: 4/17/19 don't forget to add findAll for user (for admin view)
        return null;
    }

    @Override
    public void update(User entity) {
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
