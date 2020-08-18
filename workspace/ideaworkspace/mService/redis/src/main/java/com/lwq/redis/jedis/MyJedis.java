package com.lwq.redis.jedis;

import redis.clients.jedis.Jedis;

/**
 * @Description: 如何成为逼王之参数为接口--使用接口的参数作为变量,多态来封装实现
 * @author: LinWeiQi
 */
public class MyJedis {
    public static void main(String[] args) {
//        Jedis jedis = new Jedis("192.168.45.45");
//        jedis.auth("123");
//        String ping = jedis.ping();
//        System.out.println(ping);

        final JedisConfig jedis = new JedisConfig();
        jedis.excute(new CallWithJedis() {//这里实现了接口的call,而excute方法中调用了call()
            @Override
            public void call(Jedis jedis) {
                jedis.auth("123");
                System.out.println(jedis.ping());
            }
        });
        //lambda
        jedis.excute(jedis1 -> {
            jedis1.auth("123");
            System.out.println(jedis1.ping());
        });
    }

}
