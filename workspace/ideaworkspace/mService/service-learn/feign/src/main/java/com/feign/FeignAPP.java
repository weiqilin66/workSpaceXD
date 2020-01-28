package com.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Description: Feign是自带断路器(D版默认关闭需要配置开启) 集成ribbon
 * @author: LinWeiQi
 */
@SpringBootApplication(scanBasePackages = "com.feign")
@EnableDiscoveryClient
@EnableFeignClients //注解开启Feign的功能
public class FeignAPP {
    public static void main(String[] args) {
        SpringApplication.run(FeignAPP.class);
    }
}
