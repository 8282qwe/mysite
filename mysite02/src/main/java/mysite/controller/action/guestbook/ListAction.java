package mysite.controller.action.guestbook;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mysite.controller.action.ActionServlet;
import mysite.dao.GuestbookDao;
import mysite.vo.GuestbookVo;

import java.io.IOException;
import java.util.List;

public class ListAction implements ActionServlet.Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<GuestbookVo> list = new GuestbookDao().findAll();
        req.setAttribute("list", list);
        req.getRequestDispatcher("/WEB-INF/views/guestbook/list.jsp").forward(req, resp);
    }
}
