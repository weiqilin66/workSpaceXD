package com.lwq.hr.controller;

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
}
