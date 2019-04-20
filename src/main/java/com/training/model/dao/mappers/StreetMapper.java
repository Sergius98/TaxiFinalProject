package com.training.model.dao.mappers;

import com.training.model.entity.Discount;
import com.training.model.entity.Street;
import com.training.model.entity.User;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StreetMapper {
    private static final Logger log = Logger.getLogger(StreetMapper.class);

    public Street extractStreet(ResultSet set) throws SQLException {
        Street street = new Street();
        street.setId(set.getInt("id"));
        street.setName("en", set.getString("name_en"));
        street.setName("ua", set.getString("name_ua"));
        street.setX(set.getInt("x"));
        street.setX(set.getInt("y"));
        return street;
    }

    public Optional<Street> extractFromResultSet(PreparedStatement prepStatement) {
        Optional<Street> user = Optional.empty();
        try (ResultSet resultSet = prepStatement.executeQuery()) {
            while (resultSet.next()) {
                user = Optional.of(extractStreet(resultSet));
            }
        } catch (SQLException e) {
            log.warn("there is a SQLException in extractFromResultSet");
            log.debug(e.getMessage(), e);
        }
        return user;
    }


    public List<Street> extractAllFromResultSet(PreparedStatement prepStatement) {
        List<Street> discountsList = new ArrayList<>();

        try (ResultSet resultSet = prepStatement.executeQuery()) {
            while (resultSet.next()) {
                discountsList.add(extractStreet(resultSet));
            }
        } catch (SQLException e) {
            log.warn("there is a SQLException in extractAllFromResultSet");
            log.debug(e.getMessage(), e);
        }
        return discountsList;
    }
}
