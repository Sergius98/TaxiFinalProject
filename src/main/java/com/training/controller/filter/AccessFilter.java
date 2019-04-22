package com.training.controller.filter;

import com.training.controller.IServletConstants;
import com.training.controller.utill.impl.AccessPathExtractor;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.IntStream;

/**
 * manage user role and page access
 * check if they are even
 */
@WebFilter(urlPatterns = IServletConstants.ACCESS_FILTER_PATH)
public class AccessFilter implements Filter {
    private Logger log = Logger.getLogger(AccessFilter.class);
    private AccessPathExtractor accessPathExtractor = new AccessPathExtractor();


    @Override
    public void doFilter(ServletRequest req,
                         ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        int role = getRole((HttpServletRequest)req);
        int access = getAccess((HttpServletRequest)req);

        req.setAttribute(IServletConstants.ROLE_ATTRIBUTE_KEY_WORD, role);
        log.info("role : " + role);
        log.info("access : " + access);

        if(role != access){
            accessPathExtractor.extract((HttpServletRequest) req).get();

            log.warn("unauthorized access");
            throw new IOException("user doesn't have right to see this page");
            // TODO: 4/18/19 EXCEPTION
            //throw new NoAccessException("You don`t have permission to visit this page");
        }
        chain.doFilter(req, resp);

    }

    @Override
    public void destroy() {
        log.debug("filter is destroyed");
    }

    private int getRole(HttpServletRequest req) {
        Optional<Integer> role = Optional.ofNullable((Integer)req.getSession()
                .getAttribute(IServletConstants.ROLE_ATTRIBUTE_KEY_WORD));

        return role.orElse(IServletConstants.LOWEST_ACCESS_LEVEL);
    }

    private int getAccess(HttpServletRequest req) {
        String uri = req.getRequestURI();

        return IntStream.range(0, IServletConstants.ROLES_PREFIXES.length)
                .filter(i -> uri.contains(IServletConstants.ROLES_PREFIXES[i]))
                .findFirst()
                .orElse(IServletConstants.ROLES_PREFIXES.length);
    }
}