package com.training.controller.command.guest;

import com.training.controller.command.Command;
import com.training.controller.servlet.IServletConstants;
import com.training.controller.utill.Extractor;
import com.training.model.dao.DaoFactory;
import com.training.model.dao.interfaces.UserDao;
import com.training.model.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class LoginCommand implements Command {
    private Logger log = Logger.getLogger(LoginCommand.class);
    private Extractor extractor = new Extractor(log);
    private static HashSet<Integer> active_users = new HashSet<>();

    @Override
    public String execute(HttpServletRequest req) {
        String result = IServletConstants.LOGIN_PAGE_JSP;

        Locale locale = !Optional.ofNullable(req.getSession().getAttribute("lang")).isPresent() ?
                new Locale("en") :
                new Locale((String) req.getSession().getAttribute("lang"));

        if (req.getMethod().equals("POST")){
            Optional<User> user = extractor.extractValidUser(req);
            if (user.isPresent()){
                try(UserDao dao = DaoFactory.getInstance().createUserDao()){
                    user = dao.findByNickname(user.get().getNickname());
                    if (user.isPresent() && !active_users.contains(user.get().getId())){
                        active_users.add(user.get().getId());
                        log.info("user" + user.get().getId() + " logged in");

                        req.getSession().setAttribute(IServletConstants.USER_ID_ATTRIBUTE_KEY_WORD, user.get().getId());
                        // TODO: 4/18/19 remove user from session
                        req.getSession().setAttribute(IServletConstants.USER_ATTRIBUTE_KEY_WORD, user.get());
                        req.setAttribute(IServletConstants.ROLE_ATTRIBUTE_KEY_WORD, user.get().getRole() + 1);
                        req.getSession().setAttribute(IServletConstants.ROLE_ATTRIBUTE_KEY_WORD, user.get().getRole() + 1);
                        result = IServletConstants.REDIRECT_KEY_WORD + IServletConstants.HOME_PAGE_PATH;
                    } else {
                        req.setAttribute(IServletConstants.ALERT_ATTRIBUTE_KEY_WORD, ResourceBundle.getBundle("errors", locale).getString("wrong_login"));
                        log.info("user login for [" + user.get().getId() + "] failed");
                    }
                } catch (Exception e){
                    log.info("login was failed with :" + e.getMessage());
                }
            }
        }
        return result;
    }

    public static void disconectUser(int userId){
        active_users.remove(userId);
    }
}
