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
        discount.setCarClass(set.getInt("carClass"));
        discount.setSourceStreetId(set.getInt("sourceStreetID"));
        discount.setDestinationStreetId(set.getInt("destinationStreetId"));
        discount.setMinimalBill(set.getInt("minimalBill"));
        discount.setMinimalThreshold(set.getInt("minimalThreshold"));
        discount.setDiscount(set.getInt("discount"));
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
