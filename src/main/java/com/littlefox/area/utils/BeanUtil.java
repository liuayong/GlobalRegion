package com.littlefox.area.utils;

import com.alibaba.fastjson.JSONObject;
import com.littlefox.area.model.User;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.beans.BeanMap;
//import net.sf.cglib.beans.BeanMap;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BeanUtil {
    private static Logger LOGGER = LoggerFactory.getLogger(BeanUtil.class);
    
    
    public static Object mapToBean(Map<String, Object> map, Class<?> beanClass) throws Exception {
        Object object = beanClass.newInstance();
        BeanMap beanMap = BeanMap.create(object);
        beanMap.putAll(map);
        return object;
    }
    
    
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        beanToMap();
        
    }
    
    public static Map beanToMap(Object obj) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        
        // 原文链接：https://blog.csdn.net/C_H_K/article/details/79530590
        //ConvertUtilsBean convertUtils = BeanUtilsBean.getInstance().getConvertUtils();
        //DateConverter dateConverter = new DateConverter();
        //dateConverter.setPattern("yyyy-MM-dd HH:mm:ss");
        //convertUtils.register(dateConverter, String.class);
        
        // https://www.cnblogs.com/jokerjason/p/5724493.html
        
        // todo 日期格式converter
        Map keyValues = BeanUtils.describe(obj);
        //System.out.println("keyValues = " + keyValues);
        LOGGER.info("bean covert to map:{}", JSONObject.toJSON(keyValues).toString());
        return keyValues;
    }
    
    private static void beanToMap() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        User user = new User();
        user.setPassWord("password");
        user.setComments("test method!");
        user.setUserName("wang shisheng");
        user.setCreateTime(new Date());
        
        Map keyValues = BeanUtils.describe(user);
        
        System.out.println("keyValues = " + keyValues);
        
        LOGGER.info("bean covert to map:{}", JSONObject.toJSON(keyValues).toString());
    }
    
    public static void autoBeanToMap() {
        User user = new User();
        Map<String, Object> keyValues = new HashMap<>();
        
        user.setPassWord("password");
        user.setComments("test method!");
        user.setUserName("wang shisheng");
        user.setUserCode(2018998770);
        user.setCreateTime(new Date());
        
        
        Method[] methods = user.getClass().getMethods();
        
        try {
            for (Method method : methods) {
                String methodName = method.getName();
                //反射获取属性与属性值的方法很多，以下是其一；也可以直接获得属性，不过获取的时候需要用过设置属性私有可见
                if (methodName.indexOf("get") == 0) {  //methodName.contains("get")
                    //invoke 执行get方法获取属性值
                    Object value = method.invoke(user);
                    //根据setXXXX 通过以下算法取得属性名称
                    String key = methodName.substring(methodName.indexOf("get") + 3);
                    
                    String temp = key.substring(0, 1).toLowerCase();
                    key = key.substring(1);
                    //最终得到属性名称
                    key = temp + key;
                    
                    keyValues.put(key, value);
                }
            }
        } catch (Exception e) {
            LOGGER.error("错误信息：", e);
        }
        
        System.out.println(keyValues);
        LOGGER.info("auto bean covert to map:{}", JSONObject.toJSON(keyValues).toString());
    }
}
