package com.training.controller.servlet;


public interface IServletConstants {
    String[] CURRENCY_LIST = {"dollar", "hryvnia"};
    String[]  LANGUAGES_LIST = {"en", "ua"};

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

    String PATH_ATTRIBUTE_KEY_WORD = "PATH";
    String ROLE_ATTRIBUTE_KEY_WORD = "ROLE";
    String USER_ATTRIBUTE_KEY_WORD = "USER";
    String USER_ID_ATTRIBUTE_KEY_WORD = "USER_ID";
    String ALERT_ATTRIBUTE_KEY_WORD = "ALERT";
    String CURRENCY_ATTRIBUTE_KEY_WORD = "CURRENCY";
    String CURRENCY_LIST_ATTRIBUTE_KEY_WORD = "CURRENCY_LIST";
    String LANGUAGES_LIST_KEY_WORD = "LANGUAGES_LIST";
    String CURR_ATTRIBUTE_KEY_WORD = "curr";
    String LANG_ATTRIBUTE_KEY_WORD = "lang";
    String ACTION_URI_ATTRIBUTE_KEY_WORD = "ACTION_URI";

    // paths shouldn't be changed unless there is some changes in jsp
    String HOME_PAGE_PATH = "/home";
    String LOGIN_PAGE_PATH = "/login";
    String SIGNUP_PAGE_PATH = "/signup";
    String LOGOUT_PAGE_PATH = "/logout";


    String HOME_PAGE_JSP = "/WEB-INF/view/pages/home.jsp";
    String LOGIN_PAGE_JSP = "/WEB-INF/view/pages/login.jsp";
    String SIGNUP_PAGE_JSP = "/WEB-INF/view/pages/signup.jsp";
}
