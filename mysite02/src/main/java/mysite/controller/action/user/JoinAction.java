package mysite.controller.action.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mysite.controller.action.ActionServlet;
import mysite.dao.UserDao;
import mysite.vo.UserVo;

import java.io.IOException;

public class JoinAction implements ActionServlet.Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String gender = req.getParameter("gender");

        if (new UserDao().insert(new UserVo(name, email, password, gender)) == 1) {
            resp.sendRedirect(req.getContextPath() + "/user?a=joinsuccess");
        } else resp.sendRedirect(req.getContextPath() + "/user?a=joinform");
    }
}
