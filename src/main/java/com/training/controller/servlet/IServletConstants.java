package com.training.controller.servlet;


public interface IServletConstants {
    String ROOT_PATH = "/taxi";
    String SERVLET_PATH = ROOT_PATH + "/*";

    String GUEST_PREFIX = "/guest";
    String USER_PREFIX = "/user";
    String ADMIN_PREFIX = "/admin";

    String[] ROLES_PREFIXES = {GUEST_PREFIX, USER_PREFIX, ADMIN_PREFIX};
    int LOWEST_ACCESS_LEVEL = 0; // always zero since it is the lowest possible index

    String ACCESS_FILTER_PATH = SERVLET_PATH;
    String LANGUAGE_FILTER_PATH = SERVLET_PATH;
    String CURRENCY_FILTER_PATH = SERVLET_PATH;

    String REDIRECT_KEY_WORD = "redirect:";

    String ROLE_ATTRIBUTE_KEY_WORD = "ROLE";

    // paths shouldn't be changed unless there is some changes in jsp
    String HOME_PAGE_PATH = "/home";
    String LOGIN_PAGE_PATH = "/login";
    String SIGNUP_PAGE_PATH = "/signup";
    String LOGOUT_PAGE_PATH = "/logout";


    String HOME_PAGE_JSP = "/WEB-INF/view/pages/home.jsp";
    String LOGIN_PAGE_JSP = "/WEB-INF/view/pages/login.jsp";
    String SIGNUP_PAGE_JSP = "/WEB-INF/view/pages/signup.jsp";
}
