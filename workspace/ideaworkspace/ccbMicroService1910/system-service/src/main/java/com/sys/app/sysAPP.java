package com.sys.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Description: 系统服务
 * @author: LinWeiQi
 */
@SpringBootApplication(scanBasePackages = "com.sys")
@EnableEurekaClient
public class sysAPP {
    public static void main(String[] args) {
        SpringApplication.run(sysAPP.class, args);
    }
}
