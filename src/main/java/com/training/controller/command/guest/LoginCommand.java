package com.training.controller.command.guest;

import com.training.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {

        // TODO: 4/17/19 return login page to user on Get or try to login user on Post
        request.getSession().setAttribute("user", null);
        return "/index.jsp";
    }
}
