package com.training.controller.command.admin;

import com.training.controller.IServletConstants;
import com.training.controller.command.Command;
import com.training.controller.utill.impl.DiscountExtractor;
import com.training.controller.utill.impl.Localization;
import com.training.controller.utill.impl.LoyaltyThresholdExtractor;
import com.training.model.dao.DaoFactory;
import com.training.model.dao.interfaces.DiscountDao;
import com.training.model.dao.interfaces.LoyaltyThresholdDao;
import com.training.model.entity.Discount;
import com.training.model.entity.LoyaltyThreshold;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddLoyaltyThresholdCommand implements Command {
    private static final Logger log = Logger.getLogger(AddLoyaltyThresholdCommand.class);

    private Localization localization;
    private LoyaltyThresholdExtractor extractor;

    public AddLoyaltyThresholdCommand(Localization localization, LoyaltyThresholdExtractor extractor) {
        this.localization = localization;
        this.extractor = extractor;
    }

    @Override
    public String execute(HttpServletRequest req) {
        String path;
        Locale locale = localization.extractLocale(req);
        Optional<LoyaltyThreshold> threshold = extractor.extract(req);

        try(LoyaltyThresholdDao dao = DaoFactory.getInstance().createLoyaltyThresholdDao()){
            if (dao.create(threshold.get())){
                log.info("threshold was registered");
            } else {
                throw new Exception("Insert was unsuccessful");
            }
            path = IServletConstants.REDIRECT_KEY_WORD +
                    IServletConstants.LOYALTIES_PAGE_PATH + "?" +
                    IServletConstants.PAGE_NUMBER_KEY_WORD + "="
                    + Integer.MAX_VALUE;
        } catch (Exception e){
            log.info("registration was failed with :" + e.getMessage());
            req.setAttribute(IServletConstants.ALERT_ATTRIBUTE_KEY_WORD,
                    ResourceBundle.getBundle("errors", locale)
                            .getString("invalid_discount"));
            path = IServletConstants.ROOT_PATH +
                    IServletConstants.ADMIN_PREFIX +
                    IServletConstants.LOYALTIES_PAGE_PATH;
        }

        return path;
    }

}
