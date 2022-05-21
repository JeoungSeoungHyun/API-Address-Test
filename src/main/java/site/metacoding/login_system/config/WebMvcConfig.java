package site.metacoding.login_system.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

public class WebMvcConfig implements WebMvcConfigurer {

    // 이미지 저장 폴더 경로(applcation.yml에서 환경에 따라 동적으로 설정)
    @Value("${file.path")
    private String uploadFoler;

    // 저장해 놓은 리소스들에 손쉽게 접근하기 위한 설정
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);

        registry
                // 스프링 내에서 접근 할 경로
                .addResourceHandler("/upload/**")
                // 실제 접근 경로
                .addResourceLocations("file:///" + uploadFoler)
                .setCachePeriod(60 * 60)
                .resourceChain(true)
                .addResolver(new PathResourceResolver());
    }

}
