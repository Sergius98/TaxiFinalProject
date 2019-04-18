package com.training.controller.filter;

import com.training.controller.servlet.IServletConstants;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.IntStream;

@WebFilter(urlPatterns = IServletConstants.LANGUAGE_FILTER_PATH)
public class LanguageFilter implements Filter {

    private Logger log = Logger.getLogger(LanguageFilter.class);

    private static final String[] languages_list = {"en", "ua"};

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {

        Optional<String> language = Optional.ofNullable((String)req.getParameter("lang"));
        if (language.isPresent()){
            ((HttpServletRequest)req).getSession().setAttribute("lang", req.getParameter("lang"));
        }
        req.setAttribute("LANGUAGES_LIST", languages_list);

        chain.doFilter(req, resp);

    }

    @Override
    public void destroy() {

    }

}