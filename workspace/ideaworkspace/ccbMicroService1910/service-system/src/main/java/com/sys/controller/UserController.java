package com.sys.controller;


import com.sys.mapper.UserMapper;
import lwq.base.BaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author LinWeiQi
 * @since 2020-01-07
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Resource
    UserMapper userMapper;

    @GetMapping("/getdata")
    public List getData(){

        return userMapper.getAll();
    }
}
