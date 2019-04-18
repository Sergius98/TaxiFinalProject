package com.training.controller.command.user;

import com.training.controller.command.Command;
import com.training.controller.command.guest.LoginCommand;
import com.training.controller.servlet.IServletConstants;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {

        LoginCommand.disconectUser((int)req.getSession().getAttribute(IServletConstants.USER_ID_ATTRIBUTE_KEY_WORD));
        req.getSession().setAttribute(IServletConstants.USER_ID_ATTRIBUTE_KEY_WORD, null);
        req.getSession().setAttribute(IServletConstants.ROLE_ATTRIBUTE_KEY_WORD, 0);
        return IServletConstants.REDIRECT_KEY_WORD + IServletConstants.HOME_PAGE_PATH;
    }

}
