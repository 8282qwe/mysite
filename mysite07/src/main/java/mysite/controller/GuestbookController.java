package mysite.controller;

import lombok.AllArgsConstructor;
import mysite.service.GuestBookService;
import mysite.vo.GuestbookVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@AllArgsConstructor
@Controller
@RequestMapping("/guestbook")
public class GuestbookController {
    private final GuestBookService guestBookService;

    @RequestMapping({"","/"})
    public String guestbook(Model model) {

        model.addAttribute("list", guestBookService.getContentsList());
        return "guestbook/list";
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insertGuestbook(GuestbookVo guestbookVo) {
        guestBookService.addContents(guestbookVo);
        return "redirect:/guestbook";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteGuestbook(Model model,@PathVariable("id") String id) {
        model.addAttribute("id", id);
        return "guestbook/delete";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String deleteGuestbook(@PathVariable("id") String id, @RequestParam("password") String password) {
        guestBookService.deleteContents(id,password);
        return "redirect:/guestbook";
    }
}
