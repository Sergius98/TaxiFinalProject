package com.training.controller.servlet;


public interface IServletConstants {
    String ROOT_PATH = "/taxi";
    String SERVLET_PATH = ROOT_PATH + "/*";

    String GUEST_PREFIX = "/guest";
    String USER_PREFIX = "/user";
    String ADMIN_PREFIX = "/admin";
    String[] ROLES_PREFIXES = {GUEST_PREFIX, USER_PREFIX, ADMIN_PREFIX};

    String ACCESS_FILTER_PATH = SERVLET_PATH; //don't change, access filter should work on all pages

    String REDIRECT_KEY_WORD = "redirect:";

    String HOME_PAGE_PATH = "/home";
    String LOGIN_PAGE_PATH = "/login";
    String SIGNUP_PAGE_PATH = "/signup";
    String LOGOUT_PAGE_PATH = "/logout";


    String HOME_PAGE_JSP = "/WEB-INF/view/pages/home.jsp";
    String LOGIN_PAGE_JSP = "/WEB-INF/view/pages/login.jsp";
    String SIGNUP_PAGE_JSP = "/WEB-INF/view/pages/signup.jsp";

}
