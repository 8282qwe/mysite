package mysite.controller;

import mysite.security.Auth;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Auth(role = "ADMIN")
@Controller
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping({"", "/", "/main"})
    public String index() {
        return "admin/main";
    }

    @RequestMapping("/guestbook")
    public String guestbook() {
        return "admin/guestbook";
    }

    @RequestMapping("/board")
    public String board() {
        return "admin/board";
    }

    @RequestMapping("/user")
    public String user() {
        return "admin/user";
    }
}
