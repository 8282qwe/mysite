package mysite.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import mysite.service.UserService;
import mysite.vo.UserVo;
import org.springframework.web.servlet.HandlerInterceptor;

@AllArgsConstructor
public class LoginInterceptor implements HandlerInterceptor {

    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserVo authUser = userService.getUser(email, password);

        if (authUser == null) {
            request.setAttribute("email", email);
            request.setAttribute("result", "fail");
            request.getRequestDispatcher("/user/login").forward(request, response);

            return false;
        }

        // 로그인 처리
        HttpSession session = request.getSession();
        session.setAttribute("authUser", authUser);

        response.sendRedirect(request.getContextPath());

        return false;
    }
}
