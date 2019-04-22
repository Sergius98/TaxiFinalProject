package com.training.controller.command;

import com.training.controller.IServletConstants;
import com.training.controller.command.Command;
import com.training.controller.command.guest.LoginCommand;
import com.training.controller.utill.impl.*;
import com.training.model.dao.DaoFactory;
import com.training.model.dao.interfaces.CarDao;
import com.training.model.dao.interfaces.DiscountDao;
import com.training.model.dao.interfaces.StreetDao;
import com.training.model.entity.Discount;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DiscountsCommand implements Command {
    private static final Logger log = Logger.getLogger(DiscountsCommand.class);

    private Pagenizer pagenizer;
    private RequestDataManager requestDataManager;

    public DiscountsCommand(RequestDataManager requestDataManager, Pagenizer pagenizer) {
        this.requestDataManager = requestDataManager;
        this.pagenizer = pagenizer;
    }

    @Override
    public String execute(HttpServletRequest req) {
        List<Discount> discounts = requestDataManager.getDiscountList(req);

        requestDataManager.getCarList(req);
        requestDataManager.getStreetList(req);

        pagenizer.pagenize(req, IServletConstants.PAGE_ELEMENTS_COUNT,
                discounts);

        return IServletConstants.DISCOUNTS_PAGE_JSP;
    }

}
