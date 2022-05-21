package site.metacoding.login_system.web;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import site.metacoding.login_system.service.UserService;
import site.metacoding.login_system.util.UtilValid;
import site.metacoding.login_system.web.dto.JoinReqDto;
import site.metacoding.login_system.web.dto.JusoRespDto;

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
    public String joinForm(String roadFullAddr, Model model) {
        model.addAttribute("data", roadFullAddr);
        return "user/joinForm";
    }

    @PostMapping("/join")
    public String join(@Valid JoinReqDto joinReqDto, BindingResult bindingResult) {

        UtilValid.에러처리(bindingResult);

        userService.회원가입(joinReqDto.toEntity());
        return "redirect:/login-form";
    }

    @GetMapping("/addrFind")
    public String addrFind() {
        return "jusoPopup";
    }

    @PostMapping("/addrFind")
    public String addrResp(JusoRespDto jusoRespDto, Model model) {
        model.addAttribute("data", jusoRespDto);
        return "jusoPopup";
    }

    @GetMapping("/api/user/username/same-check")
    public ResponseEntity<?> usermaeSameCheck(String username) {
        // true(같지 않다.)
        boolean isNotSame = userService.유저네임중복체크(username);
        return new ResponseEntity<>(isNotSame, HttpStatus.OK);

    }

}
