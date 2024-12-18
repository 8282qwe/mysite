package mysite.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mysite.dao.GuestbookDao;
import mysite.vo.GuestbookVo;

import java.io.IOException;
import java.util.List;

@WebServlet("/guestbook")
public class GuestbookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String action = req.getParameter("a");
        if ("insert".equalsIgnoreCase(action)) {
            String name = req.getParameter("name");
            String password = req.getParameter("password");
            String contents = req.getParameter("contents");

            GuestbookVo vo = new GuestbookVo();
            vo.setName(name);
            vo.setPassword(password);
            vo.setContents(contents);
            new GuestbookDao().insert(vo);

            res.sendRedirect(req.getContextPath()+"/guestbook");
        } else if ("deleteform".equalsIgnoreCase(action)) {
            req.getRequestDispatcher("/WEB-INF/views/guestbook/deleteform.jsp").forward(req, res);
        } else if ("delete".equalsIgnoreCase(action)) {
            String id = req.getParameter("id");
            String password = req.getParameter("password");

            new GuestbookDao().deleteByIdAndPassword(Long.parseLong(id), password);

            System.out.println(req.getContextPath()+"/guestbook");

            res.sendRedirect(req.getContextPath()+"/guestbook");
        }
        else {
            List<GuestbookVo> list = new GuestbookDao().findAll();
            req.setAttribute("list", list);
            req.getRequestDispatcher("/WEB-INF/views/guestbook/list.jsp").forward(req, res);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }
}