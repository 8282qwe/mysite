package mysite.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mysite.dao.UserDao;
import mysite.vo.UserVo;

import java.io.IOException;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String action = req.getParameter("a");
        // /user?a=joinform
        if ("joinform".equalsIgnoreCase(action)) {
            req.getRequestDispatcher("/WEB-INF/views/user/joinform.jsp").forward(req, resp);
        } else if ("join".equals(action)) { // /user?a=join(POST)
            String name = req.getParameter("name");
            String password = req.getParameter("password");
            String email = req.getParameter("email");
            String gender = req.getParameter("gender");

            if (new UserDao().insert(new UserVo(name, email, password, gender)) == 1) {
                resp.sendRedirect(req.getContextPath()+"/user?a=joinsuccess");
            }
        } else if ("joinsuccess".equals(action)) { // /user?a=joinsuccess
            req.getRequestDispatcher("/WEB-INF/views/user/joinsuccess.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
