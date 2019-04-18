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

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {

        Optional<String> language = Optional.ofNullable((String)req.getParameter(IServletConstants.LANG_ATTRIBUTE_KEY_WORD));
        if (language.isPresent()){
            ((HttpServletRequest)req).getSession().setAttribute(IServletConstants.LANG_ATTRIBUTE_KEY_WORD, req.getParameter(IServletConstants.LANG_ATTRIBUTE_KEY_WORD));
        }

        req.setAttribute(IServletConstants.LANGUAGES_LIST_KEY_WORD,
                IServletConstants.LANGUAGES_LIST);

        chain.doFilter(req, resp);

    }

    @Override
    public void destroy() {

    }

}