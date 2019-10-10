package com.forezp.servicefeign.fallback;

import com.forezp.servicefeign.service.SchedualServiceHi;
import org.springframework.stereotype.Component;

//SchedualServiceHiHystric需要实现SchedualServiceHi 接口，并注入到Ioc容器中

/**
 * feign的断路器相当于让接口指向另外一个实现类 ,快速返回一个字符串而不是等待响应结束
 */
@Component
public class SchedualServiceHiHystric implements SchedualServiceHi {
    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry "+name;
    }
}