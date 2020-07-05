package org.wayne.provide.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wayne.commons.User;

import javax.ws.rs.POST;

/**
 * @Description:
 * @author: LinWeiQi
 */
@RestController
public class HelloProController {

    @Value("${server.port}")
    String port;

    @RequestMapping("/hello")
    public String hello(){
        return "hello, i come from "+port;
    }

    @RequestMapping("/hello2")
    public String hello2(String name){

        return name;
    }
    @RequestMapping("/hello3")
    public String hello2(String name,int age){
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(",").append(age);
        return sb.toString();
    }

    @PostMapping("/user1")//key value方式
    public User post1(User user){
        return user;
    }
    @PostMapping("/user2")//JSON方式传参
    public User post(@RequestBody User user){
        return user;
    }

}
