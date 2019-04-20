package com.training.controller.utill.impl;

import com.training.controller.IServletConstants;
import com.training.controller.utill.interfaces.IAuthorization;
import com.training.controller.utill.interfaces.IExtractor;
import com.training.controller.utill.interfaces.IUserDataManeger;
import com.training.model.dao.DaoFactory;
import com.training.model.dao.interfaces.UserDao;
import com.training.model.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Authorization implements IAuthorization {
    private Logger log = Logger.getLogger(Authorization.class);
    // TODO: 4/19/19 turn Authorization into singleton instead of static references
    private static Set<Integer> active_users = ConcurrentHashMap.newKeySet();
    //private static HashSet<Integer> active_users = new HashSet<>();
// TODO: 4/20/19 move to constructor
    private Localization localization = new Localization();

    private UserExtractor extractor;
    private IUserDataManeger userDataManager;

    public Authorization(UserExtractor extractor, IUserDataManeger userDataManager){
        this.extractor = extractor;
        this.userDataManager = userDataManager;
    }

    @Override
    public Optional<String> signUp(HttpServletRequest req) {
        Optional<String> path = Optional.empty();
        Locale locale = localization.extractLocale(req);
        Optional<User> user = extractor.extract(req);

        if (user.isPresent()){
            try(UserDao dao = DaoFactory.getInstance().createUserDao()){
                if (dao.create(user.get())){
                    log.info("user was registered");
                    userDataManager.setUser(req, user);
                    path = Optional.of(IServletConstants.REDIRECT_KEY_WORD +
                            IServletConstants.HOME_PAGE_PATH);
                } else {
                    req.setAttribute(IServletConstants.ALERT_ATTRIBUTE_KEY_WORD,
                            ResourceBundle.getBundle("errors", locale)
                                    .getString("nickname_is_taken"));
                    log.info("user registration failed");
                }
            } catch (Exception e){
                log.info("registration was failed with :" + e.getMessage());
            }
        }
        return path;
    }

    @Override
    public Optional<String> logIn(HttpServletRequest req) {
        Optional<String> path = Optional.empty();
        Locale locale = localization.extractLocale(req);
        Optional<User> user = extractor.extract(req);

        if (user.isPresent()){
            try(UserDao dao = DaoFactory.getInstance().createUserDao()){
                user = dao.findByNickname(user.get().getNickname());
                if (user.isPresent() && !active_users.contains(user.get().getId())){
                    active_users.add(user.get().getId());
                    log.info("user" + user.get().getId() + " logged in");

                    userDataManager.setUser(req, user);

                    path = Optional.of(IServletConstants.REDIRECT_KEY_WORD +
                            IServletConstants.HOME_PAGE_PATH);
                } else {
                    req.setAttribute(IServletConstants.ALERT_ATTRIBUTE_KEY_WORD,
                            ResourceBundle.getBundle("errors", locale)
                                    .getString("wrong_login"));
                    log.info("user login for [" + user.get().getId() + "] failed");
                }
            } catch (Exception e){
                log.info("login was failed with :" + e.getMessage());
            }
        }
        return path;
    }

    @Override
    public void logOut(HttpServletRequest req) {
        int userId = (int)req.getSession()
                .getAttribute(IServletConstants.USER_ID_ATTRIBUTE_KEY_WORD);
        active_users.remove(userId);
        userDataManager.setUser(req, Optional.empty());
    }
}
