package com.lwq.redis.bussiness;

import com.lwq.redis.jedis.CallWithJedis;
import com.lwq.redis.jedis.JedisConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import java.util.Arrays;
import java.util.UUID;

/**
 * @Description: 做分布式锁
 * @author: LinWeiQi
 */
public class FenBuShiSuo {
    public static void main(String[] args) {

        final JedisConfig jedisConfig = new JedisConfig();
        jedisConfig.excute(new CallWithJedis() {
            @Override
            public void call(Jedis jedis) {
                final String s = UUID.randomUUID().toString();//随机value值验证最后释放的锁是否同一个锁
                jedis.auth("123");

                final String res = jedis.set("k1", s,
                        new SetParams()
                        .nx()
                        .ex(20)
                );//setParam可同时多个操作,保证原子性 设置过期时间防止死锁,key可以是业务主键
                if ("OK".equals(res)) {//获取到锁  大写ok
                    //.....业务代码
                    System.out.println("执行业务代码");
                    //释放锁 1获取当前业务主键value,2对比value和生成的s,3对比一直释放锁del  为了保证原子性引入Lua
                    //调用lua
                    jedis.evalsha("b8059ba43af6ffe8bed3db65bac35d452f8115d8", Arrays.asList("k1"),Arrays.asList(s));
                }else {
                    System.out.println("拿不到锁");
                }
            }
        });
    }
    /**
     * [root@localhost lua]# cat release_eq_value.lua |redis-cli -a 123 script load --pipe
     * Warning: Using a password with '-a' or '-u' option on the command line interface may not be safe.
     * "b8059ba43af6ffe8bed3db65bac35d452f8115d8"
     * [root@localhost lua]#  cat release_eq_value.lua
     * # cat release_eq_value.lua |redis-cli -a 123 script load --pipe加载到redis中
     * # 脚本
     * if redis.call("get",KEYS[1])==ARGV[1] then
     *    redis.call("dell",KEYS[1])
     * ELSE
     *    return 0
     * [root@localhost lua]#
     */
}
