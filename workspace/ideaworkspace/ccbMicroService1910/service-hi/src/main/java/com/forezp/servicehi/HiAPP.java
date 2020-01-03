package com.forezp.servicehi;

import brave.sampler.Sampler;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 当client向server注册时，它会提供一些元数据，例如主机和端口，URL，主页等。
 * Eureka server 从每个client实例接收心跳消息。 如果心跳超时，则通常将该实例从注册server中删除
 */
//@EnableEurekaClient 表明自己是一个eurekaclient.
//@RestController注解相当于@ResponseBody ＋ @Controller合在一起的作用
@EnableEurekaClient
@SpringBootApplication
@RestController
public class HiAPP {

    public static void main(String[] args) {
        SpringApplication.run(HiAPP.class, args);
    }
    public static final Logger LOG = Logger.getLogger(HiAPP.class.getName());
    @Autowired
    RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
    @RequestMapping("/hi")
    public String callHome(){
        LOG.log(Level.INFO, "calling trace service-hi  ");
        return restTemplate.getForObject("http://localhost:8989/miya", String.class);
    }
    @RequestMapping("/info")
    public String info(){
        LOG.log(Level.INFO, "calling trace service-hi ");

        return "i'm service-hi";

    }

    @Bean
    public Sampler defaultSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }

}
