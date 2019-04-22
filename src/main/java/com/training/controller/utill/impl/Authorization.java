package com.training.controller.utill.impl;

import com.training.controller.IServletConstants;
import com.training.controller.utill.interfaces.IAuthorization;
import com.training.controller.utill.interfaces.IUserDataManager;
import com.training.model.dao.DaoFactory;
import com.training.model.dao.interfaces.UserDao;
import com.training.model.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Authorization implements IAuthorization {
    private static Logger log = Logger.getLogger(Authorization.class);
    private static Set<Integer> active_users = ConcurrentHashMap.newKeySet();

    private static volatile Authorization instance;

    private static volatile  Localization localization;
    private static volatile  UserExtractor extractor;
    private static volatile IUserDataManager userDataManager;

    public static void init(UserExtractor extractor, IUserDataManager userDataManager, Localization localization){
        synchronized (Authorization.class) {
            Authorization.extractor = extractor;
            Authorization.userDataManager = userDataManager;
            Authorization.localization = localization;
            log.debug("Authorization was initilized");
        }
    }

    public static Authorization getInstance() {
        if (instance == null) {
            synchronized (Authorization.class) {
                if (instance == null) {
                    instance = new Authorization();
                    if (Authorization.extractor == null){
                        Authorization.extractor = new UserExtractor();
                        log.debug("extractor wasn't initilized");
                    }
                    if (Authorization.userDataManager == null){
                        Authorization.userDataManager = new UserDataManager();
                        log.debug("userDataManager wasn't initilized");
                    }
                    if (Authorization.localization == null){
                        Authorization.localization = new Localization();
                        log.debug("localization wasn't initilized");
                    }
                }
            }
        }
        return instance;
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
                    logIn(req);//userDataManager.setUser(req, user);
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
                log.trace(e,e);
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
                    log.info("user [id=" + user.get().getId() + "] logged in");

                    userDataManager.setUser(req, user);

                    path = Optional.of(IServletConstants.REDIRECT_KEY_WORD +
                            IServletConstants.HOME_PAGE_PATH);
                } else {
                    req.setAttribute(IServletConstants.ALERT_ATTRIBUTE_KEY_WORD,
                            ResourceBundle.getBundle("errors", locale)
                                    .getString("wrong_login"));
                    log.info("user login for [id=" + user.get().getId() + "] failed");
                }
            } catch (Exception e){
                log.info("login was failed with :" + e.getMessage());
                log.trace(e,e);
            }
        }
        return path;
    }

    @Override
    public void logOut(HttpServletRequest req) {
        int userId = (int)req.getSession()
                .getAttribute(IServletConstants.USER_ID_ATTRIBUTE_KEY_WORD);
        active_users.remove(userId);
        log.info("user [id=" + userId + "] logedout");
        userDataManager.setUser(req, Optional.empty());
    }
}
