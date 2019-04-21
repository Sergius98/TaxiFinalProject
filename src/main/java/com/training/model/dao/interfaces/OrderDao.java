package com.training.model.dao.interfaces;

import com.training.model.dao.GenericDao;
import com.training.model.entity.Order;
import com.training.model.entity.Street;

import java.util.Optional;

public interface OrderDao extends GenericDao<Order> {
    Optional<Order> getOrder(int src, int dst, int user, int car);
}
