package mysite.controller;

import mysite.security.Auth;
import mysite.service.FileUploadService;
import mysite.service.SiteService;
import mysite.vo.SiteVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Auth(role = "ADMIN")
@Controller
@RequestMapping("/admin")
public class AdminController {
    private final FileUploadService fileUploadService;
    private final SiteService siteService;

    public AdminController(FileUploadService fileUploadService, SiteService siteService) {
        this.fileUploadService = fileUploadService;
        this.siteService = siteService;
    }

    @RequestMapping({"", "/", "/main"})
    public String index(Model model) {
        model.addAttribute("sitevo", siteService.getSite());
        return "admin/main";
    }

    @RequestMapping("/main/update")
    public String updateMain(@RequestParam("file") MultipartFile file, SiteVo siteVo) {
        System.out.println(siteVo);
        siteService.updateSite(siteVo, Optional.ofNullable(fileUploadService.restore(file)).orElse(""));

        return "redirect:/admin";
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
