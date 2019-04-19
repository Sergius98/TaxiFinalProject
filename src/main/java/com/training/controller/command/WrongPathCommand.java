package com.training.controller.command;

import com.training.controller.IServletConstants;

import javax.servlet.http.HttpServletRequest;

public class WrongPathCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        // TODO: 4/19/19 logger
        return IServletConstants.REDIRECT_KEY_WORD + IServletConstants.HOME_PAGE_PATH;
    }

}
