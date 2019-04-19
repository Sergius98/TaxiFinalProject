package com.training.controller.utill.interfaces;

import com.training.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public interface IUserDataManeger {
    void setUser(HttpServletRequest req, Optional<User> optionalUser);
    void setUserDataFromSession(HttpServletRequest req);
    void setUserAccessPathFromSessionData(HttpServletRequest req);
}
