package site.metacoding.api_address.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/")
    public String main() {
        return "main";
    }

    @GetMapping("/login-form")
    public String loginForm() {
        return "user/loginForm";
    }
}
