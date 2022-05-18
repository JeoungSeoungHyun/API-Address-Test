package site.metacoding.api_address.web;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ch.qos.logback.classic.pattern.Util;
import lombok.RequiredArgsConstructor;
import site.metacoding.api_address.service.UserService;
import site.metacoding.api_address.util.UtilValid;
import site.metacoding.api_address.web.dto.JoinReqDto;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public String main() {
        return "main";
    }

    @GetMapping("/login-form")
    public String loginForm() {
        return "user/loginForm";
    }

    @GetMapping("/join-form")
    public String joinForm() {
        return "user/joinForm";
    }

    @PostMapping("/join")
    public String join(@Valid JoinReqDto joinReqDto, BindingResult bindingResult) {

        UtilValid.에러처리(bindingResult);

        userService.회원가입(joinReqDto.toEntity());
        return "redirect:/login-form";
    }
}
