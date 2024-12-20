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

        UserVo vo = new UserVo();
        vo.setId(authUser.getId());
        vo.setName(req.getParameter("name"));
        vo.setPassword(req.getParameter("password"));
        vo.setGender(req.getParameter("gender"));


        if (new UserDao().updateById(vo)) {
            authUser.setName(vo.getName());
        }

        resp.sendRedirect(req.getContextPath()+"/user?a=updateform");
    }
}
