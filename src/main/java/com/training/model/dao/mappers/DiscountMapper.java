package com.training.model.dao.mappers;

import com.training.model.entity.Discount;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DiscountMapper  extends AbstractMapper<Discount>{
    private static final Logger log = Logger.getLogger(DiscountMapper.class);

    public Discount extractEntity(ResultSet set) throws SQLException {
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
            discount.setMinimalBill((long) set.getObject("minimalBill"));
            log.debug("minimalBill:" + discount.getMinimalBill());
        } catch (NullPointerException e){
            log.debug("minimalBill is empty");
        }
        try {
            discount.setMinimalThreshold((long) set.getObject("minimalThreshold"));
            log.debug("minimalThreshold:" + discount.getMinimalThreshold());
        } catch (NullPointerException e){
            log.debug("minimalThreshold is empty");
        }
        try {
            discount.setDiscount((long) set.getObject("discount"));
            log.debug("discount:" + discount.getDiscount());
        } catch (NullPointerException e){
            log.debug("discount is empty");
        }
        return discount;
    }
}
