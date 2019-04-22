package com.training.controller.command;

import com.training.controller.IServletConstants;
import com.training.controller.utill.interfaces.IUserDataManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class HomeCommand implements Command {
    private static final Logger log = Logger.getLogger(HomeCommand.class);
    private IUserDataManager userDataManager;

    public HomeCommand(IUserDataManager userDataManager) {
        this.userDataManager = userDataManager;
    }

    @Override
    public String execute(HttpServletRequest req) {

        userDataManager.setUserDataFromSession(req);
        return IServletConstants.HOME_PAGE_JSP;
    }
}

