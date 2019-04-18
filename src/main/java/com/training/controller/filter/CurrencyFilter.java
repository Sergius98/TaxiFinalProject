package com.training.controller.filter;

import com.training.controller.servlet.IServletConstants;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

@WebFilter(urlPatterns = IServletConstants.CURRENCY_FILTER_PATH)
public class CurrencyFilter implements Filter {

    private Logger log = Logger.getLogger(CurrencyFilter.class);

    private static final String[] currency_list = {"dollar", "hryvnia"};

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {

        Optional<String> language = Optional.ofNullable((String)req.getParameter("curr"));
        if (language.isPresent()){
            ((HttpServletRequest)req).getSession().setAttribute("curr", req.getParameter("curr"));
        }
        req.setAttribute("CURRENCY_LIST", currency_list);

        chain.doFilter(req, resp);

    }

    @Override
    public void destroy() {

    }

}