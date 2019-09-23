package com.atguigu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBootApplication标注为主程序
 */
@SpringBootApplication
public class HelloWorldMainApp {
    public static void main(String[] args) {

        SpringApplication.run(HelloWorldMainApp.class,args);
    }
}
