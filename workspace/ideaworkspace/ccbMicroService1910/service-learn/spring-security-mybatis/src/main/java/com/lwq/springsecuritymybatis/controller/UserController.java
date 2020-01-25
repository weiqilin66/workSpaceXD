package com.lwq.springsecuritymybatis.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author LinWeiQi
 * @since 2020-01-19
 */
@RestController
public class UserController {

    @GetMapping("/hello")
    public String hello(){

        return "hello security";
    }
    @GetMapping("/admin/get")
    public String admin(){

        return "admin";
    }
    @GetMapping("/dba/get")
    public String dba(){

        return "dba";
    }

    @GetMapping("/user/get")
    public String user(){

        return "user";
    }
}
