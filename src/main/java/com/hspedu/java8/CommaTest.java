package com.hspedu.java8;

import com.hspedu.util.StreamUtil;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * @Project: GlobalRegion
 * @Description
 * @Author: Administrator
 * @Create: 2023/3/5
 **/
public class CommaTest {
    
    @Test
    public void stringToListTest() {
        String ids = "1,2,3,4,5,6";
        String splitStr = ",";
        List<Long> longList = StreamUtil.stringToList(ids);
        assertTrue(longList.size() > 0);
        int matches = StringUtils.countMatches(ids, splitStr);
        assertEquals(matches + 1, longList.size());
        System.out.println(longList);
    }
    
    @Test
    public void listToStringTest() {
        Integer[] integers = {1, 2, 3, 4, 5, 6};
        List<Long> longList = Arrays.stream(integers).map(num -> (long) num).collect(Collectors.toList());
        String string = StreamUtil.listToString(longList);
        
        assertNotNull(string);
        assertEquals(StringUtils.countMatches(string, ",") + 1, longList.size());
        assertEquals(string, StreamUtil.listToString(longList));
    }
    
    @Test
    public void listToStringTest2() {
        Integer[] integers = {1, 2, 3, 4, 5, 6};
        // Arrays.asList(integers).stream() -> Arrays.stream(integers)
        List<Long> longList = Arrays.stream(integers).map(num -> (long) num).collect(Collectors.toList());
        assertNotNull(longList);    // 不能为空
        assertEquals(longList.size(), integers.length); // 长度一直
        assertEquals(Arrays.toString(integers), longList.toString());   // 打印出的字符串形式toString一致
    }
}
