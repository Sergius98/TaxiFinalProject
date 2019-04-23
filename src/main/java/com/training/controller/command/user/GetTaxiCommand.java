package com.training.controller.command.user;

import com.training.controller.IServletConstants;
import com.training.controller.command.Command;
import com.training.controller.utill.impl.RequestDataManager;
import com.training.model.dao.DaoFactory;
import com.training.model.dao.interfaces.CarDao;
import com.training.model.dao.interfaces.StreetDao;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class GetTaxiCommand implements Command {
    private Logger log = Logger.getLogger(GetTaxiCommand.class);
    private RequestDataManager requestDataManager;

    public GetTaxiCommand(RequestDataManager requestDataManager) {
        this.requestDataManager = requestDataManager;
    }

    @Override
    public String execute(HttpServletRequest req) {
        requestDataManager.getCarList(req);
        requestDataManager.getStreetList(req);

        return IServletConstants.GET_TAXI_PAGE_JSP;
    }

}
