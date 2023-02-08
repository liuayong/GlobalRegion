// package com.infoservice.dms.test1;
package com.hspedu.demo.redis;


import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;

import java.nio.charset.StandardCharsets;

/*
echo %CLASSPATH%
echo $CLASSPATH

javac  -cp .;./jedis-2.9.0.jar  RedisTest1.java


java -cp .;./jedis-2.9.0.jar  RedisTest1
java -cp .:./jedis-2.9.0.jar RedisTest1


*/
public class RedisTest1 {
    private static String redisServer = "localhost"; // "dms.redis.com";

    private static String passWord = "";    // gU1wF5b_3a 1BPMm_VQjR


    public static void main(String[] args) {

        isExist("SAVE");
        setObject("SAVE", "存储对象");
        Object save = getObject("SAVE");
        System.out.println("save = " + save);
    }


    // 将序列化对象从缓存中取出
    public static Object getObject(String key) {
        // String redisServer = "r-qj88c6ecb7330ba4.redis.rds.ops.cloud.byd.com";
        // String passWord = "bYDms2020";
        try {

            Jedis jedis = new Jedis(redisServer);
            if (!StringUtils.isEmpty(passWord))
                jedis.auth(passWord);// 输入密码
            System.out.println("SUCCESS CONNECT REDIS FOR SERVING");
            // Object ob = ObjectTranscoder.deserialize(jedis.get(key.getBytes()));
            Object ob = new String(jedis.get(key.getBytes()));
            jedis.close();
            return ob;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 将序列化对象缓存起来
    public static void setObject(String key, Object ob) {
        try {
            Jedis jedis = new Jedis(redisServer);
            if (!StringUtils.isEmpty(passWord))
                jedis.auth(passWord);// 输入密码
            System.out.println("SUCCESS CONNECT REDIS FOR SERVING");
            jedis.set(key.getBytes(), (ob + "").getBytes(StandardCharsets.UTF_8));
            jedis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static boolean isExist(String key) {
        try {
            Jedis jedis = new Jedis(redisServer);
            if (!StringUtils.isEmpty(passWord))
                jedis.auth(passWord);// 输入密码
            boolean isExist = jedis.exists(key);
            jedis.close();
            return isExist;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
