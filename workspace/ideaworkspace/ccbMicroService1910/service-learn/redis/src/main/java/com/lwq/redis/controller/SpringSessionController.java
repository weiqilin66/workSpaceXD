package com.lwq.redis.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @Description:
 * @author: LinWeiQi
 */
@RestController
@RequestMapping("springSession")
public class SpringSessionController {

    @Value("${server.port}")
    String port;

    @GetMapping("/set")
    public String set(HttpSession session){

        session.setAttribute("name","wayne");
        return port;
    }

    @GetMapping("/get")
    public String get(HttpSession session){
        Object name = session.getAttribute("name");
        String str = String.valueOf(name);
        return str;
    }
}
