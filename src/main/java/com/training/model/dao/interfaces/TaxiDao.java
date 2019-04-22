package com.training.model.dao.interfaces;

import com.training.model.dao.GenericDao;
import com.training.model.entity.Order;
import com.training.model.entity.Taxi;

import java.sql.SQLException;
import java.util.Optional;

public interface TaxiDao extends GenericDao<Taxi> {
    Optional<Integer> findTimeForClosestTaxiWithCarClass(int carClass, int destinationStreetId, int sourceStreetId);
    void startTransaction() throws SQLException;
    void commit() throws SQLException;
}
