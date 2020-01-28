package com.lwq.hr.controller;

import lwq.result.RespBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @author: LinWeiQi
 */
@RestController
public class LoginController {

    @GetMapping("/login")
    public RespBean login(){

        return RespBean.error("尚未登录,请登录");
    }
}
