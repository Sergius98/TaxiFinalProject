package com.training.model.dao.mappers;

import com.training.model.entity.Discount;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DiscountMapper {
    private static final Logger log = Logger.getLogger(DiscountMapper.class);

    public Discount extractDiscount(ResultSet set) throws SQLException {
        Discount discount = new Discount();
        discount.setId(set.getInt("id"));
        log.debug("id:" + set.getInt("id"));
        try {
            discount.setCarClass((int) set.getObject("carClass"));
            log.debug("carClass:" + discount.getCarClass());
        } catch (NullPointerException e){
            log.debug("carClass is empty");
        }
        try {
            discount.setSourceStreetId((int) set.getObject("sourceStreetID"));
            log.debug("sourceStreetID:" + discount.getSourceStreetId());
        } catch (NullPointerException e){
            log.debug("sourceStreetID is empty");
        }
        try {
            discount.setDestinationStreetId((int) set.getObject("destinationStreetId"));
            log.debug("destinationStreetId:" + discount.getDestinationStreetId());
        } catch (NullPointerException e){
            log.debug("destinationStreetId is empty");
        }
        try {
            discount.setMinimalBill((int) set.getObject("minimalBill"));
            log.debug("minimalBill:" + discount.getMinimalBill());
        } catch (NullPointerException e){
            log.debug("minimalBill is empty");
        }
        try {
            discount.setMinimalThreshold((int) set.getObject("minimalThreshold"));
            log.debug("minimalThreshold:" + discount.getMinimalThreshold());
        } catch (NullPointerException e){
            log.debug("minimalThreshold is empty");
        }
        try {
            discount.setDiscount((int) set.getObject("discount"));
            log.debug("discount:" + discount.getDiscount());
        } catch (NullPointerException e){
            log.debug("discount is empty");
        }
        return discount;
    }

    public Optional<Discount> extractFromResultSet(PreparedStatement prepStatement) {
        Optional<Discount> discount = Optional.empty();
        try (ResultSet resultSet = prepStatement.executeQuery()) {
            while (resultSet.next()) {
                discount = Optional.of(extractDiscount(resultSet));
            }
        } catch (SQLException e) {
            log.warn("there is a SQLException in DiscountMapper:extractFromResultSet");
            log.debug(e.getMessage(), e);
        }
        return discount;
    }

    public List<Discount> extractAllFromResultSet(PreparedStatement prepStatement) {
        List<Discount> discountsList = new ArrayList<>();

        try (ResultSet resultSet = prepStatement.executeQuery()) {
            while (resultSet.next()) {
                discountsList.add(extractDiscount(resultSet));
            }
        } catch (SQLException e) {
            log.warn("there is a SQLException in DiscountMapper:extractAllFromResultSet");
            log.debug(e.getMessage(), e);
        }
        return discountsList;
    }
}
