package com.training.controller.command;

import com.training.controller.IServletConstants;
import com.training.controller.utill.impl.Pagenizer;
import com.training.controller.utill.impl.RequestDataManager;
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
    private final Logger log = Logger.getLogger(LoyaltiesCommand.class);
    private Pagenizer pagenizer;
    private RequestDataManager requestDataManager;

    public LoyaltiesCommand(RequestDataManager requestDataManager, Pagenizer pagenizer) {
        this.requestDataManager = requestDataManager;
        this.pagenizer = pagenizer;
    }

    @Override
    public String execute(HttpServletRequest req) {
        List<LoyaltyThreshold> loyalties = requestDataManager.
                getLoyaltyThresholdList(req);

        pagenizer.pagenize(req, IServletConstants.PAGE_ELEMENTS_COUNT,
                loyalties);

        return IServletConstants.LOYALTIES_PAGE_JSP;
    }

}
