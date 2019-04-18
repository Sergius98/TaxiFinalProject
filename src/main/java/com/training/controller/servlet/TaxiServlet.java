package com.training.controller.servlet;


import com.training.controller.command.Command;
import com.training.controller.command.WrongPathCommand;
import com.training.controller.command.guest.HomeCommand;
import com.training.controller.command.guest.LoginCommand;
import com.training.controller.command.guest.SignUpCommand;
import com.training.controller.command.user.LogoutCommand;
import com.training.model.dao.DaoFactory;
import com.training.model.dao.interfaces.TaxiDao;
import com.training.model.dao.interfaces.UserDao;
import com.training.model.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@WebServlet(IServletConstants.SERVLET_PATH)
public class TaxiServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(TaxiServlet.class);
    private HashMap<String, Command> commands = new HashMap<>();
    private Command defaultPage = new WrongPathCommand();

    /*
    static private HashMap<String, Command> commands = Stream.of(new Object[][] {
            { "/home", new HomeCommand()},
            { "/login", new LoginCommand()},
    }).collect(Collectors.toMap(key -> (String)key[0], value -> (Command)value[1], (k1, k2) -> k1,  HashMap::new));
    */

    @Override
    public void init(){
        // not a user (access level 0)
        commands.put(IServletConstants.GUEST_PREFIX + IServletConstants.HOME_PAGE_PATH, new HomeCommand());
        commands.put(IServletConstants.GUEST_PREFIX + IServletConstants.LOGIN_PAGE_PATH, new LoginCommand());
        commands.put(IServletConstants.GUEST_PREFIX + IServletConstants.SIGNUP_PAGE_PATH, new SignUpCommand());

        // user (access level 1)
        commands.put(IServletConstants.USER_PREFIX + IServletConstants.HOME_PAGE_PATH, new HomeCommand());
        commands.put(IServletConstants.USER_PREFIX + IServletConstants.LOGOUT_PAGE_PATH, new LogoutCommand());

        // admin (access level 2)
        commands.put(IServletConstants.ADMIN_PREFIX + IServletConstants.HOME_PAGE_PATH, new HomeCommand());
        commands.put(IServletConstants.ADMIN_PREFIX + IServletConstants.LOGOUT_PAGE_PATH, new LogoutCommand());
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req,resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String path = req.getRequestURI().replaceAll(IServletConstants.ROOT_PATH, "");
        log.info("path:" + path);

        Command command = commands.getOrDefault(path, defaultPage);
        String page = command.execute(req);
        log.info("class:" + command.getClass().getName());

        // TODO: 4/17/19 move role to filter
        req.setAttribute("ROLE", 0);
        req.setAttribute("PATH", IServletConstants.ROOT_PATH + IServletConstants.ROLES_PREFIXES[0]);

        if ( page.contains(IServletConstants.REDIRECT_KEY_WORD) ) {
            resp.sendRedirect(req.getContextPath() + (page = page.replaceAll(IServletConstants.REDIRECT_KEY_WORD, "")) );
            log.info("redirect:" + page);
        } else {
            req.getRequestDispatcher(page).forward(req, resp);
            log.info("forward:" + page);
        }

    }
}
