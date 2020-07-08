package org.wayne.consumer.openfeignandhystrix;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.wayne.commons.entity.User;
import org.wayne.consumer.openfeign.HelloService;

/**
 * @Description: 工厂方式返回  +配置文件中配置开启
 * implements FallbackFactory<HelloService>
 * @author: LinWeiQi
 */
@Component
public class HelloFallBackFactory implements FallbackFactory<HelloService> {
    @Override
    public HelloService create(Throwable throwable) {
        return new HelloService() {
            @Override
            public String hello() {
                return "error";
            }

            @Override
            public String hello2(String name) {
                return null;
            }

            @Override
            public User user2(User user) {
                return null;
            }

            @Override
            public User user1(String username, String password) {
                return null;
            }

            @Override
            public String del(Integer id) {
                return null;
            }
        };
    }
}
