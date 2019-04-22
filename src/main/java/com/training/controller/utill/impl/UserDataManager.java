package com.training.controller.utill.impl;

import com.training.controller.IServletConstants;
import com.training.controller.utill.interfaces.IUserDataManager;
import com.training.model.dao.DaoFactory;
import com.training.model.dao.interfaces.UserDao;
import com.training.model.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * manage user's data in session
 */
public class UserDataManager implements IUserDataManager {
    private static final Logger log = Logger.getLogger(UserDataManager.class);

    @Override
    public void setUser(HttpServletRequest req, Optional<User> optionalUser){
        try{
            User user = optionalUser.get();
            int user_id = user.getId();
            int user_role = user.getRole() + 1;/*convert db's role to jsp's role*/

            /* set user's information in session */
            req.getSession().setAttribute(
                    IServletConstants.USER_ID_ATTRIBUTE_KEY_WORD, user_id);
            req.getSession().setAttribute(
                    IServletConstants.USER_ATTRIBUTE_KEY_WORD, user);
            /* set user's role in session and request */
            req.getSession().setAttribute(
                    IServletConstants.ROLE_ATTRIBUTE_KEY_WORD, user_role);
            req.setAttribute(
                    IServletConstants.ROLE_ATTRIBUTE_KEY_WORD, user_role);
            log.info("user[id="+user_id+"] has been successfully set");
        } catch (NoSuchElementException e){
            req.getSession().setAttribute(
                    IServletConstants.USER_ID_ATTRIBUTE_KEY_WORD, IServletConstants.NO_USER_ID);
            req.getSession().setAttribute(
                    IServletConstants.ROLE_ATTRIBUTE_KEY_WORD,
                    IServletConstants.LOWEST_ACCESS_LEVEL);
            log.info("guest user has been successfully set");
        }
    }

    @Override
    public void setUserDataFromSession(HttpServletRequest req) {
        int u_id = (int)Optional.ofNullable(req.getSession().getAttribute(
                IServletConstants.USER_ID_ATTRIBUTE_KEY_WORD))
                .orElse(IServletConstants.NO_USER_ID);

        try(UserDao dao = DaoFactory.getInstance().createUserDao()){
            Optional<User> user = dao.findById(u_id);
            if (user.isPresent()){
                setUser(req, user);
            } else {
                log.info("user info extraction for [id=" + u_id + "] failed");
            }
        } catch (Exception e){
            log.info("user info extraction failed with :" + e.getMessage());
            log.trace(e, e);
        }
        req.setAttribute(IServletConstants.USER_ATTRIBUTE_KEY_WORD,
                req.getSession().getAttribute(
                        IServletConstants.USER_ATTRIBUTE_KEY_WORD));
    }

    @Override
    public void setUserAccessPathFromSessionData(HttpServletRequest req) {

    }
}
