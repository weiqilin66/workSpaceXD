package com.fre.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.text.SimpleDateFormat;

/**
 * @Description: 修改配置springboot自动配置
 * @author: LinWeiQi
 */
@Configuration
public class WebMvcConfig {
    /**
     * @return org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
     * @TODO 修改jackson 配置日期格式,可以注入objectMapper实现
     * @date 2020/1/16
     */
    @Bean
    MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy/MM/dd"));
        converter.setObjectMapper(objectMapper);

        return converter;
    }


}
