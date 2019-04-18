package com.training.controller.command.guest;

import com.training.controller.command.Command;
import com.training.controller.servlet.IServletConstants;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class HomeCommand implements Command {
    private static final Logger log = Logger.getLogger(HomeCommand.class);

    @Override
    public String execute(HttpServletRequest req) {
        if (!req.getMethod().equals("GET")){
            log.warn("method != GET");
        }
        // TODO: 4/18/19 get user from db instead of session
        req.setAttribute(IServletConstants.USER_ATTRIBUTE_KEY_WORD, req.getSession().getAttribute(IServletConstants.USER_ATTRIBUTE_KEY_WORD));
        return IServletConstants.HOME_PAGE_JSP;
    }
}