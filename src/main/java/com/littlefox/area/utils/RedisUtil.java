package com.littlefox.area.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {
    
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    
    public void set(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }
    
    public void set(String key, int value) {
        stringRedisTemplate.opsForValue().set(key, String.valueOf(value));
    }
    
    public void set(String key, long value) {
        stringRedisTemplate.opsForValue().set(key, String.valueOf(value));
    }
    
    public void set(String key, Object obj) {
        String json = JsonUtil.toJson(obj);
        stringRedisTemplate.opsForValue().set(key, json);
    }
    
    public void set(String key, String value, long expire) {
        stringRedisTemplate.opsForValue().set(key, value, expire, TimeUnit.SECONDS);
    }
    
    public void set(String key, int value, long expire) {
        stringRedisTemplate.opsForValue().set(key, String.valueOf(value), expire, TimeUnit.SECONDS);
    }
    
    public void set(String key, long value, long expire) {
        stringRedisTemplate.opsForValue().set(key, String.valueOf(value), expire, TimeUnit.SECONDS);
    }
    
    public void set(String key, Object obj, long expire) {
        String json = JsonUtil.toJson(obj);
        stringRedisTemplate.opsForValue().set(key, json, expire, TimeUnit.SECONDS);
    }
    
    public void hset(String h, String hk, Object hv) {
        stringRedisTemplate.opsForHash().put(h, hk, hv);
    }
    
    public Object hget(String h, String hk) {
        return stringRedisTemplate.opsForHash().get(h, hk);
    }
    
    public void hdel(String h, String... hk) {
        stringRedisTemplate.opsForHash().delete(h, hk);
    }
    
    public void setExpire(String key, long expire) {
        stringRedisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }
    
    public void del(String key) {
        stringRedisTemplate.delete(key);
    }
    
    public void del(Collection<String> keyList) {
        stringRedisTemplate.delete(keyList);
    }
    
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }
    
    public <T> T get(String key, Class<T> valueType) {
        String json = get(key);
        if (json == null || json.length() <= 0) {
            return null;
        }
        return JsonUtil.readValue(json, valueType);
    }
    
    public <T> T get(String key, Class<?> collectionClass, Class<?> valueType) {
        String json = get(key);
        if (json == null || json.length() <= 0) {
            return null;
        }
        return JsonUtil.readValue(json, collectionClass, valueType);
    }
}
