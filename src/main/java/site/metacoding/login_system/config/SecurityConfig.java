package site.metacoding.login_system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import site.metacoding.login_system.handler.LoginFailureHandler;
import site.metacoding.login_system.handler.LoginSuccessHandler;

@EnableWebSecurity // 해당 파일로 시큐리티가 활성화
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // 패스워드 암호화 시 BCrypt 알고리즘 사용을 위해 IoC 컨테이너에 등록
    @Bean
    public BCryptPasswordEncoder encode() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // super.configure(http); => 기본 시큐리티 활성화
        http.csrf().disable(); // postman으로 테스트하기 위해 놔둔다.
        http.authorizeRequests()
                .antMatchers("/s/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/login-form")
                .loginProcessingUrl("/login")
                .failureHandler(new LoginFailureHandler())
                .successHandler(new LoginSuccessHandler())
                .and()
                .logout()
                .invalidateHttpSession(true).deleteCookies("JSESSIONID");
    }
}
