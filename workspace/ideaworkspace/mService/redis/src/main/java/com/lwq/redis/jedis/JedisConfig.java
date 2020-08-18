package com.lwq.redis.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @Description: 接口作为参数
 * @author: LinWeiQi
 */
public class JedisConfig {
    private JedisPool jedisPool;

    public JedisConfig(){
        jedisPool=new JedisPool("192.168.45.45");
    }
    //逼王核心
    public void excute(CallWithJedis callWithJedis){//传入接口则要实现接口
        try(Jedis jedis = jedisPool.getResource()){//try with source被隐藏了
            callWithJedis.call(jedis);//call为传入时实现的接口
        }

    }
}
