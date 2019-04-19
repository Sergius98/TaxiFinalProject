package com.training.controller.utill.impl;

import com.training.controller.utill.interfaces.IExtractor;
import com.training.model.entity.User;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class UserExtractor implements IExtractor<User> {
    private Logger log = Logger.getLogger(UserExtractor.class);
    private Localization localization = new Localization();

    public Optional<User> extract(HttpServletRequest req){
        String nickname = req.getParameter("nickname");
        String password = req.getParameter("password");
        Boolean fail = false;
        Optional<User> user = Optional.empty();
        Locale locale = localization.extractLocale(req);

        req.setAttribute("NICKNAME", nickname);
        log.info("nickname:"+nickname);
        log.info("password:"+password);

        if (!nickname.matches(ResourceBundle.getBundle("regex").getString("nickname_regex"))){
            fail = true;
            req.setAttribute("ALERT", ResourceBundle.getBundle("errors", locale).getString("nickname_is_wrong"));
            log.info("nickname_is_wrong");
        }

        if (!fail && !password.matches(ResourceBundle.getBundle("regex").getString("password_regex"))){
            fail = true;
            req.setAttribute("ALERT", ResourceBundle.getBundle("errors", locale).getString("password_is_wrong"));
            log.info("password_is_wrong");
        }

        if (!fail){
            user = Optional.of(new User(nickname, password));
        }

        return user;
    }
}
