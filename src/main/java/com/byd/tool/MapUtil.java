package com.byd.tool;

import org.springframework.cglib.beans.BeanMap;

import java.util.*;

public class MapUtil {
    //  CollectionUtils.union()
    
    public static Map<String, Object> sortMap(Map<String, Object> sourceMap) {
        Set<String> keySet = sourceMap.keySet();
        //List<String> collect = keySet.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
        
        //创建TreeSet排序的lambda表达式; 相比于传统方式,代码量上有了很大的减少;
        TreeSet<String> set = new TreeSet<>(((o1, o2) -> o1.compareTo(o2)));
        set.addAll(keySet);
        Map<String, Object> newMap = new HashMap<>();
        for (String key : set) {
            newMap.put(key, sourceMap.get(key));
        }
        return newMap;
    }
    
    /**
     * 对象转Map
     *
     * @param object
     * @return
     */
    public static Map<String, Object> beanToMap(Object object) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (object != null) {
            BeanMap beanMap = BeanMap.create(object);
            for (Object key : beanMap.keySet()) {
                map.put(key + "", beanMap.get(key));
            }
        }
        return map;
    }
    
    /**
     * map转对象
     *
     * @param map
     * @param beanClass
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T mapToBean(Map<String, Object> map, Class<T> beanClass) throws Exception {
        T bean = beanClass.newInstance();
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.putAll(map);
        return bean;
    }
    
    public static <T> T mapToBean2(Map<String, String> map, Class<T> beanClass) throws Exception {
        T bean = beanClass.newInstance();
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.putAll(map);
        return bean;
    }
}
