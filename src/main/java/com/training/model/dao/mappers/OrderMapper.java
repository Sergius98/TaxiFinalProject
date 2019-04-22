package com.training.model.dao.mappers;

import com.training.model.entity.Discount;
import com.training.model.entity.Order;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderMapper extends AbstractMapper<Order> {
    private static final Logger log = Logger.getLogger(OrderMapper.class);

    public Order extractEntity(ResultSet set) throws SQLException {
        Order order = new Order();
        order.setUserThresholdsDiscount(set.getDouble("userThresholdsDiscount"));
        order.setOrderDiscountsSum(set.getLong("orderDiscountsSum"));
        order.setOrderPrice(set.getLong("bill"));
        return order;
    }
}
