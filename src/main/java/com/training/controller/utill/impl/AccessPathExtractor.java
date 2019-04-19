package com.training.controller.utill.impl;

import com.training.controller.IServletConstants;
import com.training.controller.utill.interfaces.IExtractor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.text.html.Option;
import java.util.Optional;

public class AccessPathExtractor implements IExtractor<String> {
    @Override
    public Optional<String> extract(HttpServletRequest req) {
        HttpSession session = req.getSession();
        int role = 0;
        String accessPath = IServletConstants.ROOT_PATH;
        // set role paths
        if (Optional.ofNullable(session.getAttribute(IServletConstants.ROLE_ATTRIBUTE_KEY_WORD)).isPresent()){
            role = (int)session.getAttribute(IServletConstants.ROLE_ATTRIBUTE_KEY_WORD);
        }

        try{
            accessPath += IServletConstants.ROLES_PREFIXES[role];
        } catch (Exception e){
            accessPath += IServletConstants.ROLES_PREFIXES[IServletConstants.LOWEST_ACCESS_LEVEL];
        }

        req.setAttribute(IServletConstants.PATH_ATTRIBUTE_KEY_WORD, accessPath);

        return Optional.of(accessPath);
    }
}
