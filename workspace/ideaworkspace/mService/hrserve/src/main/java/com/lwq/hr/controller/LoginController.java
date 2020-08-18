package com.lwq.hr.controller;

import lwq.returnbean.RespBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 废弃,未登录不跳转到这个路径,authenticationEntryPoint自定义未登录方式为返回JSON
 * @author: LinWeiQi
 */
@RestController
public class LoginController {
    // 废弃
    @GetMapping("/login")
    public RespBean login(){

        return RespBean.error("尚未登录,请登录");
    }
}
