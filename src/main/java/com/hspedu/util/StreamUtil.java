package com.hspedu.util;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

/**
 * @Project: GlobalRegion
 * @Description
 * @Author: Administrator
 * @Create: 2023/3/4
 **/
public class StreamUtil {
    
    /*
    //You can use the Lambda functions of Java 8 to achieve this without looping
//来自：http://stackoverflow.com/questions/19946980/convert-string-to-listlong

String ids= "1,2,3,4,5,6";
List<Long> listIds = Arrays.asList(ids.split(",")).stream().map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
System.out.println(Arrays.toString(listIds .toArray()));//[1,2,3,3,4,5,6]

     */
    public static List<Long> stringToList(String commaSepStr) {
        //List<String> collect = Stream.of(commaSepStr.split(",")).collect(Collectors.toList());
        List<Long> longList = Stream.of(commaSepStr.split(",")).map(e -> Long.parseLong(e.trim())).collect(Collectors.toList());
        return longList;
    }
    
    /**
     * https://juejin.cn/post/7127510321849171982
     *
     * @param longList
     * @return
     */
    public static String listToString(List<Long> longList) {
        String collect = longList.stream().map(e -> e + "").collect(Collectors.joining(","));
        return collect;
    }
}
