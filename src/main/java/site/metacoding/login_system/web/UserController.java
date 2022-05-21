package site.metacoding.login_system.web;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.RequiredArgsConstructor;
import site.metacoding.login_system.config.auth.LoginUser;
import site.metacoding.login_system.domain.User;
import site.metacoding.login_system.handler.ex.CustomException;
import site.metacoding.login_system.service.UserService;
import site.metacoding.login_system.util.UtilValid;
import site.metacoding.login_system.web.dto.JusoRespDto;
import site.metacoding.login_system.web.dto.user.InfoUpdateReqDto;
import site.metacoding.login_system.web.dto.user.JoinReqDto;
import site.metacoding.login_system.web.dto.user.PasswordUpdateReqDto;
import site.metacoding.login_system.web.dto.user.UserDetailRespDto;

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
        return "user/joinForm";
    }

    @PostMapping("/join")
    public String join(@Valid JoinReqDto joinReqDto, BindingResult bindingResult) {

        UtilValid.페이지요청에러처리(bindingResult);
        userService.회원가입(joinReqDto.toEntity());

        return "redirect:/login-form";
    }

    // 팝업창 요청
    @GetMapping("/popup")
    public String addrFind() {
        return "jusoPopup";
    }

    // 주소API 응답 주소 매핑(Post)
    @PostMapping("/popup")
    public String addrResp(JusoRespDto jusoRespDto, Model model) {

        model.addAttribute("data", jusoRespDto);

        return "jusoPopup";
    }

    // 아이디 중복체크 요청 매핑
    @GetMapping("/api/user/username/same-check")
    public ResponseEntity<?> usermaeSameCheck(String username) {
        // true(같지 않다.)
        boolean isNotSame = userService.유저네임중복체크(username);
        return new ResponseEntity<>(isNotSame, HttpStatus.OK);
    }

    // 유저정보 요청 매핑
    @GetMapping("/s/user/{userId}")
    public String userDetail(@PathVariable Integer userId, @AuthenticationPrincipal LoginUser loginUser, Model model) {
        User principal = (User) loginUser.getUser();

        UserDetailRespDto updateRespDto = userService.유저정보찾기(userId, principal);
        model.addAttribute("data", updateRespDto);

        return "user/userDetail";
    }

    @GetMapping("/s/user/{userId}/info-update-form")
    public String infoUpdateForm(@PathVariable Integer userId, @AuthenticationPrincipal LoginUser loginUser,
            Model model) {
        User principal = (User) loginUser.getUser();

        UserDetailRespDto updateRespDto = userService.유저정보찾기(userId, principal);
        model.addAttribute("data", updateRespDto);

        return "user/infoUpdateForm";
    }

    @PutMapping("/s/api/user/{userId}/info")
    public ResponseEntity<?> infoUpdate(@PathVariable Integer userId, @AuthenticationPrincipal LoginUser loginUser,
            @Valid @RequestBody InfoUpdateReqDto infoUpdateReqDto, BindingResult bindingResult) {
        User principal = (User) loginUser.getUser();
        boolean result = false;

        UtilValid.데이터요청에러처리(bindingResult);
        result = userService.회원정보수정(userId, principal, infoUpdateReqDto);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/s/user/{userId}/password-update-form")
    public String passwordUpdateForm(@PathVariable Integer userId, @AuthenticationPrincipal LoginUser loginUser,
            Model model) {
        User principal = (User) loginUser.getUser();
        // 권한확인
        if (userId != principal.getId()) {
            throw new CustomException("권한이 없습니다");
        }
        return "user/passwordUpdateForm";
    }

    @PutMapping("/s/api/user/{userId}/password")
    public ResponseEntity<?> passwordUpdate(@PathVariable Integer userId, @AuthenticationPrincipal LoginUser loginUser,
            @Valid @RequestBody PasswordUpdateReqDto passwordUpdateReqDto, BindingResult bindingResult) {
        User principal = (User) loginUser.getUser();
        boolean result = false;

        UtilValid.데이터요청에러처리(bindingResult);
        result = userService.회원정보수정(userId, principal, passwordUpdateReqDto);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
