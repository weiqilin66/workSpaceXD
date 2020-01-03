package com.config.qq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @Description: 描述
 * @author: LinWeiQi
 */
@SpringBootApplication
@EnableConfigServer  // 注解开启配置服务器的功能
public class ConfigAPP {
    public static void main(String[] args) {

        SpringApplication.run(ConfigAPP.class,args);
    }
}
