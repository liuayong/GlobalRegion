package com.byd.tool;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ListUtil {
    public static boolean isEmpty(List list) {
        return null == list || list.size() == 0;
    }
    
    
    /**
     * 取Map集合的并集
     *
     * @param map1 大集合
     * @param map2 小集合
     * @return 两个集合的并集
     */
    public static Map<String, Object> getUnionSetByGuava(Map<String, Object> map1, Map<String, Object> map2) {
        Set<String> bigMapKey = map1.keySet();
        Set<String> smallMapKey = map2.keySet();
        Set<String> differenceSet = Sets.union(bigMapKey, smallMapKey);
        Map<String, Object> result = Maps.newHashMap();
        for (String key : differenceSet) {
            if (map1.containsKey(key)) {
                result.put(key, map1.get(key));
            } else {
                result.put(key, map2.get(key));
            }
        }
        return result;
    }
    
    /**
     * 取Map集合的差集
     *
     * @param bigMap   大集合
     * @param smallMap 小集合
     * @return 两个集合的差集
     */
    public static Map<String, Object> getDifferenceSetByGuava(Map<String, Object> bigMap, Map<String, Object> smallMap) {
        Set<String> bigMapKey = bigMap.keySet();
        Set<String> smallMapKey = smallMap.keySet();
        Set<String> differenceSet = Sets.difference(bigMapKey, smallMapKey);
        Map<String, Object> result = Maps.newHashMap();
        for (String key : differenceSet) {
            result.put(key, bigMap.get(key));
        }
        return result;
    }
    
    /**
     * 取Map集合的交集（String,String）
     *
     * @param map1 大集合
     * @param map2 小集合
     * @return 两个集合的交集
     */
    public static Map<String, Object> getIntersectionSetByGuava(Map<String, Object> map1, Map<String, Object> map2) {
        Set<String> bigMapKey = map1.keySet();
        Set<String> smallMapKey = map2.keySet();
        Set<String> differenceSet = Sets.intersection(bigMapKey, smallMapKey);
        Map<String, Object> result = Maps.newHashMap();
        for (String key : differenceSet) {
            result.put(key, map1.get(key));
        }
        return result;
    }
    
    public static void main(String[] args) {
        Map<String, String> map1 = new HashMap<>();
        Map<String, String> map2 = new HashMap<>();
        
        map1.put("1111", "aaa");
        map1.put("one", "oneoneoe");
        
        map2.put("1111", "1111");
        map2.put("2222", "2222");
        
        
        Set<String> differenceSet = Sets.difference(map1.keySet(), map2.keySet());
        System.out.println("differenceSet = " + differenceSet);
        Set<String> differenceSet2 = Sets.difference(map2.keySet(), map1.keySet());
        System.out.println("differenceSet2 = " + differenceSet2);
        
        
        for (Map.Entry<String, String> entry : map2.entrySet()) {
            String key = entry.getKey();
            if (!map1.containsKey(key)) {
                System.out.println(key);
            }
            
        }
        
        
    }
}
