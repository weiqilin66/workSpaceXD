package com.fre.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description: 配置CORS跨域问题
 * @author: LinWeiQi
 */
@Configuration
public class MyCORS implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("http://localhost:8080").allowedHeaders("*")
                .allowedMethods("*")
                .maxAge(30*1000);// 探测请求
    }
}
