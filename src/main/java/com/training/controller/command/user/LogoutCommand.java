package com.training.controller.command.user;

import com.training.controller.command.Command;
import com.training.controller.command.guest.LoginCommand;
import com.training.controller.servlet.IServletConstants;
import com.training.controller.utill.impl.Authorization;
import com.training.controller.utill.impl.UserExtractor;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {
    private Logger log = Logger.getLogger(LoginCommand.class);
    // TODO: 4/19/19 get instance of Authorization without giving extractor
    private UserExtractor extractor = new UserExtractor();
    private Authorization authorizator = new Authorization(extractor);

    @Override
    public String execute(HttpServletRequest req) {
        authorizator.logOut(req, (int)req.getSession().getAttribute(IServletConstants.USER_ID_ATTRIBUTE_KEY_WORD));

        req.getSession().setAttribute(IServletConstants.USER_ID_ATTRIBUTE_KEY_WORD, null);
        req.getSession().setAttribute(IServletConstants.ROLE_ATTRIBUTE_KEY_WORD, 0);
        return IServletConstants.REDIRECT_KEY_WORD + IServletConstants.HOME_PAGE_PATH;
    }

}
