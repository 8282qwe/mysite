package mysite.controller.action.board;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mysite.controller.action.ActionServlet;
import mysite.dao.BoardDao;
import mysite.vo.UserVo;

import java.io.IOException;

public class DeleteAction implements ActionServlet.Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        if (req.getSession().getAttribute("authUser") == null) {
            resp.sendRedirect(req.getContextPath() + "/user?a=loginform");
            return;
        }

        UserVo user = (UserVo) req.getSession().getAttribute("authUser");

        new BoardDao().deleteBoardById(new BoardDao().findById(Long.parseLong(id)), user.getId());

        resp.sendRedirect(req.getContextPath() + "/board");
    }
}
