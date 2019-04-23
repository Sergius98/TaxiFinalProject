package com.training.controller.command.user;

import com.training.controller.IServletConstants;
import com.training.controller.command.Command;
import com.training.controller.utill.impl.Localization;
import com.training.controller.utill.impl.RequestDataManager;
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

    private Localization localization;
    private RequestDataManager requestDataManager;

    public SearchTaxiCommand(RequestDataManager requestDataManager, Localization localization) {
        this.requestDataManager = requestDataManager;
        this.localization = localization;
    }

    @Override
    public String execute(HttpServletRequest req) {
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
            log.info("registration was failed with :" + e.getMessage());
            log.trace(e,e);
            req.setAttribute(IServletConstants.ALERT_ATTRIBUTE_KEY_WORD,
                    ResourceBundle.getBundle("errors", locale)
                            .getString("invalid_order"));
        }

        requestDataManager.getCarList(req);
        requestDataManager.getStreetList(req);

        return IServletConstants.GET_TAXI_PAGE_JSP;
    }
}
