package com.training.controller.servlet;


import com.training.controller.IServletConstants;
import com.training.controller.command.*;
import com.training.controller.command.admin.AddDiscountCommand;
import com.training.controller.command.admin.AddLoyaltyThresholdCommand;
import com.training.controller.command.admin.DeleteDiscountCommand;
import com.training.controller.command.admin.DeleteLoyaltyThresholdCommand;
import com.training.controller.command.guest.GetTaxiCommand;
import com.training.controller.command.guest.LoginCommand;
import com.training.controller.command.guest.SearchTaxiCommand;
import com.training.controller.command.guest.SignUpCommand;
import com.training.controller.command.user.LogoutCommand;
import com.training.controller.utill.impl.AccessPathExtractor;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(IServletConstants.SERVLET_PATH)
public class TaxiServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(TaxiServlet.class);
    private HashMap<String, Command> commands = new HashMap<>();
    private Command defaultPage = new WrongPathCommand();
    AccessPathExtractor accessPathExtractor = new AccessPathExtractor();

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
        commands.put(IServletConstants.GUEST_PREFIX + IServletConstants.DISCOUNTS_PAGE_PATH, new DiscountsCommand());
        commands.put(IServletConstants.GUEST_PREFIX + IServletConstants.LOYALTIES_PAGE_PATH, new LoyaltiesCommand());

        // user (access level 1)
        commands.put(IServletConstants.USER_PREFIX + IServletConstants.HOME_PAGE_PATH, new HomeCommand());
        commands.put(IServletConstants.USER_PREFIX + IServletConstants.LOGOUT_PAGE_PATH, new LogoutCommand());
        commands.put(IServletConstants.USER_PREFIX + IServletConstants.DISCOUNTS_PAGE_PATH, new DiscountsCommand());
        commands.put(IServletConstants.USER_PREFIX + IServletConstants.LOYALTIES_PAGE_PATH, new LoyaltiesCommand());
        commands.put(IServletConstants.USER_PREFIX + IServletConstants.GET_TAXI_PAGE_PATH, new GetTaxiCommand());
        commands.put(IServletConstants.USER_PREFIX + IServletConstants.SEARCH_TAXI_PAGE_PATH, new SearchTaxiCommand());

        // admin (access level 2)
        commands.put(IServletConstants.ADMIN_PREFIX + IServletConstants.HOME_PAGE_PATH, new HomeCommand());
        commands.put(IServletConstants.ADMIN_PREFIX + IServletConstants.LOGOUT_PAGE_PATH, new LogoutCommand());
        commands.put(IServletConstants.ADMIN_PREFIX + IServletConstants.DISCOUNTS_PAGE_PATH, new DiscountsCommand());
        commands.put(IServletConstants.ADMIN_PREFIX + IServletConstants.DISCOUNTS_DELETE_PAGE_PATH, new DeleteDiscountCommand());
        commands.put(IServletConstants.ADMIN_PREFIX + IServletConstants.DISCOUNTS_ADD_PAGE_PATH, new AddDiscountCommand());
        commands.put(IServletConstants.ADMIN_PREFIX + IServletConstants.LOYALTIES_PAGE_PATH, new LoyaltiesCommand());
        commands.put(IServletConstants.ADMIN_PREFIX + IServletConstants.DELETE_LOYALTY_THRESHOLD_PAGE_PATH, new DeleteLoyaltyThresholdCommand());
        commands.put(IServletConstants.ADMIN_PREFIX + IServletConstants.ADD_LOYALTY_THRESHOLD_PAGE_PATH, new AddLoyaltyThresholdCommand());
        commands.put(IServletConstants.ADMIN_PREFIX + IServletConstants.GET_TAXI_PAGE_PATH, new GetTaxiCommand());
        commands.put(IServletConstants.ADMIN_PREFIX + IServletConstants.SEARCH_TAXI_PAGE_PATH, new SearchTaxiCommand());
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

    private void process(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        int role = 0;
        String accessPath;
        String path = req.getRequestURI().replaceAll(
                IServletConstants.ROOT_PATH, "");

        log.info("path:" + path);

        req.setAttribute(IServletConstants.ACTION_URI_ATTRIBUTE_KEY_WORD,
                req.getRequestURI());

        // do command
        Command command = commands.getOrDefault(path, defaultPage);
        String page = command.execute(req);

        log.debug("class:" + command.getClass().getName());

        accessPath = accessPathExtractor.extract(req).get();

        // do redirect if needed
        if ( page.contains(IServletConstants.REDIRECT_KEY_WORD) ) {
            resp.sendRedirect(req.getContextPath() + (page = accessPath +
                    page.replaceAll(IServletConstants.REDIRECT_KEY_WORD, "")));
            log.info("redirect:" + page);
        } else {
            req.getRequestDispatcher(page).forward(req, resp);
            log.info("forward:" + page);
        }

    }
}
