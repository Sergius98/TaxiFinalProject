package com.training.controller.command;

import com.training.controller.IServletConstants;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class WrongPathCommand implements Command {
    private static final Logger log = Logger.getLogger(WrongPathCommand.class);

    @Override
    public String execute(HttpServletRequest req) {
        log.info("tried to access page that doesnt exist : " + req.getRequestURL());
        return IServletConstants.REDIRECT_KEY_WORD + IServletConstants.HOME_PAGE_PATH;
    }
}
