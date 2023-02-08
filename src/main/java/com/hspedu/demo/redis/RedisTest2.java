// package com.infoservice.dms.test1;
package com.hspedu.demo.redis;

import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;

import java.nio.charset.StandardCharsets;
// import com.fasterxml.jackson.core.JsonProcessingException;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/*
echo %CLASSPATH%
echo $CLASSPATH

javac  -cp .;./jedis-2.9.0.jar  RedisTest2.java
javac  -cp .:./jedis-2.9.0.jar  RedisTest2.java


java -cp .;./jedis-2.9.0.jar  RedisTest2
java -cp .:./jedis-2.9.0.jar RedisTest2


*/
public class RedisTest2 {

    private static String redisServer = "localhost"; // "dms.redis.com";

    private static String passWord = "";    // gU1wF5b_3a 1BPMm_VQjR


    public static void main(String[] args) {
        String langDicKey = "LANG_DIC";
        //setObject(key1, "liu阿勇");
        Object value = getObject(langDicKey);
        if (value != null) {
            System.out.println(value.getClass().getName());
        }
        Map<String, Map<String, String>> langDic = (HashMap) value;
        Map<String, Map<String, String>> langDic2 = (Map<String, Map<String, String>>) getObject(langDicKey);

        System.out.println(langDic == langDic2);

        int count = 0;
        if (langDic != null) {
            System.out.println(String.format("lang size %s ", langDic.size()));
            for (Map.Entry<String, Map<String, String>> stringMapEntry : langDic.entrySet()) {
                String lang = stringMapEntry.getKey();
                Map<String, String> lanMap = (Map<String, String>) stringMapEntry.getValue();

                System.out.print("lang = " + lang + " number: ");
                System.out.println(lanMap.size());
                count += lanMap.size();
            }
            System.out.println(String.format("lang record size:  %s ", count));
            System.out.println("==============================================");
        }


        String partCode = "000000000010003790";

        // "TC_FUNC.part.partclaim.PartClaimApply.10501204", "byd020GROUP_NAME", "TC_CODE.15801001",
        String[] tranKeys = {"TC_FUNC.null.1010"}; //  partCode + "PART_NAME"
        String[] langs = {"en-us", "es-es", "zh-cn"};


        //String partName = LangUtil.getLangOne(partCode, "PART_NAME");
        //System.out.println("partName = " + partName);
        //partName = LangUtil.getLangOne(partCode, "PART_NAME", "default value");
        //System.out.println("partName = " + partName + "  " + LangUtil.getLang("en-us", tranKeys[0]));
        //


        //String code = "501069";  // getLang("501069"+"REGION_NAME")
        //String val1 = LangUtil.getLang("zh-cn", code + "REGION_NAME");
        //System.out.println("val1 = " + val1);


        if (langDic != null) {
            //System.out.println("数量: " + langDic.size());
            //System.out.println(langDic.keySet());
            for (Map.Entry<String, Map<String, String>> stringMapEntry : langDic.entrySet()) {
                String lan = stringMapEntry.getKey();
                if ("zh-cn".equals(lan)) {
                    Map<String, String> lanMap = (Map<String, String>) stringMapEntry.getValue();
                    for (Map.Entry<String, String> lanEntry : lanMap.entrySet()) {
                        String key = lanEntry.getKey();
                        //if(key.contains("lyy")) {
                        //    System.out.println("key = " + key);
                        //}

                        //if (tranKey1.equals(key)) {
                        //    System.out.println(key);
                        //    System.out.println(lanEntry.getValue());
                        //}


                        for (String tranKey : tranKeys) {
                            if (tranKey.equals(key)) {
                                //System.out.println(key);   // + ", " + LangUtil.getLangOne("zh-cn", key)
                                // System.out.println(LangUtil.getLangOne("zh-cn", key) + "," +
                                //         lanEntry.getValue() + ", " + LangUtil.getLang("en-us", tranKey));
                            }
                        }
                    }
                }
            }
        }

        //boolean exist = isExist(langDicKey);
        //System.out.println(exist);
    }


    //从缓存中删除当前用户信息
    public static void deleteOnLineInfo(String userName) {
        // String redisServer = PropertyUtil.getKey("redisServer");
        // String passWord = PropertyUtil.getKey("passWord");
        Jedis jedis = new Jedis(redisServer);
        if (!StringUtils.isEmpty(passWord))
            jedis.auth(passWord);//输入密码
        jedis.select(0);
        // logger.info("SUCCESS CONNECT REDIS FOR SERVING");
        jedis.del(userName);
        jedis.close();
    }


    public static Jedis getInstance() {

        // String redisServer = PropertyUtil.getKey("redisServer");
        // String passWord = PropertyUtil.getKey("passWord");
        Jedis jedis = new Jedis(redisServer);
        if (!StringUtils.isEmpty(passWord))
            jedis.auth(passWord);//输入密码
        jedis.select(0);
        // logger.info("SUCCESS CONNECT REDIS FOR SERVING");
        return jedis;
    }

    // 将序列化对象缓存起来
    public static void setObject(String key, Object ob) {

        try {

            // String redisServer = PropertyUtil.getKey("redisServer");
            // String passWord = PropertyUtil.getKey("passWord");
            Jedis jedis = new Jedis(redisServer);
            if (!StringUtils.isEmpty(passWord))
                jedis.auth(passWord);// 输入密码
            // logger.info("SUCCESS CONNECT REDIS FOR SERVING");
            jedis.set(key.getBytes(), ObjectTranscoder.serialize(ob));
            jedis.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // 将序列化对象从缓存中取出
    public static Object getObject(String key) {

        try {

            // String redisServer = PropertyUtil.getKey("redisServer");
            // String passWord = PropertyUtil.getKey("passWord");
            Jedis jedis = new Jedis(redisServer);
            if (!StringUtils.isEmpty(passWord))
                jedis.auth(passWord);// 输入密码
            // logger.info("SUCCESS CONNECT REDIS FOR SERVING");
            Object ob = ObjectTranscoder.deserialize(jedis.get(key.getBytes()));
            jedis.close();
            return ob;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

}
