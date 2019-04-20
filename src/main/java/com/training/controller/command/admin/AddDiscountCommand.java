package com.training.controller.command.admin;

import com.training.controller.IServletConstants;
import com.training.controller.command.Command;
import com.training.controller.utill.impl.DiscountExtractor;
import com.training.controller.utill.impl.Localization;
import com.training.controller.utill.impl.Pagenizer;
import com.training.model.dao.DaoFactory;
import com.training.model.dao.interfaces.CarDao;
import com.training.model.dao.interfaces.DiscountDao;
import com.training.model.dao.interfaces.StreetDao;
import com.training.model.entity.Discount;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class AddDiscountCommand implements Command {
    private static final Logger log = Logger.getLogger(AddDiscountCommand.class);

    private Localization localization = new Localization();
    private DiscountExtractor extractor = new DiscountExtractor();
    @Override
    public String execute(HttpServletRequest req) {
        String path;
        Locale locale = localization.extractLocale(req);
        Optional<Discount> discount = extractor.extract(req);

        try(DiscountDao dao = DaoFactory.getInstance().createDiscountDao()){
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

}
