package org.wayne.consumer.controller.resttemplate;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.wayne.commons.User;

import javax.annotation.Resource;
import javax.ws.rs.core.MultivaluedMap;
import java.net.URI;
import java.util.HashMap;

/**
 * @Description: post和get方式类似
 * 多出一个 postForLocation
 * 执行完post请求后进行重定向,例如注册成功后重定向到登录页
 * 重定向接口 成功响应码302
 * 不能为resController (return的值直接返回)
 * 分辨key-value传参和Json传参区别
 * @author: LinWeiQi
 */
@RestController
@RequestMapping("/restTemplate")
public class RestTemplatePostController {

    @Resource// Qualifier不写默认找的变量名restTemplate
    @Qualifier("restTemplate")//@bean注解的方法的方法名 此处记录@bean用法非必须
    RestTemplate restTemplate;

    @Resource
    @Qualifier("restTemplateOne")//没有负载均衡的restTemplate
    RestTemplate restTemplateOne;

    @RequestMapping("user1")
    public User getUser() {//key value传参
        LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("username", "wayne");
        return restTemplate.postForObject("http://provide/user1", map, User.class);
    }

    @RequestMapping("user2")
    public User getUser2() {//json传参
        User user = new User();
        user.setUsername("wayne");
        return restTemplate.postForObject("http://provide/user2", user, User.class);
    }

    @GetMapping("/register")
    public String register() {
        //访问的必须是重定向接口
        LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("username", "wayne");
        URI uri = restTemplate.postForLocation("http://provide/register", map);
        System.out.println(uri);
        return restTemplate.getForObject(uri,String.class);
    }
}
