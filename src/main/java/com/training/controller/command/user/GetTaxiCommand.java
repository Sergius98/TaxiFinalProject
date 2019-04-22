package com.training.controller.command.user;

import com.training.controller.IServletConstants;
import com.training.controller.command.Command;
import com.training.model.dao.DaoFactory;
import com.training.model.dao.interfaces.CarDao;
import com.training.model.dao.interfaces.StreetDao;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

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

}
