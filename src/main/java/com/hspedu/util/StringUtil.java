package com.hspedu.util;

import com.byd.tool.PrintUtil;

/**
 * @Project: GlobalRegion
 * @Description 字符串工具类
 * @Author: Administrator
 * @Create: 2023/3/4
 **/
public class StringUtil {
    
    
    public static void iteration(String str) {
        char[] chars = new char[str.length()];
        for (int i = 0, len = str.length(); i < len; i++) {
            chars[i] = str.charAt(i);
            System.out.println(chars[i]);
        }
        
        PrintUtil.println(chars);
        
        //Character[] characterList = new Character[]
    }
}
