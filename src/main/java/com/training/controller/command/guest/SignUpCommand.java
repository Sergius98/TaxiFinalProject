package com.training.controller.command.guest;

import com.training.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class SignUpCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {

        // TODO: 4/17/19 return signUp page to user on Get or try to signUp user on Post
        request.getSession().setAttribute("user", null);
        return "/index.jsp";
    }
}
