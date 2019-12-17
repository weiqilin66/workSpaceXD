package com.lwq.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 当client向server注册时，它会提供一些元数据，例如主机和端口，URL，主页等。
 * Eureka server 从每个client实例接收心跳消息。 如果心跳超时，则通常将该实例从注册server中删除
 */
//@EnableEurekaClient 表明自己是一个eurekaclient.
//@RestController注解相当于@ResponseBody ＋ @Controller合在一起的作用
@EnableEurekaClient
@SpringBootApplication
@RestController
public class TeatConnectionApp {
    public static void main(String[] args) {
        SpringApplication.run(TeatConnectionApp.class, args);
    }
    //@Value读取application.properties 或者yml里面的属性值复制给注解的属性
    @Value("${server.port}")
    String port;

    @RequestMapping("/hi")
    public String home(@RequestParam(value = "name", defaultValue = "wayne") String name) {
        return "hi " + name + " ,i am from port:" + port;
    }
}
