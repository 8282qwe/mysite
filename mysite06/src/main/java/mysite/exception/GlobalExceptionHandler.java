package mysite.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mysite.dto.JsonResult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Log LOG = LogFactory.getLog(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public void handler(Exception e, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 1. 로깅 (logging)
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        LOG.error(sw.toString());

        // 2. 요청 구분
        //      json 요청 : request header의 accept: application/json (O)
        //      html 요청 : request header의 accept: application/json (X)
        String accept = request.getHeader("accept");

        if (accept.matches(".*application/json.*")) {
            // 3. JSON 응답
            JsonResult jsonResult = JsonResult.fail(sw.toString());
            String jsonString = new ObjectMapper().writeValueAsString(jsonResult);

            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getOutputStream().write(jsonString.getBytes(StandardCharsets.UTF_8));
        } else {
            // 4. 사과 페이지 (종료)
//            response.setContentType("text/html;charset=utf-8");
//            request.setAttribute("errors", sw.toString());
//            request.getRequestDispatcher("/WEB-INF/views/errors/exception.jsp").forward(request, response);
//
//            return;
            if (e instanceof NoHandlerFoundException || e instanceof NoResourceFoundException) {
                request.getRequestDispatcher("/errors/404").forward(request, response);
            }
            else {
                request.setAttribute("errors", sw.toString());
                request.getRequestDispatcher("/errors/500").forward(request, response);
            }
        }
    }
}
