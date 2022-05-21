package site.metacoding.login_system.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${file.path")
    private String uploadFoler;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);

        registry
                .addResourceHandler("/upload/**")
                .addResourceLocations("file:///" + uploadFoler)
                .setCachePeriod(60 * 60)
                .resourceChain(true)
                .addResolver(new PathResourceResolver());
    }

}
