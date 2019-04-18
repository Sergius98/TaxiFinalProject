package com.training.controller.command.user;

import com.training.controller.command.Command;
import com.training.controller.servlet.IServletConstants;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {

        request.getSession().setAttribute("USER_ID", null);
        request.getSession().setAttribute("ROLE", 0);
        return IServletConstants.REDIRECT_KEY_WORD + IServletConstants.HOME_PAGE_PATH;
    }

}
