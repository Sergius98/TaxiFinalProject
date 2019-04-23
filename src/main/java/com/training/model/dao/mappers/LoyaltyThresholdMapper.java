package com.training.model.dao.mappers;

import com.training.model.entity.LoyaltyThreshold;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoyaltyThresholdMapper extends AbstractMapper<LoyaltyThreshold> {
    private static final Logger log = Logger.getLogger(LoyaltyThresholdMapper.class);

    public LoyaltyThreshold extractEntity(ResultSet set) throws SQLException {
        LoyaltyThreshold loyalties = new LoyaltyThreshold();
        loyalties.setId(set.getInt("id"));
        log.debug("id:" + set.getInt("id"));
        try {
            loyalties.setThreshold((long)set.getObject("threshold"));
            log.debug("Threshold:" + loyalties.getThreshold());
        } catch (NullPointerException e){
            log.debug("Threshold is empty");
        }
        try {
            loyalties.setDiscount(set.getDouble("discount"));
            log.debug("discount:" + loyalties.getDiscount());
        } catch (NullPointerException e){
            log.debug("discount is empty");
        }
        return loyalties;
    }
}
