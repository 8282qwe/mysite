package mysite.controller.action.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mysite.controller.action.ActionServlet;

import java.io.IOException;

public class LogoutAction implements ActionServlet.Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session != null) {
            // 로그아웃 방법1
            session.removeAttribute("authUser");
            session.invalidate();
        }
        resp.sendRedirect(req.getContextPath());
    }
}
