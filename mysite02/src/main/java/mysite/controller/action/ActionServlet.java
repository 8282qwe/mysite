package mysite.controller.action;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

public abstract class ActionServlet extends HttpServlet {
    // factoryMethod
    protected abstract Action getAction(String actionName);

    // operation
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Optional<String> optionalActionName = Optional.ofNullable(req.getParameter("a"));

        Action action = getAction(optionalActionName.orElse(""));
        action.execute(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public interface Action {
        void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
    }
}
