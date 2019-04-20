package com.training.controller.command;

import com.training.controller.IServletConstants;
import com.training.controller.command.Command;
import com.training.controller.command.guest.LoginCommand;
import com.training.controller.utill.impl.Authorization;
import com.training.controller.utill.impl.UserDataManager;
import com.training.controller.utill.impl.UserExtractor;
import com.training.model.dao.DaoFactory;
import com.training.model.dao.interfaces.CarDao;
import com.training.model.dao.interfaces.DiscountDao;
import com.training.model.dao.interfaces.StreetDao;
import com.training.model.entity.Discount;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DiscountsCommand implements Command {
    private static final Logger log = Logger.getLogger(DiscountsCommand.class);


    @Override
    public String execute(HttpServletRequest req) {

        try(DiscountDao dao = DaoFactory.getInstance().createDiscountDao()){
            req.setAttribute(IServletConstants.DISCOUNTS_LIST_KEY_WORD, dao.findAll());
        } catch (Exception e){
            log.info("discounts list extraction was failed with :" + e.getMessage());
        }
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
        // TODO: 4/19/19 pagination through request parameters
        //              if null - redirect to first page
        //              if too big - redirect to the last possible page

        return IServletConstants.DISCOUNTS_PAGE_JSP;
    }

}
