package com.training.controller.command.admin;

import com.training.controller.IServletConstants;
import com.training.controller.command.Command;
import com.training.model.dao.DaoFactory;
import com.training.model.dao.interfaces.DiscountDao;
import com.training.model.dao.interfaces.LoyaltyThresholdDao;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DeleteLoyaltyThresholdCommand implements Command {
    private static final Logger log = Logger.getLogger(DeleteLoyaltyThresholdCommand.class);

    @Override
    public String execute(HttpServletRequest req) {
        int id;

        try {
            id = Integer.valueOf(req.getParameter(
                    IServletConstants.ID_PARAMETER_KEY_WORD));
        } catch (Exception e){
            throw new RuntimeException("Something wrong with parameter 'id'");
        }

        try(LoyaltyThresholdDao dao = DaoFactory.getInstance().createLoyaltyThresholdDao()){
            log.info("threshold id :" + id);
            dao.delete(id);
        } catch (Exception e){
            log.info("discounts delete was failed with :" + e.getMessage());
        }


        return IServletConstants.REDIRECT_KEY_WORD
                + IServletConstants.LOYALTIES_PAGE_PATH;
    }

}
