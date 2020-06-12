package com.lwq.redis.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @Description: 服务器会为每一个用户 创建一个独立的HttpSession
 * 当用户第一次访问Servlet时,服务器端会给用户创建一个独立的Session
 * 并且生成一个SessionID,这个SessionID在响应浏览器的时候会被装进cookie中,从而被保存到浏览器中
 * 当用户再一次访问Servlet时,请求中会携带着cookie中的SessionID去访问
 * 服务器会根据这个SessionID去查看是否有对应的Session对象
 * 有就拿出来使用;没有就创建一个Session(相当于用户第一次访问)
 * <p>
 * 域的范围:
 * Context域 > Session域 > Request域
 * Session域 只要会话不结束就会存在 但是Session有默认的存活时间(30分钟)
 * @author: LinWeiQi
 */
@RestController
@RequestMapping("/springSession")
public class SpringSessionController {

    @Value("${server.port}")
    String port;

    @GetMapping("/set")
    public String set(HttpSession session) {

        session.setAttribute("name", "wayne");
        return port;
    }

    @GetMapping("/get")
    public String get(HttpSession session) {
        Object name = session.getAttribute("name");
        String str = String.valueOf(name);
        return str;
    }
}
