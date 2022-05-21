package site.metacoding.login_system.web;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import site.metacoding.login_system.web.dto.JusoRespDto;

// @Controller
public class TestJusoController {

    // 주소api 테스트 컨트롤러

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
