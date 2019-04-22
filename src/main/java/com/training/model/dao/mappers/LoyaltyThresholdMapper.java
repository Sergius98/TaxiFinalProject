package com.training.model.dao.mappers;

import com.training.model.entity.Discount;
import com.training.model.entity.LoyaltyThreshold;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LoyaltyThresholdMapper extends AbstractMapper<LoyaltyThreshold> {
    private static final Logger log = Logger.getLogger(LoyaltyThresholdMapper.class);

    public LoyaltyThreshold extractEntity(ResultSet set) throws SQLException {
        LoyaltyThreshold loyalties = new LoyaltyThreshold();
        loyalties.setId(set.getInt("id"));
        log.debug("id:" + set.getInt("id"));
        // TODO: 4/21/19 make fields not null in db
        try {
            // TODO: 4/21/19 convert int to long in db
            loyalties.setThreshold((long)(int) set.getObject("threshold"));
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
