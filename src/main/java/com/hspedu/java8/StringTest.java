package com.hspedu.java8;

import com.byd.tool.PrintUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

/**
 * @Project: GlobalRegion
 * @Description
 * @Author: Administrator
 * @Create: 2023/3/18
 **/
public class StringTest {
    
    private static String s = "Hello World 你好 中国！！";
    
    
    @Test
    public void test1() {
        
        for (int i = 0, len = s.length(); i < len; i++) {
            char ch = s.charAt(i);
            System.out.print(ch + " ");
        }
        System.out.println();
    }
    
    @Test
    public void test2() {
        // 将字符串转换为 `char[]` 数组
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            System.out.print(aChar + " ");
        }
        System.out.println();
        
        PrintUtil.println(chars);
    }
    
    @Test
    public void test3() {
        // 将字符串转换为 `char[]` 数组
        char[] chars = s.toCharArray();
        Character[] array = new Character[chars.length];
        for (int i = 0; i < chars.length; i++) {
            array[i] = chars[i];
        }
        PrintUtil.println(array);
    }
    
    @Test
    public void test4() {
        char[] charArray = s.toCharArray();
        Character[] charObjectArray = ArrayUtils.toObject(charArray);
        PrintUtil.println(charObjectArray);
    }
    
    @Test
    public void test5() {
    }
    
    
}
