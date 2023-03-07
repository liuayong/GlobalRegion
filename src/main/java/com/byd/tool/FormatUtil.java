package com.byd.tool;

import org.apache.commons.lang3.StringUtils;

/**
 * @Author : LiuYingYong
 * @Date 2023/3/1
 */
public class FormatUtil {
    
    
    // 字符串格式化长度不足补0
    public static String addZeroForNum(String str, int strLength) {
        int strLen = str.length();
        while (strLen < strLength) {
            StringBuffer sb = new StringBuffer();
            sb.append("0").append(str);// 左补0
            // sb.append(str).append("0");//右补0
            str = sb.toString();
            strLen = str.length();
        }
        return str;
    }
    
    public static String addZeroForNum2(String str, int len) {
        return StringUtils.leftPad(str, len, "0");
    }
    
    public static String addZeroForNum3(String str, int len) {
        return String.format("%0" + len + "d", str);
    }
}
