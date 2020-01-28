package com.fre.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description: 修改静态资源访问路径
 * @author: LinWeiQi
 */
@Configuration
public class WebMvcConfig2 implements WebMvcConfigurer {
   /* @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/wayneStatic/");
    }*/
}
