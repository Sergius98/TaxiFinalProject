package com.training.controller.command.guest;

import com.training.controller.command.Command;
import com.training.controller.IServletConstants;
import com.training.controller.utill.impl.Authorization;
import com.training.controller.utill.impl.UserDataManager;
import com.training.controller.utill.impl.UserExtractor;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.NoSuchElementException;

public class SignUpCommand implements Command {
    private Logger log = Logger.getLogger(SignUpCommand.class);
    private Authorization authorization;

    public SignUpCommand(Authorization authorization) {
        this.authorization = authorization;
    }

    @Override
    public String execute(HttpServletRequest req) {
        String result = IServletConstants.SIGNUP_PAGE_JSP;

        if (req.getMethod().equals("POST")){

            try{
                result = authorization.signUp(req).get();
            } catch (NoSuchElementException e){
                log.info("signUp failed");
            }
        }
        return result;
    }
}
