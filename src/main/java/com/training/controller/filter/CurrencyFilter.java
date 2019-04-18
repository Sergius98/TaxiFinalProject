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

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {

        Optional<String> language = Optional.ofNullable(req.getParameter(IServletConstants.CURR_ATTRIBUTE_KEY_WORD));
        if (language.isPresent()){
            ((HttpServletRequest)req).getSession().setAttribute(IServletConstants.CURR_ATTRIBUTE_KEY_WORD, req.getParameter(IServletConstants.CURR_ATTRIBUTE_KEY_WORD));
        }
        req.setAttribute(IServletConstants.CURRENCY_LIST_ATTRIBUTE_KEY_WORD,
                IServletConstants.CURRENCY_LIST);
        if (Optional.ofNullable(((HttpServletRequest)req).getSession().getAttribute(IServletConstants.CURR_ATTRIBUTE_KEY_WORD)).isPresent()){
            req.setAttribute(IServletConstants.CURRENCY_ATTRIBUTE_KEY_WORD, ((HttpServletRequest)req).getSession().getAttribute(IServletConstants.CURR_ATTRIBUTE_KEY_WORD));
        } else {
            req.setAttribute(IServletConstants.CURRENCY_ATTRIBUTE_KEY_WORD, IServletConstants.CURRENCY_LIST[0]);
        }

        chain.doFilter(req, resp);

    }

    @Override
    public void destroy() {

    }

}