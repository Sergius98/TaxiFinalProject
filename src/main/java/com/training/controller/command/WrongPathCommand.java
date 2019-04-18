package com.training.controller.command;

import javax.servlet.http.HttpServletRequest;

public class WrongPathCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        // TODO: 4/17/19 wrong path command (dunno what to do)
        request.getSession().setAttribute("user", null);
        return "/index.jsp";
    }

}
