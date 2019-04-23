package com.training.controller.utill.impl;

import com.training.controller.IServletConstants;
import com.training.model.dao.DaoFactory;
import com.training.model.dao.interfaces.CarDao;
import com.training.model.dao.interfaces.DiscountDao;
import com.training.model.dao.interfaces.LoyaltyThresholdDao;
import com.training.model.dao.interfaces.StreetDao;
import com.training.model.entity.Car;
import com.training.model.entity.Discount;
import com.training.model.entity.LoyaltyThreshold;
import com.training.model.entity.Street;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

//todo probably I should move it to the model? think about it latter
public class RequestDataManager {
    private static final Logger log = Logger.getLogger(RequestDataManager.class);

    public List<Car> getCarList(HttpServletRequest req){
        List<Car> list = new ArrayList<>();
        try(CarDao dao = DaoFactory.getInstance().createCarDao()){
            list = dao.findAll();
            req.setAttribute(IServletConstants.CARS_LIST_KEY_WORD, list);
        } catch (Exception e){
            log.info("cars list extraction was failed with :" + e.getMessage());
            log.trace(e, e);
        }
        return list;
    }

    public List<Street> getStreetList(HttpServletRequest req){
        List<Street> list = new ArrayList<>();
        try(StreetDao dao = DaoFactory.getInstance().createStreetDao()){
            list = dao.findAll();
            req.setAttribute(IServletConstants.STREETS_LIST_KEY_WORD, list);
        } catch (Exception e){
            log.info("street list extraction was failed with :" + e.getMessage());
            log.trace(e, e);
        }
        return list;
    }

    public List<Discount> getDiscountList(HttpServletRequest req){
        List<Discount> list = new ArrayList<>();
        try(DiscountDao dao = DaoFactory.getInstance().createDiscountDao()){
            list = dao.findAll();
            req.setAttribute(IServletConstants.DISCOUNTS_LIST_KEY_WORD, list);
        } catch (Exception e){
            log.info("discounts list extraction was failed with :" + e.getMessage());
            log.trace(e, e);
        }
        return list;
    }

    public List<LoyaltyThreshold> getLoyaltyThresholdList(HttpServletRequest req){
        List list = new ArrayList<>();
        try(LoyaltyThresholdDao dao = DaoFactory.getInstance().createLoyaltyThresholdDao()){
            list = dao.findAll();
            req.setAttribute(IServletConstants.LOYALTIES_LIST_KEY_WORD, list);
        } catch (Exception e){
            log.info("LoyaltyThreshold list extraction was failed with :" + e.getMessage());
            log.trace(e, e);
        }
        return list;
    }

}
