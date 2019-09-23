package com.atguigu.springboot03slf4logging.cotroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.HashMap;

@Controller
public class HelloController {

    @RequestMapping("/success")
    public String start(HashMap<String,Object> map){
        map.put("hello","<h1>你好</h1>");
        map.put("users", Arrays.asList("jack","tom"));
        return "success";
    }
}
