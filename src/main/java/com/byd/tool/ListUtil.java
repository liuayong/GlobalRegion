package com.byd.tool;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;
import org.springframework.boot.SpringApplication;

import java.util.*;
import java.util.stream.Collectors;


/*
https://zhuanlan.zhihu.com/p/414598548
https://blog.csdn.net/qq_40198004/article/details/107541165
<dependency>
    <groupId>commons-collections</groupId>
    <artifactId>commons-collections</artifactId>
    <version>3.2</version>
</dependency>


 */
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

    /**
     * 并集, 相同的元素不去重
     *
     * @param list1
     * @param list2
     * @param <E>
     * @return
     */
    public static <E> List<E> unionAll(Collection<E> list1, Collection<E> list2) {
        List<E> result = new ArrayList<>(list1.size() + list2.size());
        result.addAll(list1);
        result.addAll(list2);

        return result;

    }


    public static <E> List<E> unionAll2(Collection<E> list1, Collection<E> list2) {
        List<E> result = new ArrayList<>(list1.size() + list2.size());

        CollectionUtils.addAll(result, list1);
        CollectionUtils.addAll(result, list2);
        return result;
    }


    /**
     * 并集， 相同的元素去重
     *
     * @param list1
     * @param list2
     * @param <E>
     * @return
     */
    public static <E> List<E> union(Collection<E> list1, Collection<E> list2) {
        List<E> result = list1.stream().distinct().collect(Collectors.toList());
        for (E e : list2) {
            if (!result.contains(e)) {
                result.add(e);
            }
        }

        return result;
    }

    public static <E> List<E> union2(Collection<E> list1, Collection<E> list2) {
        Set<E> set = new HashSet<>(list1);
        for (E e : list2) {
            set.add(e);
        }

        return new ArrayList<>(set);
    }

    /**
     * 两个数组的交集intersection：  retainAll交集
     *
     * @param list1
     * @param list2
     * @param <E>
     * @return
     */
    public static <E> List<E> intersection(Collection<E> list1, Collection<?> list2) {
        List<E> result = new ArrayList<>(Math.min(list1.size(), list2.size()));
        // for (E e : list1) {
        //     if (list2.contains(e)) {
        //         result.add(e);
        //     }
        // }

        for (E e : list1) {
            if (!result.contains(e)) {
                result.add(e);
            }
        }

        return result;
    }

    public static <E> List<E> intersection2(Collection<E> list1, Collection<?> list2) {
        return list1.stream().filter(e -> list2.contains(e)).distinct().collect(Collectors.toList());
    }

    /**
     * 两个数组的差集(list1 - list2) subtract, list1.removeAll(list2)
     *
     * @param list1
     * @param list2
     * @param <E>
     * @return
     */
    public static <E> List<E> subtract(Collection<E> list1, Collection<?> list2) {
        List<E> result = new ArrayList<>();
        for (E e : list1) {
            if (!list2.contains(e)) {
                result.add(e);
            }
        }

        return result;
    }


    public static <E> List<E> subtract2(Collection<E> list1, Collection<?> list2) {
        return list1.stream().filter(e -> !list2.contains(e)).collect(Collectors.toList());
    }

}
