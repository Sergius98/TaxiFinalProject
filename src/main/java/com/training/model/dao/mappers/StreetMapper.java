package com.training.model.dao.mappers;

import com.training.model.entity.Street;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StreetMapper extends AbstractMapper<Street> {
    private static final Logger log = Logger.getLogger(StreetMapper.class);

    public Street extractEntity(ResultSet set) throws SQLException {
        Street street = new Street();
        street.setId(set.getInt("id"));
        street.setName("en", set.getString("name_en"));
        street.setName("ua", set.getString("name_ua"));
        street.setX(set.getInt("x"));
        street.setY(set.getInt("y"));
        return street;
    }
}
