package com.training.controller.command.guest;

import com.training.controller.IServletConstants;
import com.training.controller.command.Command;
import com.training.controller.utill.impl.Authorization;
import com.training.controller.utill.impl.Localization;
import com.training.controller.utill.impl.UserDataManager;
import com.training.controller.utill.impl.UserExtractor;
import com.training.model.dao.DaoFactory;
import com.training.model.dao.interfaces.CarDao;
import com.training.model.dao.interfaces.DiscountDao;
import com.training.model.dao.interfaces.StreetDao;
import com.training.model.entity.Discount;
import com.training.model.entity.Order;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class GetTaxiCommand implements Command {
    private Logger log = Logger.getLogger(GetTaxiCommand.class);

    @Override
    public String execute(HttpServletRequest req) {

        // TODO: 4/21/19 move to util
        try(CarDao dao = DaoFactory.getInstance().createCarDao()){
            req.setAttribute(IServletConstants.CARS_LIST_KEY_WORD, dao.findAll());
        } catch (Exception e){
            log.info("cars list extraction was failed with :" + e.getMessage());
        }
        try(StreetDao dao = DaoFactory.getInstance().createStreetDao()){
            req.setAttribute(IServletConstants.STREETS_LIST_KEY_WORD, dao.findAll());
        } catch (Exception e){
            log.info("street list extraction was failed with :" + e.getMessage());
        }
        

        return IServletConstants.GET_TAXI_PAGE_JSP;
    }
/*
    private Localization localization = new Localization();
    private OrderExtractor extractor = new OrderExtractor();
    @Override
    public String execute(HttpServletRequest req) {
        String path;
        Locale locale = localization.extractLocale(req);
        Optional<Order> discount = extractor.extract(req);

        try(OrderDao dao = DaoFactory.getInstance().createDiscountDao()){
            if (dao.create(discount.get())){
                log.info("discount was registered");
            } else {
                throw new Exception("Insert was unsuccessful");
            }
            path = IServletConstants.REDIRECT_KEY_WORD +
                    IServletConstants.DISCOUNTS_PAGE_PATH + "?" +
                    IServletConstants.PAGE_NUMBER_KEY_WORD + "="
                    + Integer.MAX_VALUE;
        } catch (Exception e){
            log.info("registration was failed with :" + e.getMessage());
            req.setAttribute(IServletConstants.ALERT_ATTRIBUTE_KEY_WORD,
                    ResourceBundle.getBundle("errors", locale)
                            .getString("invalid_discount"));
            path = IServletConstants.ROOT_PATH +
                    IServletConstants.ADMIN_PREFIX +
                    IServletConstants.DISCOUNTS_PAGE_PATH;
        }

        return path;
    }
*/
}
