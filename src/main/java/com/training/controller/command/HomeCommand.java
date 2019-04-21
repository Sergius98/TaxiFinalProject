package com.training.controller.command;

import com.training.controller.IServletConstants;
import com.training.controller.utill.impl.UserDataManager;
import com.training.controller.utill.interfaces.IUserDataManeger;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class HomeCommand implements Command {
    private static final Logger log = Logger.getLogger(HomeCommand.class);

    // TODO: 4/19/19 move to constructor
    private IUserDataManeger userDataManager = new UserDataManager();

    @Override
    public String execute(HttpServletRequest req) {

        // TODO: 4/18/19 get user from db instead of session
        userDataManager.setUserDataFromSession(req);
        return IServletConstants.HOME_PAGE_JSP;
    }
}

