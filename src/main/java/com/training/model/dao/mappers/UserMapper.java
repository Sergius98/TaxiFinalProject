package com.training.model.dao.mappers;

import com.training.model.entity.User;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserMapper {
    private static final Logger log = Logger.getLogger(UserMapper.class);

    public User extractUser(ResultSet set) throws SQLException {
        User user = new User();
        user.setId(set.getInt("id"));
        user.setNickname(set.getString("nickname"));
        user.setPassword(set.getString("password"));
        user.setRole(set.getInt("role"));
        user.setSpendMoney(set.getInt("spendMoney"));
        return user;
    }

    public Optional<User> extractFromResultSet(PreparedStatement prepStatement) {
        Optional<User> user = Optional.empty();
        try (ResultSet resultSet = prepStatement.executeQuery()) {
            while (resultSet.next()) {
                user = Optional.of(extractUser(resultSet));
            }
        } catch (SQLException e) {
            log.warn("there is a SQLException in UserMapper:extractFromResultSet");
            log.debug(e.getMessage(), e);
        }
        return user;
    }
}
