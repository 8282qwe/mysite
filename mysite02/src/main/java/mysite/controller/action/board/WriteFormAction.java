package mysite.controller.action.board;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mysite.controller.action.ActionServlet;
import mysite.dao.BoardDao;

import java.io.IOException;

public class WriteFormAction implements ActionServlet.Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("authUser") == null) {
            resp.sendRedirect(req.getContextPath() + "/user?a=loginform");
        }
        else {
            if (req.getParameter("id") != null) {
                Long id = Long.valueOf(req.getParameter("id"));
                req.setAttribute("board",new BoardDao().findById(id));
            }
            req.getRequestDispatcher("WEB-INF/views/board/write.jsp").forward(req, resp);
        }
    }
}
