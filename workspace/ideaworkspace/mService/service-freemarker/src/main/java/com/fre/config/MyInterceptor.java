package com.fre.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description: 配置拦截器策略
 * @author: LinWeiQi
 */
@Configuration
public class MyInterceptor implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(myInterceptorConfig()).addPathPatterns("/**");
    }
    @Bean
    MyInterceptorConfig myInterceptorConfig(){
        return new MyInterceptorConfig();
    }
}
