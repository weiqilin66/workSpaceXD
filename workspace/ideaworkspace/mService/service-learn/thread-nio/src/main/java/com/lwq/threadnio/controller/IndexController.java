package com.lwq.threadnio.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @author: LinWeiQi
 */
@RestController
@RequestMapping("/index")
public class IndexController {

    @GetMapping("/")
    public String g(){

        return "hr-thread-nio";
    }
}
