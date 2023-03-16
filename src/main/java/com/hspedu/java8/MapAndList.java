package com.hspedu.java8;

import com.byd.tool.PrintUtil;
import com.hspedu.pojo.Student;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Project: GlobalRegion
 * @Description
 * @Author: Administrator
 * @Create: 2023/3/17
 **/
public class MapAndList {
    
    
    @Test
    public void toList1() {
        List<Student> list = Student.getList();
        Map<String, Integer> map = list.stream().collect(Collectors.toMap(Student::getName, Student::getAge));
        PrintUtil.println(map);
        
        List<String> keys = new ArrayList<>(map.keySet());
        List<Integer> vals = new ArrayList<>(map.values());
        PrintUtil.println(keys);
        PrintUtil.println(vals);
        
        
        // Java 8, Convert all Map keys to a List
        List<String> result3 = map.keySet().stream()
                .collect(Collectors.toList());
        
        // Java 8, Convert all Map values  to a List
        List<Integer> result4 = map.values().stream()
                .collect(Collectors.toList());
        
        Assert.assertEquals(keys, result3);
        Assert.assertEquals(vals, result4);
        
    }
    
    @Test
    public void toList2() {
        
        Map<Integer, String> map =  new LinkedHashMap<>(); // new HashMap<>();
        map.put(10, "apple");
        map.put(20, "orange");
        map.put(30, "banana");
        map.put(40, "watermelon");
        map.put(50, "dragonfruit");
        
        PrintUtil.println(map);
        // split a map into 2 List
        List<Integer> resultSortedKey = new ArrayList<>();
        List<String> keys = new ArrayList<>();
        List<String> resultValues = map.entrySet().stream()
                .peek(e -> keys.add("key_" + e.getKey()))
                //sort a Map by key and stored in resultSortedKey
                .sorted(Map.Entry.<Integer, String>comparingByKey().reversed())
                .peek(e -> resultSortedKey.add(e.getKey()))
                .map(x -> x.getValue())
                // filter banana and return it to resultValues
                .filter(x -> !"banana".equalsIgnoreCase(x))
                .collect(Collectors.toList());
        
        PrintUtil.println(resultSortedKey);
        
        resultValues.forEach(System.out::println);
        
        PrintUtil.println(keys);
    }
    
}
