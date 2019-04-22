package com.training.controller.command;

import com.training.controller.IServletConstants;
import com.training.controller.utill.impl.Pagenizer;
import com.training.model.dao.DaoFactory;
import com.training.model.dao.interfaces.CarDao;
import com.training.model.dao.interfaces.DiscountDao;
import com.training.model.dao.interfaces.LoyaltyThresholdDao;
import com.training.model.dao.interfaces.StreetDao;
import com.training.model.entity.Discount;
import com.training.model.entity.LoyaltyThreshold;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LoyaltiesCommand implements Command {
    private static final Logger log = Logger.getLogger(LoyaltiesCommand.class);

    // TODO: 4/20/19 move to constructor
    private Pagenizer pagenizer = new Pagenizer();

    @Override
    public String execute(HttpServletRequest req) {
        List<LoyaltyThreshold> loyalties = new ArrayList<>();

        try(LoyaltyThresholdDao dao = DaoFactory.getInstance().createLoyaltyThresholdDao()){
            loyalties = dao.findAll();
            req.setAttribute(IServletConstants.LOYALTIES_LIST_KEY_WORD, loyalties);
        } catch (Exception e){
            log.info("LoyaltyThreshold list extraction was failed with :" + e.getMessage());
        }
        
        pagenizer.pagenize(req, IServletConstants.PAGE_ELEMENTS_COUNT,
                loyalties);

        return IServletConstants.LOYALTIES_PAGE_JSP;
    }

}
