package mysite.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import mysite.security.UserDetailsImpl;
import mysite.service.UserService;
import mysite.vo.UserVo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@AllArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @RequestMapping(value = "/join", method = RequestMethod.GET)
    public String join(@ModelAttribute UserVo userVo) {
        return "user/join";
    }

    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public String joinUser(@ModelAttribute @Valid UserVo userVo, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAllAttributes(result.getModel());
            return "user/join";
        }

        userService.joinUser(userVo);
        return "redirect:/user/joinsuccess";
    }

    @RequestMapping("/joinsuccess")
    public String joinSuccess() {
        return "user/joinsuccess";
    }

    @RequestMapping(value = "/login")
    public String login(@ModelAttribute UserVo userVo) {
        return "user/login";
    }


    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(@AuthenticationPrincipal UserDetailsImpl authUser, Model model) {
        // 4. @AuthenticationPrincipal 사용

        UserVo userVo = userService.getUser(authUser.getId());

        model.addAttribute("vo", userVo);
        return "user/update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(HttpSession session, UserVo userVo, Authentication authentication) {
        // 1. HttpSession을 사용하는 방법
//        SecurityContext sc = (SecurityContext) session.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
//        Authentication auth = sc.getAuthentication();
//        UserVo authUser = (UserVo) auth.getPrincipal();

        // 2. SecurityContextHolder (Spring Security ThreadLocal Helper)
//        UserVo authUser = (UserVo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // 3. Authentication 사용
        UserVo authUser = (UserVo) authentication.getPrincipal();

        userVo.setId(authUser.getId());
        userService.updateUser(userVo);

        authUser.setName(userVo.getName());

        return "redirect:/user/update";
    }

}
