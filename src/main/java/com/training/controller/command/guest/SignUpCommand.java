package com.training.controller.command.guest;

import com.training.controller.command.Command;
import com.training.controller.servlet.IServletConstants;
import com.training.controller.utill.impl.Authorization;
import com.training.controller.utill.impl.UserExtractor;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.NoSuchElementException;

public class SignUpCommand implements Command {
    private Logger log = Logger.getLogger(SignUpCommand.class);
    private UserExtractor extractor = new UserExtractor();
    private Authorization authorization = new Authorization(extractor);

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
