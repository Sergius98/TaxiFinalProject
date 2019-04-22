package com.training.model.dao.mappers;

import com.training.model.entity.Car;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarMapper extends AbstractMapper<Car>{
    private static final Logger log = Logger.getLogger(CarMapper.class);

    @Override
    public Car extractEntity(ResultSet set) throws SQLException {
        Car car = new Car();
        car.setId(set.getInt("id"));
        car.setName("en", set.getString("name_en"));
        car.setName("ua", set.getString("name_ua"));
        car.setPrice(set.getLong("price"));
        return car;
    }
}
