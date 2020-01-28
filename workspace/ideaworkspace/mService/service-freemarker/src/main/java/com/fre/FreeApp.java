package com.fre;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Description: 系统服务
 * @author: LinWeiQi
 */
// @SpringBootApplication(scanBasePackages = "com.fre")
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.fre.mapper")
public class FreeApp {
    public static void main(String[] args) {
        SpringApplication.run(FreeApp.class, args);
    }
}
