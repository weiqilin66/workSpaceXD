package com.druid.controller;


import com.druid.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import lwq.base.BaseController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author LinWeiQi
 * @since 2020-01-17
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    UserMapper userMapper;

    @GetMapping("/getdata")
    public String getdata(){
        String resStr = "";
        return resStr;
    }
}
