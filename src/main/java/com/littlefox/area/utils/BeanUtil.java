package com.littlefox.area.utils;

import org.springframework.cglib.beans.BeanMap;
//import net.sf.cglib.beans.BeanMap;
import java.util.Map;

public class BeanUtil {
    
    
    public static Object mapToBean(Map<String, Object> map, Class<?> beanClass) throws Exception {
        Object object = beanClass.newInstance();
        BeanMap beanMap = BeanMap.create(object);
        beanMap.putAll(map);
        return object;
    }
}
