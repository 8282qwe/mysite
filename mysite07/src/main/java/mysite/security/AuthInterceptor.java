package mysite.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mysite.vo.UserVo;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1. Handler 종류 확인 (handler 가 DefaultServletHttpRequestHandler 일 경우, 해당 인터셉터를 그냥 통과시킴)
        // (정적자원,/assets/**, mapping 이 안되어있는 경우)
        if (!(handler instanceof HandlerMethod)) return true;

        // 2. casting
        HandlerMethod handlerMethod = (HandlerMethod) handler;

        // 3. Hanlder에서 @Auth 가져오기
        Auth auth = handlerMethod.getMethodAnnotation(Auth.class);

        // 4. Handler Method에서 에서 @Auth가 없으면 클래스(타입)에 @Auth
        if (auth == null) {
            auth = handlerMethod.getBeanType().getAnnotation(Auth.class);
        }

        // 5. @Auth 가 없으면...
        if (auth == null) return true;

        String role = auth.role();

        // 6. @Auth 가 붙어 있기 때문에 인증(Authentication) 여부 확인
        HttpSession session = request.getSession();
        UserVo authUser = (UserVo) session.getAttribute("authUser");

        if (authUser == null) {
            response.sendRedirect(request.getContextPath() + "/user/login");
            return false;
        }

        // 7. @Auth 가 붙어 있고 인증도 되어있는 경우
        if (!role.equals(authUser.getRole())) {
            if (authUser.getRole().equals("ADMIN")) return true;

            response.sendRedirect(request.getContextPath());
            return false;
        }

        return true;
    }
}
