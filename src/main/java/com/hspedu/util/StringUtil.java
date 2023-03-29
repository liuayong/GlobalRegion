package com.hspedu.util;

import com.byd.tool.PrintUtil;

import java.util.Collection;
import java.util.Map;

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


    /**
     * 判断对象或对象数组中每一个对象是否为空: 对象为null，字符序列长度为0，集合类、Map为empty
     *
     * @param obj
     * @return
     */
    public static boolean isNullOrEmpty(Object obj) {
        if (obj == null) return true;
        if (obj instanceof CharSequence) return ((CharSequence) obj).length() == 0;
        if (obj instanceof Collection) return ((Collection) obj).isEmpty();
        if (obj instanceof Map) return ((Map) obj).isEmpty();
        if (obj instanceof Object[]) {
            Object[] object = (Object[]) obj;
            if (object.length == 0) {
                return true;
            }
            boolean empty = true;
            for (int i = 0; i < object.length; i++) {
                if (!isNullOrEmpty(object[i])) {
                    empty = false;
                    break;
                }
            }
            return empty;
        }
        return false;
    }
}
