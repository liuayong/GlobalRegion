package com.byd.tool;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;
import org.springframework.boot.SpringApplication;

import java.util.*;

import static java.util.stream.Collectors.toList;

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


    @Test
    public void intersection() {

        List<String> listA = new ArrayList<String>();
        listA.add("a");
        listA.add("b");
        listA.add("b");
        listA.add("c");
        List<String> listB = new ArrayList<String>();
        listB.add("a");
        listB.add("e");
        listB.add("f");
        System.out.println("集合A:" + listA);
        System.out.println("集合B:" + listB);
        System.out.println("------------------------");
        //并集
        Collection<String> union = CollectionUtils.union(listA, listB);
        System.out.println("并集：" + union);
        //交集
        Collection<String> intersection = CollectionUtils.intersection(listA, listB);
        System.out.println("交集：" + intersection);
        //交集的补集
        Collection<String> disjunction = CollectionUtils.disjunction(listA, listB);
        System.out.println("交集的补集   ：" + disjunction);
        //差集(集合相减)
        Collection<String> subtract = CollectionUtils.subtract(listA, listB);
        System.out.println("差集(集合相减)   ：" + subtract);
    }

    @Test
    public void intersection2() {
        List<String> stringList = new ArrayList<>();
        stringList.add("a");
        stringList.add("b");
        stringList.add("c");
        stringList.add("i");
        stringList.add("j");
        stringList.add("a");

        //一、求交集
        //方法1：直接通过retainAll直接过滤
        List<String> stringList1 = new ArrayList<>(Arrays.asList("a,b,c,d,e,f,g,h".split(",")));
        stringList1.retainAll(stringList);
        System.out.println("交集1: " + stringList1);

        //方法2：通过过滤掉存在于stringList的数据
        List<String> stringList1_2 = new ArrayList<>(Arrays.asList("a,b,c,d,e,f,g,h".split(",")));
        List<String> strings = stringList1_2.stream()
                .filter(item -> stringList.contains(item))
                .collect(toList());
        System.out.println("交集2：" + strings);

        //二、并集
        //有重并集
        List<String> stringList2 = new ArrayList<>(Arrays.asList("a,b,c,d,e,f,g,h".split(",")));
        stringList2.addAll(stringList);
        System.out.println("并集: " + stringList2);

        //无重并集
        List<String> stringList2_2 = new ArrayList<>(Arrays.asList("a,b,c,d,e,f,g,h".split(",")));
        List<String> stringList_1 = new ArrayList<>(Arrays.asList("a,b,c,i,j,a".split(",")));
        stringList2_2.removeAll(stringList_1);
        stringList_1.addAll(stringList2_2);

        System.out.println("无重并集: " + stringList_1);

        //三、求差集
        //方法1：直接使用removeAll()方法
        List<String> stringList3 = new ArrayList<>(Arrays.asList("a,b,c,d,e,f,g,h".split(",")));
        stringList3.removeAll(stringList);
        System.out.println("差集1: " + stringList3);

        //方法2：通过过滤掉不存在于stringList的数据，然后和本数组进行交集处理
        List<String> stringList3_2 = new ArrayList<>(Arrays.asList("a,b,c,d,e,f,g,h".split(",")));
        stringList3_2.retainAll(stringList3_2.stream()
                .filter(item -> !stringList.contains(item))
                .collect(toList()));
        System.out.println("差集2：" + stringList3_2);

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
