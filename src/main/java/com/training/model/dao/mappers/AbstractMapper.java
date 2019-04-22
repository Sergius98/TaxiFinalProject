package com.training.model.dao.mappers;

import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractMapper<T> {
    private final Logger log = Logger.getLogger(this.getClass());

    public abstract T extractEntity(ResultSet set) throws SQLException;

    public Optional<T> extractFromResultSet(PreparedStatement prepStatement) {
        Optional<T> entity = Optional.empty();
        try (ResultSet resultSet = prepStatement.executeQuery()) {
            while (resultSet.next()) {
                entity = Optional.of(extractEntity(resultSet));
            }
        } catch (SQLException e) {
            log.warn("there is a SQLException in extractFromResultSet");
            log.debug(e.getMessage(), e);
        }
        return entity;
    }


    public List<T> extractAllFromResultSet(PreparedStatement prepStatement) {
        List<T> list = new ArrayList<>();

        try (ResultSet resultSet = prepStatement.executeQuery()) {
            while (resultSet.next()) {
                list.add(extractEntity(resultSet));
            }
        } catch (SQLException e) {
            log.warn("there is a SQLException in extractAllFromResultSet");
            log.debug(e.getMessage(), e);
        }
        return list;
    }
}
