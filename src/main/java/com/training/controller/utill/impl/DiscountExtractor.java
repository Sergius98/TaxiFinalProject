package com.training.controller.utill.impl;

import com.training.controller.utill.interfaces.IExtractor;
import com.training.model.dao.DaoFactory;
import com.training.model.dao.interfaces.CarDao;
import com.training.model.dao.interfaces.DiscountDao;
import com.training.model.dao.interfaces.StreetDao;
import com.training.model.entity.Discount;
import com.training.model.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class DiscountExtractor implements IExtractor<Discount> {
    private Logger log = Logger.getLogger(DiscountExtractor.class);
    private Localization localization = new Localization();

    private Optional<Integer> extractInt(HttpServletRequest req, String param){
        Optional<Integer> value;
        try {
            value = Optional.of(Integer.valueOf(req.getParameter(param)));
        } catch (NumberFormatException e) {
            value = Optional.empty();
        }
        return value;
    }

    private Optional<Long> extractLong(HttpServletRequest req, String param){
        Optional<Long> value;
        try {
            value = Optional.of(Long.valueOf(req.getParameter(param)));
        } catch (NumberFormatException e) {
            value = Optional.empty();
        }
        return value;
    }

    public Optional<Discount> extract(HttpServletRequest req){
        Boolean fail = false;
        Optional<Discount> discount;
        int filled_rows = 0;

        Optional<Integer> carClass;
        Optional<Integer>  sourceStreetId;
        Optional<Integer>  destinationStreetId;
        Optional<Long>  minimalBill;
        Optional<Long> minimalThreshold;
        Optional<Long> discount_sum;

        try {
            carClass = extractInt(req, "car_class");
            sourceStreetId = extractInt(req, "source_street");
            destinationStreetId = extractInt(req, "destination_street");
            minimalBill = extractLong(req, "minimal_bill");
            minimalThreshold = extractLong(req, "minimal_threshold");
            discount_sum = extractLong(req, "discount");

            try(CarDao dao = DaoFactory.getInstance().createCarDao()){
                dao.findById(carClass.get()).get();
                filled_rows++;
            } catch (Exception e){
                carClass = Optional.empty();
            }

            try(StreetDao dao = DaoFactory.getInstance().createStreetDao()){
                dao.findById(sourceStreetId.get()).get();
                filled_rows++;
            } catch (Exception e){
                sourceStreetId = Optional.empty();
            }

            try(StreetDao dao = DaoFactory.getInstance().createStreetDao()){
                dao.findById(destinationStreetId.get()).get();
                filled_rows++;
            } catch (Exception e){
                destinationStreetId = Optional.empty();
            }

            if (minimalBill.isPresent()){
                filled_rows++;
            }
            if (minimalThreshold.isPresent()){
                filled_rows++;
            }

            if (!discount_sum.isPresent() || discount_sum.get() < 1){
                throw new Exception("Empty or negative discount sum");
            }

            if (filled_rows < 1){
                throw new Exception("Empty form");
            }
            discount = Optional.of(new Discount(carClass, sourceStreetId,
                    destinationStreetId, minimalBill, minimalThreshold,
                    discount_sum.get()));

        } catch (Exception e){
            log.debug("car_class :" + req.getParameter("car_class"));
            log.debug("source_street :" + req.getParameter("source_street"));
            log.debug("destination_street :" + req.getParameter("destination_street"));
            log.debug("minimal_bill :" + req.getParameter("minimal_bill"));
            log.debug("minimal_threshold :" + req.getParameter("minimal_threshold"));
            log.debug("discount :" + req.getParameter("discount"));
            log.debug("extraction failed with" + e.getMessage());
            log.debug(e.getMessage(), e);
            discount = Optional.empty();
        }

        return discount;
    }
}
