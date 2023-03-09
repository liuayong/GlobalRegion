package com.byd.tool;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;

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
public class ListTest {


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


        String type = "subtract";
        if ("intersection".equalsIgnoreCase(type)) {
            //一、求交集
            //方法1：直接通过retainAll直接过滤
            List<String> stringList1 = new ArrayList<>(Arrays.asList("a,b,c,d,e,f,g,h".split(",")));
            stringList1.retainAll(stringList);
            System.out.println("交集1: " + stringList1);
            // PrintUtil.println(stringList1); // stringList1 破坏了原始的 List对象

            //方法2：通过过滤掉存在于stringList的数据
            List<String> stringList1_2 = new ArrayList<>(Arrays.asList("a,b,c,d,e,f,g,h".split(",")));
            List<String> strings = stringList1_2.stream()
                    .filter(item -> stringList.contains(item))
                    .collect(Collectors.toList());
            System.out.println("交集2：" + strings);
            // PrintUtil.println(stringList1_2);  // 没有破坏原始的List对象

            // 方法3
            PrintUtil.println(ListUtil.intersection(stringList, stringList1_2));
            PrintUtil.println(ListUtil.intersection2(stringList, stringList1_2));
            PrintUtil.println(CollectionUtils.intersection(stringList, stringList1_2));
        } else if ("unionAll".equalsIgnoreCase(type)) {
            //二、并集
            //有重并集
            List<String> stringList2 = new ArrayList<>(Arrays.asList("a,b,c,d,e,f,g,h".split(",")));
            // stringList2.addAll(stringList);
            // System.out.println(stringList2);
            // PrintUtil.println(stringList2); // 破坏原始的List对象

            PrintUtil.println(CollectionUtils.union(stringList, stringList2));  // 去重第二个list在第一个list中存在的元素
            PrintUtil.println(ListUtil.unionAll(stringList, stringList2));
            PrintUtil.println(ListUtil.unionAll2(stringList, stringList2));


        } else if ("union".equalsIgnoreCase(type)) {
            //无重并集
            List<String> stringList2_2 = new ArrayList<>(Arrays.asList("a,b,c,d,e,f,g,h".split(",")));
            List<String> stringList_1 = new ArrayList<>(Arrays.asList("a,b,c,i,j,a".split(",")));
            // stringList2_2.removeAll(stringList_1);
            // stringList_1.addAll(stringList2_2);
            // System.out.println("无重并集: " + stringList_1);  //无重并集: [a, b, c, i, j, a, d, e, f, g, h]


            PrintUtil.println(CollectionUtils.union(stringList_1, stringList2_2));
            PrintUtil.println(ListUtil.union(stringList_1, stringList2_2));
            PrintUtil.println(ListUtil.union2(stringList_1, stringList2_2));

        } else if ("subtract".equalsIgnoreCase(type)) {


            //三、求差集
            //方法1：直接使用removeAll()方法
            List<String> stringList3 = new ArrayList<>(Arrays.asList("a,b,c,d,e,f,g,h".split(",")));
            // stringList3.removeAll(stringList);
            // System.out.println("差集1: " + stringList3);  // 差集1: [d, e, f, g, h]

            //方法2：通过过滤掉不存在于stringList的数据，然后和本数组进行交集处理
            List<String> stringList3_2 = new ArrayList<>(Arrays.asList("a,b,c,d,e,f,g,h".split(",")));
            // stringList3_2.retainAll(stringList3_2.stream()
            //         .filter(item -> !stringList.contains(item))
            //         .collect(Collectors.toList()));
            // System.out.println("差集2：" + stringList3_2); // 差集2：[d, e, f, g, h]

            PrintUtil.println(CollectionUtils.subtract(stringList3, stringList));
            PrintUtil.println(ListUtil.subtract(stringList3, stringList));
            PrintUtil.println(ListUtil.subtract2(stringList3, stringList));
        }
    }

    @Test
    public void mapUnionAll() {
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

    @Test
    public void unionAllTest() {
        List<String> listA = new ArrayList<String>();
        listA.add("a");
        listA.add("b");
        listA.add("b");
        listA.add("c");
        listA.add("hello");
        listA.add("morning");
        listA.add("!!!");

        List<String> listB = new ArrayList<String>();
        listB.add("a");
        listB.add("e");
        listB.add("f");
        listB.add("c");
        listB.add("help");
        listB.add("???");

        List<String> result = ListUtil.union2(listA, listB);
        PrintUtil.println(result);
        PrintUtil.println(ListUtil.union(listA, listB));
        PrintUtil.println(CollectionUtils.union(listA, listB));




    }

}
