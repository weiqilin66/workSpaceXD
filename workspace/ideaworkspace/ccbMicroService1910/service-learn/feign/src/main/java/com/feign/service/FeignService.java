package com.feign.service;

import com.feign.fallback.SchedualServiceHiHystric;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description: 描述
 * @author: LinWeiQi
 */
@FeignClient(value = "SERVICE-HI", fallback = SchedualServiceHiHystric.class) // 通过@ FeignClient（“服务名”），来指定调用哪个服务
public interface FeignService {
    @GetMapping("/hi")
    String sayHiFromClientOne(@RequestParam(value = "name") String name);
}
