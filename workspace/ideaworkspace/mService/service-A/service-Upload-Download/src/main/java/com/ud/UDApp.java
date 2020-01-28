package com.ud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Description: 上传下载
 * @author: LinWeiQi
 */
@EnableEurekaClient
@SpringBootApplication
public class UDApp {
    public static void main(String[] args) {
        SpringApplication.run(UDApp.class,args);
    }
}
