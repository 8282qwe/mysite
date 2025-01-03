package mysite.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.PrintWriter;
import java.io.StringWriter;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Log LOG = LogFactory.getLog(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public String handler(Exception e, Model model) {
        // 1. 로깅 (logging)
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        LOG.error(sw.toString());

        // 2. 사과 페이지 (종료)
        model.addAttribute("errors", sw.toString());
        return "errors/exception";
    }
}
