package mysite.controller;

import mysite.vo.UserVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
//    private final SiteService siteService;

//    public MainController(SiteService siteService) {
//        this.siteService = siteService;
//    }

    @RequestMapping({"/", "/main"})
    public String index(Model model) {
//        model.addAttribute("sitevo", siteService.getSite());
        return "main/index";
    }

    @ResponseBody
    @RequestMapping("/msg01")
    public String message01() {
        return "Hello World";
    }

    @ResponseBody
    @RequestMapping("/msg02")
    public String message02() {
        return "안녕 세상";
    }

    @ResponseBody
    @RequestMapping("/msg03")
    public UserVo message03() {
        UserVo userVo = new UserVo();
        userVo.setId(10L);
        userVo.setName("둘리");
        userVo.setEmail("dooly@gmail.com");
        return userVo;
    }
}
