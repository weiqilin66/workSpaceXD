package com.lwq.eurekaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer //服务注册中心
public class EurekaService {

    public static void main(String[] args) {
        SpringApplication.run(EurekaService.class, args);
    }

}