package com.training.controller.command.user;

import com.training.controller.IServletConstants;
import com.training.controller.command.Command;
import com.training.controller.utill.impl.Localization;
import com.training.model.dao.DaoFactory;
import com.training.model.dao.interfaces.CarDao;
import com.training.model.dao.interfaces.OrderDao;
import com.training.model.dao.interfaces.StreetDao;
import com.training.model.dao.interfaces.TaxiDao;
import com.training.model.entity.Order;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class ComfirmTaxiCommand implements Command {
    private Logger log = Logger.getLogger(ComfirmTaxiCommand.class);

    private Localization localization = new Localization();

    @Override
    public String execute(HttpServletRequest req) {
        Locale locale = localization.extractLocale(req);
        Optional<Integer> delay;
        String path = IServletConstants.ROOT_PATH +
                IServletConstants.ADMIN_PREFIX +
                IServletConstants.SEARCH_TAXI_PAGE_PATH;

        try(TaxiDao dao = DaoFactory.getInstance().createTaxiDao()){
            delay = dao.findTimeForClosestTaxiWithCarClass(
                    Integer.valueOf(req.getParameter("car_class")),
                    Integer.valueOf(req.getParameter("destination_street")),
                    Integer.valueOf(req.getParameter("source_street")
                    ));
            req.setAttribute(IServletConstants.DELAY_KEY_WORD, delay.get());
            req.setAttribute(IServletConstants.SUCCESSFUL_CONFIRMATION_KEY_WORD, 1);
            path = IServletConstants.GET_TAXI_PAGE_JSP;
        } catch (Exception e){
            log.info("registration was failed with :" + e.getMessage(), e);
            req.setAttribute(IServletConstants.ALERT_ATTRIBUTE_KEY_WORD,
                    ResourceBundle.getBundle("errors", locale)
                            .getString("no_matching_cars"));
        }

        return path;
    }
}
