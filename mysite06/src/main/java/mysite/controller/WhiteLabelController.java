package mysite.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class WhiteLabelController implements ErrorController {

    @RequestMapping("/404")
    public String error404() {
        return "error/404";
    }

    @RequestMapping("/500")
    public String error500() {
        return "error/500";
    }

    @RequestMapping("/403")
    public String error403() {
        return "error/403";
    }

    @RequestMapping("/unknown")
    public String errorUnknown() {
        return "error/unknown";
    }

    @RequestMapping("")
    public String handlerError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "errors/404";
            }
            else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "errors/500";
            }
            else if (statusCode == HttpStatus.FORBIDDEN.value()) {
                return "errors/403";
            }

        }
        return "error/unknown";
    }
}
