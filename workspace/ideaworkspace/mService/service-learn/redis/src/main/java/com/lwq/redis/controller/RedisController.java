package com.lwq.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: StringRedisTemplate操作redis
 * @author: LinWeiQi
 */
@RestController
public class RedisController {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @GetMapping("/set")
    public void getStr() {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        ops.set("name", "wayne");

    }

    @GetMapping("/get")
    public void get2() {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        System.out.println(ops.get("name"));
    }
}
