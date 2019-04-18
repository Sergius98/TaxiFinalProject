package com.training.controller.command.guest;

import com.training.controller.command.Command;
import com.training.controller.filter.AccessFilter;
import com.training.controller.servlet.IServletConstants;
import com.training.controller.utill.Extractor;
import com.training.model.dao.DaoFactory;
import com.training.model.dao.interfaces.UserDao;
import com.training.model.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class SignUpCommand implements Command {
    private Logger log = Logger.getLogger(SignUpCommand.class);
    private Extractor extractor = new Extractor(log);

    @Override
    public String execute(HttpServletRequest req) {
        String result = IServletConstants.SIGNUP_PAGE_JSP;

        Locale locale = !Optional.ofNullable(req.getSession().getAttribute("lang")).isPresent() ?
                new Locale("en") :
                new Locale((String) req.getSession().getAttribute("lang"));

        if (req.getMethod().equals("POST")){
            // TODO: 4/17/19 try to signUp user on Post
            Optional<User> user = extractor.extractValidUser(req);
            if (user.isPresent()){
                try(UserDao dao = DaoFactory.getInstance().createUserDao()){
                    if (dao.create(user.get())){
                        log.info("user was registered");
                        req.getSession().setAttribute(IServletConstants.ROLE_ATTRIBUTE_KEY_WORD, user.get().getRole() + 1);
                        req.getSession().setAttribute(IServletConstants.USER_ID_ATTRIBUTE_KEY_WORD, user.get().getId());
                        req.setAttribute(IServletConstants.ROLE_ATTRIBUTE_KEY_WORD, user.get().getRole() + 1);
                        result = IServletConstants.REDIRECT_KEY_WORD + IServletConstants.HOME_PAGE_PATH;
                    } else {
                        req.setAttribute(IServletConstants.ALERT_ATTRIBUTE_KEY_WORD, ResourceBundle.getBundle("errors", locale).getString("nickname_is_taken"));
                        log.info("user registration failed");
                    }
                } catch (Exception e){
                    log.info("registration was failed with :" + e.getMessage());
                }
            }
        }
        return result;
    }
}
