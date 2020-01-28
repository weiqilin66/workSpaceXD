package com.feign.fallback;

import com.feign.service.FeignService;
import feign.Feign;
import org.springframework.stereotype.Component;

/**
 * @Description: 熔断方法需要实现 熔断的接口
 * @author: LinWeiQi
 */
@Component
public class SchedualServiceHiHystric implements FeignService {
    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry " + name;
    }
}
