package site.metacoding.api_address.web;

import javax.validation.Valid;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import site.metacoding.api_address.service.UserService;
import site.metacoding.api_address.util.UtilValid;
import site.metacoding.api_address.web.dto.JoinReqDto;
import site.metacoding.api_address.web.dto.JusoRespDto;

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

    @GetMapping("/test")
    public String testAddr() {
        return "jusoPopupTest";
    }

    @PostMapping("/test")
    public String testAddrResp(JusoRespDto jusoRespDto, Model model) {
        model.addAttribute("data", jusoRespDto);
        return "jusoPopupTest";
    }

}
