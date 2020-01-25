package com.ribbon.controller;

import com.ribbon.service.HiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 描述
 * @author: LinWeiQi
 */
@RestController
public class RibbonController {

    @Autowired
    HiService hiService;

    @GetMapping("/hi")
    public String sayHi(String name) {

        return hiService.hiService(name);
    }
}
