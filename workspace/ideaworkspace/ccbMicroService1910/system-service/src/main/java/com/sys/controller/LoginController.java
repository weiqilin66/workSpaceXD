package com.sys.controller;

import com.sys.entity.User;
import com.sys.vo.UserVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 登录 & 菜单
 * @author: LinWeiQi
 */
@RestController("/dashboard")
public class LoginController {

    @RequestMapping(value ="/login", method = RequestMethod.GET)
    public User login(UserVO userVo){

        return null;
    }

}
