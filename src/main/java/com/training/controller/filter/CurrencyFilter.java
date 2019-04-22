package com.training.controller.filter;

import com.training.controller.IServletConstants;
import com.training.controller.utill.impl.CurrencyFormatter;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

/**
 * manage currency(current and list) in request and session
 */
@WebFilter(urlPatterns = IServletConstants.CURRENCY_FILTER_PATH)
public class CurrencyFilter implements Filter {

    private Logger log = Logger.getLogger(CurrencyFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse resp,
                         FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        Optional<String> currency = Optional.ofNullable(req.getParameter(
                IServletConstants.CURR_ATTRIBUTE_KEY_WORD));

        if (currency.isPresent()){
            log.info("new currency: " + currency.get());
            req.getSession().setAttribute(
                    IServletConstants.CURR_ATTRIBUTE_KEY_WORD,
                    req.getParameter(
                            IServletConstants.CURR_ATTRIBUTE_KEY_WORD));
        }
        req.setAttribute(IServletConstants.CURRENCY_LIST_ATTRIBUTE_KEY_WORD,
                         IServletConstants.CURRENCY_LIST);

        currency = Optional.ofNullable((String)req.getSession()
                .getAttribute(IServletConstants.CURR_ATTRIBUTE_KEY_WORD));
        if (currency.isPresent()){
            log.info("currency: " + currency.get());
            req.setAttribute(IServletConstants.CURRENCY_ATTRIBUTE_KEY_WORD,
                    currency.get());
        } else {
            log.info("currency: " + IServletConstants.CURRENCY_LIST[0]);
            req.setAttribute(IServletConstants.CURRENCY_ATTRIBUTE_KEY_WORD,
                    IServletConstants.CURRENCY_LIST[0]);
        }

        req.setAttribute(IServletConstants.CURRENCY_FORMATTER_KEY_WORD,
                new CurrencyFormatter((String)req.getAttribute(
                        IServletConstants.CURRENCY_ATTRIBUTE_KEY_WORD)));

        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
        log.debug("filter is destroyed");
    }

}