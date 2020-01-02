package com.ribbon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Description: 提供服务来访问test-demo服务
 * @author: LinWeiQi
 */
@Service
public class HiService {

        @Autowired
        RestTemplate restTemplate;

        //直接用的程序名替代了具体的url地址，在ribbon中它会根据服务名来选择具体的服务实例，
        // 根据服务实例在请求的时候会用具体的url替换掉服务名
        public String hiService(String name) {
            return restTemplate.getForObject("http://TEST-CONNECT/hi?name="+name,String.class);
        }

}
