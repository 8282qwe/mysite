package mysite.controller.api;

import mysite.service.UserService;
import mysite.vo.UserVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController("userApiController")
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // "{exist : false or true}"
    @GetMapping("/checkemail")
    public Object checkEmail(@RequestParam(value = "email", required = true, defaultValue = "") String email) {
        UserVo userVo = userService.getUser(email);
        return Map.of("exist", userVo != null);
    }
}
