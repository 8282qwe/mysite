package mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
