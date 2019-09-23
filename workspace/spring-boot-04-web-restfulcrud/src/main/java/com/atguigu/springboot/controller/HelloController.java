package com.atguigu.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;

@Controller
public class HelloController {

 /*   @RequestMapping("/login")
    public String login(){
        return "login";
    }*/
    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){

        return "helloWorld!";
    }

    @RequestMapping("/success")
    public String success(HashMap<String,Object> map){
        map.put("hello","你好");
        map.put("users", Arrays.asList("zhangsan","lisi","wangwu"));
        return "success";
    }
}
