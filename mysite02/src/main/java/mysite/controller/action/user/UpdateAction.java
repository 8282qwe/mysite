package mysite.controller.action.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mysite.controller.action.ActionServlet;
import mysite.dao.UserDao;
import mysite.vo.UserVo;

import java.io.IOException;

public class UpdateAction implements ActionServlet.Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        // Access Control
        if (session == null) {
            resp.sendRedirect(req.getContextPath());
            return;
        }

        UserVo authUser = (UserVo) session.getAttribute("authUser");
        if (authUser == null) {
            resp.sendRedirect(req.getContextPath());
            return;
        }

        authUser.setId(Long.parseLong(req.getParameter("id")));
        authUser.setName(req.getParameter("name"));
        authUser.setPassword(req.getParameter("password"));
        authUser.setEmail(req.getParameter("email"));
        authUser.setGender(req.getParameter("gender"));

        new UserDao().updateById(authUser);

        resp.sendRedirect(req.getContextPath()+"/user?a=updateform");
    }
}
