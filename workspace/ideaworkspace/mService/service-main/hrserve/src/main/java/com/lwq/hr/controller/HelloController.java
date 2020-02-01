package com.lwq.hr.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @author: LinWeiQi
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello(){

        return "hello!";
    }
    @GetMapping("/system/cfg")
    public String h1(){

        return "h1";
    }

}
