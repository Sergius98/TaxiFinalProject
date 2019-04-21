package com.training.model.dao.mappers;

import com.training.model.entity.Car;
import com.training.model.entity.Street;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarMapper {
    private static final Logger log = Logger.getLogger(CarMapper.class);

    // TODO: 4/21/19 abstract
    public Car extractCar(ResultSet set) throws SQLException {
        Car car = new Car();
        car.setId(set.getInt("id"));
        car.setName("en", set.getString("name_en"));
        car.setName("ua", set.getString("name_ua"));
        car.setPrice(set.getLong("price"));
        return car;
    }

    public Optional<Car> extractFromResultSet(PreparedStatement prepStatement) {
        Optional<Car> car = Optional.empty();
        try (ResultSet resultSet = prepStatement.executeQuery()) {
            while (resultSet.next()) {
                car = Optional.of(extractCar(resultSet));
            }
        } catch (SQLException e) {
            log.warn("there is a SQLException in extractFromResultSet");
            log.debug(e.getMessage(), e);
        }
        return car;
    }


    public List<Car> extractAllFromResultSet(PreparedStatement prepStatement) {
        List<Car> carList = new ArrayList<>();

        try (ResultSet resultSet = prepStatement.executeQuery()) {
            while (resultSet.next()) {
                carList.add(extractCar(resultSet));
            }
        } catch (SQLException e) {
            log.warn("there is a SQLException in extractAllFromResultSet");
            log.debug(e.getMessage(), e);
        }
        return carList;
    }
}
