package com.feign.controller;

import com.feign.service.FeignService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 描述
 * @author: LinWeiQi
 */
@RestController
public class FeignController {
    @Autowired
    FeignService feignService;

    @GetMapping("/hi")
    public String saHi(String name){

        return feignService.sayHiFromClientOne(name);
    }
}
