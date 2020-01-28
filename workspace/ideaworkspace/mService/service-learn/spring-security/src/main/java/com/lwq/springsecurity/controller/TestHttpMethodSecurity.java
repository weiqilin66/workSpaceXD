package com.lwq.springsecurity.controller;

import com.lwq.springsecurity.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @author: LinWeiQi
 */
@RestController
public class TestHttpMethodSecurity {
    // 有无角色都能访问的接口
    @GetMapping("/hello")
    public String hello(){
        String resStr = "hello security!";
        return resStr;
    }
    // SecurityConfig配置后只有admin角色才能访问
    @GetMapping("/admin/hello")
    public String admin(){
        String resStr = "";
        resStr = "hello admin";
        return resStr;        
    }
    // SecurityConfig配置后至少有角色才能访问
    @RequestMapping("/user/hello")
    public String user(){
        String resStr = "";
        resStr = "hello user";
        return resStr;
    }

    @Autowired
    SecurityService securityService;
    
    @GetMapping("/get1")
    public String get1(){
       
        return securityService.admin();
    }

    @GetMapping("/get2")
    public String get2(){

        return securityService.user();
    }
}
