package com.training.controller.servlet;


import com.training.controller.IServletConstants;
import com.training.controller.command.*;
import com.training.controller.command.admin.AddDiscountCommand;
import com.training.controller.command.admin.AddLoyaltyThresholdCommand;
import com.training.controller.command.admin.DeleteDiscountCommand;
import com.training.controller.command.admin.DeleteLoyaltyThresholdCommand;
import com.training.controller.command.user.ConfirmTaxiCommand;
import com.training.controller.command.user.GetTaxiCommand;
import com.training.controller.command.guest.LoginCommand;
import com.training.controller.command.user.SearchTaxiCommand;
import com.training.controller.command.guest.SignUpCommand;
import com.training.controller.command.user.LogoutCommand;
import com.training.controller.utill.impl.*;
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
    static private HashMap<String, Command> commands = Stream.of(
        new Object[][] {
            { "/home", new HomeCommand()},
            { "/login", new LoginCommand()},
        }
    ).collect(Collectors.toMap(key -> (String)key[0],
                               value -> (Command)value[1],
                               (k1, k2) -> k1,
                               HashMap::new));
    */

    @Override
    public void init(){
        Localization localization = new Localization();
        UserDataManager userDataManager = new UserDataManager();
        RequestDataManager requestDataManager = new RequestDataManager();
        Pagenizer pagenizer = new Pagenizer();
        UserExtractor userExtractor = new UserExtractor();
        DiscountExtractor discountExtractor = new DiscountExtractor();
        LoyaltyThresholdExtractor loyaltyThresholdExtractor =
                new LoyaltyThresholdExtractor();

        Authorization.init(userExtractor, userDataManager, localization);

        HomeCommand homeCommand = new HomeCommand(userDataManager);

        LoginCommand loginCommand = new LoginCommand(
                Authorization.getInstance());
        SignUpCommand signupCommand = new SignUpCommand(
                Authorization.getInstance());
        LogoutCommand logoutCommand = new LogoutCommand(
                Authorization.getInstance());

        SearchTaxiCommand searchTaxiCommand = new SearchTaxiCommand(
                requestDataManager, localization);
        GetTaxiCommand getTaxiCommand = new GetTaxiCommand(requestDataManager);
        ConfirmTaxiCommand confirmTaxiCommand = new ConfirmTaxiCommand(
                localization);

        DiscountsCommand discountsCommand = new DiscountsCommand(
                requestDataManager, pagenizer);
        DeleteDiscountCommand deleteDiscountCommand =
                new DeleteDiscountCommand();
        AddDiscountCommand addDiscountCommand = new AddDiscountCommand(
                localization, discountExtractor);

        LoyaltiesCommand loyaltiesCommand = new LoyaltiesCommand(
                requestDataManager, pagenizer);
        DeleteLoyaltyThresholdCommand deleteLoyaltyThresholdCommand =
                new DeleteLoyaltyThresholdCommand();
        AddLoyaltyThresholdCommand addLoyaltyThresholdCommand =
                new AddLoyaltyThresholdCommand(localization,
                                               loyaltyThresholdExtractor);

        // not a user (access level 0)
        commands.put(IServletConstants.GUEST_PREFIX +
                IServletConstants.HOME_PAGE_PATH, homeCommand);
        commands.put(IServletConstants.GUEST_PREFIX +
                IServletConstants.LOGIN_PAGE_PATH, loginCommand);
        commands.put(IServletConstants.GUEST_PREFIX +
                IServletConstants.SIGNUP_PAGE_PATH, signupCommand);
        commands.put(IServletConstants.GUEST_PREFIX +
                IServletConstants.DISCOUNTS_PAGE_PATH, discountsCommand);
        commands.put(IServletConstants.GUEST_PREFIX +
                IServletConstants.LOYALTIES_PAGE_PATH, loyaltiesCommand);

        // user (access level 1)
        commands.put(IServletConstants.USER_PREFIX +
                IServletConstants.HOME_PAGE_PATH, homeCommand);
        commands.put(IServletConstants.USER_PREFIX +
                IServletConstants.LOGOUT_PAGE_PATH, logoutCommand);
        commands.put(IServletConstants.USER_PREFIX +
                IServletConstants.DISCOUNTS_PAGE_PATH, discountsCommand);
        commands.put(IServletConstants.USER_PREFIX +
                IServletConstants.LOYALTIES_PAGE_PATH, loyaltiesCommand);
        commands.put(IServletConstants.USER_PREFIX +
                IServletConstants.GET_TAXI_PAGE_PATH, getTaxiCommand);
        commands.put(IServletConstants.USER_PREFIX +
                IServletConstants.SEARCH_TAXI_PAGE_PATH, searchTaxiCommand);
        commands.put(IServletConstants.USER_PREFIX +
                IServletConstants.CONFIRM_TAXI_PAGE_PATH, confirmTaxiCommand);

        // admin (access level 2)
        commands.put(IServletConstants.ADMIN_PREFIX +
                IServletConstants.HOME_PAGE_PATH, homeCommand);
        commands.put(IServletConstants.ADMIN_PREFIX +
                IServletConstants.LOGOUT_PAGE_PATH, logoutCommand);
        commands.put(IServletConstants.ADMIN_PREFIX +
                IServletConstants.DISCOUNTS_PAGE_PATH, discountsCommand);
        commands.put(IServletConstants.ADMIN_PREFIX +
                IServletConstants.DISCOUNTS_DELETE_PAGE_PATH,
                deleteDiscountCommand);
        commands.put(IServletConstants.ADMIN_PREFIX +
                IServletConstants.DISCOUNTS_ADD_PAGE_PATH, addDiscountCommand);
        commands.put(IServletConstants.ADMIN_PREFIX +
                IServletConstants.LOYALTIES_PAGE_PATH, loyaltiesCommand);
        commands.put(IServletConstants.ADMIN_PREFIX +
                IServletConstants.DELETE_LOYALTY_THRESHOLD_PAGE_PATH,
                deleteLoyaltyThresholdCommand);
        commands.put(IServletConstants.ADMIN_PREFIX +
                IServletConstants.ADD_LOYALTY_THRESHOLD_PAGE_PATH, addLoyaltyThresholdCommand);
        commands.put(IServletConstants.ADMIN_PREFIX +
                IServletConstants.GET_TAXI_PAGE_PATH, getTaxiCommand);
        commands.put(IServletConstants.ADMIN_PREFIX +
                IServletConstants.SEARCH_TAXI_PAGE_PATH, searchTaxiCommand);
        commands.put(IServletConstants.ADMIN_PREFIX +
                IServletConstants.CONFIRM_TAXI_PAGE_PATH, confirmTaxiCommand);
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
