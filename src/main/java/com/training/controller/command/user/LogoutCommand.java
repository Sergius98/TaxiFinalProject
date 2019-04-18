package com.training.controller.command.user;

import com.training.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {

        request.getSession().setAttribute("user", null);
        return "/index.jsp";
    }

}
