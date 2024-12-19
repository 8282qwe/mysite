package mysite.controller;

import jakarta.servlet.annotation.WebServlet;
import mysite.controller.action.ActionServlet;
import mysite.controller.action.user.LogoutAction;
import mysite.controller.action.user.UpdateFormAction;
import mysite.controller.action.main.MainAction;
import mysite.controller.action.user.*;

import java.io.Serial;
import java.util.Map;

@WebServlet("/user")
public class UserServlet extends ActionServlet {

    @Serial
    private static final long serialVersionUID = 7234473671816624065L;

    private Map<String, Action> mapAction = Map.of("joinform", new JoinFormAction(),
            "join", new JoinAction(),
            "joinsuccess", new JoinSuccessAction(),
            "loginform", new LoginFormAction(),
            "login", new LoginAction(),
            "logout", new LogoutAction(),
            "updateform", new UpdateFormAction(),
            "update", new UpdateAction());

    @Override
    protected Action getAction(String actionName) {
        return mapAction.getOrDefault(actionName, new MainAction());
    }
}
