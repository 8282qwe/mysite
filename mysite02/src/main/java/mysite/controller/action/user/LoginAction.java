package mysite.controller.action.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mysite.controller.action.ActionServlet;
import mysite.dao.UserDao;
import mysite.vo.UserVo;

import java.io.IOException;
import java.util.Optional;

public class LoginAction implements ActionServlet.Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = Optional.ofNullable(req.getParameter("email")).orElse("");
        String password = Optional.ofNullable(req.getParameter("password")).orElse("");

        UserVo vo = new UserDao().findByEmailAndPassword(email, password);

        // 로그인 실패
        if (vo == null) {
            req.setAttribute("result","fail");
            req.setAttribute("email", email);
            req.getRequestDispatcher("/WEB-INF/views/user/loginform.jsp").forward(req, resp);
            return;
        }

        //로그인 처리
        HttpSession session = req.getSession(true);
        session.setAttribute("authUser", vo);

        resp.sendRedirect(req.getContextPath());
    }
}
