package com.atguigu.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {
    @Value("${persion.name}")
    private String name;
    @RequestMapping("/hello")
    public String xx(){
        return "world!"+name;
    }
}
