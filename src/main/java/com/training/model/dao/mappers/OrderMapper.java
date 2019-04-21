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

public class OrderMapper {
    private static final Logger log = Logger.getLogger(OrderMapper.class);

    public Order extractOrder(ResultSet set) throws SQLException {
        Order order = new Order();
        order.setUserThresholdsDiscount(set.getDouble("userThresholdsDiscount"));
        order.setOrderDiscountsSum(set.getLong("orderDiscountsSum"));
        order.setOrderPrice(set.getLong("bill"));
        return order;
    }

    public Optional<Order> extractFromResultSet(PreparedStatement prepStatement) {
        Optional<Order> order = Optional.empty();
        try (ResultSet resultSet = prepStatement.executeQuery()) {
            while (resultSet.next()) {
                order = Optional.of(extractOrder(resultSet));
            }
        } catch (SQLException e) {
            log.warn("there is a SQLException in extractFromResultSet");
            log.debug(e.getMessage(), e);
        }
        return order;
    }

    public List<Discount> extractAllFromResultSet(PreparedStatement prepStatement) {
        List<Discount> discountsList = new ArrayList<>();
// TODO: 4/21/19 throw exception
        return discountsList;
    }
}
