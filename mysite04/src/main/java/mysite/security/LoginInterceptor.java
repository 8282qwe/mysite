package mysite.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mysite.service.UserService;
import mysite.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserVo authUser = userService.getUser(email, password);

        if (authUser == null) {
            request.setAttribute("email", email);
            request.setAttribute("result", "fail");
            request.getRequestDispatcher("/WEB-INF/views/user/login.jsp").forward(request, response);

            return false;
        }

        // 로그인 처리
        HttpSession session = request.getSession();
        session.setAttribute("authUser", authUser);

        response.sendRedirect(request.getContextPath());

        return false;
    }
}
