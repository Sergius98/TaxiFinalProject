package com.training.model.dao.impl;

import com.training.model.dao.interfaces.CarDao;
import com.training.model.dao.interfaces.TaxiDao;
import com.training.model.entity.Car;
import com.training.model.entity.Taxi;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class JDBCDummyCarDao implements CarDao {
    private static final List<Car> cars = Collections.synchronizedList(new ArrayList<Car>());

    static{
        cars.add(new Car(cars.size(), "econom", 100));
        cars.add(new Car(cars.size(), "normal", 150));
        cars.add(new Car(cars.size(), "business", 225));
        cars.add(new Car(cars.size(), "vip", 500));
        cars.add(new Car(cars.size(), "lux", 2000));
    }

    JDBCDummyCarDao(Connection connection) {
    }

    @Override
    public boolean create(Car entity) {

        return false;
    }

    @Override
    public Optional<Car> findById(int id) {
        Optional<Car> car = Optional.empty();
        try {
            car = Optional.of(cars.get(id));
        } catch (IndexOutOfBoundsException e){
            // TODO: 4/20/19 log
        }
        return car;
    }

    @Override
    public List<Car> findAll() {
        return cars;
    }

    @Override
    public void update(Car entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {

    }
}
