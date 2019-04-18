package com.training.controller.servlet;


import com.training.controller.command.Command;
import com.training.controller.command.WrongPathCommand;
import com.training.controller.command.guest.HomeCommand;
import com.training.controller.command.guest.LoginCommand;
import com.training.controller.command.guest.SignUpCommand;
import com.training.controller.command.user.LogoutCommand;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        process(req,resp);
    }

    // TODO: 4/18/19 wrong paths in error jsp
    // TODO: 4/18/19 move accessPath(PATH_ATTRIBUTE_KEY_WORD) creation into an utill
    //      and call it from accessfilter and where user is changed
    //      it should help
    private void process(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        int role = 0;
        String accessPath;
        HttpSession session = req.getSession();
        String path = req.getRequestURI().replaceAll(IServletConstants.ROOT_PATH, "");
        log.info("path:" + path);

        req.setAttribute(IServletConstants.ACTION_URI_ATTRIBUTE_KEY_WORD, req.getRequestURI());

        // do command
        Command command = commands.getOrDefault(path, defaultPage);
        String page = command.execute(req);

        log.debug("class:" + command.getClass().getName());

        // set role paths
        if (Optional.ofNullable(session.getAttribute(IServletConstants.ROLE_ATTRIBUTE_KEY_WORD)).isPresent()){
            role = (int)session.getAttribute(IServletConstants.ROLE_ATTRIBUTE_KEY_WORD);
        }

        accessPath = IServletConstants.ROOT_PATH;
        try{
            accessPath += IServletConstants.ROLES_PREFIXES[role];
        } catch (Exception e){
            accessPath += IServletConstants.ROLES_PREFIXES[IServletConstants.LOWEST_ACCESS_LEVEL];
        }

        req.setAttribute(IServletConstants.PATH_ATTRIBUTE_KEY_WORD, accessPath);

        // do redirect if needed
        if ( page.contains(IServletConstants.REDIRECT_KEY_WORD) ) {
            resp.sendRedirect(req.getContextPath() + (page = accessPath +
                    page.replaceAll(IServletConstants.REDIRECT_KEY_WORD, "")) );
            log.info("redirect:" + page);
        } else {
            req.getRequestDispatcher(page).forward(req, resp);
            log.info("forward:" + page);
        }

    }
}
