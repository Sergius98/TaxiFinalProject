package com.training.controller.utill;

import com.training.model.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class Extractor {
    private Logger log;
    public Extractor(Logger log){
        this.log = log;
    }
    public Optional<User> extractValidUser(HttpServletRequest req){
        String nickname = req.getParameter("nickname");
        String password = req.getParameter("password");
        req.setAttribute("NICKNAME", nickname);
        log.info("nickname:"+nickname);
        log.info("password:"+password);
        Boolean fail = false;
        Optional<User> user = Optional.empty();
        Locale locale = !Optional.ofNullable(req.getSession().getAttribute("lang")).isPresent() ?
                new Locale("en") :
                new Locale((String) req.getSession().getAttribute("lang"));
        if (!fail && !nickname.matches(ResourceBundle.getBundle("regex").getString("nickname_regex"))){
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
