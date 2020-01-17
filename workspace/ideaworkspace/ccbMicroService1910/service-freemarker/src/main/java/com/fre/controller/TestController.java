package com.fre.controller;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 描述
 * @author: LinWeiQi
 */
@RestController
@Data
@ConfigurationProperties(prefix = "wayne.jdbc")
public class TestController {
    private String driver ;

    @GetMapping("/driver")
    public String t1(){
        String resStr = driver;
        System.out.println("driver: "+driver);
        return resStr;
    }
}
