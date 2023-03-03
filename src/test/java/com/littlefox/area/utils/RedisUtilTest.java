package com.littlefox.area.utils;

import com.littlefox.area.Application;
import com.littlefox.area.constants.REDIS_KEY;
import com.littlefox.area.enums.RedisKey;
import com.littlefox.area.enums.RoleType;
import com.littlefox.area.model.Area;
import com.littlefox.area.service.AreaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.MessageFormat;
import java.util.List;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class RedisUtilTest {
    
    @Autowired
    private RedisUtil redisUtil;
    
    @Autowired
    private AreaService areaService;
    
    @Test
    public void get() {
        System.out.println(redisUtil);
        String val = redisUtil.get("name");
        System.out.println("val = " + val);
        
    }
    
    @Test
    public void set() {
        Random random = new Random();
        redisUtil.set("name:" + Math.random(), random.nextInt(10000));
    }
    
    
    @Test
    public void saveList() {
        String key = MessageFormat.format(RedisKey.KEY_DICT, "saveList");
        //redisUtil.get(key, List.class, Dict.class);
        
        System.out.println("key = " + key);
        
        String key2 = RedisKey.formatTokenKey(Client.PC, RoleType.admin, "1111", "token");
        System.out.println("key2 = " + key2);
        
        List<Area> areas = areaService.selectAreaList("", "0", "zh_CN");
        System.out.println("areas = " + areas.size());
    }
    
    @Test
    public void verify() {
        // 1.校验该客户端,连续发送了多少次短信.
        String key = MessageFormat.format(REDIS_KEY.KEY_SMS_SEND_CODE_COUNT, "deviceId");
        String countStr = redisUtil.get(key);
        System.out.println("key = " + key);
        
        int count = 0;
        //过期时间
        long expire = 600;
        
        //本次次数+1
        count++;
        //redisUtil.set(key, count, expire);
        
        System.out.println("countStr = " + countStr);
        System.out.println(redisUtil.get("name"));
    }
    
    
}