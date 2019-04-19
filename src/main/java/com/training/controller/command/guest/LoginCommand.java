package com.training.controller.command.guest;

import com.training.controller.command.Command;
import com.training.controller.IServletConstants;
import com.training.controller.utill.impl.Authorization;
import com.training.controller.utill.impl.UserDataManager;
import com.training.controller.utill.impl.UserExtractor;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class LoginCommand implements Command {
    private Logger log = Logger.getLogger(LoginCommand.class);
    // TODO: 4/19/19 /for all commands/ move dependency into constructor
    private Authorization authorization = new Authorization(new UserExtractor(), new UserDataManager());

    @Override
    public String execute(HttpServletRequest req) {
        String result = IServletConstants.LOGIN_PAGE_JSP;


        if (req.getMethod().equals("POST")){
            try{
                result = authorization.logIn(req).get();
            } catch (NoSuchElementException e){
                log.info("login failed");
            }
        }
        return result;
    }


}
