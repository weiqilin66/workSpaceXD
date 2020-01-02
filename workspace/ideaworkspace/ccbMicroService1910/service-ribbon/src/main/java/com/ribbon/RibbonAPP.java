package com.ribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @Description: 某个服务的多实例间实现负载均衡,属于服务内部的负载均衡
 * @Impl: 通过调用restTemplate.getForObject(“http://SERVICE-HI/hi?name=”+name,String.class)
 * @author: LinWeiQi
 */
@SpringBootApplication
@EnableEurekaClient  // 如果是其他注册中心使用@EnableDiscoveryClient
@EnableHystrix // 开启熔断功能
public class RibbonAPP {
    public static void main(String[] args) {
        SpringApplication.run(RibbonAPP.class,args);
    }

    // @Bean向程序的ioc注入一个bean: restTemplate;并通过@LoadBalanced注解表明这个restRemplate开启负载均衡的功能
    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
