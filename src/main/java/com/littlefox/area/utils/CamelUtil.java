package com.littlefox.area.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class CamelUtil {
    private static final char UNDER_LINE = '_';
    
    
    public static void main(String[] args) {
        test1();
        
        
    }
    
    private static void test2() {
        String[] strs = {"subStr", "SubStr", "SUBStR", "sUBStR"};
        List<String> underCaseStrs = new ArrayList<>();
        for (String str : strs) {
            String s = toUnderCase(str);
            underCaseStrs.add(s);
        }
        System.out.println(Arrays.toString(underCaseStrs.toArray()));
    }
    
    private static void test1() {
        String[] strs = {"sub_str", "Sub_Str", "SUB_sTR", "SUB_STR"};
        List<String> camelCaseStrs = new ArrayList<>();
        for (String str : strs) {
            String s = toCamelCase(str, true);
            camelCaseStrs.add(s);
        }
        System.out.println(Arrays.toString(camelCaseStrs.toArray()));
    }
    
    /**
     * 驼峰转为下划线
     *
     * @param name
     * @return
     */
    public static String toUnderCase(String name) {
        if (name == null) {
            return null;
        }
        
        int len = name.length();
        StringBuffer res = new StringBuffer(len + 2);
        char pre = 0;
        for (int i = 0; i < len; i++) {
            char ch = name.charAt(i);
            if (Character.isUpperCase(ch)) {
                if (pre != UNDER_LINE) {
                    res.append(UNDER_LINE);
                }
                res.append(Character.toLowerCase(ch));
            } else {
                res.append(ch);
            }
            pre = ch;
        }
        return res.toString();
    }
    
    /**
     * 下划线转驼峰
     *
     * @param name
     * @return
     */
    public static String toCamelCase(String name) {
        if (null == name || name.length() == 0) {
            return null;
        }
        
        int length = name.length();
        StringBuffer sb = new StringBuffer(length);
        boolean underLineNextChar = false;
        
        for (int i = 0; i < length; ++i) {
            char c = name.charAt(i);
            if (c == UNDER_LINE) {
                underLineNextChar = true;
            } else if (underLineNextChar) {
                sb.append(Character.toUpperCase(c));
                underLineNextChar = false;
            } else {
                sb.append(c);
            }
        }
        
        return sb.toString();
    }
    
    /**
     * 下划线转驼峰
     *
     * @param name
     * @param correct 矫正未标准模式
     * @return
     */
    public static String toCamelCase(String name, boolean correct) {
        if (null == name || name.length() == 0) {
            return null;
        }
        if (correct) {
            name = name.toLowerCase();
        }
        
        int length = name.length();
        StringBuffer sb = new StringBuffer(length);
        boolean underLineNextChar = false;
        
        for (int i = 0; i < length; ++i) {
            char c = name.charAt(i);
            if (c == UNDER_LINE) {
                underLineNextChar = true;
            } else if (underLineNextChar) {
                sb.append(Character.toUpperCase(c));
                underLineNextChar = false;
            } else {
                sb.append(c);
            }
        }
        
        // 首字母大写  upperCaseFirst cs[0]-=32;
        //if (correct) {
        //    String firstChar = sb.substring(0, 1);
        //    sb.replace(0, 1, firstChar.toUpperCase());
        //    //System.out.println("首字母" + sb.charAt(0) + firstChar);
        //}
        return sb.toString();
    }
    
    
}
