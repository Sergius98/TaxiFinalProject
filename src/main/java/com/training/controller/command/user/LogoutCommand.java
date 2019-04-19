package com.training.controller.command.user;

import com.training.controller.command.Command;
import com.training.controller.command.guest.LoginCommand;
import com.training.controller.IServletConstants;
import com.training.controller.utill.impl.Authorization;
import com.training.controller.utill.impl.UserDataManager;
import com.training.controller.utill.impl.UserExtractor;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {
    private Logger log = Logger.getLogger(LoginCommand.class);
    // TODO: 4/19/19 get instance of Authorization without giving extractor

    private Authorization authorization = new Authorization(new UserExtractor(), new UserDataManager());

    @Override
    public String execute(HttpServletRequest req) {
        authorization.logOut(req);

        return IServletConstants.REDIRECT_KEY_WORD + IServletConstants.HOME_PAGE_PATH;
    }

}
