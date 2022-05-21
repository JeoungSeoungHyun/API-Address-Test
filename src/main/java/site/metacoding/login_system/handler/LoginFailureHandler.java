package site.metacoding.login_system.handler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import site.metacoding.login_system.handler.ex.CustomException;
import site.metacoding.login_system.util.Script;

public class LoginFailureHandler implements AuthenticationFailureHandler {

    // 로그인이 실패했을 경우 응답해주기 위한 핸들러
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
        // 한글 출력 위해 utf-8 인코딩
        response.setContentType("text/html; charset=UTF-8");
        // response를 통해 스트림으로 응답
        try {
            PrintWriter out = response.getWriter();
            out.print(Script.back("아이디 혹은 패스워드가 잘못되었습니다."));
            out.flush();
            out.close();
        } catch (Exception e) {
            throw new CustomException("문제가 발생하였습니다.");
        }
    }

}
