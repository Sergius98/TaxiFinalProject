package com.training.controller.utill.interfaces;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public interface IAuthorization {
    Optional<String> signUp(HttpServletRequest req);
    Optional<String> logIn(HttpServletRequest req);
    void logOut(HttpServletRequest req);
}
