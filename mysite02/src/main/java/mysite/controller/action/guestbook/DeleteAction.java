package mysite.controller.action.guestbook;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mysite.controller.action.ActionServlet;
import mysite.dao.GuestbookDao;

import java.io.IOException;

public class DeleteAction implements ActionServlet.Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String password = req.getParameter("password");

        new GuestbookDao().deleteByIdAndPassword(Long.parseLong(id), password);

        resp.sendRedirect(req.getContextPath()+"/guestbook");
    }
}
