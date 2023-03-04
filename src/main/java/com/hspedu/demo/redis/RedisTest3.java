package com.hspedu.demo.redis;

import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;

import java.nio.charset.StandardCharsets;

public class RedisTest3 {
    private static String redisServer = "localhost"; // "dms.redis.com";

    private static String passWord = "";    // gU1wF5b_3a 1BPMm_VQjR

    private static Jedis jedis;

    static {
        jedis = new Jedis(redisServer);
        if (!StringUtils.isEmpty(passWord))
            jedis.auth(passWord);// 输入密码
        System.out.println("SUCCESS CONNECT REDIS FOR SERVING");
    }

    // 将序列化对象缓存起来
    public static void setObject(String key, Object ob) {

        try {

            Jedis jedis = new Jedis(redisServer);
            if (!StringUtils.isEmpty(passWord))
                jedis.auth(passWord);// 输入密码
            System.out.println("SUCCESS CONNECT REDIS FOR SERVING");
            jedis.set(key.getBytes(), "hello111".getBytes(StandardCharsets.UTF_8));
            jedis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
