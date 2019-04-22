package com.training.controller.utill.impl;

import com.training.controller.IServletConstants;
import com.training.controller.utill.interfaces.IUserDataManager;
import com.training.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.NoSuchElementException;
import java.util.Optional;

public class UserDataManager implements IUserDataManager {

    @Override
    public void setUser(HttpServletRequest req, Optional<User> optionalUser){
        try{
            User user = optionalUser.get();
            int user_id = user.getId();
            // in db(boolean var) user is 0, admin is 1.
            // in the project we also have guest(0)
            // so we add 1 to the users role
            int user_role = user.getRole() + 1;

            req.getSession().setAttribute(
                    IServletConstants.USER_ID_ATTRIBUTE_KEY_WORD, user_id);
            // TODO: 4/18/19 remove user from session attributes(leave only id)
            req.getSession().setAttribute(
                    IServletConstants.USER_ATTRIBUTE_KEY_WORD, user);
            //
            req.getSession().setAttribute(
                    IServletConstants.ROLE_ATTRIBUTE_KEY_WORD, user_role);
            req.setAttribute(
                    IServletConstants.ROLE_ATTRIBUTE_KEY_WORD, user_role);
        } catch (NoSuchElementException e){
            req.getSession().setAttribute(
                    IServletConstants.USER_ID_ATTRIBUTE_KEY_WORD, IServletConstants.NO_USER_ID);
            req.getSession().setAttribute(
                    IServletConstants.ROLE_ATTRIBUTE_KEY_WORD,
                    IServletConstants.LOWEST_ACCESS_LEVEL);
        }
    }

    @Override
    public void setUserDataFromSession(HttpServletRequest req) {
        req.setAttribute(IServletConstants.USER_ATTRIBUTE_KEY_WORD, req.getSession().getAttribute(IServletConstants.USER_ATTRIBUTE_KEY_WORD));
    }

    @Override
    public void setUserAccessPathFromSessionData(HttpServletRequest req) {

    }
}
