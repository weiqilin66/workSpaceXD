package com.lwq.springsecuritymybatis;

import com.lwq.springsecuritymybatis.entity.User;
import com.lwq.springsecuritymybatis.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class SpringSecurityMybatisApplicationTests {

    @Resource
    UserMapper userMapper;

    @Test
    void contextLoads() {
        User root = userMapper.loadUserByUsername("root");
        System.out.println(root);


    }

}
