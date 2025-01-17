package mysite.security;

import jakarta.servlet.http.HttpServletRequest;
import mysite.vo.UserVo;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class AuthUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        AuthUser authUser = parameter.getParameterAnnotation(AuthUser.class);

        // @AuthUser 가 붙어있지 않으면
        if (authUser == null) return false;

        // 파라미터 타입이 UserVo가 아니면
        if (!parameter.getParameterType().equals(UserVo.class)) {
            return false;
        }

        return true;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        if (!supportsParameter(parameter)) {
            return WebArgumentResolver.UNRESOLVED;
        }
        HttpServletRequest req = (HttpServletRequest) webRequest.getNativeRequest();
        return req.getSession().getAttribute("authUser");
    }
}
