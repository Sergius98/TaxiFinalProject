package com.training.controller.command;

import com.training.controller.IServletConstants;
import com.training.controller.command.Command;
import com.training.controller.command.guest.LoginCommand;
import com.training.controller.utill.impl.*;
import com.training.model.dao.DaoFactory;
import com.training.model.dao.interfaces.CarDao;
import com.training.model.dao.interfaces.DiscountDao;
import com.training.model.dao.interfaces.StreetDao;
import com.training.model.entity.Discount;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DiscountsCommand implements Command {
    private static final Logger log = Logger.getLogger(DiscountsCommand.class);

    // TODO: 4/20/19 move to constructor
    private Pagenizer pagenizer = new Pagenizer();

    @Override
    public String execute(HttpServletRequest req) {
        List<Discount> discounts = new ArrayList<>();

        try(DiscountDao dao = DaoFactory.getInstance().createDiscountDao()){
            discounts = dao.findAll();
            req.setAttribute(IServletConstants.DISCOUNTS_LIST_KEY_WORD, discounts);
        } catch (Exception e){
            log.info("discounts list extraction was failed with :" + e.getMessage());
        }
        try(CarDao dao = DaoFactory.getInstance().createCarDao()){
            req.setAttribute(IServletConstants.CARS_LIST_KEY_WORD, dao.findAll());
        } catch (Exception e){
            log.info("cars list extraction was failed with :" + e.getMessage());
            log.debug(e, e);
        }
        try(StreetDao dao = DaoFactory.getInstance().createStreetDao()){
            req.setAttribute(IServletConstants.STREETS_LIST_KEY_WORD, dao.findAll());
        } catch (Exception e){
            log.info("street list extraction was failed with :" + e.getMessage());
        }

        // TODO: 4/21/19 util
        Optional<Integer> page = Optional.ofNullable(
                (Integer)((HttpServletRequest) req).getSession()
                        .getAttribute(IServletConstants.PAGE_NUMBER_KEY_WORD));
        Optional<String> newPage = Optional.ofNullable(
                req.getParameter(IServletConstants.PAGE_NUMBER_KEY_WORD));

        if (newPage.isPresent()){
            try{
                page = Optional.of(Integer.valueOf(req.getParameter(
                                IServletConstants.PAGE_NUMBER_KEY_WORD)));
            } catch (NumberFormatException e){
                page = Optional.of(1);
            }
        }

        pagenizer.pagenize(req, IServletConstants.PAGE_ELEMENTS_COUNT,
                discounts, page.orElse(1));

        return IServletConstants.DISCOUNTS_PAGE_JSP;
    }

}
