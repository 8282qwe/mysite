package mysite.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;

public class SiteInterceptor implements HandlerInterceptor {
    private final LocaleResolver localeResolver;

    public SiteInterceptor(LocaleResolver localeResolver) {
        this.localeResolver = localeResolver;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute("lang", localeResolver.resolveLocale(request).getLanguage());
//        request.setAttribute("sitevo", siteService.getSiteVo());

        return true;
    }
}
