package com.fre.controller;


import com.fre.entity.User;
import lwq.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author LinWeiQi
 * @since 2020-01-07
 */
@Controller
public class UserController extends BaseController {

    @GetMapping("/user")
    public String getList(Model model){
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User("1",i,"www."+i+".com");
            list.add(user);
        }
        model.addAttribute("list",list);
        return "User";
    }

}
