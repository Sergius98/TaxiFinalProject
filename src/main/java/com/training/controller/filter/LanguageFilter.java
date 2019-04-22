package com.training.controller.filter;

import com.training.controller.IServletConstants;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

/**
 * manage languages(current and list) in request and session
 */
@WebFilter(urlPatterns = IServletConstants.LANGUAGE_FILTER_PATH)
public class LanguageFilter implements Filter {
    private Logger log = Logger.getLogger(LanguageFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse resp,
                         FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        Optional<String> language = Optional.ofNullable(
                (String)req.getSession().getAttribute(
                                IServletConstants.LANG_ATTRIBUTE_KEY_WORD));
        Optional<String> newLanguage = Optional.ofNullable(
                req.getParameter(IServletConstants.LANG_ATTRIBUTE_KEY_WORD));

        if (newLanguage.isPresent()){
            log.info("new language: " + newLanguage.get());
            req.getSession().setAttribute(
                    IServletConstants.LANG_ATTRIBUTE_KEY_WORD,
                    req.getParameter(
                            IServletConstants.LANG_ATTRIBUTE_KEY_WORD));
        } else if (!language.isPresent()){
            log.info("default language");
            req.getSession().setAttribute(
                    IServletConstants.LANG_ATTRIBUTE_KEY_WORD,
                    IServletConstants.LANGUAGES_LIST[0]);
        }

        req.setAttribute(IServletConstants.LANGUAGES_LIST_KEY_WORD,
                IServletConstants.LANGUAGES_LIST);

        chain.doFilter(req, resp);

    }

    @Override
    public void destroy() {
        log.debug("filter is destroyed");
    }

}