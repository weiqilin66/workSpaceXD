package com.config.cilent;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 描述
 * @author: LinWeiQi
 */
@SpringBootApplication
@RestController
public class ClientAPP {
    public static void main(String[] args) {
        SpringApplication.run(ClientAPP.class,args);
    }

    @Value("${word}")
    String word;
    @GetMapping("/word")
    public String getWord(){

        return word;
    }
}
