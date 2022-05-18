package site.metacoding.api_address.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import site.metacoding.api_address.config.auth.LoginUser;
import site.metacoding.api_address.domain.User;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    // Authentication에 저장된 userDetails가 아닌 session에서 바로 user의 정보를 얻기 위해
    // 로그인 성공시 핸들러에서 session의 principal이라는 key에 user의 정보를 저장해놓는다.
    // mustache에서 세션에 접근하여 사용
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        User principal = loginUser.getUser();

        HttpSession session = request.getSession();
        session.setAttribute("principal", principal);
        System.out.println("로그인 잘 됨 : " + session.getAttribute("principal"));
        response.sendRedirect("/");
    }

}
