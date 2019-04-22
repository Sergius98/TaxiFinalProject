package com.training.controller;


public interface IServletConstants {
    int PAGE_ELEMENTS_COUNT = 3;

    String[] CURRENCY_LIST = {"dollar", "hryvnia"};
    String[]  LANGUAGES_LIST = {"en", "ua"};

    String ROOT_PATH = "/taxi";
    String SERVLET_PATH = ROOT_PATH + "/*";

    String GUEST_PREFIX = "/guest";
    String USER_PREFIX = "/user";
    String ADMIN_PREFIX = "/admin";

    String[] ROLES_PREFIXES = {GUEST_PREFIX, USER_PREFIX, ADMIN_PREFIX};
    int LOWEST_ACCESS_LEVEL = 0;

    String ACCESS_FILTER_PATH = SERVLET_PATH;
    String LANGUAGE_FILTER_PATH = SERVLET_PATH;
    String CURRENCY_FILTER_PATH = SERVLET_PATH;
    String ENCODING_FILTER_PATH = SERVLET_PATH;

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
    String DISCOUNTS_LIST_KEY_WORD = "DISCOUNTS_LIST";
    String CARS_LIST_KEY_WORD = "CARS_LIST";
    String STREETS_LIST_KEY_WORD = "STREETS_LIST";
    String CURRENCY_FORMATTER_KEY_WORD = "CURRENCY_FORMATTER";
    String FIRST_ELEMENT_KEY_WORD = "FIRST_ELEMENT";
    String LAST_ELEMENT_KEY_WORD = "LAST_ELEMENT";
    String PAGE_NUMBER_KEY_WORD = "page";
    String ID_PARAMETER_KEY_WORD = "id";
    String LOYALTIES_LIST_KEY_WORD = "LOYALTIES_LIST";
    String SUCCESSFUL_SEARCH_KEY_WORD = "SUCCESSFUL_SEARCH";
    String ORDER_KEY_WORD = "ORDER";
    String SUCCESSFUL_CONFIRMATION_KEY_WORD = "SUCCESSFUL_CONFIRMATION";
    String DELAY_KEY_WORD = "DELAY";

    String HOME_PAGE_PATH = "/home";
    String LOGIN_PAGE_PATH = "/login";
    String SIGNUP_PAGE_PATH = "/signup";
    String LOGOUT_PAGE_PATH = "/logout";
    String DISCOUNTS_PAGE_PATH = "/discounts";
    String DISCOUNTS_DELETE_PAGE_PATH = "/discounts/delete";
    String DISCOUNTS_ADD_PAGE_PATH = "/discounts/add";
    String LOYALTIES_PAGE_PATH = "/loyalties";
    String DELETE_LOYALTY_THRESHOLD_PAGE_PATH = "/loyalties/delete";
    String ADD_LOYALTY_THRESHOLD_PAGE_PATH = "/loyalties/add";
    String GET_TAXI_PAGE_PATH = "/get_taxi";
    String SEARCH_TAXI_PAGE_PATH = "/get_taxi/search";
    String CONFIRM_TAXI_PAGE_PATH = "/get_taxi/confirm";


    String HOME_PAGE_JSP = "/WEB-INF/view/pages/home.jsp";
    String LOGIN_PAGE_JSP = "/WEB-INF/view/pages/login.jsp";
    String SIGNUP_PAGE_JSP = "/WEB-INF/view/pages/signup.jsp";
    String DISCOUNTS_PAGE_JSP = "/WEB-INF/view/pages/discounts.jsp";
    String LOYALTIES_PAGE_JSP = "/WEB-INF/view/pages/loyalties.jsp";
    String GET_TAXI_PAGE_JSP = "/WEB-INF/view/pages/get_taxi.jsp";
}
