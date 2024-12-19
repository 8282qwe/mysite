package mysite.controller.action.guestbook;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mysite.controller.action.ActionServlet;
import mysite.dao.GuestbookDao;
import mysite.vo.GuestbookVo;

import java.io.IOException;

public class InsertAction implements ActionServlet.Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String contents = req.getParameter("contents");

        GuestbookVo vo = new GuestbookVo();
        vo.setName(name);
        vo.setPassword(password);
        vo.setContents(contents);
        new GuestbookDao().insert(vo);

        resp.sendRedirect(req.getContextPath()+"/guestbook");
    }
}
