package mysite.web;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpFilter;

import java.io.IOException;
import java.io.Serial;
import java.util.Optional;

public class EncodingFilter extends HttpFilter implements Filter {

    @Serial
    private static final long serialVersionUID = 2419439970543691107L;

    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = Optional.ofNullable(filterConfig.getInitParameter("encoding")).orElse("UTF-8");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // request 처리
        System.out.println("EncodingFilter.doFilter() called: request");
        servletRequest.setCharacterEncoding(encoding);

        filterChain.doFilter(servletRequest, servletResponse);

        // response 처리
        System.out.println("EncodingFilter.doFilter() called: response");
    }

    @Override
    public void destroy() {
    }
}
