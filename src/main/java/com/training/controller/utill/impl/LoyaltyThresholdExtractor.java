package com.training.controller.utill.impl;

import com.training.controller.utill.interfaces.IExtractor;
import com.training.model.dao.DaoFactory;
import com.training.model.dao.interfaces.CarDao;
import com.training.model.dao.interfaces.StreetDao;
import com.training.model.entity.Discount;
import com.training.model.entity.LoyaltyThreshold;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class LoyaltyThresholdExtractor implements IExtractor<LoyaltyThreshold> {
    private Logger log = Logger.getLogger(LoyaltyThresholdExtractor.class);


    public Optional<LoyaltyThreshold> extract(HttpServletRequest req){
        Boolean fail = false;
        Optional<LoyaltyThreshold> loyaltyThreshold;
        int filled_rows = 0;

        try {
             loyaltyThreshold = Optional.of(new LoyaltyThreshold(
                     Long.valueOf(req.getParameter("minimal_threshold")),
                     Double.valueOf(req.getParameter("discount"))));

            if (loyaltyThreshold.get().getDiscount() < 0 ||
                    loyaltyThreshold.get().getThreshold() < 0){
                throw new Exception("negative values");
            }
            // TODO: 4/21/19 delete when change to long
            if (loyaltyThreshold.get().getDiscount() > Integer.MAX_VALUE ||
                    loyaltyThreshold.get().getThreshold() > Integer.MAX_VALUE){
                throw new Exception("too big values");
            }
        } catch (Exception e){
            log.debug("discount :" + req.getParameter("discount"));
            log.debug("minimal_threshold :" + req.getParameter("minimal_threshold"));
            log.debug("extraction failed with" + e.getMessage());
            log.debug(e.getMessage(), e);
            loyaltyThreshold = Optional.empty();
        }

        return loyaltyThreshold;
    }
}
