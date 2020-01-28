package com.druid;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description:
 * @author: LinWeiQi
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.druid.mapper")
public class MybatisDruidApp {
    public static void main(String[] args) {
        SpringApplication.run(MybatisDruidApp.class, args);
    }
}
