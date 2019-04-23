package com.training.model.dao.mappers;

import com.training.model.entity.User;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper extends AbstractMapper<User>  {
    private static final Logger log = Logger.getLogger(UserMapper.class);

    public User extractEntity(ResultSet set) throws SQLException {
        User user = new User();
        user.setId(set.getInt("id"));
        user.setNickname(set.getString("nickname"));
        user.setPassword(set.getString("password"));
        user.setRole(set.getInt("role"));
        user.setSpendMoney(set.getLong("spendMoney"));
        return user;
    }
}
