package com.training.controller.command.admin;

import com.training.controller.IServletConstants;
import com.training.controller.command.Command;
import com.training.model.dao.DaoFactory;
import com.training.model.dao.interfaces.DiscountDao;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DeleteDiscountCommand implements Command {
    private static final Logger log = Logger.getLogger(DeleteDiscountCommand.class);

    @Override
    public String execute(HttpServletRequest req) {
        int id;

        try {
            id = Integer.valueOf(req.getParameter(
                    IServletConstants.ID_PARAMETER_KEY_WORD));
        } catch (Exception e){
            throw new RuntimeException("Something wrong with parameter 'id'");
        }

        //todo move work with dao to services(or at least to model)
        //todo or not? just think about it latter
        try(DiscountDao dao = DaoFactory.getInstance().createDiscountDao()){
            log.info("discount id :" + id);
            dao.delete(id);
        } catch (Exception e){
            log.info("discounts delete was failed with :" + e.getMessage());
        }


        return IServletConstants.REDIRECT_KEY_WORD
                + IServletConstants.DISCOUNTS_PAGE_PATH;
    }

}
