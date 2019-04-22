package com.training.controller.command.user;

import com.training.controller.IServletConstants;
import com.training.controller.command.Command;
import com.training.controller.utill.impl.Localization;
import com.training.model.dao.DaoFactory;
import com.training.model.dao.interfaces.CarDao;
import com.training.model.dao.interfaces.OrderDao;
import com.training.model.dao.interfaces.StreetDao;
import com.training.model.entity.Order;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class SearchTaxiCommand implements Command {
    private Logger log = Logger.getLogger(SearchTaxiCommand.class);

    private Localization localization = new Localization();

    @Override
    public String execute(HttpServletRequest req) {
        String path;
        Locale locale = localization.extractLocale(req);
        Optional<Order> order;

        try(OrderDao dao = DaoFactory.getInstance().createOrderDao()){
            order = dao.getOrder(
                    Integer.valueOf(req.getParameter("source_street")),
                    Integer.valueOf(req.getParameter("destination_street")),
                    (int)req.getSession().getAttribute(IServletConstants.USER_ID_ATTRIBUTE_KEY_WORD),
                    Integer.valueOf(req.getParameter("car_class")));
            req.setAttribute(IServletConstants.ORDER_KEY_WORD, order.get());
            req.setAttribute(IServletConstants.SUCCESSFUL_SEARCH_KEY_WORD, 1);
        } catch (Exception e){
            log.info("registration was failed with :" + e.getMessage(), e);
            req.setAttribute(IServletConstants.ALERT_ATTRIBUTE_KEY_WORD,
                    ResourceBundle.getBundle("errors", locale)
                            .getString("invalid_order"));
        }

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
